package net.themcbrothers.uselessmod.world.level.block.grower;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

import static net.themcbrothers.uselessmod.world.worldgen.UselessTreeFeatures.*;

public class UselessOakTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<ConfiguredFeature<TreeConfiguration, ?>> getConfiguredFeature(Random random, boolean hasFlower) {
        if (random.nextInt(10) == 0) {
            return hasFlower ? FANCY_USELESS_OAK_BEES_005 : FANCY_USELESS_OAK;
        } else {
            return hasFlower ? USELESS_OAK_BEES_005 : USELESS_OAK;
        }
    }
}
