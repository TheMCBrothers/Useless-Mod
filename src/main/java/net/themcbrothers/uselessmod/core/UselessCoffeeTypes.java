package net.themcbrothers.uselessmod.core;

import net.themcbrothers.uselessmod.api.CoffeeType;

import java.util.Collections;
import java.util.function.Supplier;

public class UselessCoffeeTypes {
    static void register() {
    }

    public static final Supplier<CoffeeType> BLACK = Registration.COFFEE_TYPES.register("black",
            () -> new CoffeeType(new CoffeeType.Properties(0x44211A, Collections.emptySet())));
    public static final Supplier<CoffeeType> MILK = Registration.COFFEE_TYPES.register("milk",
            () -> new CoffeeType(new CoffeeType.Properties(0x96493A, Collections.emptySet())));
    public static final Supplier<CoffeeType> SUGAR = Registration.COFFEE_TYPES.register("sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x7F3E31, Collections.emptySet()).foil()));
    public static final Supplier<CoffeeType> MILK_SUGAR = Registration.COFFEE_TYPES.register("milk_sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x99493B, Collections.emptySet())));
}
