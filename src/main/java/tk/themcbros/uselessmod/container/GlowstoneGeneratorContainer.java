package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.tileentity.GlowstoneGeneratorTileEntity;

public class GlowstoneGeneratorContainer extends Container {
	
	private IInventory inventory;
	private IIntArray fields;

	public GlowstoneGeneratorContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(1), new IntArray(3));
	}
	
	public GlowstoneGeneratorContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
		super(ModContainerTypes.GLOWSTONE_GENRATOR, id);
		this.inventory = inventory;
		this.fields = fields;
		
		this.addSlot(new Slot(inventory, 0, 80, 33));
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
		
		this.trackIntArray(fields);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.inventory.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index != 0) {
				if (GlowstoneGeneratorTileEntity.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 1 && index < 28) {
					if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 28 && index < 37 && !this.mergeItemStack(itemstack1, 1, 28, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getCookTimeScaled() {
		int i = this.fields.get(2);
		int j = 25;
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getEnergyStoredScaled() {
		int i = this.fields.get(0);
		int j = this.fields.get(1);
		return j != 0 && i != 0 ? i * 60 / j : 0;
	}
	
	public int getEnergyStored() {
		return this.fields.get(0);
	}
	
	public int getMaxEnergyStored() {
		return this.fields.get(1);
	}

}
