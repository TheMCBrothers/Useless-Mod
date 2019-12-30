package tk.themcbros.uselessmod.world.feature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import tk.themcbros.uselessmod.lists.ModBiomeFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class UselessTree extends Tree {

	@Nullable
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random random) {
		return random.nextInt(10) == 0 ? Feature.FANCY_TREE.func_225566_b_(ModBiomeFeatures.FANCY_USELESS_TREE) : Feature.NORMAL_TREE.func_225566_b_(ModBiomeFeatures.USELESS_TREE);
	}
}
