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
import tk.themcbros.uselessmod.container.slots.EnergyItemSlot;
import tk.themcbros.uselessmod.container.slots.MachineUpgradeSlot;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.lists.ModContainerTypes;

import javax.annotation.Nonnull;

public class ChargerContainer extends Container {

	private IIntArray fields;
	private IInventory inventory;

	private final int machineSlotCount = 5;

	public ChargerContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(2), new Inventory(3), new IntArray(4));
	}

	public ChargerContainer(int id, PlayerInventory playerInventory, IInventory inventory, IInventory upgradeInventory, IIntArray fields) {
		super(ModContainerTypes.CHARGER, id);
		this.inventory = inventory;
		this.fields = fields;
		this.addSlot(new EnergyItemSlot(inventory, 0, 62, 30));
		this.addSlot(new EnergyItemSlot(inventory, 1, 152, 55));
		
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 0, 8, 8));
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 1, 8, 26));
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 2, 8, 44));

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

	@Nonnull
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index >= machineSlotCount) {
				if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
					return ItemStack.EMPTY;
				} else if (itemstack1.getItem() instanceof UpgradeItem) { // Upgrade Slots
					if (!this.mergeItemStack(itemstack1, 2, machineSlotCount, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= machineSlotCount && index < machineSlotCount + 27) { // Inventory
					if (!this.mergeItemStack(itemstack1, machineSlotCount + 27, machineSlotCount + 36, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= machineSlotCount + 27 && index < machineSlotCount + 36 && !this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 27, false)) { // Hotbar
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 36, false)) {
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
	public int getEnergyStoredScaled() {
		int i = this.fields.get(0);
		int j = this.fields.get(1);
		return j != 0 && i != 0 ? i * 45 / j : 0;
	}

	public int getEnergyStored() {
		return this.fields.get(0);
	}

	public int getMaxEnergyStored() {
		return this.fields.get(1);
	}
}
