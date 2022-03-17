package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;

import java.util.List;

public final class UselessVegetationFeatures {
    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> USELESS_FOREST_TREES = FeatureUtils.register("useless_forest_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(UselessTreePlacements.FANCY_USELESS_OAK_BEES_0002, 0.1F)), UselessTreePlacements.USELESS_OAK_BEES_0002)));
}
