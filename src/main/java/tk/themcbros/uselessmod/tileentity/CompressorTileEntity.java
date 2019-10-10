package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CompressorContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompressorTileEntity extends MachineTileEntity {

	public static final int RF_PER_TICK = MachineConfig.compressor_rf_per_tick.get();
	
	private int compressTime;
	private int compressTimeTotal;
	
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 5;
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
		super(ModTileEntities.COMPRESSOR, MachineTier.STANDARD, false);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.compressor");
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		return new CompressorContainer(windowId, playerInventory, this, this.upgradeInventory, fields);
	}

	private boolean isActive() {
		return this.getEnergyStored() > 0 && compressTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = false;

		assert this.world != null;
		if (!this.world.isRemote) {
			
			this.receiveEnergyFromSlot(this.getSizeInventory() - 1);
			
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.items.get(0).isEmpty()) {
				CompressorRecipe irecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.COMPRESSING, this, this.world).orElse(null);
				if (!this.isActive() && this.canCompress(irecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					compressTime++;
				}

				if (this.isActive() && this.canCompress(irecipe)) {
					this.compressTime++;
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
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
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, this.isActive()), 3);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}
	
	private int getCompressTime() {
		assert this.world != null;
		int compressTime = this.world.getRecipeManager().getRecipe(RecipeTypes.COMPRESSING, this, this.world)
				.map(CompressorRecipe::getCompressTime).orElse(200);
		return this.getProcessTime(compressTime);
	}
	
	private boolean canCompress(@Nullable CompressorRecipe recipe) {
		if (!this.items.get(0).isEmpty() && recipe != null) {
			ItemStack itemstack = recipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(1);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() < itemstack1.getMaxStackSize()) {
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
				}
			}
		} else {
			return false;
		}
	}

	private void compressItem(@Nullable CompressorRecipe recipe) {
		if (recipe != null && this.canCompress(recipe)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = recipe.getRecipeOutput();
			ItemStack itemstack2 = this.items.get(1);
			if (itemstack2.isEmpty()) {
				this.items.set(1, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
		}
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull Direction side) {
		return side == Direction.DOWN ? new int[] { 1 } : new int[] { 0 };
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index != 1;
	}

	@Override
	public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull Direction direction) {
		return index == 1;
	}

	@Override
	public int getSizeInventory() {
		return 3;
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
			this.compressTimeTotal = this.getCompressTime();
			this.compressTime = 0;
			this.markDirty();
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("CompressTime", this.compressTime);
		compound.putInt("CompressTimeTotal", this.compressTimeTotal);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.compressTime = compound.getInt("CompressTime");
		this.compressTimeTotal = compound.getInt("CompressTimeTotal");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new CompressorContainer(id, player);
	}

}
