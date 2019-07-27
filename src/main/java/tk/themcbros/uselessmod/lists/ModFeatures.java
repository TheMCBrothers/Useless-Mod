package tk.themcbros.uselessmod.lists;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import tk.themcbros.uselessmod.world.feature.UselessFlowersFeature;
import tk.themcbros.uselessmod.world.structures.UselessHutStructure;

public class ModFeatures {

	public static final Feature<NoFeatureConfig> USELESS_FLOWERS = new UselessFlowersFeature(NoFeatureConfig::deserialize);
	public static final UselessHutStructure USELESS_HUT = new UselessHutStructure(NoFeatureConfig::deserialize);
	
}
