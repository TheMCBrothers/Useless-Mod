package tk.themcbros.uselessmod.jei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class UselessRecipeValidator {

	public static List<CrusherRecipe> getCrusherRecipes() {
		List<CrusherRecipe> results = new ArrayList<CrusherRecipe>();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.CRUSHING) {
				CrusherRecipe chipalyzerrecipe = (CrusherRecipe) recipe;
				results.add(chipalyzerrecipe);
			}
		}
		return results;
	}
	
	public static List<CompressorRecipe> getCompressorRecipes() {
		List<CompressorRecipe> results = new ArrayList<CompressorRecipe>();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.COMPRESSING) {
				CompressorRecipe chipalyzerrecipe = (CompressorRecipe) recipe;
				results.add(chipalyzerrecipe);
			}
		}
		return results;
	}
	
	public static List<CoffeeRecipe> getCoffeeRecipes() {
		List<CoffeeRecipe> results = new ArrayList<CoffeeRecipe>();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.COFFEE) {
				CoffeeRecipe chipalyzerrecipe = (CoffeeRecipe) recipe;
				results.add(chipalyzerrecipe);
			}
		}
		return results;
	}
	
}
