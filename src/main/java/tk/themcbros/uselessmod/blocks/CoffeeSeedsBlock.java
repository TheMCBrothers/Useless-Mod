package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.lists.ModItems;

public class CoffeeSeedsBlock extends UselessCropsBlock {
	
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 2.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 4.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D),
			Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D) };

	public CoffeeSeedsBlock(Properties properties, boolean wild) {
		super(properties, wild);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[getAge(state)];
	}
	
	@Override
	protected IItemProvider getSeedsItem() {
		return ModItems.COFFEE_SEEDS;
	}
	
}