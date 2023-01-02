package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

public final class ModRecipeTypes {
    static void register() {
    }

    public static final RegistryObject<RecipeType<CoffeeRecipe>> COFFEE = Registration.RECIPE_TYPES.register("coffee", () -> new RecipeType<>() {
    });
}
