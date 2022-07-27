package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public final class UselessVegetationPlacements {
    public static final Holder<PlacedFeature> FLOWER_USELESS = PlacementUtils.register("uselessmod:flower_default", UselessVegetationFeatures.USELESS_FLOWER_DEFAULT, RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> TREES_USELESS_OAK = PlacementUtils.register("uselessmod:trees_useless_oak", UselessVegetationFeatures.USELESS_FOREST_TREES, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
}
