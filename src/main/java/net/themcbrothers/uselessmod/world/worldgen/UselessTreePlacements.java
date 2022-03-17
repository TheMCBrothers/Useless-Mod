package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.themcbrothers.uselessmod.init.ModBlocks;

public final class UselessTreePlacements {
    public static final PlacedFeature USELESS_OAK_BEES_0002 = PlacementUtils.register("useless_oak_bees_0002", UselessTreeFeatures.USELESS_OAK_BEES_0002.filteredByBlockSurvival(ModBlocks.USELESS_OAK_SAPLING.get()));
    public static final PlacedFeature USELESS_OAK_BEES_002 = PlacementUtils.register("useless_oak_bees_002", UselessTreeFeatures.USELESS_OAK_BEES_002.filteredByBlockSurvival(ModBlocks.USELESS_OAK_SAPLING.get()));
    public static final PlacedFeature FANCY_USELESS_OAK_BEES_0002 = PlacementUtils.register("fancy_useless_oak_bees_0002", UselessTreeFeatures.FANCY_USELESS_OAK_BEES_0002.filteredByBlockSurvival(ModBlocks.USELESS_OAK_SAPLING.get()));
    public static final PlacedFeature FANCY_USELESS_OAK_BEES_002 = PlacementUtils.register("fancy_useless_oak_bees_002", UselessTreeFeatures.FANCY_USELESS_OAK_BEES_002.filteredByBlockSurvival(ModBlocks.USELESS_OAK_SAPLING.get()));
    public static final PlacedFeature FANCY_USELESS_OAK_BEES = PlacementUtils.register("fancy_useless_oak_bees", UselessTreeFeatures.FANCY_USELESS_OAK_BEES.filteredByBlockSurvival(ModBlocks.USELESS_OAK_SAPLING.get()));
}
