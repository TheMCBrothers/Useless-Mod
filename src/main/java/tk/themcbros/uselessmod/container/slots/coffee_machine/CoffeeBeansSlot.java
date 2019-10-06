package tk.themcbros.uselessmod.container.slots.coffee_machine;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeBeansSlot extends Slot {

	public CoffeeBeansSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == ModItems.COFFEE_BEANS;
	}

}
