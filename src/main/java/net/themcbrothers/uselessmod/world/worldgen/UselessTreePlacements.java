package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;

public final class UselessTreePlacements {
    public static final ResourceKey<PlacedFeature> USELESS_OAK_BEES_0002 = PlacementUtils.createKey(UselessMod.MOD_ID + ":useless_oak_bees_0002");
    public static final ResourceKey<PlacedFeature> USELESS_OAK_BEES_002 = PlacementUtils.createKey(UselessMod.MOD_ID + ":useless_oak_bees_002");
    public static final ResourceKey<PlacedFeature> FANCY_USELESS_OAK_BEES_0002 = PlacementUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees_0002");
    public static final ResourceKey<PlacedFeature> FANCY_USELESS_OAK_BEES_002 = PlacementUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees_002");
    public static final ResourceKey<PlacedFeature> FANCY_USELESS_OAK_BEES = PlacementUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, USELESS_OAK_BEES_0002, configuredFeatures.getOrThrow(UselessTreeFeatures.USELESS_OAK_BEES_0002), PlacementUtils.filteredByBlockSurvival(UselessBlocks.USELESS_OAK_SAPLING.get()));
        PlacementUtils.register(context, USELESS_OAK_BEES_002, configuredFeatures.getOrThrow(UselessTreeFeatures.USELESS_OAK_BEES_002), PlacementUtils.filteredByBlockSurvival(UselessBlocks.USELESS_OAK_SAPLING.get()));
        PlacementUtils.register(context, FANCY_USELESS_OAK_BEES_0002, configuredFeatures.getOrThrow(UselessTreeFeatures.FANCY_USELESS_OAK_BEES_0002), PlacementUtils.filteredByBlockSurvival(UselessBlocks.USELESS_OAK_SAPLING.get()));
        PlacementUtils.register(context, FANCY_USELESS_OAK_BEES_002, configuredFeatures.getOrThrow(UselessTreeFeatures.FANCY_USELESS_OAK_BEES_002), PlacementUtils.filteredByBlockSurvival(UselessBlocks.USELESS_OAK_SAPLING.get()));
        PlacementUtils.register(context, FANCY_USELESS_OAK_BEES, configuredFeatures.getOrThrow(UselessTreeFeatures.FANCY_USELESS_OAK_BEES), PlacementUtils.filteredByBlockSurvival(UselessBlocks.USELESS_OAK_SAPLING.get()));
    }
}
