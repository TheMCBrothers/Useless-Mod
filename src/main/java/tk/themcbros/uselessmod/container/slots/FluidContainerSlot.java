package tk.themcbros.uselessmod.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class FluidContainerSlot extends Slot {
	public FluidContainerSlot(IInventory inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).map((handler) -> handler.getFluidInTank(0).isEmpty()).orElse(false);
	}
}
