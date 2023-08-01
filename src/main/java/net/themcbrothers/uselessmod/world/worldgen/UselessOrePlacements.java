package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static net.themcbrothers.uselessmod.init.Registration.PLACED_FEATURES;

public final class UselessOrePlacements {
    public static final RegistryObject<PlacedFeature> ORE_USELESS_MIDDLE = PLACED_FEATURES.register("ore_useless_middle", () -> new PlacedFeature(UselessOreFeatures.ORE_USELESS.getHolder().orElseThrow(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
    public static final RegistryObject<PlacedFeature> ORE_USELESS_NETHER = PLACED_FEATURES.register("ore_useless_nether", () -> new PlacedFeature(UselessOreFeatures.ORE_USELESS.getHolder().orElseThrow(), commonOrePlacement(5, PlacementUtils.RANGE_10_10)));
    public static final RegistryObject<PlacedFeature> ORE_USELESS_END = PLACED_FEATURES.register("ore_useless_end", () -> new PlacedFeature(UselessOreFeatures.ORE_USELESS.getHolder().orElseThrow(), commonOrePlacement(10, PlacementUtils.FULL_RANGE)));
    public static final RegistryObject<PlacedFeature> ORE_SUPER_USELESS_NORMAL = PLACED_FEATURES.register("ore_super_useless_normal", () -> new PlacedFeature(UselessOreFeatures.ORE_SUPER_USELESS.getHolder().orElseThrow(), commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)))));
    public static final RegistryObject<PlacedFeature> ORE_SUPER_USELESS_NETHER = PLACED_FEATURES.register("ore_super_useless_nether", () -> new PlacedFeature(UselessOreFeatures.ORE_SUPER_USELESS.getHolder().orElseThrow(), commonOrePlacement(5, PlacementUtils.RANGE_10_10)));
    public static final RegistryObject<PlacedFeature> ORE_SUPER_USELESS_END = PLACED_FEATURES.register("ore_super_useless_end", () -> new PlacedFeature(UselessOreFeatures.ORE_SUPER_USELESS.getHolder().orElseThrow(), commonOrePlacement(10, PlacementUtils.FULL_RANGE)));

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(count), placementModifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), placementModifier);
    }

    public static void register() {
    }
}
