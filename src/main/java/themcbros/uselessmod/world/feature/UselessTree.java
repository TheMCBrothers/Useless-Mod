package themcbros.uselessmod.world.feature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import themcbros.uselessmod.init.UselessFeatures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author TheMCBrothers
 */
public class UselessTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(@Nonnull Random randomIn, boolean largeHive) {
        if (randomIn.nextInt(10) == 0) {
            return largeHive ? UselessFeatures.FANCY_USELESS_TREE_BEES : UselessFeatures.FANCY_USELESS_TREE;
        } else {
            return largeHive ? UselessFeatures.USELESS_TREE_BEES : UselessFeatures.USELESS_TREE;
        }
    }
}
