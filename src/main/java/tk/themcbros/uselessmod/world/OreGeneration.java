package tk.themcbros.uselessmod.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.config.OreGenConfig;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.world.feature.EndOreFeature;

public class OreGeneration {

    private static final EndOreFeature END_ORE = new EndOreFeature(OreFeatureConfig::deserialize);

    public static void setupOreGeneration() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (OreGenConfig.generate_overworld.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.USELESS_ORE.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 0, 0, 128))));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SUPER_USELESS_ORE.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 0, 0, 32))));
            }
            if (OreGenConfig.generate_nether.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.USELESS_ORE_NETHER.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 10, 20, 128))));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.SUPER_USELESS_ORE_NETHER.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 10, 20, 64))));
            }
            if (OreGenConfig.generate_end.get()) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, END_ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.USELESS_ORE_END.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 0, 0, 256))));
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, END_ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SUPER_USELESS_ORE_END.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 0, 0, 256))));
            }
        }
    }

}
