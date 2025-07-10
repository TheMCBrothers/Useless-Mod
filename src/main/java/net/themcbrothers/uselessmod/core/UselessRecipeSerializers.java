package net.themcbrothers.uselessmod.core;

import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;
import net.themcbrothers.uselessmod.world.item.crafting.LightSwitchConvertRecipe;

import java.util.function.Supplier;

import static net.themcbrothers.uselessmod.core.Registration.RECIPE_SERIALIZERS;

public final class UselessRecipeSerializers {
    static void register() {
    }

    public static final Supplier<RecipeSerializer<CoffeeRecipe>> COFFEE =
            RECIPE_SERIALIZERS.register("coffee", CoffeeRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<LightSwitchConvertRecipe>> LIGHT_SWITCH_CONVERT =
            RECIPE_SERIALIZERS.register("light_switch_convert", () -> new CustomRecipe.Serializer<>(LightSwitchConvertRecipe::new));
}
