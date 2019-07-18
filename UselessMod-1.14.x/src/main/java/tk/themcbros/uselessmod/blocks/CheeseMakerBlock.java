package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class CheeseMakerBlock extends HorizontalBlock implements ILiquidContainer, IBucketPickupHandler {

	private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");
	private static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	private final VoxelShape SHAPE;// = Block.makeCuboidShape(2D, 0D, 2D, 14D, 7D, 14D);

	public CheeseMakerBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH)
				.with(WATERLOGGED, Boolean.valueOf(false)).with(ACTIVE, Boolean.valueOf(false)).with(LAVALOGGED, Boolean.valueOf(false)));
		SHAPE = this.generateShape();
	}

	private VoxelShape generateShape() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(Block.makeCuboidShape(2D, 0D, 2D, 14D, 1D, 14D)); // BOTTOM
		shapes.add(Block.makeCuboidShape(2D, 1D, 2D, 14D, 7D, 3D)); // WALL
		shapes.add(Block.makeCuboidShape(2D, 1D, 13D, 14D, 7D, 14D)); // WALL
		shapes.add(Block.makeCuboidShape(2D, 1D, 3D, 3D, 7D, 13D)); // WALL
		shapes.add(Block.makeCuboidShape(13D, 1D, 3D, 14D, 7D, 13D)); // WALL

//		shapes.add(Block.makeCuboidShape(7D, 0D, 14, 9D, 10D, 16D)); // ARM1
//		// Skipped 'Arm2', as it has rotation
//		shapes.add(Block.makeCuboidShape(7D, 11.536D, 7.456D, 9D, 13.536D, 12.464D)); // ARM3

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	@Override
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}

	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}

	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			boolean flag = worldIn.isBlockPowered(pos);
			if (flag != state.get(ACTIVE)) {

				worldIn.setBlockState(pos, state.with(ACTIVE, Boolean.valueOf(flag)), 2);
				if (state.get(WATERLOGGED)) {
					worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
				} else if(state.get(LAVALOGGED)) {
					worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.LAVA, Fluids.LAVA.getTickRate(worldIn));
				}
			}
		}
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState iblockstate = this.getDefaultState();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

		Direction enumfacing = context.getPlacementHorizontalFacing();
		iblockstate = iblockstate.with(HORIZONTAL_FACING, enumfacing.getOpposite());

		if (context.getWorld().isBlockPowered(context.getPos())) {
			iblockstate = iblockstate.with(ACTIVE, Boolean.valueOf(true));
		}

		return iblockstate.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(LAVALOGGED, ifluidstate.getFluid() == Fluids.LAVA);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, ACTIVE, WATERLOGGED, LAVALOGGED);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return func_220055_a(worldIn, pos.down(), Direction.UP);
	}

	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else if (state.get(LAVALOGGED)) {
			worldIn.setBlockState(pos, state.with(LAVALOGGED, Boolean.valueOf(false)), 3);
			return Fluids.LAVA;
		} else {
			return Fluids.EMPTY;
		}
	}

	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		if(state.get(WATERLOGGED)) return Fluids.WATER.getStillFluidState(false);
		if(state.get(LAVALOGGED)) return Fluids.LAVA.getStillFluidState(false);
		return super.getFluidState(state);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return (!state.get(WATERLOGGED) && fluidIn == Fluids.WATER) || (!state.get(LAVALOGGED) && fluidIn == Fluids.LAVA);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(),
						fluidStateIn.getFluid().getTickRate(worldIn));
			}

			return true;
		} else if (!state.get(LAVALOGGED) && fluidStateIn.getFluid() == Fluids.LAVA) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(LAVALOGGED, Boolean.valueOf(true)), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(),
						fluidStateIn.getFluid().getTickRate(worldIn));
			}

			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState,
			IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		} else if (stateIn.get(LAVALOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.LAVA, Fluids.LAVA.getTickRate(worldIn));
		}

		return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState()
				: super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

}
