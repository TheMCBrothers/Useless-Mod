package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class CoffeeTableBlock extends Block implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty TALL = BooleanProperty.create("tall");
	
	private final VoxelShape SHAPE;
	private final VoxelShape SHAPE_TALL;
	
	public CoffeeTableBlock(Properties builder) {
		super(builder);
		SHAPE = this.generateShape();
		SHAPE_TALL = this.generateTallShape();
	}
	
	private VoxelShape generateShape() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0, 0.375, 0, 1, 0.5, 1)); // PLATE
		shapes.add(VoxelShapes.create(0.031, 0, 0.031, 0.156, 0.375, 0.156)); // LEGNW
		shapes.add(VoxelShapes.create(0.844, 0, 0.031, 0.969, 0.375, 0.156)); // LEGNE
		shapes.add(VoxelShapes.create(0.844, 0, 0.844, 0.969, 0.375, 0.969)); // LEGSE
		shapes.add(VoxelShapes.create(0.031, 0, 0.844, 0.156, 0.375, 0.969)); // LEGSW

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}
	
	private VoxelShape generateTallShape() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0, 0.875, 0, 1, 1, 1)); // PLATE
		shapes.add(VoxelShapes.create(0.031, 0, 0.031, 0.156, 0.875, 0.156)); // LEGNW
		shapes.add(VoxelShapes.create(0.844, 0, 0.031, 0.969, 0.875, 0.156)); // LEGNE
		shapes.add(VoxelShapes.create(0.844, 0, 0.844, 0.969, 0.875, 0.969)); // LEGSE
		shapes.add(VoxelShapes.create(0.031, 0, 0.844, 0.156, 0.875, 0.969)); // LEGSW

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.get(TALL) ? SHAPE_TALL : SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.get(TALL) ? SHAPE_TALL : SHAPE;
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.get(TALL) ? SHAPE_TALL : SHAPE;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, TALL);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getPos();
		BlockState blockstate = context.getWorld().getBlockState(blockpos);
		IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
		if (blockstate.getBlock() == this) {
			blockstate = blockstate.with(TALL, Boolean.valueOf(true));
		} else {
			blockstate = this.getDefaultState().with(TALL, Boolean.valueOf(false));
		}
		return blockstate.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return (useContext.getItem().getItem() == this.asItem() && !state.get(TALL));
	}

	@Override
	public boolean func_220074_n(BlockState state) {
		return false;
	}
	
	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return stateIn;
	}
	
}
