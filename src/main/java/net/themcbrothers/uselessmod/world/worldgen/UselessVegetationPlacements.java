package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public final class UselessVegetationPlacements {
    public static final Holder<PlacedFeature> TREES_USELESS_OAK = PlacementUtils.register("trees_useless_oak", UselessVegetationFeatures.USELESS_FOREST_TREES, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
}
