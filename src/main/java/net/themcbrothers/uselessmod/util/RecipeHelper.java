package net.themcbrothers.uselessmod.util;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.themcbrothers.uselessmod.core.UselessRecipePropertySet;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

import java.util.Optional;

public class RecipeHelper {
    private static RecipeManager recipeManager;

    @SubscribeEvent
    public void onAddReloadListeners(AddServerReloadListenersEvent event) {
        recipeManager = event.getServerResources().getRecipeManager();

        net.themcbrothers.lib.util.RecipeHelper.addPropertySet(UselessRecipePropertySet.COFFEE_MACHINE_CUP,
                recipe -> recipe instanceof CoffeeRecipe coffeeRecipe ? Optional.of(coffeeRecipe.getCupIngredient()) : Optional.empty());
        net.themcbrothers.lib.util.RecipeHelper.addPropertySet(UselessRecipePropertySet.COFFEE_MACHINE_BEAN,
                recipe -> recipe instanceof CoffeeRecipe coffeeRecipe ? Optional.of(coffeeRecipe.getBeanIngredient()) : Optional.empty());
        net.themcbrothers.lib.util.RecipeHelper.addPropertySet(UselessRecipePropertySet.COFFEE_MACHINE_EXTRA,
                recipe -> recipe instanceof CoffeeRecipe coffeeRecipe ? coffeeRecipe.getExtraIngredient() : Optional.empty());
    }

    public static RecipeManager getRecipeManager() {
        /*if (!recipeManager.byType.getClass().equals(HashMultimap.class)) {
            recipeManager.byType = HashMultimap.create(recipeManager.byType);
        }

        if (!recipeManager.byName.getClass().equals(HashMap.class)) {
            recipeManager.byName = new HashMap<>(recipeManager.byName);
        }*/

        return recipeManager;
    }

    public static void addRecipe(RecipeHolder<?> recipe) {
        // TODO: fix recipes
//        getRecipeManager().byType.put(recipe.value().getType(), recipe);
//        getRecipeManager().byName.put(recipe.id(), recipe);
    }
}
