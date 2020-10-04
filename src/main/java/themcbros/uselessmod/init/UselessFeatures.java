package themcbros.uselessmod.init;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.world.feature.EndOreFeature;

import java.util.OptionalInt;
import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class UselessFeatures {

    public static final Feature<OreFeatureConfig> END_ORE = registerFeature("end_ore", new EndOreFeature(OreFeatureConfig.field_236566_a_));

    // Trees
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> USELESS_TREE = register("useless",
            Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.USELESS_LOG),
                    new SimpleBlockStateProvider(States.USELESS_LEAVES),
                    new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1)))
                    .setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> USELESS_TREE_BEES = register("useless_bees_005",
            Feature.TREE.withConfiguration(USELESS_TREE.func_242767_c()
                    .func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT))));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_USELESS_TREE = register("fancy_useless",
            Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.USELESS_LOG),
                    new SimpleBlockStateProvider(States.USELESS_LEAVES),
                    new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                    new FancyTrunkPlacer(3, 11, 0),
                    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                    .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FANCY_USELESS_TREE_BEES = register("fancy_useless_bees_005",
            Feature.TREE.withConfiguration(FANCY_USELESS_TREE.func_242767_c()
                    .func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT))));
    public static final ConfiguredFeature<?, ?> USELESS_TREES = register("useless_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(FANCY_USELESS_TREE.withChance(0.2F), USELESS_TREE_BEES.withChance(0.1F)), USELESS_TREE)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.field_242902_f.configure(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));

    // Flowers
    private static final ImmutableList<Supplier<ConfiguredFeature<?, ?>>> ROSES = ImmutableList.of(
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.USELESS_ROSE), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()),
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.RED_ROSE), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()),
            () -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.BLUE_ROSE), new SimpleBlockPlacer())).tries(64).func_227317_b_().build()));
    public static final ConfiguredFeature<?, ?> USELESS_ROSES = register("useless_roses", Feature.SIMPLE_RANDOM_SELECTOR.withConfiguration(new SingleRandomFeature(ROSES)).func_242730_a(FeatureSpread.func_242253_a(-3, 4)).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5));

    // Ores
    public static final ConfiguredFeature<?, ?> USELESS_ORE = register("ore_useless", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, States.USELESS_ORE, 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
    public static final ConfiguredFeature<?, ?> USELESS_ORE_NETHER = register("ore_useless_nether", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241883_b, States.USELESS_ORE_NETHER, 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
    public static final ConfiguredFeature<?, ?> USELESS_ORE_END = register("ore_useless_end", END_ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, States.USELESS_ORE_END, 9)).func_242733_d(64).func_242728_a().func_242731_b(20));
    public static final ConfiguredFeature<?, ?> SUPER_USELESS_ORE = register("ore_super_useless", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, States.SUPER_USELESS_ORE, 9)).func_242733_d(32).func_242728_a().func_242731_b(2));
    public static final ConfiguredFeature<?, ?> SUPER_USELESS_ORE_NETHER = register("ore_super_useless_nether", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241883_b, States.SUPER_USELESS_ORE_NETHER, 9)).func_242733_d(32).func_242728_a().func_242731_b(2));
    public static final ConfiguredFeature<?, ?> SUPER_USELESS_ORE_END = register("ore_super_useless_end", END_ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, States.SUPER_USELESS_ORE_END, 9)).func_242733_d(32).func_242728_a().func_242731_b(2));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String identifier,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, UselessMod.rl(identifier), configuredFeature);
    }

    private static <FC extends IFeatureConfig> Feature<FC> registerFeature(String identifier, Feature<FC> feature) {
        return Registry.register(Registry.FEATURE, UselessMod.rl(identifier), feature);
    }

    public static void init() {
    }

    static final class States {
        protected static final BlockState USELESS_LOG = BlockInit.USELESS_LOG.get().getDefaultState();
        protected static final BlockState USELESS_LEAVES = BlockInit.USELESS_LEAVES.get().getDefaultState();
        protected static final BlockState USELESS_ORE = BlockInit.USELESS_ORE.get().getDefaultState();
        protected static final BlockState USELESS_ORE_NETHER = BlockInit.USELESS_ORE_NETHER.get().getDefaultState();
        protected static final BlockState USELESS_ORE_END = BlockInit.USELESS_ORE_END.get().getDefaultState();
        protected static final BlockState SUPER_USELESS_ORE = BlockInit.SUPER_USELESS_ORE.get().getDefaultState();
        protected static final BlockState SUPER_USELESS_ORE_NETHER = BlockInit.SUPER_USELESS_ORE_NETHER.get().getDefaultState();
        protected static final BlockState SUPER_USELESS_ORE_END = BlockInit.SUPER_USELESS_ORE_END.get().getDefaultState();
        protected static final BlockState RED_ROSE = BlockInit.RED_ROSE.get().getDefaultState();
        protected static final BlockState BLUE_ROSE = BlockInit.BLUE_ROSE.get().getDefaultState();
        protected static final BlockState USELESS_ROSE = BlockInit.USELESS_ROSE.get().getDefaultState();
    }

}
