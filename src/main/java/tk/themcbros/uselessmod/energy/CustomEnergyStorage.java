package tk.themcbros.uselessmod.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {
	
	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}
	
	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}
	
	public CustomEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}
	
	public CustomEnergyStorage(int capacity) {
		super(capacity);
	}
	
	public int getMaxReceive() {
		return this.maxReceive;
	}
	
	public int getMaxExtract() {
		return this.maxExtract;
	}

	public void setEnergyStored(int energy) {
		this.energy = energy;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void modifyEnergyStored(int energy) {
		this.energy += energy;
		
		if(this.energy > capacity) this.energy = capacity;
		else if(this.energy < 0) this.energy = 0;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT compound = new CompoundNBT();
		compound.putInt("Energy", this.energy);
		compound.putInt("Capacity", this.capacity);
		compound.putInt("MaxReceive", this.maxReceive);
		compound.putInt("MaxExtract", this.maxExtract);
		return compound;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		this.energy = nbt.getInt("Energy");
		this.capacity = nbt.getInt("Capacity");
		this.maxReceive = nbt.getInt("MaxReceive");
		this.maxExtract = nbt.getInt("MaxExtract");
	}

}
