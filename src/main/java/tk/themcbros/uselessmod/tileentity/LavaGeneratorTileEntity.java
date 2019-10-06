package tk.themcbros.uselessmod.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import tk.themcbros.uselessmod.container.LavaGeneratorContainer;
import tk.themcbros.uselessmod.lists.ModTileEntities;
import tk.themcbros.uselessmod.machine.MachineTier;

public class LavaGeneratorTileEntity extends MachineTileEntity {

	public static final int TICKS_PER_MB = 10; // TODO make config entry
	
	private FluidTank lavaTank = new FluidTank(4000, fluidStack -> fluidStack.getFluid() == Fluids.LAVA);
	private int burnTime;
	private int totalBurnTime;
	
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
				LavaGeneratorTileEntity.this.burnTime = value;
				break;
			case 1:
				LavaGeneratorTileEntity.this.totalBurnTime = value;
				break;
			case 2:
				LavaGeneratorTileEntity.this.lavaTank.setFluid(new FluidStack(lavaTank.getFluid(), value));
				break;
			case 3:
				LavaGeneratorTileEntity.this.lavaTank.setCapacity(value);
				break;
			case 4:
				FluidStack stack = LavaGeneratorTileEntity.this.lavaTank.getFluid();
				LavaGeneratorTileEntity.this.lavaTank.setFluid(new FluidStack(Registry.FLUID.getByValue(value), stack.getAmount(), stack.getTag()));

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
				return LavaGeneratorTileEntity.this.totalBurnTime;
			case 2:
				return LavaGeneratorTileEntity.this.lavaTank.getFluidAmount();
			case 3:
				return LavaGeneratorTileEntity.this.lavaTank.getCapacity();
			case 4:
				return Registry.FLUID.getId(LavaGeneratorTileEntity.this.lavaTank.getFluid().getRawFluid());
			case 5:
				return LavaGeneratorTileEntity.this.energyStorage.getEnergyStored();
			case 6:
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
		this.lavaTank.writeToNBT(compound);
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		this.lavaTank.readFromNBT(compound);
		super.read(compound);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction side) {
		return index == 1;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, Direction side) {
		return index == 0;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return side == Direction.DOWN ? new int[] {} : new int[] { 0, 1 };
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.items.set(index, stack);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new LavaGeneratorContainer(id, player, this, this.fields);
	}

}
