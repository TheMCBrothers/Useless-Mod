package tk.themcbros.uselessmod.container.slots.coffee_machine;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class WaterTankSlot extends Slot {

	public WaterTankSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return !stack.isEmpty() && stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				.map(h -> h.getFluidInTank(0).getFluid().isIn(FluidTags.WATER)).orElse(false);
	}

}
