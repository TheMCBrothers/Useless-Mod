package tk.themcbros.uselessmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessTallGrassBlock extends TallGrassBlock {

	public UselessTallGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == ModBlocks.USELESS_GRASS_BLOCK || block == ModBlocks.USELESS_DIRT;
	}
	
	@Override
	public void func_225535_a_(ServerWorld worldIn, Random random, BlockPos pos, BlockState state) { // grow()
		DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == ModBlocks.USELESS_FERN ? ModBlocks.LARGE_USELESS_FERN
				: ModBlocks.TALL_USELESS_GRASS);
		if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
			doubleplantblock.placeAt(worldIn, pos, 2);
		}
	}
	
}
