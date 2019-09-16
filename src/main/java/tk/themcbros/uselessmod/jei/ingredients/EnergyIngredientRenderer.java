package tk.themcbros.uselessmod.jei.ingredients;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.util.ITooltipFlag;

public class EnergyIngredientRenderer implements IIngredientRenderer<EnergyIngredient> {
	
	private final IDrawable overlay;
	
	public EnergyIngredientRenderer(@Nullable IDrawable overlay) {
		this.overlay = overlay;
	}
	
	@Override
	public void render(int xPosition, int yPosition, EnergyIngredient ingredient) {
		if (ingredient != null) {
			if(this.overlay != null)
				this.overlay.draw(xPosition, yPosition);
			GlStateManager.color4f(1, 1, 1, 1);
		}
	}

	@Override
	public List<String> getTooltip(EnergyIngredient ingredient, ITooltipFlag tooltipFlag) {
		List<String> tooltip = new ArrayList<>();
		String displayName = ingredient.getEnergy() + " FE";
		tooltip.add(displayName);
		return tooltip;
	}

}
