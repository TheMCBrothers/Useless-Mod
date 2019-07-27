package tk.themcbros.uselessmod.container.slots.coffee_machine;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MilkTankSlot extends Slot {

	public MilkTankSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == Items.MILK_BUCKET;
	}

}
