package tk.themcbros.uselessmod.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tk.themcbros.uselessmod.blocks.CoffeeMachineBlock;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.CoffeeMachineContainer;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class CoffeeMachineTileEntity extends TileEntity implements ITickableTileEntity, ISidedInventory, INamedContainerProvider {

	public static final int RF_PER_TICK = MachineConfig.coffee_machine_rf_per_tick.get();
	private static final double WATER_PER_TICK = MachineConfig.coffee_machine_water_per_tick.get();
	private static final int COFFEE_BEANS_PER_COFFEE = MachineConfig.coffee_machine_coffee_beans_per_coffee.get();
	private static final int WATER_CAPACITY = MachineConfig.coffee_machine_water_capacity.get();
	private static final int COFFEE_BEANS_CAPACITY = MachineConfig.coffee_machine_coffee_beans_capacity.get();
	
	private static final int[] SLOTS_TOP = new int[] { 2,3,5 };
	private static final int[] SLOTS_BOTTOM = new int[] { 4 };
	private static final int[] SLOTS_SIDES = new int[] { 0,1,2,3,5 };
	
	private NonNullList<ItemStack> coffeeMachineItemStacks = NonNullList.withSize(6, ItemStack.EMPTY);
	
	private CustomEnergyStorage energyStorage;
	
	private int waterAmount;
	private int coffeeBeansAmount;
	
	private int maxWaterAmount = WATER_CAPACITY;
	private int maxCoffeeBeansAmount = COFFEE_BEANS_CAPACITY;
	
	private int cookTime;
	private int cookTimeTotal;
	
	private IIntArray fields = new IIntArray() {
		@Override
		public int size() {
			return 8;
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
				CoffeeMachineTileEntity.this.waterAmount = value;
				break;
			case 3:
				CoffeeMachineTileEntity.this.maxWaterAmount = value;
				break;
			case 4:
				CoffeeMachineTileEntity.this.coffeeBeansAmount = value;
				break;
			case 5:
				CoffeeMachineTileEntity.this.maxCoffeeBeansAmount = value;
				break;
			case 6:
				CoffeeMachineTileEntity.this.cookTime = value;
				break;
			case 7:
				CoffeeMachineTileEntity.this.cookTimeTotal = value;
				break;

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
				return CoffeeMachineTileEntity.this.waterAmount;
			case 3:
				return CoffeeMachineTileEntity.this.maxWaterAmount;
			case 4:
				return CoffeeMachineTileEntity.this.coffeeBeansAmount;
			case 5:
				return CoffeeMachineTileEntity.this.maxCoffeeBeansAmount;
			case 6:
				return CoffeeMachineTileEntity.this.cookTime;
			case 7:
				return CoffeeMachineTileEntity.this.cookTimeTotal;
			default:
				return 0;
			}
		}
	};
	
	public CoffeeMachineTileEntity() {
		super(ModTileEntities.COFFEE_MACHINE);
		
		this.energyStorage = new CustomEnergyStorage(8000, 100, 0);
	}
	
	private boolean isActive() {
		return this.getEnergyStored() >= RF_PER_TICK && cookTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isActive();
		boolean flag1 = this.getBlockState().get(CoffeeMachineBlock.CUP);
		boolean flag2 = false;
		boolean cup = (this.coffeeMachineItemStacks.get(2).getItem() == ModItems.CUP || this.coffeeMachineItemStacks.get(4).getItem() == ModItems.COFFEE_CUP);
		if((flag) && (!flag1)) flag2 = true;
		
		if(this.isActive()) this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
		
		if(!world.isRemote) {
			
			if((cup && !flag1) || (!cup && flag1)) flag2 = true;
			
			if(this.coffeeMachineItemStacks.get(0).getItem() == Items.WATER_BUCKET && waterAmount <= maxWaterAmount-Fluid.BUCKET_VOLUME) {
				this.coffeeMachineItemStacks.set(0, new ItemStack(Items.BUCKET));
				waterAmount += Fluid.BUCKET_VOLUME;
				this.markDirty();
			}
			if(this.coffeeMachineItemStacks.get(1).getItem() == ModItems.COFFEE_BEANS && coffeeBeansAmount <= maxCoffeeBeansAmount-1) {
				this.coffeeMachineItemStacks.get(1).shrink(1);
				coffeeBeansAmount += 1;
				this.markDirty();
			}
			
			if (this.isActive() || this.energyStorage.getEnergyStored() >= RF_PER_TICK && !this.coffeeMachineItemStacks.get(0).isEmpty()) {
				CoffeeRecipe coffeeRecipe = this.world.getRecipeManager().getRecipe(RecipeTypes.COFFEE, this, this.world).orElse(null);
				if (!this.isActive() && this.canCook(coffeeRecipe)) {
					this.energyStorage.modifyEnergyStored(-RF_PER_TICK);
					cookTime++;
					this.waterAmount -= WATER_PER_TICK;
				}

				if (this.isActive() && this.canCook(coffeeRecipe)) {
					this.cookTime += 1;
					this.waterAmount -= WATER_PER_TICK;
					if (this.cookTime == this.cookTimeTotal) {
						this.cookTime = 0;
						this.cookTimeTotal = this.getCookTime();
						this.cookItem(coffeeRecipe);
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			}

			if (flag != this.isActive()) {
				flag1 = true;
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(CoffeeMachineBlock.ACTIVE, Boolean.valueOf(this.isActive())), 3);
			}
			
			if(flag2) {
				BlockState state = world.getBlockState(pos);
				state = state.with(CoffeeMachineBlock.ACTIVE, Boolean.valueOf(this.isActive()))
						.with(CoffeeMachineBlock.CUP, Boolean.valueOf(cup));
				this.world.setBlockState(pos, state);
			}
		}
	}
	
	protected int getCookTime() {
		return this.world.getRecipeManager().getRecipe(RecipeTypes.COFFEE, this, this.world)
				.map(CoffeeRecipe::getCookTime).orElse(200);
	}
	
	private boolean canCook(@Nullable CoffeeRecipe recipe) {
		final int waterUse = (int) (WATER_PER_TICK * (this.getCookTime()-this.cookTime));
		if (!this.coffeeMachineItemStacks.get(3).isEmpty() 
				&& !this.coffeeMachineItemStacks.get(2).isEmpty() 
				&& coffeeBeansAmount >= COFFEE_BEANS_PER_COFFEE
				&& waterAmount >= waterUse && recipe != null) {
			ItemStack itemstack = recipe.getRecipeOutput();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.coffeeMachineItemStacks.get(4);
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

	private void cookItem(@Nullable CoffeeRecipe recipe) {
		if (recipe != null && this.canCook(recipe)) {
			ItemStack itemstack = this.coffeeMachineItemStacks.get(2);
			ItemStack itemstack1 = this.coffeeMachineItemStacks.get(3);
			ItemStack itemstack2 = recipe.getRecipeOutput();
			ItemStack itemstack3 = this.coffeeMachineItemStacks.get(4);
			if (itemstack3.isEmpty()) {
				this.coffeeMachineItemStacks.set(4, itemstack2.copy());
			} else if (itemstack3.getItem() == itemstack2.getItem()) {
				itemstack3.grow(itemstack2.getCount());
			}
			
			itemstack.shrink(1);
			if(itemstack1.hasContainerItem() && itemstack1.getMaxStackSize() == 1)
				this.coffeeMachineItemStacks.set(3, itemstack1.getContainerItem());
			else
				itemstack1.shrink(1);
			this.coffeeBeansAmount -= COFFEE_BEANS_PER_COFFEE;
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		ItemStackHelper.saveAllItems(compound, coffeeMachineItemStacks);
		compound.put("Energy", this.energyStorage.serializeNBT());
		CompoundNBT machineCompound = new CompoundNBT();
		machineCompound.putInt("WaterAmount", this.waterAmount);
		machineCompound.putInt("MilkAmount",this. coffeeBeansAmount);
		machineCompound.putInt("CookTime", this.cookTime);
		machineCompound.putInt("CookTimeTotal",this. cookTimeTotal);
		compound.put("Machine", machineCompound);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.coffeeMachineItemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, coffeeMachineItemStacks);
		this.energyStorage.deserializeNBT(compound.getCompound("Energy"));
		CompoundNBT machineCompound = compound.getCompound("Machine");
		this.waterAmount = machineCompound.getInt("WaterAmount");
		this.coffeeBeansAmount = machineCompound.getInt("MilkAmount");
		this.cookTime = machineCompound.getInt("CookTime");
		this.cookTimeTotal = machineCompound.getInt("CookTimeTotal");
	}

	public int getWaterAmount() {
		return this.waterAmount;
	}
	
	public int getMilkAmount() {
		return this.coffeeBeansAmount;
	}
	
	public int getMaxWaterAmount() {
		return this.maxWaterAmount;
	}
	
	public int getMaxMilkAmount() {
		return this.maxCoffeeBeansAmount;
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		return ItemStackHelper.getAndSplit(this.coffeeMachineItemStacks, arg0, arg1);
	}

	@Override
	public int getSizeInventory() {
		return this.coffeeMachineItemStacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.coffeeMachineItemStacks.get(index);
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.coffeeMachineItemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
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
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.coffeeMachineItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.coffeeMachineItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.coffeeMachineItemStacks.set(index, stack);
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
	public void clear() {
		this.coffeeMachineItemStacks.clear();
	}

	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, Direction arg2) {
		return true;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, Direction arg2) {
		return this.isItemValidForSlot(arg0, arg1);
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
	public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
		return new CoffeeMachineContainer(windowId, playerInventory, this, this.fields);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.uselessmod.coffee_machine");
	}
	
	private LazyOptional<? extends IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper
			.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	private LazyOptional<CustomEnergyStorage> energyHandler = LazyOptional.of(() -> this.energyStorage);
	
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
		if(capability == CapabilityEnergy.ENERGY) {
			return energyHandler.cast();
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		this.energyHandler.invalidate();
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

	public int getEnergyStored() {
		return this.energyStorage.getEnergyStored();
	}
	
	public int getMaxEnergyStored() {
		return this.energyStorage.getMaxEnergyStored();
	}

}
