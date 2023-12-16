package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeType;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

import java.util.function.Supplier;

public final class ModRecipeTypes {
    static void register() {
    }

    public static final Supplier<RecipeType<CoffeeRecipe>> COFFEE = Registration.RECIPE_TYPES.register("coffee", () -> new RecipeType<>() {
    });
}
