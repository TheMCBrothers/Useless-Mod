package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.ModContainerTypes;

public class ElectricFurnaceContainer extends Container {

	private IIntArray fields;
	private IInventory inventory;
	private World world;

	public ElectricFurnaceContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(2), new IntArray(4));
	}

	public ElectricFurnaceContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
		super(ModContainerTypes.ELECTRIC_FURNACE, id);
		this.inventory = inventory;
		this.fields = fields;
		this.addSlot(new Slot(inventory, 0, 56, 35));
		this.addSlot(new Slot(inventory, 1, 116, 35));
		this.world = playerInventory.player.world;

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
			if (index == 1) {
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (this.canSmelt(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 2 && index < 29) {
					if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
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

	protected boolean canSmelt(ItemStack stack) {
		return this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(stack), this.world)
				.isPresent();
	}

	@OnlyIn(Dist.CLIENT)
	public int getCookTimeScaled() {
		int i = this.fields.get(2);
		int j = this.fields.get(3);
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
