package net.themcbrothers.uselessmod.util;

import com.google.common.collect.HashMultimap;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

import java.util.HashMap;

public class RecipeHelper {
    private static RecipeManager recipeManager;

    @SubscribeEvent
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        recipeManager = event.getServerResources().getRecipeManager();
    }

    public static RecipeManager getRecipeManager() {
        if (!recipeManager.byType.getClass().equals(HashMultimap.class)) {
            recipeManager.byType = HashMultimap.create(recipeManager.byType);
        }

        if (!recipeManager.byName.getClass().equals(HashMap.class)) {
            recipeManager.byName = new HashMap<>(recipeManager.byName);
        }

        return recipeManager;
    }

    public static void addRecipe(RecipeHolder<?> recipe) {
        getRecipeManager().byType.put(recipe.value().getType(), recipe);
        getRecipeManager().byName.put(recipe.id(), recipe);
    }
}
