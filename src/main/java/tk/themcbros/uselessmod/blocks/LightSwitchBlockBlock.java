package tk.themcbros.uselessmod.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.tileentity.LightSwitchTileEntity;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class LightSwitchBlockBlock extends Block {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	public static final Map<BlockState, BlockState> LIGHTS_ON_OFF = Maps.newHashMap();
	public static final Map<BlockState, BlockState> LIGHTS_OFF_ON = Maps.newHashMap();
	
	public static void init() {
		LIGHTS_ON_OFF.put(Blocks.REDSTONE_LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.TRUE), Blocks.REDSTONE_LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.FALSE));
		LIGHTS_ON_OFF.put(Blocks.LANTERN.getDefaultState(), ModBlocks.UNLIT_LANTERN.getDefaultState());
		LIGHTS_ON_OFF.put(Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE), ModBlocks.UNLIT_LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
		LIGHTS_ON_OFF.put(ModBlocks.LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.TRUE), ModBlocks.LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.FALSE));
		
		LIGHTS_OFF_ON.put(Blocks.REDSTONE_LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.FALSE), Blocks.REDSTONE_LAMP.getDefaultState().with(BlockStateProperties.LIT, Boolean.TRUE));
		LIGHTS_OFF_ON.put(ModBlocks.UNLIT_LANTERN.getDefaultState(), Blocks.LANTERN.getDefaultState());
		LIGHTS_OFF_ON.put(ModBlocks.UNLIT_LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE), Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
		
		for(DyeColor color : DyeColor.values()) {
			BlockState lampState = ModBlocks.LAMP.getDefaultState().with(LampBlock.COLOR, color);
			BlockState lampStateOn = lampState.with(BlockStateProperties.LIT, Boolean.TRUE);
			BlockState lampStateOff = lampState.with(BlockStateProperties.LIT, Boolean.FALSE);
			LIGHTS_ON_OFF.put(lampStateOn, lampStateOff);
			LIGHTS_OFF_ON.put(lampStateOff, lampStateOn);
		}
	}

	public LightSwitchBlockBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.FALSE));
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
		builder.add(POWERED);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		boolean flag1 = state.get(POWERED);
		if (flag && !flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.TRUE), 1 | 2 | 4);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		} else if (!flag && flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.FALSE), 1 | 2 | 4);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		}

	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isRemote) {
			switchLights(state, worldIn, pos);
		}
	}

	protected static void switchLights(BlockState state, World worldIn, BlockPos pos) {
		Boolean value = state.get(POWERED);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity != null && tileEntity instanceof LightSwitchTileEntity) {
			LightSwitchTileEntity lightSwitch = (LightSwitchTileEntity) tileEntity;
			for (BlockPos blockPos : lightSwitch.getBlockPositions()) {
				BlockState blockState = worldIn.getBlockState(blockPos);
				
				if(value) {
					if(LIGHTS_OFF_ON.containsKey(blockState)) {
						blockState = LIGHTS_OFF_ON.get(blockState);
					} else if (!LIGHTS_ON_OFF.containsKey(blockState)){
						lightSwitch.getBlockPositions().remove(blockPos);
						return;
					}
				} else {
					if(LIGHTS_ON_OFF.containsKey(blockState)) {
						blockState = LIGHTS_ON_OFF.get(blockState);
					} else if (!LIGHTS_OFF_ON.containsKey(blockState)) {
						lightSwitch.getBlockPositions().remove(blockPos);
						return;
					}
				}
				
//				if (blockState.has(BlockStateProperties.LIT))
//					blockState = blockState.with(BlockStateProperties.LIT, Boolean.valueOf(value));
//				else if (blockState.getBlock() == ModBlocks.UNLIT_LANTERN)
//					blockState = Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING,
//							blockState.get(LanternBlock.HANGING));
//				else if (blockState.getBlock() == Blocks.LANTERN)
//					blockState = ModBlocks.UNLIT_LANTERN.getDefaultState().with(LanternBlock.HANGING,
//							blockState.get(LanternBlock.HANGING));
				
				worldIn.setBlockState(blockPos, blockState, 1 | 2 | 16 | 32);
			}
		}
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
