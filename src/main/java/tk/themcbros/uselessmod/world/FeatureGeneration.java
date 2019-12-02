package tk.themcbros.uselessmod.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BigTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import tk.themcbros.uselessmod.lists.ModBiomes;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class FeatureGeneration {
	
	public static void setupFeatureGeneration() {

		ModBiomes.USELESS_BIOME.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(ModBlocks.USELESS_WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));
	}

}
