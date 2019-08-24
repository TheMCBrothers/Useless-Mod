package tk.themcbros.uselessmod.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import tk.themcbros.uselessmod.items.UpgradeItem;

public class MachineUpgradeSlot extends Slot {

	public MachineUpgradeSlot(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}
	
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof UpgradeItem;
	}
	
	@Override
	public int getSlotStackLimit() {
		return 8;
	}
	
}
