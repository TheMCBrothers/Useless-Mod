package net.themcbrothers.uselessmod.world.level.block.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.themcbrothers.uselessmod.world.worldgen.UselessTreeFeatures.*;

public class UselessOakTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean largeHive) {
        if (random.nextInt(10) == 0) {
            return largeHive ? FANCY_USELESS_OAK_BEES_005 : FANCY_USELESS_OAK;
        } else {
            return largeHive ? USELESS_OAK_BEES_005 : USELESS_OAK;
        }
    }
}
