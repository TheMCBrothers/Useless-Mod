package tk.themcbros.uselessmod.tileentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

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
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.energy.EnergyCableNetwork;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {
	
	private EnergyCableNetwork network = null;
	private int maxTransfer = MachineConfig.energy_cable_max_transfer.get();
	private int savedEnergy = 0;
	
	public boolean hasSavedEnergy() {
		return this.savedEnergy > 0;
	}
	
	public int getSavedEnergy() {
		return this.savedEnergy;
	}
	
	public EnergyCableNetwork getNetwork() {
		return this.network;
	}

	public EnergyCableTileEntity(int maxTransfer) {
		super(ModTileEntities.ENERGY_CABLE);
		this.maxTransfer = maxTransfer;
	}
	
	public EnergyCableTileEntity() {
		super(ModTileEntities.ENERGY_CABLE);
	}
	
	@Nullable
	public EnergyCableTileEntity getOffsetCableTE(Direction dir) {
		TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(dir));
		if(tileEntity != null && tileEntity instanceof EnergyCableTileEntity) {
			if(tileEntity.getBlockState().getBlock() == this.getBlockState().getBlock())
				return (EnergyCableTileEntity) tileEntity;
		}
		return null;
	}
	
	public void updateNetwork() {
		if(!this.world.isRemote) {
			List<EnergyCableNetwork> existingNetworks = new ArrayList<EnergyCableNetwork>();
			for(Direction direction : Direction.values()) {
				TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(direction));
				if(tileEntity != null && tileEntity instanceof EnergyCableTileEntity && tileEntity.getBlockState().getBlock() == this.getBlockState().getBlock()) {
					EnergyCableNetwork network = ((EnergyCableTileEntity) tileEntity).getNetwork();
					if(!existingNetworks.contains(network) && network != null && EnergyCableNetwork.NETWORK_LIST.containsKey(network.key));
						existingNetworks.add(network);
				}
			}
			
			if(existingNetworks.size() > 0 && this.network == null) {
				if(existingNetworks.size() == 1) {
					this.network = existingNetworks.get(0);
					if(this.network == null) {
						return;
					}
					this.network.addCable(this);
					return;
				}
				EnergyCableNetwork[] networks = new EnergyCableNetwork[existingNetworks.size()];
				for(int i = 0; i < existingNetworks.size(); i++) {
					networks[i] = existingNetworks.get(i);
				}
				this.network = EnergyCableNetwork.combinedNetwork(networks);
				this.network.addCable(this);
				return;
			} else if(this.network == null) {
				this.network = EnergyCableNetwork.createWithCable(this, maxTransfer);
				return;
			}
		}
	}
	
	public void updateConnections() {
		if (this.network == null) {
			updateNetwork();
			return;
		}
		boolean[] sides = new boolean[Direction.values().length];
		for(Direction direction : Direction.values()) {
			BlockPos pos = this.pos.offset(direction);
			TileEntity tileEntity = this.world.getTileEntity(pos);
			if(tileEntity != null) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent((energyStorage) -> {
					if(energyStorage.canExtract() || energyStorage.canReceive()) {
						sides[direction.getIndex()] = true;
						if(!this.network.CONSUMERS.contains(tileEntity) && (tileEntity.getBlockState().getBlock() != this.getBlockState().getBlock()))
							this.network.CONSUMERS.add(tileEntity);
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
		
		if(!state.equals(oldState)) {
			this.updateNetwork();
			this.world.setBlockState(this.pos, state, 3);
			this.markDirty();
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		if(this.savedEnergy > 0)
			compound.putInt("SavedEnergy", this.savedEnergy);
//		if(this.network != null) {
//			compound.putString("NetworkKey", this.network.key != null ? this.network.key : "null");
//			compound.put("Network", this.network.serializeNBT());
//		}
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
//		this.network = EnergyCableNetwork.NETWORK_LIST.containsKey(compound.getString("NetworkKey"))
//				? EnergyCableNetwork.NETWORK_LIST.get(compound.getString("NetworkKey"))
//						: null;
		this.savedEnergy = compound.getInt("SavedEnergy");
		this.updateNetwork();
	}
	
	private void transferEnergy() {
		if(this.network == null) return;
		if(this.network.energyStorage.getEnergyStored() > 0) {
			for(TileEntity tileEntity : this.network.CONSUMERS) {
				tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(handler -> {
					if(handler != null && handler.canReceive()) {
						int accepted = handler.receiveEnergy(this.network.energyStorage.getMaxExtract(), false);
						this.network.energyStorage.modifyEnergyStored(-accepted);
					}
				});
			}
			this.markDirty();
		}
	}
	
	@Override
	public void tick() {
		if(!this.world.isRemote) {
			this.updateConnections();
			this.transferEnergy();
		}
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY && this.network != null && this.network.energyHolder != null)
			return this.network.energyHolder.cast();
		return super.getCapability(cap, side);
	}
	
	@Override
	public void remove() {
		super.remove();
		if(!this.world.isRemote && this.network != null)
			this.network.removeCable(this);
	}
	
	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
	}

	public void setNetwork(EnergyCableNetwork network2) {
		this.network = network2;
	}

}
