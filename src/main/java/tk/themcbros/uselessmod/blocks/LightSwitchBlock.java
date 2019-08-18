package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.tileentity.LightSwitchTileEntity;

public class LightSwitchBlock extends HorizontalFaceBlock {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	private final VoxelShape SHAPE_FLOOR_NORTH_SOUTH = Block.makeCuboidShape(4.0D, 0.0D, 1.0D, 12.0D, 2.0D, 15.0D);
	private final VoxelShape SHAPE_FLOOR_EAST_WEST = Block.makeCuboidShape(1.0D, 0.0D, 4.0D, 15.0D, 2.0D, 12.0D);
	private final VoxelShape SHAPE_CEILING_NORTH_SOUTH = Block.makeCuboidShape(4.0D, 14.0D, 1.0D, 12.0D, 16.0D, 15.0D);
	private final VoxelShape SHAPE_CEILING_EAST_WEST = Block.makeCuboidShape(1.0D, 14.0D, 4.0D, 15.0D, 16.0D, 12.0D);
	private final VoxelShape SHAPE_WALL_NORTH = Block.makeCuboidShape(4.0D, 1.0D, 14.0D, 12.0D, 15.0D, 16.0D);
	private final VoxelShape SHAPE_WALL_SOUTH = Block.makeCuboidShape(4.0D, 1.0D, 0.0D, 12.0D, 15.0D, 2.0D);
	private final VoxelShape SHAPE_WALL_EAST = Block.makeCuboidShape(0.0D, 1.0D, 4.0D, 2.0D, 15.0D, 12.0D);
	private final VoxelShape SHAPE_WALL_WEST = Block.makeCuboidShape(14.0D, 1.0D, 4.0D, 16.0D, 15.0D, 12.0D);

	public LightSwitchBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(FACE,
				AttachFace.FLOOR));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction facing = state.get(HORIZONTAL_FACING);
		switch (state.get(FACE)) {
		case FLOOR:
			return facing == Direction.NORTH || facing == Direction.SOUTH ? SHAPE_FLOOR_NORTH_SOUTH : SHAPE_FLOOR_EAST_WEST;
		case CEILING:
			return facing == Direction.NORTH || facing == Direction.SOUTH ? SHAPE_CEILING_NORTH_SOUTH : SHAPE_CEILING_EAST_WEST;
		case WALL:
			return  facing == Direction.NORTH ? SHAPE_WALL_NORTH :
					facing == Direction.SOUTH ? SHAPE_WALL_SOUTH :
					facing == Direction.EAST ? SHAPE_WALL_EAST :
					facing == Direction.WEST ? SHAPE_WALL_WEST : VoxelShapes.fullCube();
		}
		return VoxelShapes.fullCube();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		CompoundNBT tag = stack.getTag();
		if(tag != null) {
			if(tag.contains("blocks")) {
				long[] longArray = tag.getLongArray("blocks");
				List<BlockPos> blockPoss = new ArrayList<BlockPos>();
				for(long l : longArray) {
					blockPoss.add(BlockPos.fromLong(l));
				}
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				if(tileEntity != null && tileEntity instanceof LightSwitchTileEntity) {
					((LightSwitchTileEntity) tileEntity).setBlockPositions(blockPoss);
					tileEntity.markDirty();
				}
			}
		}
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, FACE, POWERED);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState state = super.getStateForPlacement(context);
		return state != null ? state.with(POWERED, Boolean.FALSE) : null;
	}

	/**
	 * @deprecated call via
	 *             {@link BlockState#getWeakPower(IBlockAccess,BlockPos,EnumFacing)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) ? 15 : 0;
	}

	/**
	 * @deprecated call via
	 *             {@link BlockState#getStrongPower(IBlockAccess,BlockPos,EnumFacing)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) && getFacing(blockState) == side ? 15 : 0;
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		
		state = state.cycle(POWERED);
		worldIn.setBlockState(pos, state, 3);
		SoundEvent sound = state.get(POWERED) ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
		this.playSound(player, worldIn, pos, sound);
		
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity != null && tileEntity instanceof LightSwitchTileEntity) {
			LightSwitchTileEntity lightSwitch = (LightSwitchTileEntity) tileEntity;
			for(BlockPos blockPos : lightSwitch.getBlockPositions()) {
				BlockState blockState = worldIn.getBlockState(blockPos);
				if(blockState.has(BlockStateProperties.LIT))
					blockState = blockState.cycle(BlockStateProperties.LIT);
				else if(blockState.getBlock() == ModBlocks.UNLIT_LANTERN)
					blockState = Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, blockState.get(LanternBlock.HANGING));
				else if(blockState.getBlock() == Blocks.LANTERN)
					blockState = ModBlocks.UNLIT_LANTERN.getDefaultState().with(LanternBlock.HANGING, blockState.get(LanternBlock.HANGING));
				worldIn.setBlockState(blockPos, blockState, 3);
			}
		}
		
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		return true;
	}

	private void playSound(@Nullable PlayerEntity playerIn, IWorld worldIn, BlockPos pos, SoundEvent sound) {
		worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 0.3F, 0.5F);
	}
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new LightSwitchTileEntity();
	}

}
