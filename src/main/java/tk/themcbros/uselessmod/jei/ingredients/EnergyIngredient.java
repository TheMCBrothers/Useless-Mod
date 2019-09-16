package tk.themcbros.uselessmod.jei.ingredients;

import mezz.jei.api.ingredients.IIngredientType;

public class EnergyIngredient {

	public static final IIngredientType<EnergyIngredient> TYPE = () -> EnergyIngredient.class;
	
	private final int energy;
	private final boolean input;
	
	public EnergyIngredient(int energy, boolean input) {
		this.energy = energy;
		this.input = input;
	}
	public int getEnergy() {
		return energy;
	}
	
	public EnergyIngredient copy() {
		return new EnergyIngredient(this.energy, this.input);
	}
	
	public boolean isInput() {
		return input;
	}
	
}
