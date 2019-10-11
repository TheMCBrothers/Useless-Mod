package tk.themcbros.uselessmod.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.caps.CapabilityProviderEnergy;
import tk.themcbros.uselessmod.caps.EnergyConversionStorage;
import tk.themcbros.uselessmod.energy.IEnergyContainerItem;

public class CreativeEnergyBlockItem extends BlockItem implements IEnergyContainerItem {
	
	public CreativeEnergyBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 0d;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return stack.getCount() == 1;
	}
	
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0xFFFF0000;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return maxExtract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return this.getMaxEnergyStored(container);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return 2_000_000_000;
	}
	
	/* Capabilities */
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}

}
