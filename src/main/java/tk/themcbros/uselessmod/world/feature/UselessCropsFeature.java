package tk.themcbros.uselessmod.world.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import tk.themcbros.uselessmod.blocks.UselessCropsBlock;
import tk.themcbros.uselessmod.lists.ModBlocks;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

public class UselessCropsFeature extends FlowersFeature {

	private static final UselessCropsBlock[] FLOWERS = new UselessCropsBlock[] { ModBlocks.WILD_USELESS_WHEAT, ModBlocks.WILD_COFFEE_SEEDS };

	public UselessCropsFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer) {
		super(deserializer);
	}

	@Nonnull
	@Override
	public BlockState getRandomFlower(@Nonnull Random rand, BlockPos pos) {
		double d0 = MathHelper.clamp((1.0D
				+ Biome.INFO_NOISE.getValue((double) pos.getX() / 48.0D, (double) pos.getZ() / 48.0D))
				/ 2.0D, 0.0D, 0.9999D);
		UselessCropsBlock block = FLOWERS[(int) (d0 * (double) FLOWERS.length)];
		return block.getMaturePlant();
	}

}
