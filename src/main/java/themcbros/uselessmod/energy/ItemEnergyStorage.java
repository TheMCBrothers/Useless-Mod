package themcbros.uselessmod.energy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.api.energy.IUselessEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemEnergyStorage implements IUselessEnergyStorage, ICapabilityProvider {

    private final ItemStack container;

    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;

    public ItemEnergyStorage(ItemStack container, int capacity) {
        this(container, capacity, 1000);
    }

    public ItemEnergyStorage(ItemStack container, int capacity, int maxTransfer) {
        this(container, capacity, maxTransfer, maxTransfer);
    }

    public ItemEnergyStorage(ItemStack container, int capacity, int maxReceive, int maxExtract) {
        this.container = container;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    private void setEnergy(int energy) {
        this.container.getOrCreateTag().putInt("EnergyStored", energy);
    }

    private int getEnergy() {
        return this.container.getOrCreateTag().getInt("EnergyStored");
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energy = getEnergy();
        int energyReceived = Math.min(this.getMaxEnergyStored() - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            setEnergy(energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energy = getEnergy();
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            setEnergy(energy);
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return this.getEnergy();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public int getMaxTransfer(boolean extract) {
        return extract ? this.maxExtract : this.maxReceive;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityUselessEnergy.USELESS_ENERGY || cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> this).cast();
        return LazyOptional.empty();
    }

}
