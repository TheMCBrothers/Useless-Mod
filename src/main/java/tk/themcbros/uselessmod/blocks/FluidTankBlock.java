package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;

public class FluidTankBlock extends Block {

	public FluidTankBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, @Nonnull BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			if (FluidUtil.interactWithFluidHandler(player, handIn, worldIn, pos, hit.getFace())) {
				worldIn.notifyBlockUpdate(pos, state, state, 3);
			}
		}
		return true;
	}

	@Override
	public boolean isNormalCube(BlockState p_220081_1_, IBlockReader p_220081_2_, BlockPos p_220081_3_) {
		return false;
	}

	@Override
	public boolean isSolid(BlockState p_200124_1_) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
