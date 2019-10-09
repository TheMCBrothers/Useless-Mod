package tk.themcbros.uselessmod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.ElectricFurnaceContainer;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.machine.Upgrade;

public class ElectricFurnaceTileEntity extends MachineTileEntity {

	private static final int[] SLOTS_TOP = { 0 };
	private static final int[] SLOTS_SIDE = { 0 };
	private static final int[] SLOTS_BOTTOM = { 1 };
	private static final int RF_PER_TICK = MachineConfig.furnace_rf_per_tick.get();

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
		super(ModTileEntities.ELECTRIC_FURNACE, MachineTier.STANDARD, false);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.electric_furnace");
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		return new ElectricFurnaceContainer(windowId, playerInventory, this, this.upgradeInventory, fields);
	}

	private boolean isBurning() {
		return this.cookTime > 0 && this.energyStorage.getEnergyStored() > 0;
	}

	@Override
	public void tick() {

		boolean flag = isBurning();
		boolean flag1 = false;
		ItemStack input = items.get(0);

		if (!this.world.isRemote) {
			if (energyStorage.getEnergyStored() >= RF_PER_TICK) {
				if (cookTime > 0) {
					energyStorage.modifyEnergyStored(-RF_PER_TICK);
					cookTime++;
					FurnaceRecipe irecipe = this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this, this.world)
							.orElse(null);
					if (cookTime == this.cookTimeTotal) {
						this.cookTime = 0;
						this.cookTimeTotal = this.getCookTime();
						this.smeltItem(irecipe);
						flag1 = true;
						return;
					}
				} else {
					if (!input.isEmpty()) {
						FurnaceRecipe irecipe = this.world.getRecipeManager()
								.getRecipe(IRecipeType.SMELTING, this, this.world).orElse(null);
						if (this.canSmelt(irecipe)) {
							cookTimeTotal = this.getCookTime();
							cookTime++;
							energyStorage.modifyEnergyStored(-RF_PER_TICK);
						} else {
							cookTime = 0;
						}
					}
				}
			}

			if (flag != isBurning()) {
				flag1 = true;
				this.world.setBlockState(this.pos,
						this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, this.isBurning()), 3);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}

	private int getCookTime() {
		int cookTime = this.world.getRecipeManager().getRecipe(IRecipeType.SMELTING, this, this.world)
				.map(FurnaceRecipe::getCookTime).orElse(200);
		return this.getProcessTime(cookTime);
	}

	protected boolean canSmelt(@Nullable FurnaceRecipe irecipe) {
		if (!this.items.get(0).isEmpty() && irecipe != null) {
			ItemStack itemstack = irecipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(1);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private void smeltItem(@Nullable FurnaceRecipe irecipe) {
		if (irecipe != null && this.canSmelt(irecipe)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = irecipe.getRecipeOutput();
			ItemStack itemstack2 = this.items.get(1);
			if (itemstack2.isEmpty()) {
				this.items.set(1, itemstack1.copy());
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
		return 2;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.cookTime = 0;
			this.markDirty();
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("CookTime", this.cookTime);
		compound.putInt("CookTimeTotal", this.cookTimeTotal);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.cookTime = compound.getInt("CookTime");
		this.cookTimeTotal = compound.getInt("CookTimeTotal");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new ElectricFurnaceContainer(id, player);
	}

}
