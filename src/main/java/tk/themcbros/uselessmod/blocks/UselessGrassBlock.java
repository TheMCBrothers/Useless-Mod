package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import tk.themcbros.uselessmod.lists.ModBlocks;

import java.util.List;
import java.util.Random;

public class UselessGrassBlock extends GrassBlock {

	public UselessGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void grow(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
		BlockPos blockpos = p_225535_3_.up();
		BlockState blockstate = ModBlocks.USELESS_GRASS.getDefaultState();

		for(int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;
			int j = 0;

			while(true) {
				if (j >= i / 16) {
					BlockState blockstate2 = p_225535_1_.getBlockState(blockpos1);
					if (blockstate2.getBlock() == blockstate.getBlock() && p_225535_2_.nextInt(10) == 0) {
						((IGrowable)blockstate.getBlock()).grow(p_225535_1_, p_225535_2_, blockpos1, blockstate2);
					}

					if (!blockstate2.isAir()) {
						break;
					}

					BlockState blockstate1;
					if (p_225535_2_.nextInt(8) == 0) {
						List<ConfiguredFeature<?, ?>> list = p_225535_1_.getBiome(blockpos1).getFlowers();
						if (list.isEmpty()) {
							break;
						}

						ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig)(list.get(0)).config).feature;
						blockstate1 = ((FlowersFeature)configuredfeature.feature).getFlowerToPlace(p_225535_2_, blockpos1, configuredfeature.config);
					} else {
						blockstate1 = blockstate;
					}

					if (blockstate1.isValidPosition(p_225535_1_, blockpos1)) {
						p_225535_1_.setBlockState(blockpos1, blockstate1, 3);
					}
					break;
				}

				blockpos1 = blockpos1.add(p_225535_2_.nextInt(3) - 1, (p_225535_2_.nextInt(3) - 1) * p_225535_2_.nextInt(3) / 2, p_225535_2_.nextInt(3) - 1);
				if (p_225535_1_.getBlockState(blockpos1.down()).getBlock() != this || p_225535_1_.getBlockState(blockpos1).isCollisionShapeOpaque(p_225535_1_, blockpos1)) {
					break;
				}

				++j;
			}
		}

	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!func_220257_b(state, worldIn, pos)) {
			if (!worldIn.isAreaLoaded(pos, 3)) return;
			worldIn.setBlockState(pos, ModBlocks.USELESS_DIRT.getDefaultState());
		} else {
			if (worldIn.getLight(pos.up()) >= 9) {
				BlockState blockstate = this.getDefaultState();

				for(int i = 0; i < 4; ++i) {
					BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
					if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.USELESS_DIRT && func_220256_c(blockstate, worldIn, blockpos)) {
						worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW));
					}
				}
			}

		}
	}

	private static boolean func_220257_b(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = world.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
			return i < world.getMaxLightLevel();
		}
	}

	private static boolean func_220256_c(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return func_220257_b(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

}
