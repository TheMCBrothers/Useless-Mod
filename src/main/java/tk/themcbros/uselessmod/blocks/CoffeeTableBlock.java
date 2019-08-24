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
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
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
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

public class CoffeeTableBlock extends Block implements IWaterLoggable {
	
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty TALL = BooleanProperty.create("tall");
	public static final BooleanProperty CABLE = BooleanProperty.create("cable");
	
	private final VoxelShape SHAPE;
	private final VoxelShape SHAPE_TALL;
	
	public CoffeeTableBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(WATERLOGGED, Boolean.FALSE)
				.with(TALL, Boolean.FALSE)
				.with(CABLE, Boolean.FALSE)
				.with(NORTH, Boolean.FALSE)
				.with(SOUTH, Boolean.FALSE)
				.with(EAST, Boolean.FALSE)
				.with(WEST, Boolean.FALSE)
				.with(UP, Boolean.FALSE)
				.with(DOWN, Boolean.FALSE));
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
	
	private VoxelShape endResultShape(BlockState state) {
		VoxelShape result = state.get(TALL) ? SHAPE_TALL : SHAPE;
		
		if(state.get(CABLE)) {
			result = VoxelShapes.combine(result, EnergyCableBlock.getCableShape(state), IBooleanFunction.OR);
		}
		
		return result.simplify();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if(state.get(TALL) && state.get(CABLE) && context.func_216378_a(VoxelShapes.fullCube(), pos, false))
			return EnergyCableBlock.getCableShape(state);
		return state.get(TALL) ? SHAPE_TALL : SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.endResultShape(state);
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return this.endResultShape(state);
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
		builder.add(WATERLOGGED, TALL, CABLE, NORTH, SOUTH, EAST, WEST, UP, DOWN);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getPos();
		BlockState blockstate = context.getWorld().getBlockState(blockpos);
		IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
		ItemStack stack = context.getItem();
		if (blockstate.getBlock() == this && stack.getItem() == ModItems.STRIPPED_OAK_COFFEE_TABLE) {
			blockstate = this.getDefaultState().with(TALL, Boolean.valueOf(true));
		} else if (blockstate.getBlock() == ModBlocks.STRIPPED_OAK_COFFEE_TABLE) {
			blockstate = this.getDefaultState().with(CABLE, Boolean.valueOf(true)).with(TALL, Boolean.valueOf(true));
		} else {
			blockstate = this.getDefaultState();
		}
		return blockstate.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(state.getBlock() == this && state.get(TALL) && newState.getBlock() == ModBlocks.ENERGY_CABLE) {
			worldIn.setBlockState(pos, state.with(CABLE, Boolean.TRUE), 3);
		}
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return state.get(CABLE);
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return state.get(CABLE) ? new EnergyCableTileEntity() : null;
	}
	
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return (useContext.getItem().getItem() == this.asItem() && !state.get(TALL)) || (useContext.getItem().getItem() == ModItems.ENERGY_CABLE && state.get(TALL) && !state.get(CABLE));
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
		
		if(stateIn.get(CABLE)) {
			TileEntity tileEntity = worldIn.getTileEntity(currentPos);
			if(tileEntity instanceof EnergyCableTileEntity) {
				((EnergyCableTileEntity) tileEntity).updateConnections();
			}
		}

		return stateIn;
	}
	
}
