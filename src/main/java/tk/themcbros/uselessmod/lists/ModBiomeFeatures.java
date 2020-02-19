package tk.themcbros.uselessmod.lists;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;

public class ModBiomeFeatures {

    private static final BlockState USELESS_LOG = ModBlocks.USELESS_LOG.getDefaultState();
    private static final BlockState USELESS_LEAVES = ModBlocks.USELESS_LEAVES.getDefaultState();
    private static final BlockState WILD_USELESS_WHEAT = ModBlocks.WILD_USELESS_WHEAT.getMaturePlant();
    private static final BlockState WILD_COFFEE_SEEDS = ModBlocks.WILD_COFFEE_SEEDS.getMaturePlant();
    private static final BlockState RED_ROSE = ModBlocks.RED_ROSE.getDefaultState();
    private static final BlockState BLUE_ROSE = ModBlocks.BLUE_ROSE.getDefaultState();
    private static final BlockState STATE_TALL_USELESS_GRASS = ModBlocks.TALL_USELESS_GRASS.getDefaultState();
    private static final BlockState STATE_LARGE_USELESS_FERN = ModBlocks.LARGE_USELESS_FERN.getDefaultState();

    public static final TreeFeatureConfig USELESS_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final TreeFeatureConfig USELESS_TREE_WITH_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().decorators(ImmutableList.of(new BeehiveTreeDecorator(0.002F))).setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final TreeFeatureConfig USELESS_TREE_WITH_MORE_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final TreeFeatureConfig FANCY_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(0, 0))).setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final TreeFeatureConfig FANCY_TREE_WITH_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(0, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.002F))).setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final TreeFeatureConfig FANCY_TREE_WITH_MORE_BEEHIVES_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(0, 0))).decorators(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling(ModBlocks.USELESS_SAPLING).build();
    public static final BlockClusterFeatureConfig USELESS_WHEAT = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WILD_USELESS_WHEAT), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig COFFEE_SEEDS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WILD_COFFEE_SEEDS), new SimpleBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig USELESS_FLOWERS = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(RED_ROSE, 2).func_227407_a_(BLUE_ROSE, 1), new SimpleBlockPlacer())).tries(64).build();
    public static final BlockClusterFeatureConfig TALL_USELESS_GRASS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(STATE_TALL_USELESS_GRASS), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    public static final BlockClusterFeatureConfig LARGE_USELESS_FERN = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(STATE_LARGE_USELESS_FERN), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();

}
