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
import tk.themcbros.uselessmod.container.slots.EnergyItemSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeInputSecondSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeInputSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeOutputSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.MilkTankSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.WaterTankSlot;
import tk.themcbros.uselessmod.lists.ModContainerTypes;

public class CoffeeMachineContainer extends Container {

	private IInventory coffeeMachineInventory;
	private IIntArray fields;
	
	public CoffeeMachineContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(6), new IntArray(8));
	}

	public CoffeeMachineContainer(int id, PlayerInventory playerInventory, IInventory coffeeMachineInventory, IIntArray fields) {
		super(ModContainerTypes.COFFEE_MACHINE, id);
		this.coffeeMachineInventory = coffeeMachineInventory;
		this.fields = fields;
		
		// Machine Slots
		this.addSlot(new WaterTankSlot(coffeeMachineInventory, 0, 7, 55));
		this.addSlot(new MilkTankSlot(coffeeMachineInventory, 1, 27, 55));
		this.addSlot(new CoffeeInputSlot(coffeeMachineInventory, 2, 61, 26));
		this.addSlot(new CoffeeInputSecondSlot(coffeeMachineInventory, 3, 79, 26));
		this.addSlot(new CoffeeOutputSlot(coffeeMachineInventory, 4, 112, 45));
		this.addSlot(new EnergyItemSlot(coffeeMachineInventory, 5, 152, 55));
		
		// Inventory Slots
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
		return this.coffeeMachineInventory.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		int numSlots = 6;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < numSlots) {
				if (!this.mergeItemStack(itemstack1, numSlots , this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, numSlots, false)) {
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
		this.coffeeMachineInventory.closeInventory(playerIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getWaterGuiHeight(int height) {
		int i = this.fields.get(2);
		int j = this.fields.get(3);
		return j != 0 && i != 0 ? i * height / j : 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getMilkGuiHeight(int height) {
		int i = this.fields.get(4);
		int j = this.fields.get(5);
		return j != 0 && i != 0 ? i * height / j : 0;
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
	
	public int getWaterAmount() {
		return this.fields.get(2);
	}
	
	public int getMaxWaterAmount() {
		return this.fields.get(3);
	}
	
	public int getMilkAmount() {
		return this.fields.get(4);
	}
	
	public int getMaxMilkAmount() {
		return this.fields.get(5);
	}
	
	public int getCookTime() {
		return this.fields.get(6);
	}
	
	public int getCookTimeTotal() {
		return this.fields.get(7);
	}

}
