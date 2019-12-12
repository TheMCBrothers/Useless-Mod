package tk.themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MachineConfig {

	public static ForgeConfigSpec.IntValue coffee_machine_rf_per_tick;
	public static ForgeConfigSpec.DoubleValue coffee_machine_water_per_tick;
	public static ForgeConfigSpec.IntValue coffee_machine_coffee_beans_per_coffee;
	public static ForgeConfigSpec.IntValue coffee_machine_water_capacity;
	
	public static ForgeConfigSpec.IntValue crusher_rf_per_tick;
	public static ForgeConfigSpec.IntValue furnace_rf_per_tick;
	public static ForgeConfigSpec.IntValue compressor_rf_per_tick;
	public static ForgeConfigSpec.IntValue magma_crucible_rf_per_tick;

	public static ForgeConfigSpec.IntValue magma_crucible_tank_capacity;
	public static ForgeConfigSpec.IntValue fluid_tank_capacity;

	public static ForgeConfigSpec.IntValue lava_generator_tank_capacity;
	public static ForgeConfigSpec.IntValue lava_generator_ticks_per_mb;
	public static ForgeConfigSpec.IntValue lava_generator_rf_per_tick;

	public static ForgeConfigSpec.IntValue energy_cable_capacity;
	
	public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
		common.comment("This is the machine config");

		// Coffee Machine
		coffee_machine_rf_per_tick = common
				.comment("How much RF per tick the coffee machine uses.")
				.defineInRange("machines.coffee_machine.rf_per_tick", 10, 1, 1000);
		coffee_machine_water_per_tick = common
				.comment("How much water per tick the coffee machine uses.")
				.defineInRange("machines.coffee_machine.water_per_tick", 1.5D, 1D, 1000D);
		coffee_machine_coffee_beans_per_coffee = common
				.comment("How many coffee beans per coffee the coffee machine uses.")
				.defineInRange("machines.coffee_machine.beans_per_coffee", 2, 1, 64);
		coffee_machine_water_capacity = common
				.comment("Capacity of the water tank in the coffee machine")
				.defineInRange("machines.coffee_machine.water_tank_capacity", 2000, 1000, 16000);

		// Crusher / Furnace / Compressor
		crusher_rf_per_tick = common
				.comment("How much RF per tick the electric crusher uses.")
				.defineInRange("machines.electric_crusher.rf_per_tick", 15, 1, 1000);
		furnace_rf_per_tick = common
				.comment("How much RF per tick the electric furnace uses.")
				.defineInRange("machines.electric_furnace.rf_per_tick", 15, 1, 1000);
		compressor_rf_per_tick = common
				.comment("How much RF per tick the compressor uses.")
				.defineInRange("machines.compressor.rf_per_tick", 15, 1, 1000);

		// Magma Crucible
		magma_crucible_rf_per_tick = common
				.comment("How much RF per tick the magma crucible uses.")
				.defineInRange("machines.magma_crucible.rf_per_tick", 15, 1, 1000);
		magma_crucible_tank_capacity = common
				.comment("How many mB can be stored in a magma crucible.")
				.defineInRange("machines.magma_crucible.tank_capacity", 4000, 1000, 10000);

		// Lava Generator
		lava_generator_tank_capacity = common
				.comment("Tank capacity of the lava generator.")
				.defineInRange("machines.lava_generator.tank_capacity", 4000, 1000, 10000);
		lava_generator_ticks_per_mb = common
				.comment("How many ticks between one mB.")
				.defineInRange("machines.lava_generator.ticks_per_mb", 10, 1, 200);
		lava_generator_rf_per_tick = common
				.comment("How much RF is produced by lava generator")
				.defineInRange("machines.lava_generator.rf_per_tick", 120, 1, 10000);

		// Energy Cable
		energy_cable_capacity = common
				.comment("How much FE is stored in one Energy Cable")
				.defineInRange("energy_cable.capacity", 1000, 100, 10000);

		// Other
		fluid_tank_capacity = common
				.comment("How much mB can be stored in a fluid tank.")
				.defineInRange("fluid_tank.capacity", 10000, 1000, 100000);
	}
	
}
