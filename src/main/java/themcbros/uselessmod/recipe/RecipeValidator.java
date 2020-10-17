package themcbros.uselessmod.recipe;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themcbros.uselessmod.init.RecipeTypeInit;

import java.util.List;

/**
 * @author TheMCBrothers
 */
public class RecipeValidator {

    @OnlyIn(Dist.CLIENT)
    public static List<CoffeeRecipe> getCoffeeRecipes() {
        assert Minecraft.getInstance().world != null;
        return getCoffeeRecipes(Minecraft.getInstance().world);
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
