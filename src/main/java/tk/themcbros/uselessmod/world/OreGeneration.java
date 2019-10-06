package tk.themcbros.uselessmod.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.config.OreGenConfig;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.world.feature.CustomOreFeature;

public class OreGeneration {
	
	private static final CustomOreFeature END_ORE = new CustomOreFeature(null);
	
	public static void setupOreGeneration() {

		for (Biome biome : ForgeRegistries.BIOMES) {

			if (OreGenConfig.generate_overworld.get()) {

				biome.addFeature(Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(Feature.ORE,
								new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
										ModBlocks.USELESS_ORE.getDefaultState(), 5),
								Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 20, 50, 100)));

				biome.addFeature(Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(Feature.ORE,
								new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
										ModBlocks.SUPER_USELESS_ORE.getDefaultState(), 5),
								Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 10, 15, 30)));

			}
			
			if (OreGenConfig.generate_end.get()) {
				
				biome.addFeature(Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(END_ORE,
								new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
										ModBlocks.USELESS_ORE_END.getDefaultState(), 5),
								Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 0, 100, 200)));
				
				biome.addFeature(Decoration.UNDERGROUND_ORES,
						Biome.createDecoratedFeature(END_ORE,
								new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
										ModBlocks.SUPER_USELESS_ORE_END.getDefaultState(), 5),
								Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 0, 100, 200)));

			}

		}
		
		

	}
	
	public static void setupNetherOreGeneration() {
		if (OreGenConfig.generate_nether.get()) {

			OreFeature uselessOreFeature = new OreFeature(OreFeatureConfig::deserialize);
			Biomes.NETHER.addFeature(Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(uselessOreFeature,
							new OreFeatureConfig(FillerBlockType.NETHERRACK,
									ModBlocks.USELESS_ORE_NETHER.getDefaultState(), 5),
							Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.useless_ore_chance.get(), 20, 100, 128)));
			
			OreFeature superUselessOreFeature = new OreFeature(OreFeatureConfig::deserialize);
			Biomes.NETHER.addFeature(Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(superUselessOreFeature,
							new OreFeatureConfig(FillerBlockType.NETHERRACK,
									ModBlocks.SUPER_USELESS_ORE_NETHER.getDefaultState(), 5),
							Placement.COUNT_RANGE, new CountRangeConfig(OreGenConfig.super_useless_ore_chance.get(), 10, 30, 128)));

		}
	}

}
