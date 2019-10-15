package tk.themcbros.uselessmod.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

public class FluidUtils {

	@Nullable
	public static IFluidHandler getFluid(IBlockReader world, BlockPos pos) {
		return getFluid(world, pos, null);
	}

	@Nullable
	public static IFluidHandler getFluid(IBlockReader world, BlockPos pos, @Nullable Direction side) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null) return null;
		return tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).orElse(null);
	}

}
