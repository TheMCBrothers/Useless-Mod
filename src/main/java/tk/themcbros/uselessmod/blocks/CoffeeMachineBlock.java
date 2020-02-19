package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import tk.themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineBlock extends Block implements IWaterLoggable {
	
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty CUP = BooleanProperty.create("cup");
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private final VoxelShape RAYTRACE_SHAPE = Block.makeCuboidShape(3D, 0D, 3D, 13D, 15D, 13D);
	
	private final VoxelShape SHAPE_NORTH;
	private final VoxelShape SHAPE_SOUTH;
	private final VoxelShape SHAPE_EAST;
	private final VoxelShape SHAPE_WEST;
	private final VoxelShape SHAPE_CUP_NORTH;
	private final VoxelShape SHAPE_CUP_SOUTH;
	private final VoxelShape SHAPE_CUP_EAST;
	private final VoxelShape SHAPE_CUP_WEST;

	public CoffeeMachineBlock(Properties properties) {
		super(properties);
		
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(CUP, Boolean.FALSE)
				.with(ACTIVE, Boolean.FALSE)
				.with(WATERLOGGED, Boolean.FALSE));
		
		SHAPE_NORTH = generateShapeNorth();
		SHAPE_SOUTH = generateShapeSouth();
		SHAPE_EAST = generateShapeEast();
		SHAPE_WEST = generateShapeWest();
		
		SHAPE_CUP_NORTH = generateCupShapeNorth();
		SHAPE_CUP_SOUTH = generateCupShapeSouth();
		SHAPE_CUP_EAST = generateCupShapeEast();
		SHAPE_CUP_WEST = generateCupShapeWest();
	}
	
	@Override
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return RAYTRACE_SHAPE;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if(!state.get(CUP)) {
			if(state.get(HORIZONTAL_FACING) == Direction.NORTH) return SHAPE_NORTH;
			else if(state.get(HORIZONTAL_FACING) == Direction.SOUTH) return SHAPE_SOUTH;
			else if(state.get(HORIZONTAL_FACING) == Direction.EAST) return SHAPE_EAST;
			else if(state.get(HORIZONTAL_FACING) == Direction.WEST) return SHAPE_WEST;
		} else {
			if(state.get(HORIZONTAL_FACING) == Direction.NORTH) return VoxelShapes.or(SHAPE_NORTH, SHAPE_CUP_NORTH).simplify();
			else if(state.get(HORIZONTAL_FACING) == Direction.SOUTH) return VoxelShapes.or(SHAPE_SOUTH, SHAPE_CUP_SOUTH).simplify();
			else if(state.get(HORIZONTAL_FACING) == Direction.EAST) return VoxelShapes.or(SHAPE_EAST, SHAPE_CUP_EAST).simplify();
			else if(state.get(HORIZONTAL_FACING) == Direction.WEST) return VoxelShapes.or(SHAPE_WEST, SHAPE_CUP_WEST).simplify();
		}
		return VoxelShapes.fullCube();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity instanceof CoffeeMachineTileEntity && player instanceof ServerPlayerEntity) {
				if(FluidUtil.interactWithFluidHandler(player, handIn, ((CoffeeMachineTileEntity) tileEntity).getWaterTank()))
					player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1f, 1f);
				else
					player.openContainer((INamedContainerProvider) tileEntity);
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CoffeeMachineTileEntity();
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, CUP, ACTIVE, WATERLOGGED);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
		return this.getDefaultState()
				.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!worldIn.isRemote) {
			boolean flag = worldIn.isBlockPowered(pos);
			if (flag != state.get(ACTIVE)) {

				worldIn.setBlockState(pos, state.with(ACTIVE, Boolean.valueOf(flag)), 2);
				if (state.get(WATERLOGGED)) {
					worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
				}
			}
		}
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return stateIn;
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
	}
	
	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state.with(HORIZONTAL_FACING, direction.rotate(state.get(HORIZONTAL_FACING)));
	}
	
	// SHAPES
	
	private VoxelShape generateShapeNorth() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.188, 0, 0.188, 0.812, 0.125, 0.812));
		shapes.add(VoxelShapes.create(0.188, 0.125, 0.688, 0.812, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.188, 0.562, 0.188, 0.812, 0.938, 0.688));
		shapes.add(VoxelShapes.create(0.438, 0.5, 0.375, 0.562, 0.562, 0.5));
		shapes.add(VoxelShapes.create(0.25, 0.625, 0.187, 0.75, 0.875, 0.188));
		shapes.add(VoxelShapes.create(0.312, 0.688, 0.186, 0.438, 0.812, 0.187));
		shapes.add(VoxelShapes.create(0.562, 0.688, 0.186, 0.688, 0.812, 0.187));
		shapes.add(VoxelShapes.create(0.25, 0.125, 0.25, 0.75, 0.126, 0.625));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateShapeSouth() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.188, 0, 0.188, 0.812, 0.125, 0.812));
		shapes.add(VoxelShapes.create(0.188, 0.125, 0.188, 0.812, 0.938, 0.312));
		shapes.add(VoxelShapes.create(0.188, 0.562, 0.312, 0.812, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.438, 0.5, 0.5, 0.562, 0.562, 0.625));
		shapes.add(VoxelShapes.create(0.25, 0.625, 0.812, 0.75, 0.875, 0.813));
		shapes.add(VoxelShapes.create(0.562, 0.688, 0.813, 0.688, 0.812, 0.814));
		shapes.add(VoxelShapes.create(0.312, 0.688, 0.813, 0.438, 0.812, 0.814));
		shapes.add(VoxelShapes.create(0.25, 0.125, 0.375, 0.75, 0.126, 0.75));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateShapeEast() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.188, 0, 0.188, 0.812, 0.125, 0.812));
		shapes.add(VoxelShapes.create(0.188, 0.125, 0.188, 0.312, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.312, 0.562, 0.188, 0.812, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.5, 0.5, 0.438, 0.625, 0.562, 0.562));
		shapes.add(VoxelShapes.create(0.812, 0.625, 0.25, 0.813, 0.875, 0.75));
		shapes.add(VoxelShapes.create(0.813, 0.688, 0.312, 0.814, 0.812, 0.438));
		shapes.add(VoxelShapes.create(0.813, 0.688, 0.562, 0.814, 0.812, 0.688));
		shapes.add(VoxelShapes.create(0.375, 0.125, 0.25, 0.75, 0.126, 0.75));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateShapeWest() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.188, 0, 0.188, 0.812, 0.125, 0.812));
		shapes.add(VoxelShapes.create(0.688, 0.125, 0.188, 0.812, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.188, 0.562, 0.188, 0.688, 0.938, 0.812));
		shapes.add(VoxelShapes.create(0.375, 0.5, 0.438, 0.5, 0.562, 0.562));
		shapes.add(VoxelShapes.create(0.187, 0.625, 0.25, 0.188, 0.875, 0.75));
		shapes.add(VoxelShapes.create(0.186, 0.688, 0.562, 0.187, 0.812, 0.688));
		shapes.add(VoxelShapes.create(0.186, 0.688, 0.312, 0.187, 0.812, 0.438));
		shapes.add(VoxelShapes.create(0.25, 0.125, 0.25, 0.625, 0.126, 0.75));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateCupShapeNorth() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.375, 0.126, 0.312, 0.625, 0.188, 0.562));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.312, 0.625, 0.438, 0.375));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.5, 0.625, 0.438, 0.562));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.375, 0.438, 0.438, 0.5));
		shapes.add(VoxelShapes.create(0.562, 0.188, 0.375, 0.625, 0.438, 0.5));
		shapes.add(VoxelShapes.create(0.25, 0.188, 0.375, 0.375, 0.251, 0.5));
		shapes.add(VoxelShapes.create(0.25, 0.313, 0.375, 0.375, 0.376, 0.5));
		shapes.add(VoxelShapes.create(0.25, 0.251, 0.375, 0.312, 0.313, 0.5));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateCupShapeSouth() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.375, 0.126, 0.438, 0.625, 0.188, 0.688));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.625, 0.625, 0.438, 0.688));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.438, 0.625, 0.438, 0.5));
		shapes.add(VoxelShapes.create(0.562, 0.188, 0.5, 0.625, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.5, 0.438, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.625, 0.188, 0.5, 0.75, 0.251, 0.625));
		shapes.add(VoxelShapes.create(0.625, 0.313, 0.5, 0.75, 0.376, 0.625));
		shapes.add(VoxelShapes.create(0.688, 0.251, 0.5, 0.75, 0.313, 0.625));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateCupShapeEast() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.438, 0.126, 0.375, 0.688, 0.188, 0.625));
		shapes.add(VoxelShapes.create(0.625, 0.188, 0.375, 0.688, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.438, 0.188, 0.375, 0.5, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.5, 0.188, 0.375, 0.625, 0.438, 0.438));
		shapes.add(VoxelShapes.create(0.5, 0.188, 0.562, 0.625, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.5, 0.188, 0.25, 0.625, 0.251, 0.375));
		shapes.add(VoxelShapes.create(0.5, 0.313, 0.25, 0.625, 0.376, 0.375));
		shapes.add(VoxelShapes.create(0.5, 0.251, 0.25, 0.625, 0.313, 0.312));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

	private VoxelShape generateCupShapeWest() {
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(VoxelShapes.create(0.312, 0.126, 0.375, 0.562, 0.188, 0.625));
		shapes.add(VoxelShapes.create(0.312, 0.188, 0.375, 0.375, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.5, 0.188, 0.375, 0.562, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.562, 0.5, 0.438, 0.625));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.375, 0.5, 0.438, 0.438));
		shapes.add(VoxelShapes.create(0.375, 0.188, 0.625, 0.5, 0.251, 0.75));
		shapes.add(VoxelShapes.create(0.375, 0.313, 0.625, 0.5, 0.376, 0.75));
		shapes.add(VoxelShapes.create(0.375, 0.251, 0.688, 0.5, 0.313, 0.75));

		VoxelShape result = VoxelShapes.empty();
		for (VoxelShape shape : shapes) {
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);
		}
		return result.simplify();
	}

}
