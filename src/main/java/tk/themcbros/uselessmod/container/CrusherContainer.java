package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class CrusherContainer extends Container {
	
	private IInventory inventory;
	private IIntArray fields;
	private World world;

	public CrusherContainer(int id, PlayerInventory playerInv) {
		this(id, playerInv, new Inventory(3), new IntArray(3));
	}

	public CrusherContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
		super(ModContainerTypes.CRUSHER, id);
		this.inventory = inventory;
		this.fields = fields;
		this.world = playerInventory.player.world;
		
		this.addSlot(new Slot(inventory, 0, 56, 17));
		this.addSlot(new Slot(inventory, 1, 56, 53));
		this.addSlot(new Slot(inventory, 2, 116, 35));

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

	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (this.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.canCrush(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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

	protected boolean canCrush(ItemStack stack) {
		return this.world.getRecipeManager()
				.getRecipe(RecipeTypes.CRUSHING, new Inventory(stack), this.world)
				.isPresent();
	}

	protected boolean isItemFuel(ItemStack stack) {
		return AbstractFurnaceTileEntity.isFuel(stack);
	}

	@OnlyIn(Dist.CLIENT)
	public int func_217060_j() {
		int i = this.fields.get(1);
		int j = this.fields.get(2);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int func_217059_k() {
		int i = 200;

		return this.fields.get(0) * 13 / i;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean func_217061_l() {
		return this.fields.get(0) > 0;
	}

}
