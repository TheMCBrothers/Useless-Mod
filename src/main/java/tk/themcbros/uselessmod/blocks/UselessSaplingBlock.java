package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessSaplingBlock extends SaplingBlock {

	public UselessSaplingBlock(Tree p_i48337_1_, Properties properties) {
		super(p_i48337_1_, properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == ModBlocks.USELESS_GRASS_BLOCK || state.getBlock() == ModBlocks.USELESS_DIRT || super.isValidGround(state, worldIn, pos);
	}

}
