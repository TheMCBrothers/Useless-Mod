package tk.themcbros.uselessmod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SnowBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class UselessGrassBlock extends GrassBlock {

	public UselessGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.up();
		BlockState blockstate = ModBlocks.USELESS_GRASS.getDefaultState();

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;
			int j = 0;

			while (true) {
				if (j >= i / 16) {
					BlockState blockstate2 = worldIn.getBlockState(blockpos1);
					if (blockstate2.getBlock() == blockstate.getBlock() && rand.nextInt(10) == 0) {
						((IGrowable) blockstate.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
					}

					if (!blockstate2.isAir(worldIn, blockpos1)) {
						break;
					}

					BlockState blockstate1;
					if (rand.nextInt(8) == 0) {
						List<ConfiguredFeature<?>> list = worldIn.getBiome(blockpos1).getFlowers();
						if (list.isEmpty()) {
							break;
						}

						blockstate1 = ((FlowersFeature) ((DecoratedFeatureConfig) (list.get(0)).config).feature.feature)
								.getRandomFlower(rand, blockpos1);
					} else {
						blockstate1 = blockstate;
					}

					if (blockstate1.isValidPosition(worldIn, blockpos1)) {
						worldIn.setBlockState(blockpos1, blockstate1, 3);
					}
					break;
				}

				blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2,
						rand.nextInt(3) - 1);
				if (worldIn.getBlockState(blockpos1.down()).getBlock() != this
						|| worldIn.getBlockState(blockpos1).func_224756_o(worldIn, blockpos1)) {
					break;
				}

				++j;
			}
		}

	}

	private static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
		BlockPos blockpos = p_220257_2_.up();
		BlockState blockstate = p_220257_1_.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP,
					blockstate.getOpacity(p_220257_1_, blockpos));
			return i < p_220257_1_.getMaxLightLevel();
		}
	}

	private static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
		BlockPos blockpos = p_220256_2_.up();
		return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_)
				&& !p_220256_1_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return;
			if (!func_220257_b(state, worldIn, pos)) {
				worldIn.setBlockState(pos, ModBlocks.USELESS_DIRT.getDefaultState());
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					BlockState blockstate = this.getDefaultState();

					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3,
								random.nextInt(3) - 1);
						if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.USELESS_DIRT
								&& func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, blockstate.with(SNOWY,
									Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
						}
					}
				}

			}
		}
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
			IPlantable plantable) {
		return true;
	}

}
