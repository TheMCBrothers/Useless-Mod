package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.themcbrothers.uselessmod.UselessMod;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public final class UselessVegetationPlacements {
    public static final ResourceKey<PlacedFeature> FLOWER_USELESS = PlacementUtils.createKey(UselessMod.MOD_ID + ":flower_default");
    public static final ResourceKey<PlacedFeature> TREES_USELESS_OAK = PlacementUtils.createKey(UselessMod.MOD_ID + ":trees_useless_oak");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, FLOWER_USELESS, configuredFeatures.getOrThrow(UselessVegetationFeatures.USELESS_FLOWER_DEFAULT), RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        PlacementUtils.register(context, TREES_USELESS_OAK, configuredFeatures.getOrThrow(UselessVegetationFeatures.USELESS_FOREST_TREES), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    }
}
