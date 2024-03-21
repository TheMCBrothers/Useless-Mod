package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;

import java.util.List;

public final class UselessOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_USELESS = FeatureUtils.createKey(UselessMod.MOD_ID + ":ore_useless");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SUPER_USELESS = FeatureUtils.createKey(UselessMod.MOD_ID + ":ore_super_useless");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest ruleTestStone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest ruleTestDeepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest ruleTestNetherrack = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest ruleTestEndStone = new TagMatchTest(Tags.Blocks.END_STONES);

        List<OreConfiguration.TargetBlockState> oreTargetsUseless = List.of(
                OreConfiguration.target(ruleTestStone, UselessBlocks.USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestDeepslate, UselessBlocks.DEEPSLATE_USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestNetherrack, UselessBlocks.NETHER_USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestEndStone, UselessBlocks.END_USELESS_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> oreTargetsSuperUseless = List.of(
                OreConfiguration.target(ruleTestStone, UselessBlocks.SUPER_USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestDeepslate, UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestNetherrack, UselessBlocks.NETHER_SUPER_USELESS_ORE.get().defaultBlockState()),
                OreConfiguration.target(ruleTestEndStone, UselessBlocks.END_SUPER_USELESS_ORE.get().defaultBlockState())
        );

        FeatureUtils.register(context, ORE_USELESS, Feature.ORE, new OreConfiguration(oreTargetsUseless, 9));
        FeatureUtils.register(context, ORE_SUPER_USELESS, Feature.ORE, new OreConfiguration(oreTargetsSuperUseless, 4));
    }
}
