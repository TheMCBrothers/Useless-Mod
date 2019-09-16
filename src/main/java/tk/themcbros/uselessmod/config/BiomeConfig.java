package tk.themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BiomeConfig {

	public static ForgeConfigSpec.BooleanValue useless_biome_enabled;
	public static ForgeConfigSpec.IntValue useless_biome_weight;
	
	public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
		common.comment("This is the biome config");
		
		useless_biome_enabled = common.comment("Defines if the useless biome is enabled").define("biome.useless_biome_enabled", true);
		useless_biome_weight = common.comment("The weight of the Useless Biome (Plains = 10)").defineInRange("biome.useless_biome_weight", 20, 1, 1000);
	}
	
}
