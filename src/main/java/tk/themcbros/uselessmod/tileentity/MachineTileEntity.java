package tk.themcbros.uselessmod.tileentity;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;

public abstract class MachineTileEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity, ISidedInventory, IRestorableTileEntity {
	
	protected CustomEnergyStorage energyStorage;
	
	public MachineTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.energyStorage = new CustomEnergyStorage(16000, 100, 0);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.put("Energy", this.energyStorage.serializeNBT());
		writeRestorableToNBT(compound);
		
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.energyStorage = new CustomEnergyStorage(16000);
		this.energyStorage.deserializeNBT(compound.getCompound("Energy"));
		
		readRestorableFromNBT(compound);
	}
	
	protected final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(this::createEnergyHandler);
	
	private IEnergyStorage createEnergyHandler() {
		return this.energyStorage;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY) return energyHandler.cast();
		return super.getCapability(cap, side);
	}
	
	public int getEnergyStored() {
		return this.energyStorage.getEnergyStored();
	}
	
	public int getMaxEnergyStored() {
		return this.energyStorage.getMaxEnergyStored();
	}

}
