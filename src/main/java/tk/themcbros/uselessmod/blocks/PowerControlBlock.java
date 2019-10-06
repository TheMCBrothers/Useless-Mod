package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.tileentity.PowerControlTileEntity;

public class PowerControlBlock extends Block {
	
	public static final EnumProperty<PowerSide> NORTH = EnumProperty.create("north", PowerSide.class);
	public static final EnumProperty<PowerSide> EAST = EnumProperty.create("east", PowerSide.class);
	public static final EnumProperty<PowerSide> SOUTH = EnumProperty.create("south", PowerSide.class);
	public static final EnumProperty<PowerSide> WEST = EnumProperty.create("west", PowerSide.class);
	public static final EnumProperty<PowerSide> UP = EnumProperty.create("up", PowerSide.class);
	public static final EnumProperty<PowerSide> DOWN = EnumProperty.create("down", PowerSide.class);

	public PowerControlBlock(Properties properties) {
		super(properties);
		
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(NORTH, PowerSide.NONE)
				.with(EAST, PowerSide.NONE)
				.with(SOUTH, PowerSide.NONE)
				.with(WEST, PowerSide.NONE)
				.with(UP, PowerSide.NONE)
				.with(DOWN, PowerSide.NONE));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
	}
	
	public EnumProperty<PowerSide> sideFromFace(Direction face) {
		switch (face) {
		case NORTH:
			return PowerControlBlock.NORTH;
		case DOWN:
			return PowerControlBlock.DOWN;
		case EAST:
			return PowerControlBlock.EAST;
		case SOUTH:
			return PowerControlBlock.SOUTH;
		case UP:
			return PowerControlBlock.UP;
		case WEST:
			return PowerControlBlock.WEST;
		default:
			return PowerControlBlock.NORTH;
		}
	}
	
	public PowerSide sideFromSide(BlockState state, Direction face) {
		return (PowerSide) state.get(sideFromFace(face));
	}
	
	public void swapSide(BlockState state, BlockPos pos, World worldIn, Direction face) {
		EnumProperty<PowerSide> side = this.sideFromFace(face);
		if(state.get(side) == PowerSide.NONE)
			state = state.with(side, PowerSide.INPUT);
		else if(state.get(side) == PowerSide.INPUT)
			state = state.with(side, PowerSide.OUTPUT);
		else if(state.get(side) == PowerSide.OUTPUT)
			state = state.with(side, PowerSide.NONE);
		else
			state = state.with(side, PowerSide.NONE);
		worldIn.setBlockState(pos, state, 3);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		final ItemStack stack = player.getHeldItem(handIn);
		if (stack.getItem() == ModItems.HAMMER) {
			swapSide(state, pos, worldIn, hit.getFace());
		}
		return false;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PowerControlTileEntity();
	}
	
	public static enum PowerSide implements IStringSerializable {
		NONE("none"),
		INPUT("input"),
		OUTPUT("output");

		private final String name;
		
		private PowerSide(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
	}

}
