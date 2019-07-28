package tk.themcbros.uselessmod.lists;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.world.feature.UselessCropsFeature;
import tk.themcbros.uselessmod.world.feature.UselessFlowersFeature;
import tk.themcbros.uselessmod.world.structures.UselessHutStructure;

public class ModFeatures {

	public static final Feature<NoFeatureConfig> USELESS_FLOWERS = new UselessFlowersFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> USELESS_CROPS = new UselessCropsFeature(NoFeatureConfig::deserialize);
	public static final UselessHutStructure USELESS_HUT = new UselessHutStructure(NoFeatureConfig::deserialize);
	
	public static void register() {
		ForgeRegistries.FEATURES.register(USELESS_FLOWERS.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "useless_flowers")));
		ForgeRegistries.FEATURES.register(USELESS_CROPS.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "useless_crops")));
	}
	
}
