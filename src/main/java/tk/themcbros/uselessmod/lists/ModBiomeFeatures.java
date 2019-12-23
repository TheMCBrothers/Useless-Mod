package tk.themcbros.uselessmod.lists;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;

public class ModBiomeFeatures {

    private static final BlockState USELESS_LOG = ModBlocks.USELESS_LOG.getDefaultState();
    private static final BlockState USELESS_LEAVES = ModBlocks.USELESS_LEAVES.getDefaultState();
    private static final BlockState WILD_USELESS_WHEAT = ModBlocks.WILD_USELESS_WHEAT.getMaturePlant();
    private static final BlockState WILD_COFFEE_SEEDS = ModBlocks.WILD_COFFEE_SEEDS.getMaturePlant();

    public static final TreeFeatureConfig FANCY_USELESS_TREE = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(0, 0))).func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.01F))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.USELESS_SAPLING).func_225568_b_();
    public static final TreeFeatureConfig USELESS_TREE = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(USELESS_LOG), new SimpleBlockStateProvider(USELESS_LEAVES), new BlobFoliagePlacer(2, 0))).func_225569_d_(4).func_227354_b_(2).func_227360_i_(3).func_227352_a_().func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F))).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.USELESS_SAPLING).func_225568_b_();
    public static final BlockClusterFeatureConfig USELESS_WHEAT = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WILD_USELESS_WHEAT), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
    public static final BlockClusterFeatureConfig COFFEE_SEEDS = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WILD_COFFEE_SEEDS), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
}
