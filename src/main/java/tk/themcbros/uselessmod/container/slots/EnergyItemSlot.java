package tk.themcbros.uselessmod.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class EnergyItemSlot extends Slot {

	public EnergyItemSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		boolean valid = false;
		if(stack.hasTag() && stack.getTag().contains("Energy")) valid = true;
		return valid;
	}

}
