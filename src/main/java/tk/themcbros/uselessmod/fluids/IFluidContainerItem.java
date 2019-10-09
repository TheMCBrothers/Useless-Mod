package tk.themcbros.uselessmod.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

/**
 * @author TheMCLoveMan
 */
public interface IFluidContainerItem {

	FluidStack getFluid(ItemStack container);

	int getCapacity(ItemStack container);

	int fill(ItemStack container, FluidStack resource, IFluidHandler.FluidAction action);

	FluidStack drain(ItemStack container, FluidStack resource, IFluidHandler.FluidAction action);

	FluidStack drain(ItemStack container, int maxDrain, IFluidHandler.FluidAction action);

	default boolean isFluidValid(ItemStack container, FluidStack stack) {
		return true;
	}

}
