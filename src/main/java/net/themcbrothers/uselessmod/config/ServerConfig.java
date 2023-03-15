package net.themcbrothers.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fluids.FluidType;

public final class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // World Generation
    public static final ForgeConfigSpec.BooleanValue ORE_GEN_OVERWORLD;
    public static final ForgeConfigSpec.BooleanValue ORE_GEN_NETHER;
    public static final ForgeConfigSpec.BooleanValue ORE_GEN_END;

    // Coffee Machine
    public static final ForgeConfigSpec.IntValue COFFEE_MACHINE_ENERGY_CAPACITY;
    public static final ForgeConfigSpec.IntValue COFFEE_MACHINE_ENERGY_TRANSFER;
    public static final ForgeConfigSpec.IntValue COFFEE_MACHINE_ENERGY_PER_TICK;
    public static final ForgeConfigSpec.IntValue COFFEE_MACHINE_WATER_CAPACITY;
    public static final ForgeConfigSpec.IntValue COFFEE_MACHINE_MILK_CAPACITY;

    static {
        BUILDER.push("oregen");
        ORE_GEN_OVERWORLD = BUILDER.comment("Should ores generate in the overworld and other dimensions?").define("oreGenOverworld", true);
        ORE_GEN_NETHER = BUILDER.comment("Should ores generate in the nether?").define("oreGenNether", true);
        ORE_GEN_END = BUILDER.comment("Should ores generate in the end?").define("oreGenEnd", true);
        BUILDER.pop();

        BUILDER.push("coffee_machine");
        COFFEE_MACHINE_ENERGY_CAPACITY = BUILDER.comment("Energy capacity of the Coffee Machine").defineInRange("energyCapacity", 10_000, 1000, Short.MAX_VALUE);
        COFFEE_MACHINE_ENERGY_TRANSFER = BUILDER.comment("Max Energy transfer rate").defineInRange("energyTransfer", 1000, 50, Short.MAX_VALUE);
        COFFEE_MACHINE_ENERGY_PER_TICK = BUILDER.comment("Energy consumed by the Coffee Machine while running").defineInRange("energyPerTick", 15, 1, Short.MAX_VALUE);
        COFFEE_MACHINE_WATER_CAPACITY = BUILDER.comment("Water tank capacity of the Coffee Machine").defineInRange("waterTankCapacity", 4000, FluidType.BUCKET_VOLUME, 16_000);
        COFFEE_MACHINE_MILK_CAPACITY = BUILDER.comment("Milk tank capacity of the Coffee Machine").defineInRange("milkTankCapacity", 4000, FluidType.BUCKET_VOLUME, 16_000);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
