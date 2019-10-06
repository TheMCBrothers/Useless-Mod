package tk.themcbros.uselessmod.compat.jei;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.recipes.GlowstoneGeneratorRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class UselessRecipeValidator {

	public static List<CrusherRecipe> getCrusherRecipes() {
		List<CrusherRecipe> results = Lists.newArrayList();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.CRUSHING) {
				CrusherRecipe crusherRecipe = (CrusherRecipe) recipe;
				results.add(crusherRecipe);
			}
		}
		return results;
	}
	
	public static List<CompressorRecipe> getCompressorRecipes() {
		List<CompressorRecipe> results = Lists.newArrayList();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.COMPRESSING) {
				CompressorRecipe compressorRecipe = (CompressorRecipe) recipe;
				results.add(compressorRecipe);
			}
		}
		return results;
	}
	
	public static List<CoffeeRecipe> getCoffeeRecipes() {
		List<CoffeeRecipe> results = Lists.newArrayList();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.COFFEE) {
				CoffeeRecipe coffeeRecipe = (CoffeeRecipe) recipe;
				results.add(coffeeRecipe);
			}
		}
		return results;
	}
	
	public static List<GlowstoneGeneratorRecipe> getGlowstoneGeneratorRecipes() {
		List<GlowstoneGeneratorRecipe> results = Lists.newArrayList();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == RecipeTypes.GLOWSTONE_GENERATING) {
				GlowstoneGeneratorRecipe glowstoneGeneratorRecipe = (GlowstoneGeneratorRecipe) recipe;
				results.add(glowstoneGeneratorRecipe);
			}
		}
		return results;
	}
	
}
