package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import tk.themcbros.uselessmod.container.slots.EnergyItemSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeBeansSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeInputSecondSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeInputSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.CoffeeOutputSlot;
import tk.themcbros.uselessmod.container.slots.coffee_machine.WaterTankSlot;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.recipes.RecipeTypes;
import tk.themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

public class CoffeeMachineContainer extends Container {

	private IInventory coffeeMachineInventory;
	private IIntArray fields;
	private BlockPos pos;
	private World world;
	
	public CoffeeMachineContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new CoffeeMachineTileEntity(), new IntArray(7));
	}

	public CoffeeMachineContainer(int id, PlayerInventory playerInventory, CoffeeMachineTileEntity coffeeMachineInventory, IIntArray fields) {
		super(ModContainerTypes.COFFEE_MACHINE, id);
		this.coffeeMachineInventory = coffeeMachineInventory;
		this.fields = fields;
		this.world = playerInventory.player.world;
		this.pos = coffeeMachineInventory.getPos();
		
		// Machine Slots
		this.addSlot(new WaterTankSlot(coffeeMachineInventory, 0, 7, 55));
		this.addSlot(new CoffeeBeansSlot(coffeeMachineInventory, 1, 27, 55));
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
		int machineSlotCount = 6;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == machineSlotCount - 2) {
				if (!this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 36, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= machineSlotCount) {

				if (this.isValidFluidContainer(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (itemstack1.getItem() == ModItems.COFFEE_BEANS) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (itemstack1.getItem() == ModItems.CUP) {
					if (!this.mergeItemStack(itemstack1, 2, 3, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.canSmelt(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 3, 4, false)) {
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
		}

		return itemstack;
	}

	private boolean isEnergyItem(ItemStack itemstack1) {
		return !itemstack1.isEmpty() && itemstack1.getCapability(CapabilityEnergy.ENERGY)
				.map(IEnergyStorage::canExtract).orElse(false);
	}

	private boolean isValidFluidContainer(ItemStack stack) {
		return !stack.isEmpty() && stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				.map(handler -> handler.getFluidInTank(0).getFluid().isIn(FluidTags.WATER)).orElse(false);
	}

	private boolean canSmelt(ItemStack p_217057_1_) {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.COFFEE, new Inventory(p_217057_1_), this.world)
				.isPresent();
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.coffeeMachineInventory.closeInventory(playerIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getEnergyStoredScaled(int height) {
		int i = this.fields.get(0);
		int j = this.fields.get(1);
		return j != 0 && i != 0 ? i * height / j : 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getCookTimeScaled() {
		int i = this.fields.get(4);
		int j = this.fields.get(5);
		return j != 0 && i != 0 ? i * 41 / j : 0;
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
	
	public int getCookTime() {
		return this.fields.get(4);
	}
	
	public int getCookTimeTotal() {
		return this.fields.get(5);
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public World getWorld() {
		return world;
	}

	@SuppressWarnings("deprecation")
	public Fluid getWaterTankFluid() {
		return Registry.FLUID.getByValue(this.fields.get(6));
	}
	
}
