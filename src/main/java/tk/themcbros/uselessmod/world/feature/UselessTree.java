package tk.themcbros.uselessmod.world.feature;

import java.util.Random;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BigTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

public class UselessTree extends Tree {

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return random.nextInt(10) == 0 ? new UselessBigTreeFeature(NoFeatureConfig::deserialize, true) : new UselessTreeFeature(NoFeatureConfig::deserialize, true, false);
	}

}
