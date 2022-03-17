package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeType;
import net.themcbrothers.uselessmod.UselessMod;

public final class ModRecipeTypes {
    static void register() {
    }

    public static final RecipeType<?> COFFEE = RecipeType.register(UselessMod.rl("coffee").toString());
}
