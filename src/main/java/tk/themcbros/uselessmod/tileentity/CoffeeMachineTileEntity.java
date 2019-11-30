package tk.themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
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
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;
import tk.themcbros.uselessmod.blocks.CoffeeMachineBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CoffeeMachineContainer;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CoffeeMachineTileEntity extends MachineTileEntity {

	public static final int RF_PER_TICK = MachineConfig.coffee_machine_rf_per_tick.get();
	public static final double WATER_PER_TICK = MachineConfig.coffee_machine_water_per_tick.get();
	public static final int COFFEE_BEANS_PER_COFFEE = MachineConfig.coffee_machine_coffee_beans_per_coffee.get();
	public static final int WATER_CAPACITY = MachineConfig.coffee_machine_water_capacity.get();
	
	private static final int[] SLOTS_TOP = new int[] { 2,3,5 };
	private static final int[] SLOTS_BOTTOM = new int[] { 4 };
	private static final int[] SLOTS_SIDES = new int[] { 0,1,2,3,5 };
	
	private FluidTank waterTank = new FluidTank(WATER_CAPACITY, (stack) -> stack.getFluid().isIn(FluidTags.WATER));
	
	private int cookTime;
	private int cookTimeTotal;
	
	@SuppressWarnings("deprecation")
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 7;
		}
		
		@Override
		public void set(int index, int value) {
			switch (index) {
			case 0:
				CoffeeMachineTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 1:
				CoffeeMachineTileEntity.this.energyStorage.setCapacity(value);
				break;
			case 2:
				CoffeeMachineTileEntity.this.waterTank.getFluid().setAmount(value);
				break;
			case 3:
				CoffeeMachineTileEntity.this.waterTank.setCapacity(value);
				break;
			case 4:
				CoffeeMachineTileEntity.this.cookTime = value;
				break;
			case 5:
				CoffeeMachineTileEntity.this.cookTimeTotal = value;
				break;
			case 6:
				CoffeeMachineTileEntity.this.waterTank.setFluid(new FluidStack(Registry.FLUID.getByValue(value), CoffeeMachineTileEntity.this.waterTank.getFluidAmount()));

			default:
				break;
			}
		}
		
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return CoffeeMachineTileEntity.this.energyStorage.getEnergyStored();
			case 1:
				return CoffeeMachineTileEntity.this.energyStorage.getMaxEnergyStored();
			case 2:
				return CoffeeMachineTileEntity.this.waterTank.getFluidAmount();
			case 3:
				return CoffeeMachineTileEntity.this.waterTank.getCapacity();
			case 4:
				return CoffeeMachineTileEntity.this.cookTime;
			case 5:
				return CoffeeMachineTileEntity.this.cookTimeTotal;
			case 6:
				return Registry.FLUID.getId(CoffeeMachineTileEntity.this.waterTank.getFluid().getFluid());
			default:
				return 0;
			}
		}
	};
	
	public CoffeeMachineTileEntity() {
		super(ModTileEntities.COFFEE_MACHINE, MachineTier.COFFEE, false);
	}
	
	private boolean isActive() {
		return this.getEnergyStored() >= RF_PER_TICK && cookTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = this.getBlockState().get(CoffeeMachineBlock.CUP);
		boolean flag2 = false;
		boolean cup = (this.items.get(2).getItem() == ModItems.CUP || this.items.get(4).getItem() == ModItems.COFFEE_CUP);
		if((flag) && (!flag1)) flag2 = true;
		
		if(!world.isRemote) {
			
			this.receiveEnergyFromSlot(this.getSizeInventory() - 1);
			
			if((cup && !flag1) || (!cup && flag1)) flag2 = true;

			final ItemStack bucketStack = this.items.get(0);
			if (!bucketStack.isEmpty()) {
				FluidActionResult result = FluidUtil.tryEmptyContainer(bucketStack, this.waterTank, FluidAttributes.BUCKET_VOLUME, null, true);
				if (result.isSuccess()) {
					ItemStack outputSlotStack = this.items.get(1);
					ItemStack resultStack = result.getResult();
					if (ItemHandlerHelper.canItemStacksStack(outputSlotStack, resultStack) && resultStack.getMaxStackSize() > 1 &&
							outputSlotStack.getCount() <= outputSlotStack.getMaxStackSize() - resultStack.getCount()) {
						outputSlotStack.grow(resultStack.getCount());
						bucketStack.shrink(1);
					} else if (outputSlotStack.isEmpty()) {
						this.items.set(1, resultStack);
						bucketStack.shrink(1);
					}
				}
			}
			
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.items.get(1).isEmpty() && !this.items.get(2).isEmpty() && !this.items.get(3).isEmpty()) {
				CoffeeRecipe coffeeRecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.COFFEE, this, this.world).orElse(null);
				if (!this.isActive() && this.canCook(coffeeRecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					this.cookTime++;
					this.waterTank.drain((int) WATER_PER_TICK, FluidAction.EXECUTE);
					flag2 = true;
				}

				if (this.isActive() && this.canCook(coffeeRecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					this.cookTime++;
					this.waterTank.drain((int) WATER_PER_TICK, FluidAction.EXECUTE);
					if (this.cookTime == this.cookTimeTotal) {
						this.cookTime = 0;
						this.cookTimeTotal = this.getCookTime();
						this.cookItem(coffeeRecipe);
						flag2 = true;
					}
				} else {
					this.cookTime = 0;
				}
			}

			if (flag != this.isActive())
				flag2 = true;
			
			if(flag2) {
				BlockState state = world.getBlockState(pos);
				state = state.with(CoffeeMachineBlock.ACTIVE, this.isActive())
						.with(CoffeeMachineBlock.CUP, cup);
				this.world.setBlockState(pos, state);
			}
		}
	}
	
	private int getCookTime() {
		if(world == null) return 200;
		return this.world.getRecipeManager().getRecipe(RecipeTypes.COFFEE, this, this.world)
				.map(CoffeeRecipe::getCookTime).orElse(200);
	}
	
	private boolean canCook(@Nullable CoffeeRecipe recipe) {
		final int waterUse = (int) (WATER_PER_TICK * (this.getCookTime()-this.cookTime));
		if (!this.items.get(3).isEmpty() 
				&& !this.items.get(2).isEmpty() 
				&& this.items.get(1).getCount() >= COFFEE_BEANS_PER_COFFEE
				&& getWaterAmount() >= waterUse && recipe != null) {
			ItemStack itemstack = recipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(4);
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

	private void cookItem(@Nullable CoffeeRecipe recipe) {
		if (recipe != null && this.canCook(recipe)) {
			ItemStack beansStack = this.items.get(1);
			ItemStack itemstack = this.items.get(2);
			ItemStack itemstack1 = this.items.get(3);
			ItemStack itemstack2 = recipe.getRecipeOutput();
			ItemStack itemstack3 = this.items.get(4);
			if (itemstack3.isEmpty()) {
				this.items.set(4, itemstack2.copy());
			} else if (itemstack3.getItem() == itemstack2.getItem()) {
				itemstack3.grow(itemstack2.getCount());
			}
			
			itemstack.shrink(1);
			if(itemstack1.hasContainerItem() && itemstack1.getMaxStackSize() == 1)
				this.items.set(3, itemstack1.getContainerItem());
			else
				itemstack1.shrink(1);
			beansStack.shrink(COFFEE_BEANS_PER_COFFEE);
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("WaterTank", this.waterTank.writeToNBT(new CompoundNBT()));
		CompoundNBT machineCompound = new CompoundNBT();
		machineCompound.putInt("CookTime", this.cookTime);
		machineCompound.putInt("CookTimeTotal",this.cookTimeTotal);
		machineCompound.putInt("WaterAmount", this.waterTank.getFluidAmount());
		compound.put("Machine", machineCompound);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		CompoundNBT machineCompound = compound.getCompound("Machine");
		this.waterTank.readFromNBT(compound);
		this.waterTank.setFluid(new FluidStack(this.waterTank.getFluid(), machineCompound.getInt("WaterAmount")));
		this.cookTime = machineCompound.getInt("CookTime");
		this.cookTimeTotal = machineCompound.getInt("CookTimeTotal");
	}
	
	public FluidTank getWaterTank() {
		return waterTank;
	}

	public int getWaterAmount() {
		return this.waterTank.getFluidAmount();
	}
	
	public int getMaxWaterAmount() {
		return this.waterTank.getCapacity();
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
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

		if (!flag) {
			cookTime = 0;
			cookTimeTotal = getCookTime();
			this.markDirty();
		}

	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, Direction arg2) {
		return true;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, Direction arg2) {
		return this.isItemValidForSlot(arg0, arg1);
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_BOTTOM;
		} else {
			return side == Direction.UP ? SLOTS_TOP : SLOTS_SIDES;
		}
	}

	@Nonnull
	@Override
	public Container createMenu(int windowId, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
		return new CoffeeMachineContainer(windowId, playerInventory, this, this.fields);
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.coffee_machine");
	}

	private LazyOptional<? extends IFluidHandler> fluidHandler = LazyOptional.of(() -> this.waterTank);
	
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return fluidHandler.cast();
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		this.fluidHandler.invalidate();
	}

	@Nonnull
	@Override
	protected Container createMenu(int id, @Nonnull PlayerInventory player) {
		return createMenu(id, player, player.player);
	}

}
