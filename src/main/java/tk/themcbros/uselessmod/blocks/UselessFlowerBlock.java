package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessFlowerBlock extends FlowerBlock {

	public UselessFlowerBlock(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return super.isValidGround(state, worldIn, pos) || block == ModBlocks.USELESS_GRASS_BLOCK || block == ModBlocks.USELESS_DIRT;
	}

}
