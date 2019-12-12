package tk.themcbros.uselessmod.proxy;

import com.blakebr0.ironjetpacks.registry.Jetpack;
import com.blakebr0.ironjetpacks.registry.JetpackRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
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
import tk.themcbros.uselessmod.compat.farmingforblockheads.FarmingForBlockheadsCompat;
import tk.themcbros.uselessmod.compat.top.TheOneProbeSupport;
import tk.themcbros.uselessmod.config.Config;
import tk.themcbros.uselessmod.handler.ForgeEventHandlers;
import tk.themcbros.uselessmod.handler.UselessPlayerEvents;
import tk.themcbros.uselessmod.lists.ModBiomes;
import tk.themcbros.uselessmod.lists.ModEntityTypes;
import tk.themcbros.uselessmod.lists.ModItems;
import tk.themcbros.uselessmod.lists.VanillaCompat;
import tk.themcbros.uselessmod.world.FeatureGeneration;
import tk.themcbros.uselessmod.world.OreGeneration;

public class CommonProxy {

	public CommonProxy() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		
		Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve("uselessmod-common.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(new UselessPlayerEvents());
	}
	
	protected void preInit(FMLCommonSetupEvent event) {
		UselessMod.LOGGER.debug("CommonProxy preInit method");

		LightSwitchBlockBlock.init();

		OreGeneration.setupOreGeneration();
		OreGeneration.setupNetherOreGeneration();

		FeatureGeneration.setupFeatureGeneration();

		VanillaCompat.register();

		if (ModList.get().isLoaded("bluepower")) {
			UselessMod.LOGGER.info("Registering recycling recipes for BluePower");
			com.bluepowermod.api.recipe.IAlloyFurnaceRegistry furnaceRegistry = com.bluepowermod.api.BPApi.getInstance().getAlloyFurnaceRegistry();
			furnaceRegistry.addRecyclingRecipe(new ItemStack(ModItems.USELESS_INGOT));
			furnaceRegistry.addRecyclingRecipe(new ItemStack(ModItems.SUPER_USELESS_INGOT));
			furnaceRegistry.addRecyclingRecipe(new ItemStack(ModItems.USELESS_NUGGET));
			furnaceRegistry.addRecyclingRecipe(new ItemStack(ModItems.SUPER_USELESS_NUGGET));
		}

		if (ModList.get().isLoaded("ironjetpacks")) {
			Jetpack uselessJet = JetpackRegistry.createJetpack("useless", 3, 0xfff, 9, 30, "tag:forge:ingots/useless");
			Jetpack superUselessJet = JetpackRegistry.createJetpack("super_useless", 4, 0xfff, 9, 30, "tag:forge:ingots/super_useless");
			uselessJet.setStats(12000000, 350, 0.61D, 0.13D, 0.15D, 0.34D, 0.03D, 1.5D, 3.2D);
			superUselessJet.setStats(36000000, 720, 0.92D, 0.155D, 0.193D, 0.42D, 0.005D, 1.8D, 3.8D);
			JetpackRegistry.getInstance().register(uselessJet);
			JetpackRegistry.getInstance().register(superUselessJet);
		}

	}
	
	protected void init(InterModEnqueueEvent event) {
		UselessMod.LOGGER.debug("CommonProxy init method");
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());

		if (ModList.get().isLoaded("theoneprobe")) {
			InterModComms.sendTo("theoneprobe", "getTheOneProbe", TheOneProbeSupport::new);
		}

		if (ModList.get().isLoaded("farmingforblockheads")) {
			FarmingForBlockheadsCompat.init();
		}

	}
	
	protected void postInit(InterModProcessEvent event) {
		UselessMod.LOGGER.debug("CommonProxy postInit method");
		FMLJavaModLoadingContext.get().getModEventBus().post(new BeddingRegistryEvent(ClosetRegistry.CASINGS, ClosetRegistry.BEDDINGS));
		
		ModEntityTypes.registerEntityWorldSpawns();
		ModBiomes.addBiomesToManager();
	}
	
}
