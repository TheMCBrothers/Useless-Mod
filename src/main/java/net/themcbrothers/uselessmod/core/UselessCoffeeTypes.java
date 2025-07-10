package net.themcbrothers.uselessmod.core;

import net.minecraft.world.item.component.Consumables;
import net.themcbrothers.uselessmod.api.CoffeeType;

import java.util.function.Supplier;

public class UselessCoffeeTypes {
    static void register() {
    }

    public static final Supplier<CoffeeType> BLACK = Registration.COFFEE_TYPES.register("black",
            () -> new CoffeeType(new CoffeeType.Properties(0x44211A, Consumables.DEFAULT_DRINK)));
    public static final Supplier<CoffeeType> MILK = Registration.COFFEE_TYPES.register("milk",
            () -> new CoffeeType(new CoffeeType.Properties(0x96493A, Consumables.MILK_BUCKET)));
    public static final Supplier<CoffeeType> SUGAR = Registration.COFFEE_TYPES.register("sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x7F3E31, Consumables.DEFAULT_DRINK).foil()));
    public static final Supplier<CoffeeType> MILK_SUGAR = Registration.COFFEE_TYPES.register("milk_sugar",
            () -> new CoffeeType(new CoffeeType.Properties(0x99493B, Consumables.DEFAULT_DRINK)));
}
