package tk.themcbros.uselessmod.lists;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.world.biome.UselessBiome;

@ObjectHolder(UselessMod.MOD_ID)
public class ModBiomes {

	@ObjectHolder("useless_biome")
	public static final Biome USELESS_BIOME = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<Biome> event) {
			IForgeRegistry<Biome> registry = event.getRegistry();
			
			registry.register(new UselessBiome().setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "useless_biome")));

			UselessMod.LOGGER.debug("Registered Biomes");
		}
	}
	
	public static void addBiomesToManager() {
		BiomeManager.addSpawnBiome(USELESS_BIOME);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(USELESS_BIOME, 30));
	}
	
}
