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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class ElectricCrusherContainer extends Container {

	private IInventory inventory;
	private IIntArray fields;
	private World world;

	public ElectricCrusherContainer(int id, PlayerInventory playerInv) {
		this(id, playerInv, new Inventory(3), new IntArray(4));
	}

	public ElectricCrusherContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
		super(ModContainerTypes.ELECTRIC_CRUSHER, id);
		this.inventory = inventory;
		this.fields = fields;
		this.world = playerInventory.player.world;

		this.addSlot(new Slot(inventory, 0, 39, 35));
		this.addSlot(new Slot(inventory, 1, 99, 24));
		this.addSlot(new Slot(inventory, 2, 99, 48));

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
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
	}

	@Override
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
				if (this.canCrush(itemstack1)) {
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

	protected boolean canCrush(ItemStack p_217057_1_) {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.CRUSHING, new Inventory(p_217057_1_), this.world)
				.isPresent();
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getEnergyStored() {
		return this.fields.get(0);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getMaxEnergyStored() {
		return this.fields.get(1);
	}

	@OnlyIn(Dist.CLIENT)
	public int getCrushTimeScaled() {
		int i = this.fields.get(2);
		int j = this.fields.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getEnergyStoredScaled() {
		return this.fields.get(0) * 60 / this.fields.get(1);
	}

}
