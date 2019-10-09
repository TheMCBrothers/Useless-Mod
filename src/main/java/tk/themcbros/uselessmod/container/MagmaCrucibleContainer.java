package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.container.slots.EnergyItemSlot;
import tk.themcbros.uselessmod.container.slots.FluidContainerSlot;
import tk.themcbros.uselessmod.container.slots.MachineUpgradeSlot;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.tileentity.MagmaCrucibleTileEntity;

public class MagmaCrucibleContainer extends Container {

	private IInventory magmaCrucibleInventory;
	private IIntArray fields;
	private BlockPos pos;
	private World world;
	
	public MagmaCrucibleContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new MagmaCrucibleTileEntity(), new Inventory(3), new IntArray(7));
	}

	public MagmaCrucibleContainer(int id, PlayerInventory playerInventory, MagmaCrucibleTileEntity magmaCrucibleInventory, IInventory upgradeInventory, IIntArray fields) {
		super(ModContainerTypes.MAGMA_CRUCIBLE, id);
		this.magmaCrucibleInventory = magmaCrucibleInventory;
		this.fields = fields;
		this.world = playerInventory.player.world;
		this.pos = magmaCrucibleInventory.getPos();
		
		// Machine Slots
		this.addSlot(new Slot(magmaCrucibleInventory, 0, 44, 30));
		this.addSlot(new FluidContainerSlot(magmaCrucibleInventory, 1, 129, 11));
		this.addSlot(new Slot(magmaCrucibleInventory, 2, 129, 48) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new EnergyItemSlot(magmaCrucibleInventory, 3, 152, 55));
		
		// Upgrade Slots
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 0, 8, 8));
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 1, 8, 26));
		this.addSlot(new MachineUpgradeSlot(upgradeInventory, 2, 8, 44));
		
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
		return this.magmaCrucibleInventory.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		int numSlots = 7;
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
		this.magmaCrucibleInventory.closeInventory(playerIn);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getEnergyStoredScaled(int height) {
		int i = this.fields.get(0);
		int j = this.fields.get(1);
		return j != 0 && i != 0 ? i * height / j : 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getCookTimeScaled() {
		int i = this.fields.get(2);
		int j = this.fields.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}
	
	public int getEnergyStored() {
		return this.fields.get(0);
	}
	
	public int getMaxEnergyStored() {
		return this.fields.get(1);
	}
	
	public int getCookTime() {
		return this.fields.get(2);
	}
	
	public int getCookTimeTotal() {
		return this.fields.get(3);
	}
	
	public int getFluidAmount() {
		return this.fields.get(4);
	}
	
	public int getMaxFluidAmount() {
		return this.fields.get(5);
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public World getWorld() {
		return world;
	}

	@SuppressWarnings("deprecation")
	public Fluid getTankFluid() {
		return Registry.FLUID.getByValue(this.fields.get(6));
	}
	
	public FluidStack getTankStack() {
		return new FluidStack(getTankFluid(), getFluidAmount());
	}
	
	public IFluidHandler getFluidTankHandler() {
		return new IFluidHandler() {
			
			@Override
			public boolean isFluidValid(int tank, FluidStack stack) {
				return true;
			}
			
			@Override
			public int getTanks() {
				return 1;
			}
			
			@Override
			public int getTankCapacity(int tank) {
				return MagmaCrucibleContainer.this.fields.get(5);
			}
			
			@Override
			public FluidStack getFluidInTank(int tank) {
				return MagmaCrucibleContainer.this.getTankStack();
			}
			
			@Override
			public int fill(FluidStack resource, FluidAction action) {
				return 0;
			}
			
			@Override
			public FluidStack drain(int maxDrain, FluidAction action) {
				return FluidStack.EMPTY;
			}
			
			@Override
			public FluidStack drain(FluidStack resource, FluidAction action) {
				return resource;
			}
		};
	}
	
}
