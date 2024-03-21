package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;

import java.util.List;

public final class UselessVegetationFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_FLOWER_DEFAULT = FeatureUtils.createKey(UselessMod.MOD_ID + ":flower_default");
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_FOREST_TREES = FeatureUtils.createKey(UselessMod.MOD_ID + ":trees_forest");

    private static RandomPatchConfiguration grassPatch(BlockStateProvider toPlace, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(toPlace)));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        FeatureUtils.register(context, USELESS_FLOWER_DEFAULT, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(UselessBlocks.RED_ROSE.get().defaultBlockState(), 2).add(UselessBlocks.BLUE_ROSE.get().defaultBlockState(), 2).add(UselessBlocks.USELESS_ROSE.get().defaultBlockState(), 1)), 64));
        FeatureUtils.register(context, USELESS_FOREST_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(placedFeatures.getOrThrow(UselessTreePlacements.FANCY_USELESS_OAK_BEES_0002), 0.1F)), placedFeatures.getOrThrow(UselessTreePlacements.USELESS_OAK_BEES_0002)));
    }
}
