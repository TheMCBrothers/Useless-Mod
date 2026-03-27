package net.themcbrothers.uselessmod.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.themcbrothers.uselessmod.UselessMod;

public class UselessRecipePropertySet {
    public static final ResourceKey<RecipePropertySet> COFFEE_MACHINE_CUP = register("coffee_machine_cup");
    public static final ResourceKey<RecipePropertySet> COFFEE_MACHINE_BEAN = register("coffee_machine_bean");
    public static final ResourceKey<RecipePropertySet> COFFEE_MACHINE_EXTRA = register("coffee_machine_extra");

    private static ResourceKey<RecipePropertySet> register(String name) {
        return ResourceKey.create(RecipePropertySet.TYPE_KEY, UselessMod.id(name));
    }
}
