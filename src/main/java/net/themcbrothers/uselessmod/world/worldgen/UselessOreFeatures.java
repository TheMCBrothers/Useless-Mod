package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.themcbrothers.uselessmod.init.ModBlocks;

import java.util.List;

import static net.minecraft.data.worldgen.features.OreFeatures.*;

public final class UselessOreFeatures {
    public static final RuleTest END_ORE_REPLACEABLES = new TagMatchTest(Tags.Blocks.END_STONES);

    public static final List<OreConfiguration.TargetBlockState> ORE_USELESS_TARGET_LIST = List.of(
            OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(END_ORE_REPLACEABLES, ModBlocks.END_USELESS_ORE.get().defaultBlockState()));

    public static final ConfiguredFeature<?, ?> ORE_USELESS = FeatureUtils.register("ore_useless", Feature.ORE.configured(new OreConfiguration(ORE_USELESS_TARGET_LIST, 9)));
}
