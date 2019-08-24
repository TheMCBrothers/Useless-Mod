package tk.themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class CreativePowerBlockTileEntity extends TileEntity implements ITickableTileEntity {
	
	private CustomEnergyStorage energyStorage;

	public CreativePowerBlockTileEntity() {
		super(ModTileEntities.CREATIVE_POWER_BLOCK);
		energyStorage = new CustomEnergyStorage(2000000000, 0, 32000, 2000000000);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		energyStorage = new CustomEnergyStorage(2000000000, 0, 32000, 2000000000);
	}
	
	private void sendEnergy() {
		if(this.energyStorage.getEnergyStored() > 0) {
			for(Direction facing : Direction.values()) {
				TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(facing));
				if(tileEntity != null) {
					IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).orElse(null);
					if(handler != null && handler.canReceive()) {
						handler.receiveEnergy(this.energyStorage.getEnergyStored(), false);
						if(this.energyStorage.getEnergyStored() <= 0)
							break;
					}
				}
			}
			this.markDirty();
		}
	}

	@Override
	public void tick() {
		sendEnergy();
	}
	
	private LazyOptional<IEnergyStorage> holder = LazyOptional.of(() -> energyStorage);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY) return holder.cast();
		return super.getCapability(cap, side);
	}

}
