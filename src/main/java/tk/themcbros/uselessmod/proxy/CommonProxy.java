package tk.themcbros.uselessmod.proxy;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.blocks.LightSwitchBlockBlock;
import tk.themcbros.uselessmod.closet.BeddingRegistryEvent;
import tk.themcbros.uselessmod.closet.ClosetRegistry;
import tk.themcbros.uselessmod.config.Config;
import tk.themcbros.uselessmod.lists.ModBiomes;
import tk.themcbros.uselessmod.lists.ModEntityTypes;
import tk.themcbros.uselessmod.lists.VanillaCompat;
import tk.themcbros.uselessmod.world.FlowerGeneration;
import tk.themcbros.uselessmod.world.OreGeneration;

public class CommonProxy {

	public CommonProxy() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		
		Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve("uselessmod-common.toml").toString());
	}
	
	protected void preInit(FMLCommonSetupEvent event) {
		UselessMod.LOGGER.debug("CommonProxy preInit method");
		
		LightSwitchBlockBlock.init();
		
		OreGeneration.setupOreGeneration();
		OreGeneration.setupNetherOreGeneration();
		
		FlowerGeneration.setupFlowerGeneration();
		
		VanillaCompat.register();
	}
	
	protected void init(InterModEnqueueEvent event) {
		UselessMod.LOGGER.debug("CommonProxy init method");
	}
	
	protected void postInit(InterModProcessEvent event) {
		UselessMod.LOGGER.debug("CommonProxy postInit method");
		FMLJavaModLoadingContext.get().getModEventBus().post(new BeddingRegistryEvent(ClosetRegistry.CASINGS, ClosetRegistry.BEDDINGS));
		
		ModEntityTypes.registerEntityWorldSpawns();
		ModBiomes.addBiomesToManager();
	}
	
}
