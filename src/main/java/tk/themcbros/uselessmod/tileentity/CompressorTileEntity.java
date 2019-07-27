package tk.themcbros.uselessmod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CompressorContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class CompressorTileEntity extends MachineTileEntity {

	public static final int RF_PER_TICK = MachineConfig.compressor_rf_per_tick.get();
	
	private static final int[] SLOTS_TOP = new int[] { 0 };
	private static final int[] SLOTS_BOTTOM = new int[] { 1 };
	private static final int[] SLOTS_SIDES = new int[] { 0 };
	
	private NonNullList<ItemStack> compressorItemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
	private int compressTime;
	private int compressTimeTotal;
	
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 4;
		}
		
		@Override
		public void set(int id, int value) {
			switch (id) {
			case 0:
				CompressorTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				CompressorTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				CompressorTileEntity.this.compressTime = value;
				break;
			case 3:
				CompressorTileEntity.this.compressTimeTotal = value;
				break;
			}
		}
		
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return CompressorTileEntity.this.getEnergyStored();
			case 1:
				return CompressorTileEntity.this.getMaxEnergyStored();
			case 2:
				return CompressorTileEntity.this.compressTime;
			case 3:
				return CompressorTileEntity.this.compressTimeTotal;
			default:
				return 0;
			}
		}
	};
	
	public CompressorTileEntity() {
		super(ModTileEntities.COMPRESSOR);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.compressor");
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		return new CompressorContainer(windowId, playerInventory, this, fields);
	}

	private boolean isActive() {
		return this.getEnergyStored() > 0 && compressTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = false;
		if (this.isActive()) {
			this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
		}

		if (!this.world.isRemote) {
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.compressorItemStacks.get(0).isEmpty()) {
				IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.COMPRESSING, this, this.world).orElse(null);
				if (!this.isActive() && this.canCompress(irecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					compressTime++;
				}

				if (this.isActive() && this.canCompress(irecipe)) {
					++this.compressTime;
					if (this.compressTime == this.compressTimeTotal) {
						this.compressTime = 0;
						this.compressTimeTotal = this.getCompressTime();
						this.compressItem(irecipe);
						flag1 = true;
					}
				} else {
					this.compressTime = 0;
				}
			}

			if (flag != this.isActive()) {
				flag1 = true;
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, Boolean.valueOf(this.isActive())), 3);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}
	
	protected int getCompressTime() {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.COMPRESSING, this, this.world)
				.map(CompressorRecipe::getCompressTime).orElse(200);
	}
	
	private boolean canCompress(@Nullable IRecipe<?> recipe) {
		if (!this.compressorItemStacks.get(0).isEmpty() && recipe != null) {
			ItemStack itemstack = recipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.compressorItemStacks.get(1);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() < itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect
																					// stack sizes in furnace recipes
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

	private void compressItem(@Nullable IRecipe<?> recipe) {
		if (recipe != null && this.canCompress(recipe)) {
			ItemStack itemstack = this.compressorItemStacks.get(0);
			ItemStack itemstack1 = recipe.getRecipeOutput();
			ItemStack itemstack2 = this.compressorItemStacks.get(1);
			if (itemstack2.isEmpty()) {
				this.compressorItemStacks.set(1, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
		}
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_BOTTOM;
		} else {
			return side == Direction.UP ? SLOTS_TOP : SLOTS_SIDES;
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 1) {
			return false;
		} else {
			return true;
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
		return this.compressorItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.compressorItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.compressorItemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(compressorItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(compressorItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.compressorItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.compressorItemStacks.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.compressTimeTotal = this.getCompressTime();
			this.compressTime = 0;
			this.markDirty();
		}

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
		this.compressorItemStacks.clear();
	}

	@Override
	public CompoundNBT writeRestorableToNBT(CompoundNBT compound) {
		ItemStackHelper.saveAllItems(compound, compressorItemStacks);
		compound.putInt("CompressTime", this.compressTime);
		compound.putInt("CompressTimeTotal", this.compressTimeTotal);
		return compound;
	}

	@Override
	public void readRestorableFromNBT(CompoundNBT compound) {
		this.compressorItemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, compressorItemStacks);
		this.compressTime = compound.getInt("CompressTime");
		this.compressTimeTotal = compound.getInt("CompressTimeTotal");
	}
	
	private LazyOptional<? extends IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper
			.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.removed && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void remove() {
		super.remove();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

}
