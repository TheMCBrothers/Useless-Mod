package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

import static net.themcbrothers.uselessmod.init.Registration.RECIPE_SERIALIZERS;

public final class ModRecipeSerializers {
    static void register() {
    }

    public static final RegistryObject<RecipeSerializer<CoffeeRecipe>> COFFEE = RECIPE_SERIALIZERS.register
            ("coffee", CoffeeRecipe.Serializer::new);
}
