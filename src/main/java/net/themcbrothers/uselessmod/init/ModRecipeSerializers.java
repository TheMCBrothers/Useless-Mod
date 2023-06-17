package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;
import net.themcbrothers.uselessmod.world.item.crafting.LightSwitchConvertRecipe;
import net.themcbrothers.uselessmod.world.item.crafting.PaintBrushDyeRecipe;

import static net.themcbrothers.uselessmod.init.Registration.RECIPE_SERIALIZERS;

public final class ModRecipeSerializers {
    static void register() {
    }

    public static final RegistryObject<RecipeSerializer<CoffeeRecipe>> COFFEE =
            RECIPE_SERIALIZERS.register("coffee", CoffeeRecipe.Serializer::new);
    public static final RegistryObject<SimpleCraftingRecipeSerializer<LightSwitchConvertRecipe>> LIGHT_SWITCH_CONVERT =
            RECIPE_SERIALIZERS.register("light_switch_convert", () -> new SimpleCraftingRecipeSerializer<>(LightSwitchConvertRecipe::new));
    public static final RegistryObject<SimpleCraftingRecipeSerializer<PaintBrushDyeRecipe>> PAINT_BRUSH_DYE =
            RECIPE_SERIALIZERS.register("paint_brush_dye", () -> new SimpleCraftingRecipeSerializer<>(PaintBrushDyeRecipe::new));
}
