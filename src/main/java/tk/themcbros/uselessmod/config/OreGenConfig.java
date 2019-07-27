package tk.themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGenConfig {

	public static ForgeConfigSpec.IntValue useless_ore_chance;
	public static ForgeConfigSpec.IntValue super_useless_ore_chance;
	public static ForgeConfigSpec.BooleanValue generate_overworld;
	public static ForgeConfigSpec.BooleanValue generate_nether;
	public static ForgeConfigSpec.BooleanValue generate_end;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("This is the ore generation config");
		useless_ore_chance = server
				.comment("Maximum number of ore veins of the Useless Ore in one chunk.")
				.defineInRange("oregen.useless_ore_chance", 15, 1, 100000);
		super_useless_ore_chance = server
				.comment("Maximum number of ore veins of the Super Useless Ore in one chunk.")
				.defineInRange("oregen.super_useless_ore_chance", 5, 1, 100000);
		generate_overworld = server
				.comment("Decide if you want Useless Mod ores to generate in the overworld")
				.define("oregen.generate_overworld", true);
		generate_nether = server
				.comment("Decide if you want Useless Mod ores to generate in the nether")
				.define("oregen.generate_nether", true);
		generate_end = server
				.comment("Decide if you want Useless Mod ores to generate in the end")
				.define("oregen.generate_end", true);
	}
	
}
