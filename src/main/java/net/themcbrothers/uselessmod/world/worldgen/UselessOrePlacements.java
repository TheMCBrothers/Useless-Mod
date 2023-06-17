package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.List;

public final class UselessOrePlacements {
    public static final ResourceKey<PlacedFeature> ORE_USELESS_MIDDLE = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_useless_middle");
    public static final ResourceKey<PlacedFeature> ORE_USELESS_NETHER = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_useless_nether");
    public static final ResourceKey<PlacedFeature> ORE_USELESS_END = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_useless_end");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS_LARGE = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_super_useless_middle");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS_NETHER = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_super_useless_nether");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS_END = PlacementUtils.createKey(UselessMod.MOD_ID + ":ore_super_useless_end");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, ORE_USELESS_MIDDLE, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        PlacementUtils.register(context, ORE_USELESS_NETHER, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(5, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_USELESS_END, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(10, PlacementUtils.FULL_RANGE));
        PlacementUtils.register(context, ORE_SUPER_USELESS_LARGE, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        PlacementUtils.register(context, ORE_SUPER_USELESS_NETHER, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), commonOrePlacement(5, PlacementUtils.RANGE_10_10));
        PlacementUtils.register(context, ORE_SUPER_USELESS_END, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), commonOrePlacement(10, PlacementUtils.FULL_RANGE));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(count), placementModifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), placementModifier);
    }
}
