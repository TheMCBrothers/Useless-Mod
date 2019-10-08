package tk.themcbros.uselessmod.helper;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class EnergyUtils {

	@Nullable
	public static IEnergyStorage getEnergy(IBlockReader world, BlockPos pos) {
		return world.getTileEntity(pos).getCapability(CapabilityEnergy.ENERGY).orElse(null);
	}

	@Nullable
	public static IEnergyStorage getEnergy(IBlockReader world, BlockPos pos, Direction side) {
		return world.getTileEntity(pos).getCapability(CapabilityEnergy.ENERGY, side).orElse(null);
	}

}
