package tk.themcbros.uselessmod.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.energy.ConnectionType;
import tk.themcbros.uselessmod.helper.ShapeUtils;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

import java.util.List;
import java.util.Map;

public class EnergyCableBlock extends Block implements IWaterLoggable {

	public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
	public static final EnumProperty<ConnectionType> EAST = EnumProperty.create("east", ConnectionType.class);
	public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
	public static final EnumProperty<ConnectionType> WEST = EnumProperty.create("west", ConnectionType.class);
	public static final EnumProperty<ConnectionType> UP = EnumProperty.create("up", ConnectionType.class);
	public static final EnumProperty<ConnectionType> DOWN = EnumProperty.create("down", ConnectionType.class);
	public static final Map<Direction, EnumProperty<ConnectionType>> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (map) -> {
		map.put(Direction.NORTH, NORTH);
		map.put(Direction.EAST, EAST);
		map.put(Direction.SOUTH, SOUTH);
		map.put(Direction.WEST, WEST);
		map.put(Direction.UP, UP);
		map.put(Direction.DOWN, DOWN);
	});
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
				.with(NORTH, ConnectionType.NONE)
				.with(SOUTH, ConnectionType.NONE)
				.with(EAST, ConnectionType.NONE)
				.with(WEST, ConnectionType.NONE)
				.with(UP, ConnectionType.NONE)
				.with(DOWN, ConnectionType.NONE)
				.with(WATERLOGGED, Boolean.FALSE));
	}
	
	public static VoxelShape getCableShape(BlockState state) {
		List<VoxelShape> shapes = Lists.newArrayList(CENTER_SHAPE);
		if(getConnection(state, Direction.NORTH).canConnect()) shapes.add(NORTH_SHAPE);
		if(getConnection(state, Direction.SOUTH).canConnect()) shapes.add(SOUTH_SHAPE);
		if(getConnection(state, Direction.EAST).canConnect()) shapes.add(EAST_SHAPE);
		if(getConnection(state, Direction.WEST).canConnect()) shapes.add(WEST_SHAPE);
		if(getConnection(state, Direction.UP).canConnect()) shapes.add(UP_SHAPE);
		if(getConnection(state, Direction.DOWN).canConnect()) shapes.add(DOWN_SHAPE);
		return ShapeUtils.combineAll(shapes);
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
		return makeConnections(context.getWorld(), context.getPos()).with(WATERLOGGED,
				Boolean.valueOf(ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8));
	}

	public BlockState makeConnections(IBlockReader worldIn, BlockPos pos) {
		return this.getDefaultState()
				.with(DOWN, createConnection(worldIn, pos.down(), ConnectionType.NONE))
				.with(UP, createConnection(worldIn, pos.up(), ConnectionType.NONE))
				.with(NORTH, createConnection(worldIn, pos.north(), ConnectionType.NONE))
				.with(EAST, createConnection(worldIn, pos.east(), ConnectionType.NONE))
				.with(SOUTH, createConnection(worldIn, pos.south(), ConnectionType.NONE))
				.with(WEST, createConnection(worldIn, pos.west(), ConnectionType.NONE));
	}

	private static ConnectionType createConnection(IBlockReader worldIn, BlockPos pos, ConnectionType current) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof EnergyCableTileEntity)
			return ConnectionType.BOTH;
		if (tileEntity != null && tileEntity.getCapability(CapabilityEnergy.ENERGY).isPresent())
			return current == ConnectionType.NONE ? ConnectionType.INPUT : current;
		return ConnectionType.NONE;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		EnumProperty<ConnectionType> property = FACING_TO_PROPERTY_MAP.get(facing);
		ConnectionType current = stateIn.get(property);
		return stateIn.with(property, createConnection(worldIn, facingPos, current));
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

	public static ConnectionType getConnection(BlockState state, Direction side) {
		return state.get(FACING_TO_PROPERTY_MAP.get(side));
	}
	
}
