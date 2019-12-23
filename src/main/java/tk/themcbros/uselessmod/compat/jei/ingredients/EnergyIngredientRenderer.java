package tk.themcbros.uselessmod.compat.jei.ingredients;

public class EnergyIngredientRenderer /*implements IIngredientRenderer<EnergyIngredient> {

	private final IDrawable overlay;

	public EnergyIngredientRenderer(@Nullable IDrawable overlay) {
		this.overlay = overlay;
	}

	@Override
	public void render(int xPosition, int yPosition, EnergyIngredient ingredient) {
		if (ingredient != null) {
			if (this.overlay != null)
				this.overlay.draw(xPosition, yPosition);
			GlStateManager.color4f(1, 1, 1, 1);
		}
	}

	@Override
	public List<String> getTooltip(EnergyIngredient ingredient, ITooltipFlag tooltipFlag) {
		return Arrays.asList(TextUtils.energy(ingredient.getEnergy()).getFormattedText());
	}

}
*/{}