package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeSeedsBlock extends CropsBlock {

	public CoffeeSeedsBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		BlockState stateTop = worldIn.getBlockState(pos.up());
		return ((state.getBlock() == Blocks.GRASS_BLOCK || state.getBlock() == ModBlocks.USELESS_GRASS_BLOCK) && stateTop.getBlock() instanceof CropsBlock && stateTop.get(CropsBlock.AGE) == 7) || super.isValidGround(state, worldIn, pos);
	}
	
	@Override
	protected IItemProvider getSeedsItem() {
		return ModItems.COFFEE_SEEDS;
	}
}