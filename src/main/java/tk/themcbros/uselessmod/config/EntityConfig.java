package tk.themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EntityConfig {

	public static ForgeConfigSpec.BooleanValue useless_entity_enabled;
	public static ForgeConfigSpec.BooleanValue grenade_enabled;
	
	public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
		common.comment("This is the ore generation config");
		useless_entity_enabled = common
				.comment("Decide if you want the Useless Entity to spawn in your world")
				.define("entity.useless_entity_enabled", true);
		grenade_enabled = common
				.comment("Decide if you want grenades enabled")
				.define("entity.grenade_enabled", true);
	}
	
}
