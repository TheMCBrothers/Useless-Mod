package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import tk.themcbros.uselessmod.blocks.MachineBlock;
import tk.themcbros.uselessmod.container.MagmaCrucibleContainer;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.machine.Upgrade;
import tk.themcbros.uselessmod.recipes.MagmaCrucibleRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MagmaCrucibleTileEntity extends MachineTileEntity {

	public static final int RF_PER_TICK = DEFAULT_RF_PER_TICK;
	public static final int TANK_CAPACITY = 4000; // todo make config entry
	
	private int cookTime, cookTimeTotal;
	private FluidTank tank = new FluidTank(TANK_CAPACITY);
	
	@SuppressWarnings("deprecation")
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 7;
		}
		
		@Override
		public void set(int id, int value) {
			switch (id) {
			case 0:
				MagmaCrucibleTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				MagmaCrucibleTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				MagmaCrucibleTileEntity.this.cookTime = value;
				break;
			case 3:
				MagmaCrucibleTileEntity.this.cookTimeTotal = value;
				break;
			case 4:
				MagmaCrucibleTileEntity.this.tank.setFluid(new FluidStack(MagmaCrucibleTileEntity.this.tank.getFluid().getFluid(), value));
				break;
			case 5:
				MagmaCrucibleTileEntity.this.tank.setCapacity(value);
				break;
			case 6:
				MagmaCrucibleTileEntity.this.tank.setFluid(new FluidStack(Registry.FLUID.getByValue(value), MagmaCrucibleTileEntity.this.tank.getFluidAmount()));
			}
		}
		
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return MagmaCrucibleTileEntity.this.getEnergyStored();
			case 1:
				return MagmaCrucibleTileEntity.this.getMaxEnergyStored();
			case 2:
				return MagmaCrucibleTileEntity.this.cookTime;
			case 3:
				return MagmaCrucibleTileEntity.this.cookTimeTotal;
			case 4:
				return MagmaCrucibleTileEntity.this.tank.getFluidAmount();
			case 5:
				return MagmaCrucibleTileEntity.this.tank.getCapacity();
			case 6:
				return Registry.FLUID.getId(MagmaCrucibleTileEntity.this.tank.getFluid().getFluid());
			default:
				return 0;
			}
		}
	};
	
	public MagmaCrucibleTileEntity() {
		super(ModTileEntities.MAGMA_CRUCIBLE, MachineTier.STANDARD, false);
	}
	
	private LazyOptional<FluidTank> fluidHandler = LazyOptional.of(() -> this.tank);

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
		if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return fluidHandler.cast();
		return super.getCapability(cap, side);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.magma_crucible");
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("CookTime", this.cookTime);
		compound.putInt("CookTimeTotal", this.cookTimeTotal);
		compound.put("Tank", this.tank.writeToNBT(new CompoundNBT()));
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.cookTime = compound.getInt("CookTime");
		this.cookTimeTotal = compound.getInt("CookTimeTotal");
		this.tank.readFromNBT(compound.getCompound("Tank"));
	}

	private boolean isActive() {
		return this.getEnergyStored() > 0 && this.cookTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = false;

		if (!this.world.isRemote) {
			
			this.receiveEnergyFromSlot(this.getSizeInventory() - 1);
			
			final ItemStack containerStack = this.items.get(1);
			if(!containerStack.isEmpty()) {
				final ItemStack bucketOutStack = this.items.get(2);
				if(bucketOutStack.isEmpty()) {
					FluidActionResult result = FluidUtil.tryFillContainer(containerStack, this.tank, FluidAttributes.BUCKET_VOLUME, null, true);
					if (result.isSuccess()) {
						ItemStack filledContainer = result.getResult();
						this.items.set(2, filledContainer);
						containerStack.shrink(1);
					}
				}
			}
			
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.items.get(0).isEmpty()) {
				MagmaCrucibleRecipe recipe = this.world.getRecipeManager().getRecipe(RecipeTypes.MAGMA_CRUCIBLE, this, this.world).orElse(null);
				if (!this.isActive() && this.canCook(recipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					cookTime++;
				}

				if (this.isActive() && this.canCook(recipe)) {
					this.cookTime++;
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					if (this.cookTime == this.cookTimeTotal) {
						this.cookTime = 0;
						this.cookTimeTotal = this.getCookTime();
						this.cookItem(recipe);
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
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
	
	private boolean canCook(@Nullable MagmaCrucibleRecipe recipe) {
		if (!this.items.get(0).isEmpty() && recipe != null) {
			FluidStack fluidStack = recipe.getResult();
			if (fluidStack.isEmpty()) {
				return false;
			} else {
				FluidStack fluidStack2 = this.tank.getFluid();
				if (fluidStack2.isEmpty()) {
					return true;
				} else if (!fluidStack2.isFluidEqual(fluidStack)) {
					return false;
				} else if (fluidStack2.getAmount() + fluidStack.getAmount() <= this.tank.getCapacity()) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	private void cookItem(@Nullable MagmaCrucibleRecipe recipe) {
		if (recipe != null && this.canCook(recipe)) {
			ItemStack itemstack = this.items.get(0);
			FluidStack fluidStack1 = recipe.getResult();
			FluidStack fluidStack2 = this.tank.getFluid();
			if (fluidStack2.isEmpty()) {
				this.tank.setFluid(fluidStack1.copy());
			} else if (fluidStack1.isFluidEqual(fluidStack2)) {
				fluidStack2.grow(fluidStack1.getAmount());
			}
			itemstack.shrink(1);
		}
	}
	
	public int getCookTime() {
		if(world == null) return 0;
		int cookTime = this.world.getRecipeManager().getRecipe(RecipeTypes.MAGMA_CRUCIBLE, this, this.world)
				.map(MagmaCrucibleRecipe::getCookTime).orElse(100);
		return this.getProcessTime(cookTime);
	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, Direction arg2) {
		return true;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, Direction arg2) {
		return isItemValidForSlot(arg0, arg1);
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull Direction direction) {
		return direction == Direction.DOWN ? new int[] { 2 } : new int[] { 0, 1 };
	}

	@Override
	public int getSizeInventory() {
		return 4;
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
			this.cookTimeTotal = this.getCookTime();
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new MagmaCrucibleContainer(id, player, this, this.upgradeInventory, this.fields);
	}

	public FluidTank getTank() {
		return this.tank;
	}

}
