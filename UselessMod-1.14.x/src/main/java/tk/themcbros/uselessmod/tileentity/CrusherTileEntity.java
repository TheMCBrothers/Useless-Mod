package tk.themcbros.uselessmod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.container.CrusherContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class CrusherTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
	
	private static final int[] field_214015_g = new int[] { 0 };
	private static final int[] field_214016_h = new int[] { 2, 1 };
	private static final int[] field_214017_i = new int[] { 1 };

	private NonNullList<ItemStack> crusherItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);

	private int burnTime;
	private int crushTime;
	private int crushTimeTotal;

	protected final IIntArray fields = new IIntArray() {
		public int get(int p_221476_1_) {
			switch (p_221476_1_) {
			case 0:
				return CrusherTileEntity.this.burnTime;
			case 1:
				return CrusherTileEntity.this.crushTime;
			case 2:
				return CrusherTileEntity.this.crushTimeTotal;
			default:
				return 0;
			}
		}

		public void set(int p_221477_1_, int p_221477_2_) {
			switch (p_221477_1_) {
			case 0:
				CrusherTileEntity.this.burnTime = p_221477_2_;
				break;
			case 1:
				CrusherTileEntity.this.crushTime = p_221477_2_;
				break;
			case 2:
				CrusherTileEntity.this.crushTimeTotal = p_221477_2_;
			}

		}

		public int size() {
			return 3;
		}
	};

	public CrusherTileEntity() {
		super(ModTileEntities.CRUSHER);
	}
	
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.crusherItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.crusherItemStacks);
		this.burnTime = compound.getInt("BurnTime");
		this.crushTime = compound.getInt("CrushTime");
		this.crushTimeTotal = compound.getInt("CrushTimeTotal");

	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt("BurnTime", this.burnTime);
		compound.putInt("CrushTime", this.crushTime);
		compound.putInt("CrushTimeTotal", this.crushTimeTotal);
		ItemStackHelper.saveAllItems(compound, this.crusherItemStacks);
		return compound;
	}
	
	private boolean isActive() {
		return this.burnTime > 0;
	}

	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = false;
		if (this.isActive()) {
			--this.burnTime;
		}

		if (!this.world.isRemote) {
			ItemStack itemstack = this.crusherItemStacks.get(1);
			if (this.isActive() || !itemstack.isEmpty() && !this.crusherItemStacks.get(0).isEmpty()) {
				IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.CRUSHING, this, this.world)
						.orElse(null);
				if (!this.isActive() && this.canCrush(irecipe)) {
					this.burnTime = this.getBurnTime(itemstack);
					if (this.isActive()) {
						flag1 = true;
						if (itemstack.hasContainerItem())
							this.crusherItemStacks.set(1, itemstack.getContainerItem());
						else if (!itemstack.isEmpty()) {
							itemstack.shrink(1);
							if (itemstack.isEmpty()) {
								this.crusherItemStacks.set(1, itemstack.getContainerItem());
							}
						}
					}
				}

				if (this.isActive() && this.canCrush(irecipe)) {
					++this.crushTime;
					if (this.crushTime == this.crushTimeTotal) {
						this.crushTime = 0;
						this.crushTimeTotal = this.func_214005_h();
						this.crushItem(irecipe);
						flag1 = true;
					}
				} else {
					this.crushTime = 0;
				}
			} else if (!this.isActive() && this.crushTime > 0) {
				this.crushTime = MathHelper.clamp(this.crushTime - 2, 0, this.crushTimeTotal);
			}

			if (flag != this.isActive()) {
				flag1 = true;
				this.world.setBlockState(this.pos,
						this.world.getBlockState(this.pos).with(MachineBlock.ACTIVE, Boolean.valueOf(this.isActive())),
						3);
			}
		}

		if (flag1) {
			this.markDirty();
		}

	}

	protected boolean canCrush(@Nullable IRecipe<?> p_214008_1_) {
		if (!this.crusherItemStacks.get(0).isEmpty() && p_214008_1_ != null) {
			ItemStack itemstack = p_214008_1_.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.crusherItemStacks.get(2);
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

	private void crushItem(@Nullable IRecipe<?> p_214007_1_) {
		if (p_214007_1_ != null && this.canCrush(p_214007_1_)) {
			ItemStack itemstack = this.crusherItemStacks.get(0);
			ItemStack itemstack1 = p_214007_1_.getRecipeOutput();
			ItemStack itemstack2 = this.crusherItemStacks.get(2);
			if (itemstack2.isEmpty()) {
				this.crusherItemStacks.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.crusherItemStacks.get(1).isEmpty()
					&& this.crusherItemStacks.get(1).getItem() == Items.BUCKET) {
				this.crusherItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.shrink(1);
		}
	}
	   
	protected int getBurnTime(ItemStack p_213997_1_) {
		if (p_213997_1_.isEmpty()) {
			return 0;
		} else {
			Item item = p_213997_1_.getItem();
			int ret = p_213997_1_.getBurnTime();
			return net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(p_213997_1_,
					ret == -1 ? AbstractFurnaceTileEntity.getBurnTimes().getOrDefault(item, 0) : ret);
		}
	}

	protected int func_214005_h() {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.CRUSHING, this, this.world)
				.map(CrusherRecipe::getCrushTime).orElse(200);
	}

	@Override
	public int getSizeInventory() {
		return this.crusherItemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.crusherItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.crusherItemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.crusherItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.crusherItemStacks, index);
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.crusherItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.crusherItemStacks.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.crushTimeTotal = this.func_214005_h();
			this.crushTime = 0;
			this.markDirty();
		}

	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clear() {
		this.crusherItemStacks.clear();
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 2) {
			return false;
		} else if (index != 1) {
			return true;
		} else {
			ItemStack itemstack = this.crusherItemStacks.get(1);
			return AbstractFurnaceTileEntity.isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
		}
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return field_214016_h;
		} else {
			return side == Direction.UP ? field_214015_g : field_214017_i;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if (direction == Direction.DOWN && index == 1) {
			Item item = stack.getItem();
			if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.uselessmod.crusher");
	}

	@Override
	protected Container createMenu(int i, PlayerInventory inventoryPlayer) {
		return new CrusherContainer(i, inventoryPlayer, this, fields);
	}
	
	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper
			.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (!this.removed && facing != null
				&& capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
