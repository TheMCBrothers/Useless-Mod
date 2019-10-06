package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

public class EnergyCableBlock extends Block implements IWaterLoggable {

	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape CENTER_SHAPE = Block.makeCuboidShape(5, 5, 5, 11, 11, 11);
	private static final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(5, 5, 0, 11, 11, 5);
	private static final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(5, 5, 11, 11, 11, 16);
	private static final VoxelShape EAST_SHAPE = Block.makeCuboidShape(11, 5, 5, 16, 11, 11);
	private static final VoxelShape WEST_SHAPE = Block.makeCuboidShape(0, 5, 5, 5, 11, 11);
	private static final VoxelShape UP_SHAPE = Block.makeCuboidShape(5, 11, 5, 11, 16, 11);
	private static final VoxelShape DOWN_SHAPE = Block.makeCuboidShape(5, 0, 5, 11, 5, 11);
	
	public EnergyCableBlock(Properties properties) {
		super(properties);
		
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(NORTH, Boolean.FALSE)
				.with(SOUTH, Boolean.FALSE)
				.with(EAST, Boolean.FALSE)
				.with(WEST, Boolean.FALSE)
				.with(UP, Boolean.FALSE)
				.with(DOWN, Boolean.FALSE)
				.with(WATERLOGGED, Boolean.FALSE));
	}
	
	public static VoxelShape getCableShape(BlockState state) {
		List<VoxelShape> shapes = new ArrayList<>();
		if(state.has(NORTH) && state.get(NORTH)) shapes.add(NORTH_SHAPE);
		if(state.has(SOUTH) && state.get(SOUTH)) shapes.add(SOUTH_SHAPE);
		if(state.has(EAST) && state.get(EAST)) shapes.add(EAST_SHAPE);
		if(state.has(WEST) && state.get(WEST)) shapes.add(WEST_SHAPE);
		if(state.has(UP) && state.get(UP)) shapes.add(UP_SHAPE);
		if(state.has(DOWN) && state.get(DOWN)) shapes.add(DOWN_SHAPE);
		
		VoxelShape result = CENTER_SHAPE;
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getCableShape(state);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return getCableShape(state);
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return getCableShape(state);
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, WATERLOGGED);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new EnergyCableTileEntity();
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return this.getDefaultState().with(WATERLOGGED,
				Boolean.valueOf(ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		
		TileEntity tileEntity = worldIn.getTileEntity(currentPos);
		if(tileEntity instanceof EnergyCableTileEntity) {
			((EnergyCableTileEntity) tileEntity).updateNetwork();
			((EnergyCableTileEntity) tileEntity).updateConnections();
		}
 
		return stateIn;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof EnergyCableTileEntity) {
			((EnergyCableTileEntity) tileEntity).updateNetwork();
			((EnergyCableTileEntity) tileEntity).updateConnections();
		}
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	
}
