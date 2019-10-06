package tk.themcbros.uselessmod.compat.jei.ingredients;

import mezz.jei.api.ingredients.IIngredientHelper;

public class EnergyIngredientHelper implements IIngredientHelper<EnergyIngredient> {

	@Override
	public EnergyIngredient getMatch(Iterable<EnergyIngredient> ingredients, EnergyIngredient ingredientToMatch) {
		for (EnergyIngredient debugIngredient : ingredients) {
			if (debugIngredient.getEnergy() == ingredientToMatch.getEnergy()) {
				return debugIngredient;
			}
		}
		return null;
	}

	@Override
	public String getDisplayName(EnergyIngredient ingredient) {
		return ingredient.getEnergy() + " FE";
	}

	@Override
	public String getUniqueId(EnergyIngredient ingredient) {
		return "forgeEnergy";
	}

	@Override
	public String getWildcardId(EnergyIngredient ingredient) {
		return "forgeEnergy";
	}

	@Override
	public String getModId(EnergyIngredient ingredient) {
		return "forge";
	}

	@Override
	public String getResourceId(EnergyIngredient ingredient) {
		return "forgeEnergy";
	}
	
	@Override
	public EnergyIngredient copyIngredient(EnergyIngredient ingredient) {
		return ingredient.copy();
	}

	@Override
	public String getErrorInfo(EnergyIngredient ingredient) {
		if (ingredient == null) {
			return "energy ingredient: null";
		}
		return getDisplayName(ingredient);
	}

}
