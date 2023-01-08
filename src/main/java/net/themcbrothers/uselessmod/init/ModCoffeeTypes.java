package net.themcbrothers.uselessmod.init;

import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.api.CoffeeType;

import java.util.Collections;

public class ModCoffeeTypes {
    static void register() {
    }

    public static final RegistryObject<CoffeeType> BLACK = Registration.COFFEE_TYPES.register("black",
            () -> new CoffeeType(new CoffeeType.Properties(0x44211A, Collections.emptySet())));
    public static final RegistryObject<CoffeeType> MILK = Registration.COFFEE_TYPES.register("milk",
            () -> new CoffeeType(new CoffeeType.Properties(0x96493A, Collections.emptySet())));
    public static final RegistryObject<CoffeeType> SUGAR = Registration.COFFEE_TYPES.register("sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x7F3E31, Collections.emptySet()).foil()));
    public static final RegistryObject<CoffeeType> MILK_SUGAR = Registration.COFFEE_TYPES.register("milk_sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x99493B, Collections.emptySet())));
}
