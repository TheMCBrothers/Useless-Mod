package tk.themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MachineConfig {

	public static ForgeConfigSpec.IntValue coffee_machine_rf_per_tick;
	public static ForgeConfigSpec.DoubleValue coffee_machine_water_per_tick;
	public static ForgeConfigSpec.IntValue coffee_machine_coffee_beans_per_coffee;
	public static ForgeConfigSpec.IntValue coffee_machine_water_capacity;
	public static ForgeConfigSpec.IntValue coffee_machine_coffee_beans_capacity;
	
	public static ForgeConfigSpec.IntValue crusher_rf_per_tick;
	public static ForgeConfigSpec.IntValue furnace_rf_per_tick;
	public static ForgeConfigSpec.IntValue compressor_rf_per_tick;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("This is the ore generation config");
		coffee_machine_rf_per_tick = server
				.comment("How much RF per tick the coffee machine uses.")
				.defineInRange("machines.coffee_machine.rf_per_tick", 10, 1, 1000);
		coffee_machine_water_per_tick = server
				.comment("How much water per tick the coffee machine uses.")
				.defineInRange("machines.coffee_machine.water_per_tick", 1.5D, 1D, 1000D);
		coffee_machine_coffee_beans_per_coffee = server
				.comment("How many coffee beans per coffee the coffee machine uses.")
				.defineInRange("machines.coffee_machine.beans_per_coffee", 2, 1, 64);
		coffee_machine_water_capacity = server
				.comment("Capacity of the water tank in the coffee machine")
				.defineInRange("machines.coffee_machine.water_tank_capacity", 4000, 1000, 16000);
		coffee_machine_coffee_beans_capacity = server
				.comment("Capacity of the coffee beans in the coffee machine")
				.defineInRange("machines.coffee_machine.beans_tank_capacity", 64, 16, 512);
		
		crusher_rf_per_tick = server
				.comment("How much RF per tick the electric crusher uses.")
				.defineInRange("machines.electric_crusher.rf_per_tick", 15, 1, 1000);
		furnace_rf_per_tick = server
				.comment("How much RF per tick the electric furnace uses.")
				.defineInRange("machines.electric_furnace.rf_per_tick", 15, 1, 1000);
		compressor_rf_per_tick = server
				.comment("How much RF per tick the compressor uses.")
				.defineInRange("machines.compressor.rf_per_tick", 15, 1, 1000);
	}
	
}
