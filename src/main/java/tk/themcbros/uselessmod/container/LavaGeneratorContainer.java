package tk.themcbros.uselessmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.lists.ModContainerTypes;
import tk.themcbros.uselessmod.tileentity.LavaGeneratorTileEntity;

public class LavaGeneratorContainer extends Container {

	private IInventory lavaGeneratorInventory;
	private IIntArray fields;
	private BlockPos pos;
	private World world;
	
	public LavaGeneratorContainer(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new LavaGeneratorTileEntity(), new IntArray(7));
	}

	public LavaGeneratorContainer(int id, PlayerInventory playerInventory, LavaGeneratorTileEntity lavaGeneratorInventory, IIntArray fields) {
		super(ModContainerTypes.LAVA_GENERATOR, id);
		this.lavaGeneratorInventory = lavaGeneratorInventory;
		this.fields = fields;
		this.world = playerInventory.player.world;
		this.pos = lavaGeneratorInventory.getPos();
		
		// Machine Slots
		
		// Upgrade Slots
		
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
		return this.lavaGeneratorInventory.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		int machineSlotCount = 0;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (!this.mergeItemStack(itemstack1, machineSlotCount, machineSlotCount + 36, false)) {
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

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.lavaGeneratorInventory.closeInventory(playerIn);
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
		return this.fields.get(5);
	}
	
	public int getMaxEnergyStored() {
		return this.fields.get(6);
	}
	
	public int getBurnTime() {
		return this.fields.get(0);
	}
	
	public int getTotalBurnTime() {
		return this.fields.get(1);
	}
	
	public int getFluidAmount() {
		return this.fields.get(2);
	}
	
	public int getMaxFluidAmount() {
		return this.fields.get(3);
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public World getWorld() {
		return world;
	}

	@SuppressWarnings("deprecation")
	public Fluid getTankFluid() {
		return Registry.FLUID.getByValue(this.fields.get(4));
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
				return LavaGeneratorContainer.this.getMaxFluidAmount();
			}
			
			@Override
			public FluidStack getFluidInTank(int tank) {
				return LavaGeneratorContainer.this.getTankStack();
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
