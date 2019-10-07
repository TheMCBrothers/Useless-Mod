package tk.themcbros.uselessmod.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.energy.ConnectionType;
import tk.themcbros.uselessmod.helper.IHammer;
import tk.themcbros.uselessmod.tileentity.PowerControlTileEntity;

import java.util.Map;

public class PowerControlBlock extends Block implements IHammer {

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

	public PowerControlBlock(Properties properties) {
		super(properties);

		this.setDefaultState(this.stateContainer.getBaseState()
				.with(NORTH, ConnectionType.NONE)
				.with(EAST, ConnectionType.NONE)
				.with(SOUTH, ConnectionType.NONE)
				.with(WEST, ConnectionType.NONE)
				.with(UP, ConnectionType.NONE)
				.with(DOWN, ConnectionType.NONE));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
	}

	public void swapSide(BlockState state, BlockPos pos, World worldIn, Direction face) {
		ConnectionType side = Util.getElementAfter(FACING_TO_PROPERTY_MAP.get(face).getAllowedValues(), getConnection(state, face));
		if (side == ConnectionType.BLOCKED) side = ConnectionType.NONE;
		state = state.with(FACING_TO_PROPERTY_MAP.get(face), side);
		worldIn.setBlockState(pos, state, 18);
	}

	public static ConnectionType getConnection(BlockState state, Direction side) {
		return state.get(FACING_TO_PROPERTY_MAP.get(side));
	}

	@Override
	public ActionResultType onHammer(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = world.getBlockState(pos);
		swapSide(state, pos, world, context.getFace());
		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PowerControlTileEntity();
	}

}
