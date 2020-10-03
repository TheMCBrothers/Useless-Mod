package themcbros.uselessmod.energy;

import net.minecraftforge.energy.IEnergyStorage;

public class DummyEnergyStorage implements IEnergyStorage {

    public static final DummyEnergyStorage INSTANCE = new DummyEnergyStorage();

    private DummyEnergyStorage() {
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
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
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
