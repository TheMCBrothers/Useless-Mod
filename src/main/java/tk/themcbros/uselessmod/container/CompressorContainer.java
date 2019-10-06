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
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.container.slots.EnergyItemSlot;
import tk.themcbros.uselessmod.container.slots.MachineUpgradeSlot;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class CompressorContainer extends Container {
	
	private IInventory inventory;
	private IIntArray fields;
	private World world;
	
	private final int machineSlotCount = 6;

	public CompressorContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(3), new Inventory(3), new IntArray(4));
	}
	
	public CompressorContainer(int id, PlayerInventory playerInventory, IInventory inventory, IInventory upgradeInventory, IIntArray fields) {
		super(ModContainerTypes.COMPRESSOR, id);
		this.inventory = inventory;
		this.fields = fields;
		this.world = playerInventory.player.world;
		
		this.addSlot(new Slot(inventory, 0, 56, 35));
		this.addSlot(new Slot(inventory, 1, 116, 35));
		this.addSlot(new EnergyItemSlot(inventory, 2, 152, 55));
		
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
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 1) {
				if (!this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 36, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= machineSlotCount) {
				if (this.canCompress(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (itemstack1.getItem() instanceof UpgradeItem) {
					if (!this.mergeItemStack(itemstack1, 2, machineSlotCount, false)) {
						return ItemStack.EMPTY;
					}
				} else if(this.isEnergyItem(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, machineSlotCount - 1, machineSlotCount, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= machineSlotCount && index < machineSlotCount + 27) {
					if (!this.mergeItemStack(itemstack1, machineSlotCount + 27, machineSlotCount + 36, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= machineSlotCount + 27 && index < machineSlotCount + 36 && !this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 27, false)) {
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

	private boolean isEnergyItem(ItemStack itemstack1) {
		return itemstack1.getCapability(CapabilityEnergy.ENERGY).isPresent();
	}

	protected boolean canCompress(ItemStack stack) {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.COMPRESSING, new Inventory(stack), this.world)
				.isPresent();
	}

	@OnlyIn(Dist.CLIENT)
	public int getCompressTimeScaled() {
		int i = this.fields.get(2);
		int j = this.fields.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
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
