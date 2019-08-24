package tk.themcbros.uselessmod.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.tileentity.LightSwitchTileEntity;

public class LightSwitchBlockBlock extends Block {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public LightSwitchBlockBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.FALSE));
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
		builder.add(POWERED);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		boolean flag1 = state.get(POWERED);
		if (flag && !flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(true)), 1 | 2 | 4);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		} else if (!flag && flag1) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(false)), 1 | 2 | 4);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
		}

	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isRemote) {
			this.switchLights(worldIn, pos);
		}
	}
	
	public void switchLights(World worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity != null && tileEntity instanceof LightSwitchTileEntity) {
			LightSwitchTileEntity lightSwitch = (LightSwitchTileEntity) tileEntity;
			for (BlockPos blockPos : lightSwitch.getBlockPositions()) {
				BlockState blockState = worldIn.getBlockState(blockPos);
				if (blockState.has(BlockStateProperties.LIT))
					blockState = blockState.cycle(BlockStateProperties.LIT);
				else if (blockState.getBlock() == ModBlocks.UNLIT_LANTERN)
					blockState = Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING,
							blockState.get(LanternBlock.HANGING));
				else if (blockState.getBlock() == Blocks.LANTERN)
					blockState = ModBlocks.UNLIT_LANTERN.getDefaultState().with(LanternBlock.HANGING,
							blockState.get(LanternBlock.HANGING));
				worldIn.setBlockState(blockPos, blockState, 1 | 2);
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
