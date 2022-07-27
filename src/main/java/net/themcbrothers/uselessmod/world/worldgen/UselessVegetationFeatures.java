package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.themcbrothers.uselessmod.init.ModBlocks;

import java.util.List;

public final class UselessVegetationFeatures {
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> USELESS_FLOWER_DEFAULT = FeatureUtils.register("uselessmod:flower_default", Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.RED_ROSE.get().defaultBlockState(), 2).add(ModBlocks.BLUE_ROSE.get().defaultBlockState(), 2).add(ModBlocks.USELESS_ROSE.get().defaultBlockState(), 1)), 64));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> USELESS_FOREST_TREES = FeatureUtils.register("uselessmod:trees_forest", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(UselessTreePlacements.FANCY_USELESS_OAK_BEES_0002, 0.1F)), UselessTreePlacements.USELESS_OAK_BEES_0002));

    private static RandomPatchConfiguration grassPatch(BlockStateProvider toPlace, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(toPlace)));
    }
}
