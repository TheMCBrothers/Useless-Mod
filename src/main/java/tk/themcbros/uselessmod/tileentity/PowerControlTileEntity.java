package tk.themcbros.uselessmod.tileentity;

import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.blocks.PowerControlBlock;
import tk.themcbros.uselessmod.energy.ConnectionType;
import tk.themcbros.uselessmod.helper.EnergyUtils;
import tk.themcbros.uselessmod.helper.IHammer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.SideConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class PowerControlTileEntity extends TileEntity implements ITickableTileEntity, IEnergyStorage, IHammer {

	public static final ModelProperty<ConnectionType> NORTH = new ModelProperty<>();
	public static final ModelProperty<ConnectionType> SOUTH = new ModelProperty<>();
	public static final ModelProperty<ConnectionType> EAST = new ModelProperty<>();
	public static final ModelProperty<ConnectionType> WEST = new ModelProperty<>();
	public static final ModelProperty<ConnectionType> UP = new ModelProperty<>();
	public static final ModelProperty<ConnectionType> DOWN = new ModelProperty<>();

	private SideConfig sideConfig = new SideConfig();

	public PowerControlTileEntity() {
		super(ModTileEntities.POWER_CONTROL_BLOCK);
	}
	
	private LazyOptional<IEnergyStorage> energyHolder = LazyOptional.of(() -> this);
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if(cap == CapabilityEnergy.ENERGY && side != null && this.canConnect(side))
			return this.energyHolder.cast();
		return LazyOptional.empty();
	}

	private boolean canConnect(@Nonnull Direction side) {
		return this.sideConfig.getConnection(side).canConnect();
	}

	@Nonnull
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		nbt.put("Sides", this.sideConfig.serializeNBT());
		return super.write(nbt);
	}

	@Override
	public void read(CompoundNBT nbt) {
		this.sideConfig = SideConfig.fromNBT(nbt.getList("Sides", Constants.NBT.TAG_LIST));
		super.read(nbt);
	}

	@Override
	public void tick() {
		// do nothing
	}

	@Nonnull
	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder()
				.withInitial(NORTH, this.sideConfig.getConnection(Direction.NORTH))
				.withInitial(SOUTH, this.sideConfig.getConnection(Direction.SOUTH))
				.withInitial(EAST, this.sideConfig.getConnection(Direction.EAST))
				.withInitial(WEST, this.sideConfig.getConnection(Direction.WEST))
				.withInitial(UP, this.sideConfig.getConnection(Direction.UP))
				.withInitial(DOWN, this.sideConfig.getConnection(Direction.DOWN))
				.build();
	}

	@Nonnull
	private CompoundNBT writeClientData(CompoundNBT tag) {
		return write(tag);
	}

	private void readClientData(CompoundNBT tag) {
		read(tag);
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tag = writeClientData(new CompoundNBT());
		return new SUpdateTileEntityPacket(this.pos, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		SideConfig old = this.sideConfig;
		readClientData(pkt.getNbtCompound());
		if (this.sideConfig != old) {
			ModelDataManager.requestModelDataRefresh(this);
			assert world != null;
			world.func_225319_b(this.getPos(), null, null);
		}
	}

	@Override
	public ActionResultType onHammer(ItemUseContext context) {
		this.swapSide(context.getFace());
		return ActionResultType.SUCCESS;
	}

	private void swapSide(Direction face) {
		ConnectionType side = Util.getElementAfter(Arrays.asList(ConnectionType.values()), this.sideConfig.getConnection(face));
		if (side == ConnectionType.BOTH || side == ConnectionType.BLOCKED) side = ConnectionType.NONE;
		this.sideConfig.setConnectionType(face, side);
		this.markDirty();
		assert this.world != null;
		if (this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 16 | 2 | 1);
		}
	}

	private boolean cannotTransfer() {
		return !this.getBlockState().get(PowerControlBlock.POWERED);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (this.cannotTransfer()) return 0;

		int energySent = 0;
		for (Direction side : Direction.values()) {
			ConnectionType type = this.sideConfig.getConnection(side);
			if (type == ConnectionType.OUTPUT) {
				assert world != null;
				IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(side), side.getOpposite());
				if (energy != null) {
					energySent += energy.receiveEnergy(maxReceive - energySent, simulate);
				}
			}
		}
		return energySent;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (this.cannotTransfer()) return 0;

		int energySent = 0;
		for (Direction side : Direction.values()) {
			ConnectionType type = this.sideConfig.getConnection(side);
			if (type == ConnectionType.INPUT) {
				assert world != null;
				IEnergyStorage energy = EnergyUtils.getEnergy(world, pos.offset(side), side.getOpposite());
				if (energy != null) {
					energySent += energy.extractEnergy(maxExtract - energySent, simulate);
				}
			}
		}
		return energySent;
	}

	@Override
	public int getEnergyStored() {
		return 0;
	}

	@Override
	public int getMaxEnergyStored() {
		return 0;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Nonnull
	public ConnectionType getConnection(Direction side) {
		return this.sideConfig.getConnection(side);
	}
}
