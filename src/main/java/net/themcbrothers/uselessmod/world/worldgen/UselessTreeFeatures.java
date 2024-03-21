package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;

import java.util.List;
import java.util.OptionalInt;

public final class UselessTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_OAK = FeatureUtils.createKey(UselessMod.MOD_ID + ":useless_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_USELESS_OAK = FeatureUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_OAK_BEES_0002 = FeatureUtils.createKey(UselessMod.MOD_ID + ":useless_oak_bees_0002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_OAK_BEES_002 = FeatureUtils.createKey(UselessMod.MOD_ID + ":useless_oak_bees_002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> USELESS_OAK_BEES_005 = FeatureUtils.createKey(UselessMod.MOD_ID + ":useless_oak_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_USELESS_OAK_BEES_0002 = FeatureUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees_0002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_USELESS_OAK_BEES_002 = FeatureUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees_002");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_USELESS_OAK_BEES_005 = FeatureUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_USELESS_OAK_BEES = FeatureUtils.createKey(UselessMod.MOD_ID + ":fancy_useless_oak_bees");

    private static TreeConfiguration.TreeConfigurationBuilder createUselessOak() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UselessBlocks.USELESS_OAK_LOG.get()), new StraightTrunkPlacer(5, 1, 0), BlockStateProvider.simple(UselessBlocks.USELESS_OAK_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyUselessOak() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UselessBlocks.USELESS_OAK_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(UselessBlocks.USELESS_OAK_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        final BeehiveDecorator beehive0002 = new BeehiveDecorator(0.002F);
        final BeehiveDecorator beehive002 = new BeehiveDecorator(0.02F);
        final BeehiveDecorator beehive005 = new BeehiveDecorator(0.05F);
        final BeehiveDecorator beehive = new BeehiveDecorator(1.0F);

        FeatureUtils.register(context, USELESS_OAK, Feature.TREE, createUselessOak().build());
        FeatureUtils.register(context, FANCY_USELESS_OAK, Feature.TREE, createFancyUselessOak().build());
        FeatureUtils.register(context, USELESS_OAK_BEES_0002, Feature.TREE, createUselessOak().decorators(List.of(beehive0002)).build());
        FeatureUtils.register(context, USELESS_OAK_BEES_002, Feature.TREE, createUselessOak().decorators(List.of(beehive002)).build());
        FeatureUtils.register(context, USELESS_OAK_BEES_005, Feature.TREE, createUselessOak().decorators(List.of(beehive005)).build());
        FeatureUtils.register(context, FANCY_USELESS_OAK_BEES_0002, Feature.TREE, createFancyUselessOak().decorators(List.of(beehive0002)).build());
        FeatureUtils.register(context, FANCY_USELESS_OAK_BEES_002, Feature.TREE, createFancyUselessOak().decorators(List.of(beehive002)).build());
        FeatureUtils.register(context, FANCY_USELESS_OAK_BEES_005, Feature.TREE, createFancyUselessOak().decorators(List.of(beehive005)).build());
        FeatureUtils.register(context, FANCY_USELESS_OAK_BEES, Feature.TREE, createFancyUselessOak().decorators(List.of(beehive)).build());
    }
}
