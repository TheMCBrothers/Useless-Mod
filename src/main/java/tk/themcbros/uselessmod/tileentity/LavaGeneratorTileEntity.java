package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.container.LavaGeneratorContainer;
import tk.themcbros.uselessmod.helper.TextUtils;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;

import javax.annotation.Nonnull;

public class LavaGeneratorTileEntity extends MachineTileEntity {

	public static final int TICKS_PER_MB = MachineConfig.lava_generator_ticks_per_mb.get();
	public static final int TANK_CAPACITY = MachineConfig.lava_generator_tank_capacity.get();
	public static final int RF_PER_TICK = MachineConfig.lava_generator_rf_per_tick.get();
	
	private FluidTank lavaTank = new FluidTank(TANK_CAPACITY, fluidStack -> fluidStack.getFluid().isIn(FluidTags.LAVA));
	private int burnTime;
	
	@SuppressWarnings("deprecation")
	private IIntArray fields = new IIntArray() {
		
		@Override
		public int size() {
			return 6;
		}
		
		@Override
		public void set(int index, int value) {
			switch (index) {
			case 0:
				LavaGeneratorTileEntity.this.burnTime = value;
				break;
			case 1:
				LavaGeneratorTileEntity.this.lavaTank.setFluid(new FluidStack(lavaTank.getFluid(), value));
				break;
			case 2:
				LavaGeneratorTileEntity.this.lavaTank.setCapacity(value);
				break;
			case 3:
				FluidStack stack = LavaGeneratorTileEntity.this.lavaTank.getFluid();
				LavaGeneratorTileEntity.this.lavaTank.setFluid(new FluidStack(Registry.FLUID.getByValue(value), stack.getAmount(), stack.getTag()));
				break;
			case 4:
				LavaGeneratorTileEntity.this.energyStorage.setEnergyStored(value);
				break;
			case 5:
				LavaGeneratorTileEntity.this.energyStorage.setCapacity(value);
				break;

			default:
				break;
			}
		}
		
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return LavaGeneratorTileEntity.this.burnTime;
			case 1:
				return LavaGeneratorTileEntity.this.lavaTank.getFluidAmount();
			case 2:
				return LavaGeneratorTileEntity.this.lavaTank.getCapacity();
			case 3:
				return Registry.FLUID.getId(LavaGeneratorTileEntity.this.lavaTank.getFluid().getRawFluid());
			case 4:
				return LavaGeneratorTileEntity.this.energyStorage.getEnergyStored();
			case 5:
				return LavaGeneratorTileEntity.this.energyStorage.getMaxEnergyStored();
			default:
				return 0;
			}
		}
	};
	
	public LavaGeneratorTileEntity() {
		super(ModTileEntities.LAVA_GENERATOR, MachineTier.STANDARD, true);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("Tank", this.lavaTank.writeToNBT(new CompoundNBT()));
		compound.putInt("BurnTime", this.burnTime);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		this.lavaTank.readFromNBT(compound.getCompound("Tank"));
		this.burnTime = compound.getInt("BurnTime");
		super.read(compound);
	}

	private void sendEnergy() {
		assert world != null;
		if(this.energyStorage.getEnergyStored() > 0) {
			for(Direction facing : Direction.values()) {
				TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(facing));
				if(tileEntity != null) {
					IEnergyStorage handler = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).orElse(null);
					if(handler != null && handler.canReceive()) {
						int accepted = handler.receiveEnergy(this.energyStorage.getEnergyStored(), false);
						this.energyStorage.modifyEnergyStored(-accepted);
						if(this.energyStorage.getEnergyStored() <= 0)
							break;
					}
				}
			}
			this.markDirty();
		}
	}

	@Override
	public void tick() {

		final ItemStack bucketStack = this.items.get(0);
		if (!bucketStack.isEmpty()) {
			FluidActionResult result = FluidUtil.tryEmptyContainer(bucketStack, this.lavaTank, FluidAttributes.BUCKET_VOLUME, null, true);
			if (result.isSuccess()) {
				ItemStack outputSlotStack = this.items.get(1);
				ItemStack resultStack = result.getResult();
				if (resultStack.isItemEqual(outputSlotStack) && resultStack.getMaxStackSize() > 1 &&
						outputSlotStack.getCount() <= outputSlotStack.getMaxStackSize() - resultStack.getCount()) {
					outputSlotStack.grow(resultStack.getCount());
					bucketStack.shrink(1);
				} else if (outputSlotStack.isEmpty()) {
					this.items.set(1, resultStack);
					bucketStack.shrink(1);
				}
			}
		}

		if (this.canRun()) {
			if (this.burnTime <= 0 && this.hasFuel()) {
				consumeFuel();
				sendUpdate(this.getActiveState(), true);
			}
		}

		if (this.burnTime > 0) {
			--this.burnTime;
			this.energyStorage.modifyEnergyStored(RF_PER_TICK);
		} else {
			this.sendUpdate(this.getInactiveState(), false);
		}

		this.sendEnergy();
	}

	private boolean hasFuel() {
		return this.lavaTank.getFluidAmount() > 0;
	}

	private boolean canRun() {
		return this.world != null && this.energyStorage.getEnergyStored() < this.energyStorage.getMaxEnergyStored();
	}

	private void consumeFuel() {
		this.lavaTank.drain(1, IFluidHandler.FluidAction.EXECUTE);
		this.burnTime = TICKS_PER_MB;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return !stack.isEmpty() && index == 0 && stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY)
				.map(handler -> handler.getFluidInTank(0).getFluid().isIn(FluidTags.LAVA)).orElse(false);
	}

	@Override
	public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull Direction side) {
		return index == 1;
	}

	@Override
	public boolean canInsertItem(int index, @Nonnull ItemStack stack, Direction side) {
		return this.isItemValidForSlot(index, stack);
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull Direction side) {
		return side == Direction.DOWN ? new int[] {1} : new int[] {0};
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
		this.items.set(index, stack);
	}

	@Nonnull
	@Override
	protected Container createMenu(int id, @Nonnull PlayerInventory player) {
		return new LavaGeneratorContainer(id, player, this, this.fields);
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return TextUtils.translate("container", "lava_generator");
	}

	private LazyOptional<? extends IFluidHandler> lavaHandler = LazyOptional.of(() -> this.lavaTank);

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
		if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return this.lavaHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	protected void invalidateCaps() {
		this.lavaHandler.invalidate();
		super.invalidateCaps();
	}
}
