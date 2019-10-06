package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import tk.themcbros.uselessmod.lists.ModContainerTypes;

public class ClosetContainer extends Container {
	
	private IInventory closetInventory;
	
	private final int numRows = 3;
	private final int numColumns = 5;
	
	public ClosetContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(15));
	}

	public ClosetContainer(int id, PlayerInventory playerInventory, IInventory closetInventory) {
		super(ModContainerTypes.CLOSET, id);
		this.closetInventory = closetInventory;
		
		this.closetInventory.openInventory(playerInventory.player);
		
		for (int i = 0; i < this.numRows; ++i) {
			for (int j = 0; j < this.numColumns; ++j) {
				this.addSlot(new Slot(closetInventory, j + i * this.numColumns, 44 + j * 18, 18 + i * 18));
			}
		}
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.closetInventory.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.numRows  * this.numColumns) {
				if (!this.mergeItemStack(itemstack1, this.numRows * this.numColumns , this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.numRows * this.numColumns, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.closetInventory.closeInventory(playerIn);
	}
	
	public IInventory getClosetInventory() {
		return closetInventory;
	}

}
