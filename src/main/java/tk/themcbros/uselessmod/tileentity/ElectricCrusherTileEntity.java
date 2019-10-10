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
import tk.themcbros.uselessmod.container.ElectricCrusherContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElectricCrusherTileEntity extends MachineTileEntity {

	public static final int RF_PER_TICK = MachineConfig.crusher_rf_per_tick.get();
	private int crushTime;
	private int crushTimeTotal;
	private ITextComponent crusherCustomName;
	
	protected final IIntArray fields = new IIntArray() {
		public int get(int id) {
			switch (id) {
			case 0:
				return ElectricCrusherTileEntity.this.getEnergyStored();
			case 1:
				return ElectricCrusherTileEntity.this.getMaxEnergyStored();
			case 2:
				return ElectricCrusherTileEntity.this.crushTime;
			case 3:
				return ElectricCrusherTileEntity.this.crushTimeTotal;
			default:
				return 0;
			}
		}

		public void set(int id, int value) {
			switch (id) {
			case 0:
				ElectricCrusherTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				ElectricCrusherTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				ElectricCrusherTileEntity.this.crushTime = value;
				break;
			case 3:
				ElectricCrusherTileEntity.this.crushTimeTotal = value;
			}

		}

		public int size() {
			return 4;
		}
	};

	public ElectricCrusherTileEntity() {
		super(ModTileEntities.ELECTRIC_CRUSHER, MachineTier.STANDARD, false);
	}

	public int getSizeInventory() {
		return 3;
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.crushTimeTotal = this.getCrushTime();
			this.crushTime = 0;
			this.markDirty();
		}

	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		this.crushTime = compound.getInt("CrushTime");
		this.crushTimeTotal = compound.getInt("CrushTimeTotal");

		if (compound.contains("CustomName", 8)) {
			this.crusherCustomName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}

	}

	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("CrushTime", this.crushTime);
		compound.putInt("CrushTimeTotal", this.crushTimeTotal);

		if (this.items != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.crusherCustomName));
		}

		return super.write(compound);
	}
	
	private boolean isActive() {
		return this.energyStorage.getEnergyStored() > 0 && this.crushTime > 0;
	}
	
	@Override
	public void tick() {
		
		boolean flag = isActive();
		boolean flag1 = false;

		assert this.world != null;
		if (!this.world.isRemote) {
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.items.get(0).isEmpty()) {
				CrusherRecipe crusherRecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.CRUSHING, this, this.world).orElse(null);
				if (!this.isActive() && this.canCrush(crusherRecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					crushTime++;
				}

				if (this.isActive() && this.canCrush(crusherRecipe)) {
					this.crushTime++;
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					if (this.crushTime == this.crushTimeTotal) {
						this.crushTime = 0;
						this.crushTimeTotal = this.getCrushTime();
						this.crushItem(crusherRecipe);
						flag1 = true;
					}
				} else {
					this.crushTime = 0;
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
	
	private int getCrushTime() {
		assert this.world != null;
		int crushTime = this.world.getRecipeManager().getRecipe(RecipeTypes.CRUSHING, this, this.world)
				.map(CrusherRecipe::getCrushTime).orElse(200);
		return this.getProcessTime(crushTime);
	}
	
	private boolean canCrush(@Nullable CrusherRecipe recipe) {
		if (!this.items.get(0).isEmpty() && recipe != null) {
			ItemStack recipeOutputStack = recipe.getRecipeOutput();
			ItemStack recipeSecondOutputStack = recipe.getSecondRecipeOutput();
			if(!recipeSecondOutputStack.isEmpty()) {
				if (recipeOutputStack.isEmpty()) {
					return false;
				} else {
					ItemStack crusherOutputStack = this.items.get(1);
					ItemStack crusherSecondOutputStack = this.items.get(2);
					if (crusherOutputStack.isEmpty() && crusherSecondOutputStack.isEmpty()) {
						return true;
					} else if (!crusherOutputStack.isItemEqual(recipeOutputStack) || !crusherSecondOutputStack.isItemEqual(recipeSecondOutputStack)) {
						return false;
					} else if (crusherOutputStack.getCount() + recipeOutputStack.getCount() <= this.getInventoryStackLimit()
							&& crusherOutputStack.getCount() < crusherOutputStack.getMaxStackSize()
							&& crusherSecondOutputStack.getCount() + recipeSecondOutputStack.getCount() <= this.getInventoryStackLimit()
							&& crusherSecondOutputStack.getCount() < crusherSecondOutputStack.getMaxStackSize()) {
						return true;
					} else {
						return crusherOutputStack.getCount() + recipeOutputStack.getCount() <= recipeOutputStack.getMaxStackSize()
								&& crusherSecondOutputStack.getCount() + recipeSecondOutputStack.getCount() <= recipeSecondOutputStack.getMaxStackSize();
					}
				}
			} else {
				if (recipeOutputStack.isEmpty()) {
					return false;
				} else {
					ItemStack crusherOutputStack = this.items.get(1);
					if (crusherOutputStack.isEmpty()) {
						return true;
					} else if (!crusherOutputStack.isItemEqual(recipeOutputStack)) {
						return false;
					} else if (crusherOutputStack.getCount() + recipeOutputStack.getCount() <= this.getInventoryStackLimit()
							&& crusherOutputStack.getCount() < crusherOutputStack.getMaxStackSize()) {
						return true;
					} else {
						return crusherOutputStack.getCount() + recipeOutputStack.getCount() <= recipeOutputStack.getMaxStackSize();
					}
				}
			}
		} else {
			return false;
		}
	}

	private void crushItem(CrusherRecipe recipe) {
		if (recipe != null && this.canCrush(recipe)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = recipe.getRecipeOutput();
			ItemStack itemstack2 = recipe.getSecondRecipeOutput();
			ItemStack itemstack3 = this.items.get(1);
			ItemStack itemstack4 = this.items.get(2);
			if (itemstack3.isEmpty())
				this.items.set(1, itemstack1.copy());
			if (itemstack4.isEmpty())
				this.items.set(2, itemstack2.copy());
			if (itemstack3.getItem() == itemstack1.getItem()) 
				itemstack3.grow(itemstack1.getCount());
			if (itemstack4.getItem() == itemstack2.getItem())
				itemstack4.grow(itemstack2.getCount());

			itemstack.shrink(1);
		}
	}

	@Override
	public void openInventory(PlayerEntity player) {
	}

	@Override
	public void closeInventory(PlayerEntity player) {
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 1 || index == 2) {
			return false;
		} else {
			return true;
		}
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull Direction side) {
		return side == Direction.DOWN ? new int[] { 1, 2 } : new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nullable Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull Direction direction) {
		return true;
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.crusher");
	}

	@Override
	public Container createMenu(int windowId, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
		return new ElectricCrusherContainer(windowId, playerInventory, this, this.upgradeInventory, this.fields);
	}

	@Nonnull
	@Override
	protected Container createMenu(int id, @Nonnull PlayerInventory player) {
		return new ElectricCrusherContainer(id, player);
	}
	
}
