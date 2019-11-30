package tk.themcbros.uselessmod.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.caps.CapabilityProviderEnergy;
import tk.themcbros.uselessmod.caps.EnergyConversionStorage;
import tk.themcbros.uselessmod.energy.IEnergyContainerItem;
import tk.themcbros.uselessmod.helper.NBTHelper;
import tk.themcbros.uselessmod.helper.TextUtils;
import tk.themcbros.uselessmod.machine.MachineTier;

import javax.annotation.Nullable;
import java.util.List;

public interface IEnergyDurabilityItem extends IEnergyContainerItem {

    String TAG_ENERGY = "Energy";
    int RF_PER_USE = 20;

    default void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        ITextComponent component = TextUtils.energyWithMax(this.getEnergyStored(stack), this.getMaxEnergyStored(stack));
        tooltip.add(component.applyTextStyle(TextFormatting.GRAY));

        if (flagIn.isAdvanced()) {
            ITextComponent haha = new StringTextComponent("Max Transfer: ")
                    .applyTextStyle(TextFormatting.YELLOW)
                    .appendSibling(new StringTextComponent("unknown").applyTextStyle(TextFormatting.GOLD));
            tooltip.add(haha);
        }
    }

    default double getDurabilityForDisplay(ItemStack stack) {
        double stored = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack) + 1;
        double max = this.getMaxEnergyStored(stack) + 1;
        return stored / max;
    }

    default Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }

    default boolean isRepairable(ItemStack stack) { return false; };

    default boolean isDamageable() {
        return false;
    }

    /**
     * @param container The energy item
     * @return TRUE if the item has enough energy to break a block
     */
    default boolean hasEnoughEnergy(ItemStack container) {
        return this.getEnergyStored(container) >= RF_PER_USE;
    }

    default ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
    }

    default boolean showDurabilityBar(ItemStack stack) {
        return stack.getCount() == 1;
    }

    default int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0xFFFF0000;
    }

    /* IEnergyContainerItem */
    @Override
    default int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = this.getEnergyStored(container);
        int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, RF_PER_USE));
        if (!simulate) {
            energy += energyReceived;
            NBTHelper.setInt(container, TAG_ENERGY, energy);
        }
        return energyReceived;
    }

    @Override
    default int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = this.getEnergyStored(container);
        int energyExtracted = Math.min(energy, Math.min(maxExtract, RF_PER_USE));
        if (!simulate) {
            energy -= energyExtracted;
            NBTHelper.setInt(container, TAG_ENERGY, energy);
        }
        return energyExtracted;
    }

    @Override
    default int getEnergyStored(ItemStack container) {
        return NBTHelper.getInt(container, TAG_ENERGY);
    }

    @Override
    default int getMaxEnergyStored(ItemStack container) {
        return MachineTier.COFFEE.getMachineCapacity();
    }

}
