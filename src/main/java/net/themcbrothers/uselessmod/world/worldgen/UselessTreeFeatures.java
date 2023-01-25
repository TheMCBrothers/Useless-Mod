package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
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
import net.themcbrothers.uselessmod.init.ModBlocks;

import java.util.List;
import java.util.OptionalInt;

public final class UselessTreeFeatures {
    private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
    private static final BeehiveDecorator BEEHIVE_002 = new BeehiveDecorator(0.02F);
    private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);
    private static final BeehiveDecorator BEEHIVE = new BeehiveDecorator(1.0F);
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> USELESS_OAK = FeatureUtils.register("useless_oak", Feature.TREE, createUselessOak().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_USELESS_OAK = FeatureUtils.register("fancy_useless_oak", Feature.TREE, createFancyUselessOak().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> USELESS_OAK_BEES_0002 = FeatureUtils.register("useless_oak_bees_0002", Feature.TREE, createUselessOak().decorators(List.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> USELESS_OAK_BEES_002 = FeatureUtils.register("useless_oak_bees_002", Feature.TREE, createUselessOak().decorators(List.of(BEEHIVE_002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> USELESS_OAK_BEES_005 = FeatureUtils.register("useless_oak_bees_005", Feature.TREE, createUselessOak().decorators(List.of(BEEHIVE_005)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_USELESS_OAK_BEES_0002 = FeatureUtils.register("fancy_useless_oak_bees_0002", Feature.TREE, createFancyUselessOak().decorators(List.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_USELESS_OAK_BEES_002 = FeatureUtils.register("fancy_useless_oak_bees_002", Feature.TREE, createFancyUselessOak().decorators(List.of(BEEHIVE_002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_USELESS_OAK_BEES_005 = FeatureUtils.register("fancy_useless_oak_bees_005", Feature.TREE, createFancyUselessOak().decorators(List.of(BEEHIVE_005)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_USELESS_OAK_BEES = FeatureUtils.register("fancy_useless_oak_bees", Feature.TREE, createFancyUselessOak().decorators(List.of(BEEHIVE)).build());

    private static TreeConfiguration.TreeConfigurationBuilder createUselessOak() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.USELESS_OAK_LOG.get()), new StraightTrunkPlacer(5, 1, 0), BlockStateProvider.simple(ModBlocks.USELESS_OAK_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyUselessOak() {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.USELESS_OAK_LOG.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(ModBlocks.USELESS_OAK_LEAVES.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();
    }
}
