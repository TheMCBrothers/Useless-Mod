package tk.themcbros.uselessmod.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class EnergyUtils {

	@Nullable
	public static IEnergyStorage getEnergy(IBlockReader world, BlockPos pos) {
		return getEnergy(world, pos, null);
	}

	@Nullable
	public static IEnergyStorage getEnergy(IBlockReader world, BlockPos pos, @Nullable Direction side) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null) return null;
		return tileEntity.getCapability(CapabilityEnergy.ENERGY, side).orElse(null);
	}

}
