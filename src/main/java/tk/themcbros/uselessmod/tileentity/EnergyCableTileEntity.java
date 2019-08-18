package tk.themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.energy.EnergyCableNetwork;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {
	
	private EnergyCableNetwork network = new EnergyCableNetwork(100);
	
	public EnergyCableNetwork getNetwork() {
		return network;
	}

	public EnergyCableTileEntity(int maxTransfer) {
		super(ModTileEntities.ENERGY_CABLE);
	}
	
	public EnergyCableTileEntity() {
		this(100);
	}
	
	public void updateNetwork() {
		for(Direction direction : Direction.values()) {
			TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(direction));
			if(tileEntity != null && tileEntity instanceof EnergyCableTileEntity && EnergyCableNetwork.NETWORK_LIST.size() > 0) {
				EnergyCableTileEntity energyCable = (EnergyCableTileEntity) tileEntity;
				if(EnergyCableNetwork.NETWORK_LIST.contains(this.network))
					EnergyCableNetwork.NETWORK_LIST.remove(this.network);
				this.network = energyCable.network;
				return;
			}
			if(!this.network.CABLES.contains(this))
				this.network.CABLES.add(this);
			if(!EnergyCableNetwork.NETWORK_LIST.contains(this.network))
				EnergyCableNetwork.NETWORK_LIST.add(this.network);
		}
	}
	
	public void updateConnections() {
		this.updateNetwork();
		this.network.CONSUMERS.clear();
		boolean[] sides = new boolean[Direction.values().length];
		for(Direction direction : Direction.values()) {
			BlockPos pos = this.pos.offset(direction);
			TileEntity tileEntity = this.world.getTileEntity(pos);
			if(tileEntity != null) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent((energyStorage) -> {
					if(energyStorage.canExtract() || energyStorage.canReceive()) {
						sides[direction.getIndex()] = true;
						if(!(tileEntity instanceof EnergyCableTileEntity)) this.network.CONSUMERS.add(tileEntity);
					}
				});
			}
		}
		
		BlockState state = this.world.getBlockState(this.pos);
		BlockState oldState = state;
		if(state.has(BlockStateProperties.NORTH) && state.has(BlockStateProperties.SOUTH)
				&& state.has(BlockStateProperties.EAST) && state.has(BlockStateProperties.WEST)
				&& state.has(BlockStateProperties.UP) && state.has(BlockStateProperties.DOWN)) {
			state = state.with(BlockStateProperties.NORTH, Boolean.valueOf(sides[Direction.NORTH.getIndex()]))
					.with(BlockStateProperties.SOUTH, Boolean.valueOf(sides[Direction.SOUTH.getIndex()]))
					.with(BlockStateProperties.EAST, Boolean.valueOf(sides[Direction.EAST.getIndex()]))
					.with(BlockStateProperties.WEST, Boolean.valueOf(sides[Direction.WEST.getIndex()]))
					.with(BlockStateProperties.UP, Boolean.valueOf(sides[Direction.UP.getIndex()]))
					.with(BlockStateProperties.DOWN, Boolean.valueOf(sides[Direction.DOWN.getIndex()]));
		}
		this.markDirty();
		
		if(!state.equals(oldState)) {
			this.world.setBlockState(this.pos, state, 3);
		}
	}
	
	private void transferEnergy() {
		if(this.network.energyStorage.getEnergyStored() > 0) {
			this.network.CONSUMERS.forEach(tileEntity -> {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
					if(handler != null && handler.canReceive()) {
						int accepted = handler.receiveEnergy(this.network.energyStorage.getMaxExtract(), false);
						this.network.energyStorage.modifyEnergyStored(-accepted);
					}
				});
			});
			this.markDirty();
		}
	}
	
	@Override
	public void tick() {
		if(!world.isRemote) {
			updateConnections();
			transferEnergy();
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("Energy", this.network.energyStorage.serializeNBT());
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.network.energyStorage = CustomEnergyStorage.fromNBT(compound.getCompound("Energy"));
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY) return this.network.energyHolder.cast();
		return super.getCapability(cap, side);
	}
	
	@Override
	public void remove() {
		super.remove();
		this.network.CABLES.remove(this);
		if(this.network.CABLES.size() <= 0) EnergyCableNetwork.NETWORK_LIST.remove(this.network);
	}

}
