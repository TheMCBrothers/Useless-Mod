package themcbros.uselessmod.recipe;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.world.World;
import themcbros.uselessmod.init.RecipeTypeInit;

import java.util.List;

/**
 * @author TheMCBrothers
 */
public class RecipeValidator {

    public static List<CoffeeRecipe> getCoffeeRecipes() {
        ClientWorld world = Minecraft.getInstance().world;
        assert world != null;
        return getCoffeeRecipes(world);
    }

    public static List<CoffeeRecipe> getCoffeeRecipes(World world) {
        List<CoffeeRecipe> results = Lists.newArrayList();
        RecipeManager recipeManager = world.getRecipeManager();
        for (IRecipe<?> recipe : recipeManager.getRecipes()) {
            if (recipe.getType() == RecipeTypeInit.COFFEE_MACHINE) {
                CoffeeRecipe coffeeRecipe = (CoffeeRecipe) recipe;
                results.add(coffeeRecipe);
            }
        }
        return results;
    }

}
