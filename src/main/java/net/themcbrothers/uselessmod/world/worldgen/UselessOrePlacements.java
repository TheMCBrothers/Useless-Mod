package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public final class UselessOrePlacements {
    public static final PlacedFeature ORE_USELESS_MIDDLE = PlacementUtils.register("ore_useless_middle", UselessOreFeatures.ORE_USELESS.placed(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
    public static final PlacedFeature ORE_USELESS_NETHER = PlacementUtils.register("ore_useless_nether", UselessOreFeatures.ORE_USELESS.placed(commonOrePlacement(5, PlacementUtils.RANGE_10_10)));
    public static final PlacedFeature ORE_USELESS_END = PlacementUtils.register("ore_useless_end", UselessOreFeatures.ORE_USELESS.placed(commonOrePlacement(10, PlacementUtils.FULL_RANGE)));

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
