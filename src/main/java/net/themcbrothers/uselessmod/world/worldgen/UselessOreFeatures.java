package net.themcbrothers.uselessmod.world.worldgen;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.init.ModBlocks;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.data.worldgen.features.OreFeatures.*;
import static net.themcbrothers.uselessmod.init.Registration.CONFIGURED_FEATURES;

public final class UselessOreFeatures {
    public static final RuleTest END_ORE_REPLACEABLES = new TagMatchTest(Tags.Blocks.END_STONES);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_USELESS_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(NETHERRACK, ModBlocks.NETHER_USELESS_ORE.get().defaultBlockState()),
            OreConfiguration.target(END_ORE_REPLACEABLES, ModBlocks.END_USELESS_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_USELESS = CONFIGURED_FEATURES.register("ore_useless", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_USELESS_TARGET_LIST.get(), 9)));

    public static void register() {
    }
}
