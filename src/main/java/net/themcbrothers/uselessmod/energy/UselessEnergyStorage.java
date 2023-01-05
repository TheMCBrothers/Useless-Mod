package net.themcbrothers.uselessmod.energy;

import net.minecraftforge.energy.EnergyStorage;

public class UselessEnergyStorage extends EnergyStorage {
    public UselessEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;

        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void growEnergy(int energy) {
        this.energy += energy;

        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }
    }

    public void setEnergyStored(int energyStored) {
        this.energy = energyStored;
    }

    public int getMaxTransfer(boolean extract) {
        return extract ? this.maxExtract : this.maxReceive;
    }

    public void setMaxEnergyStored(int capacity) {
        this.capacity = capacity;
    }
}
