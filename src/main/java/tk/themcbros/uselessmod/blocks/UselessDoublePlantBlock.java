package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessDoublePlantBlock extends DoublePlantBlock {

	public UselessDoublePlantBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == ModBlocks.USELESS_GRASS_BLOCK || block == ModBlocks.USELESS_DIRT;
	}

}
