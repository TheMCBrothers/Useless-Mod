package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import tk.themcbros.uselessmod.tileentity.FluidTankTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTankBlock extends Block {

	public FluidTankBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, @Nonnull BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())) {
				worldIn.notifyBlockUpdate(pos, state, state, 3);
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FluidTankTileEntity();
	}

	@Override
	public boolean isNormalCube(BlockState p_220081_1_, IBlockReader p_220081_2_, BlockPos p_220081_3_) {
		return false;
	}

}
