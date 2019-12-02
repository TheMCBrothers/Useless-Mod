package tk.themcbros.uselessmod.lists;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.BiomeConfig;
import tk.themcbros.uselessmod.world.biome.UselessBiome;

import java.util.List;

@ObjectHolder(UselessMod.MOD_ID)
public class ModBiomes {

	private static final List<Biome> BIOMES = Lists.newArrayList();

	@ObjectHolder("useless_biome")
	public static final Biome USELESS_BIOME = register("useless_biome", new UselessBiome());

	private static <T extends Biome> T register(String registryName, T biome) {
		biome.setRegistryName(UselessMod.getId(registryName));
		BIOMES.add(biome);
		return biome;
	}
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<Biome> event) {
			BIOMES.forEach(event.getRegistry()::register);
			UselessMod.LOGGER.debug("Registered Biomes");
		}
	}
	
	public static void addBiomesToManager() {
		if(BiomeConfig.useless_biome_enabled.get()) {
			BiomeManager.addSpawnBiome(USELESS_BIOME);
			BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(USELESS_BIOME, BiomeConfig.useless_biome_weight.get()));
		}
	}
	
}
