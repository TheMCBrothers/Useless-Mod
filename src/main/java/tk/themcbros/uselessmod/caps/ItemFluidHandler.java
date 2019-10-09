package tk.themcbros.uselessmod.caps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import tk.themcbros.uselessmod.fluids.IFluidContainerItem;

import javax.annotation.Nonnull;

public class ItemFluidHandler implements IFluidHandlerItem {

	public ItemStack itemStack;
	public IFluidContainerItem fluidItem;

	public ItemFluidHandler(IFluidContainerItem fluidItem, ItemStack container) {
		this.itemStack = container;
		this.fluidItem = fluidItem;
	}

	@Nonnull
	@Override
	public ItemStack getContainer() {
		return this.itemStack;
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Nonnull
	@Override
	public FluidStack getFluidInTank(int tank) {
		return this.fluidItem.getFluid(this.itemStack);
	}

	@Override
	public int getTankCapacity(int tank) {
		return this.fluidItem.getCapacity(this.itemStack);
	}

	@Override
	public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
		return this.fluidItem.isFluidValid(this.itemStack, stack);
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		return this.fluidItem.fill(this.itemStack, resource, action);
	}

	@Nonnull
	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		return this.fluidItem.drain(this.itemStack, resource, action);
	}

	@Nonnull
	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		return this.fluidItem.drain(this.itemStack, maxDrain, action);
	}
}
