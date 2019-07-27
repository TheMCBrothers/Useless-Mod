package tk.themcbros.uselessmod.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.recipes.RecipeTypes;

public class UselessRecipeValidator {

	private static final Logger LOGGER = LogManager.getLogger();

	public static class Results {
		private final List<CrusherRecipe> crusherRecipes = new ArrayList<>();
		private final List<CompressorRecipe> compressorRecipes = new ArrayList<>();

		public List<CrusherRecipe> getCrusherRecipes() {
			return crusherRecipes;
		}
		
		public List<CompressorRecipe> getCompressorRecipes() {
			return compressorRecipes;
		}
	}

	private UselessRecipeValidator() {
	}

	public static Results getValidRecipes(IRecipeCategory<CrusherRecipe> crusherCategory, IRecipeCategory<CompressorRecipe> compressorCategory) {
		CategoryRecipeValidator<CrusherRecipe> crusherRecipesValidator = new CategoryRecipeValidator<>(crusherCategory, 1);
		CategoryRecipeValidator<CompressorRecipe> compressorRecipesValidator = new CategoryRecipeValidator<>(compressorCategory, 1);

		Results results = new Results();
		ClientWorld world = Minecraft.getInstance().world;
		RecipeManager recipeManager = world.getRecipeManager();
		for (CrusherRecipe recipe : getRecipes(recipeManager, RecipeTypes.CRUSHING)) {
			if (crusherRecipesValidator.isRecipeValid(recipe)) {
				results.crusherRecipes.add(recipe);
			}

		}
		for (CompressorRecipe recipe : getRecipes(recipeManager, RecipeTypes.COMPRESSING)) {
			if (compressorRecipesValidator.isRecipeValid(recipe)) {
				results.compressorRecipes.add(recipe);
			}

		}
		return results;
	}

	private static <C extends IInventory, T extends IRecipe<C>> Collection<T> getRecipes(RecipeManager recipeManager, IRecipeType<T> recipeType) {
//		Map<ResourceLocation, IRecipe<C>> recipesMap = recipeManager.getRecipes(recipeType);
//		
//		//noinspection unchecked
//		return (Collection<T>) recipesMap.values();
		
		return Collections.emptyList();
	}

	private static final class CategoryRecipeValidator<T extends IRecipe<?>> {
		private static final int INVALID_COUNT = -1;
		private final IRecipeCategory<T> recipeCategory;
		private final int maxInputs;

		public CategoryRecipeValidator(IRecipeCategory<T> recipeCategory, int maxInputs) {
			this.recipeCategory = recipeCategory;
			this.maxInputs = maxInputs;
		}

		public boolean isRecipeValid(T recipe) {
			if (recipe.isDynamic()) {
				return false;
			}
			ItemStack recipeOutput = recipe.getRecipeOutput();
			if (recipeOutput == null || recipeOutput.isEmpty()) {
				String recipeInfo = getInfo(recipe);
				LOGGER.error("Recipe has no output. {}", recipeInfo);
				return false;
			}
			List<Ingredient> ingredients = recipe.getIngredients();
			if (ingredients == null) {
				String recipeInfo = getInfo(recipe);
				LOGGER.error("Recipe has no input Ingredients. {}", recipeInfo);
				return false;
			}
			int inputCount = getInputCount(ingredients);
			if (inputCount == INVALID_COUNT) {
				return false;
			} else if (inputCount > maxInputs) {
				String recipeInfo = getInfo(recipe);
				LOGGER.error("Recipe has too many inputs. {}", recipeInfo);
				return false;
			} else if (inputCount == 0) {
				String recipeInfo = getInfo(recipe);
				LOGGER.error("Recipe has no inputs. {}", recipeInfo);
				return false;
			}
			return true;
		}

		private String getInfo(T recipe) {
			return recipeCategory.getTitle();
//			return ErrorUtil.getInfoFromRecipe(recipe, recipeCategory);
		}

		protected static int getInputCount(List<Ingredient> ingredientList) {
			int inputCount = 0;
			for (Ingredient ingredient : ingredientList) {
				ItemStack[] input = ingredient.getMatchingStacks();
				if (input == null) {
					return INVALID_COUNT;
				} else {
					inputCount++;
				}
			}
			return inputCount;
		}
	}
	
}
