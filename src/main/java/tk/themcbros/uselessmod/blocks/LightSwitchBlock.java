package tk.themcbros.uselessmod.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import tk.themcbros.uselessmod.helper.ShapeUtils;
import tk.themcbros.uselessmod.tileentity.LightSwitchTileEntity;

public class LightSwitchBlock extends HorizontalFaceBlock {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	private final VoxelShape FLOOR_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 4.5D, 11.5D, 1.5D, 11.5D);
	private final VoxelShape CEILING_SHAPE = Block.makeCuboidShape(4.5D, 14.5D, 4.5D, 11.5D, 16.0D, 11.5D);
	private final VoxelShape WALL_SHAPE = Block.makeCuboidShape(0.0D, 4.5D, 4.5D, 1.5D, 11.5D, 11.5D);
	
	private final VoxelShape[] FLOOR_SHAPES = ShapeUtils.getRotatedShapes(FLOOR_SHAPE);
	private final VoxelShape[] CEILING_SHAPES = ShapeUtils.getRotatedShapes(CEILING_SHAPE);
	private final VoxelShape[] WALL_SHAPES = ShapeUtils.getRotatedShapes(WALL_SHAPE);

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
			return FLOOR_SHAPES[facing.getHorizontalIndex()];
		case CEILING:
			return CEILING_SHAPES[facing.getHorizontalIndex()];
		case WALL:
			return WALL_SHAPES[facing.getHorizontalIndex()];
		}
		return VoxelShapes.fullCube();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(stack.hasTag()) {
			if(stack.getTag().contains("BlockEntityTag", Constants.NBT.TAG_COMPOUND)) {
				CompoundNBT tag = stack.getChildTag("BlockEntityTag");
				if(tag.contains("Lights", Constants.NBT.TAG_LONG_ARRAY)) {
					long[] longArray = tag.getLongArray("Lights");
					List<BlockPos> blockPoses = Lists.newArrayList();
					for(long l : longArray) {
						blockPoses.add(BlockPos.fromLong(l));
					}
					TileEntity tileEntity = worldIn.getTileEntity(pos);
					if(tileEntity != null && tileEntity instanceof LightSwitchTileEntity) {
						((LightSwitchTileEntity) tileEntity).setBlockPositions(blockPoses);
						tileEntity.markDirty();
					}
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
	 *             {@link BlockState#getWeakPower(IBlockReader,BlockPos,Direction)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) ? 15 : 0;
	}

	/**
	 * @deprecated call via
	 *             {@link BlockState#getStrongPower(IBlockReader,BlockPos,Direction)}
	 *             whenever possible. Implementing/overriding is fine.
	 */
	public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) && getFacing(blockState) == side ? 15 : 0;
	}

	@Override
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
										   BlockRayTraceResult hit) {
		
		boolean trigger = state.get(POWERED);
		state = state.cycle(POWERED);
		worldIn.setBlockState(pos, state, 3);
		SoundEvent sound = trigger ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF : SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
		this.playSound(player, worldIn, pos, sound);
		
		LightSwitchBlockBlock.switchLights(state, worldIn, pos);
		
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		return ActionResultType.SUCCESS;
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
