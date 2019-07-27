package tk.themcbros.uselessmod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.container.ElectricFurnaceContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class ElectricFurnaceTileEntity extends MachineTileEntity {
	
	private static final int[] SLOTS_TOP = { 0 };
	private static final int[] SLOTS_SIDE = { 0 };
	private static final int[] SLOTS_BOTTOM = { 1 };
	private static final int RF_PER_TICK = 15;
	
	private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
	private int cookTime;
	private int cookTimeTotal;
	
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 4;
		}
		@Override
		public void set(int id, int value) {
			switch (id) {
			case 0:
				ElectricFurnaceTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				ElectricFurnaceTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				ElectricFurnaceTileEntity.this.cookTime = value;
				break;
			case 3:
				ElectricFurnaceTileEntity.this.cookTimeTotal = value;
				break;
			}
		}
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return ElectricFurnaceTileEntity.this.getEnergyStored();
			case 1:
				return ElectricFurnaceTileEntity.this.getMaxEnergyStored();
			case 2:
				return ElectricFurnaceTileEntity.this.cookTime;
			case 3:
				return ElectricFurnaceTileEntity.this.cookTimeTotal;
			default:
				return 0;
			}
		}
	};

	public ElectricFurnaceTileEntity() {
		super(ModTileEntities.ELECTRIC_FURNACE);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.electric_furnace");
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		return new ElectricFurnaceContainer(windowId, playerInventory, this, fields);
	}
	
	private boolean isBurning() {
		return this.cookTime > 0 && this.energyStorage.getEnergyStored() > 0;
	}

	@Override
	public void tick() {
		
		boolean flag = isBurning();
		boolean flag1 = false;
		ItemStack input = furnaceItemStacks.get(0);
		
		if(energyStorage.getEnergyStored() >= RF_PER_TICK) {
			if(cookTime > 0) {
				energyStorage.modifyEnergyStored(-RF_PER_TICK);
				cookTime++;
				IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this, this.world).orElse(null);
				if(cookTime == this.cookTimeTotal) {
					this.cookTime = 0;
					this.cookTimeTotal = this.getCookTime();
					this.smeltItem(irecipe);
					flag1 = true;
					return;
				}
			} else {
				if(!input.isEmpty()) {
					IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this, this.world).orElse(null);
					if(this.canSmelt(irecipe)) {
						cookTimeTotal = this.getCookTime();
						cookTime++;
						energyStorage.modifyEnergyStored(-RF_PER_TICK);
					} else {
						cookTime = 0;
					}
				}
			}
		}
		
		if(flag != isBurning()) {
			flag1 = true;
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, Boolean.valueOf(this.isBurning())), 3);
		}
		
		if (flag1) {
			this.markDirty();
		}
		
	}

	private int getCookTime() {
		return this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this, this.world)
				.map(FurnaceRecipe::getCookTime).orElse(200);
	}

	protected boolean canSmelt(@Nullable IRecipe<?> irecipe) {
		if (!this.furnaceItemStacks.get(0).isEmpty() && irecipe != null) {
			ItemStack itemstack = irecipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.furnaceItemStacks.get(1);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix:
																											// make
																											// furnace
																											// respect
																											// stack
																											// sizes in
																											// furnace
																											// recipes
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix:
																										// make furnace
																										// respect stack
																										// sizes in
																										// furnace
																										// recipes
				}
			}
		} else {
			return false;
		}
	}

	private void smeltItem(@Nullable IRecipe<?> irecipe) {
		if (irecipe != null && this.canSmelt(irecipe)) {
			ItemStack itemstack = this.furnaceItemStacks.get(0);
			ItemStack itemstack1 = irecipe.getRecipeOutput();
			ItemStack itemstack2 = this.furnaceItemStacks.get(1);
			if (itemstack2.isEmpty()) {
				this.furnaceItemStacks.set(1, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 1) {
			return false;
		}
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_BOTTOM;
		} else {
			return side == Direction.UP ? SLOTS_TOP : SLOTS_SIDE;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return this.furnaceItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.furnaceItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.furnaceItemStacks.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.furnaceItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.furnaceItemStacks.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.cookTime = 0;
			this.markDirty();
		}

	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}

	@Override
	public void clear() {
		this.furnaceItemStacks.clear();
	}

	@Override
	public CompoundNBT writeRestorableToNBT(CompoundNBT compound) {
		ItemStackHelper.saveAllItems(compound, furnaceItemStacks);
		compound.putInt("CookTime", this.cookTime);
		compound.putInt("CookTimeTotal", this.cookTimeTotal);
		return compound;
	}

	@Override
	public void readRestorableFromNBT(CompoundNBT compound) {
		this.furnaceItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, furnaceItemStacks);
		this.cookTime = compound.getInt("CookTime");
		this.cookTimeTotal = compound.getInt("CookTimeTotal");
	}

}
