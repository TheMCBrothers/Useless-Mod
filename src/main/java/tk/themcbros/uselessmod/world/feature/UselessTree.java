package tk.themcbros.uselessmod.world.feature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import tk.themcbros.uselessmod.lists.ModBiomeFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class UselessTree extends Tree {

	@Nullable
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_) {
		return random.nextInt(10) == 0 ? Feature.FANCY_TREE.withConfiguration(p_225546_2_ ? ModBiomeFeatures.FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG : ModBiomeFeatures.FANCY_TREE_CONFIG) : Feature.NORMAL_TREE.withConfiguration(p_225546_2_ ? ModBiomeFeatures.USELESS_TREE_WITH_MORE_BEEHIVES_CONFIG : ModBiomeFeatures.USELESS_TREE_CONFIG);
	}

}
