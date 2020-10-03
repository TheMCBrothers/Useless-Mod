package themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    // World Generation
    public final ForgeConfigSpec.BooleanValue oreGenOverworld;
    public final ForgeConfigSpec.BooleanValue oreGenNether;
    public final ForgeConfigSpec.BooleanValue oreGenEnd;

    // Coffee Machine
    public final ForgeConfigSpec.IntValue coffeeMachineEnergyCapacity;
    public final ForgeConfigSpec.IntValue coffeeMachineEnergyPerTick;
    public final ForgeConfigSpec.IntValue coffeeMachineWaterCapacity;
    public final ForgeConfigSpec.IntValue coffeeMachineRecipeWaterAmount;

    // Recipes
    public final ForgeConfigSpec.BooleanValue disableWallClosetRecipes;
    public final ForgeConfigSpec.BooleanValue disableCoffeeMachineRecipes;

    public ServerConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Welcome to the config file!");
        builder.push("oregen");
        this.oreGenOverworld = builder.comment("Should ores generate in the overworld and other dimensions?").define("oreGenOverworld", true);
        this.oreGenNether = builder.comment("Should ores generate in the nether?").define("oreGenNether", true);
        this.oreGenEnd = builder.comment("Should ores generate in the end?").define("oreGenEnd", true);
        builder.pop();

        builder.push("coffee_machine");
        this.coffeeMachineEnergyCapacity = builder.comment("Energy capacity of the Coffee Machine").defineInRange("energyCapacity", 10_000, 150, 200_000);
        this.coffeeMachineEnergyPerTick = builder.comment("Energy consumed by the Coffee Machine while running").defineInRange("energyPerTick", 15, 1, 200_000);
        this.coffeeMachineWaterCapacity = builder.comment("Water tank capacity of the Coffee Machine").defineInRange("waterTankCapacity", 2000, 500, 16000);
        this.coffeeMachineRecipeWaterAmount = builder.comment("Water amount used per operation").defineInRange("waterAmountPerOperation", 250, 1, 16000);
        builder.pop();

        builder.push("recipes");
        this.disableWallClosetRecipes = builder.comment("Disable wall closet recipes?").define("disableWallClosetRecipes", false);
        this.disableCoffeeMachineRecipes = builder.comment("Disable coffee machine recipes?").define("disableCoffeeMachineRecipes", false);
        builder.pop();
    }

}
