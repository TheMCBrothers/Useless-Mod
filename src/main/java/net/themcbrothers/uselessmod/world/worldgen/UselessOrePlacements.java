package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.List;

public final class UselessOrePlacements {
    public static final ResourceKey<PlacedFeature> ORE_USELESS = createKey("ore_useless");
    public static final ResourceKey<PlacedFeature> ORE_USELESS_NETHER = createKey("ore_useless_nether");
    public static final ResourceKey<PlacedFeature> ORE_USELESS_END = createKey("ore_useless_end");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS = createKey("ore_super_useless");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS_NETHER = createKey("ore_super_useless_nether");
    public static final ResourceKey<PlacedFeature> ORE_SUPER_USELESS_END = createKey("ore_super_useless_end");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, ORE_USELESS, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)), ConfigFeaturePlacement.Type.OVERWORLD));
        PlacementUtils.register(context, ORE_USELESS_NETHER, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(5, PlacementUtils.RANGE_10_10, ConfigFeaturePlacement.Type.NETHER));
        PlacementUtils.register(context, ORE_USELESS_END, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_USELESS), commonOrePlacement(8, PlacementUtils.FULL_RANGE, ConfigFeaturePlacement.Type.END));
        PlacementUtils.register(context, ORE_SUPER_USELESS, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)), ConfigFeaturePlacement.Type.OVERWORLD));
        PlacementUtils.register(context, ORE_SUPER_USELESS_NETHER, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), commonOrePlacement(5, PlacementUtils.RANGE_10_10, ConfigFeaturePlacement.Type.NETHER));
        PlacementUtils.register(context, ORE_SUPER_USELESS_END, configuredFeatures.getOrThrow(UselessOreFeatures.ORE_SUPER_USELESS), commonOrePlacement(8, PlacementUtils.FULL_RANGE, ConfigFeaturePlacement.Type.END));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier, ConfigFeaturePlacement.Type dimType) {
        return List.of(countModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome(), new ConfigFeaturePlacement(dimType));
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier, ConfigFeaturePlacement.Type dimType) {
        return orePlacement(CountPlacement.of(count), placementModifier, dimType);
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, UselessMod.rl(name));
    }
}
