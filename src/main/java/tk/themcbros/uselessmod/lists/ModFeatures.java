package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.BigTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.world.feature.UselessBigTreeFeature;
import tk.themcbros.uselessmod.world.feature.UselessCropsFeature;
import tk.themcbros.uselessmod.world.feature.UselessFlowersFeature;
import tk.themcbros.uselessmod.world.feature.UselessTreeFeature;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class ModFeatures {

	private static final List<Feature<?>> FEATURES = Lists.newArrayList();
	
	public static final Feature<NoFeatureConfig> USELESS_FLOWERS = register("useless_flowers", new UselessFlowersFeature(NoFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> USELESS_CROPS = register("useless_crops", new UselessCropsFeature(NoFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> USELESS_TREES = register("useless_trees", new UselessTreeFeature(NoFeatureConfig::deserialize, false, false));
	public static final Feature<NoFeatureConfig> FANCY_USELESS_TREE = register("fancy_useless_tree", new UselessBigTreeFeature(NoFeatureConfig::deserialize, false));
	
	private static final <T extends Feature<? extends IFeatureConfig>> T register(String regName, T feature) {
		feature.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, regName));
		FEATURES.add(feature);
		return feature;
	}
	
	@SubscribeEvent
	public static void onRegister(final RegistryEvent.Register<Feature<?>> event) {
		FEATURES.forEach(f -> event.getRegistry().register(f));
		UselessMod.LOGGER.info("Registered features");
	}
	
}
