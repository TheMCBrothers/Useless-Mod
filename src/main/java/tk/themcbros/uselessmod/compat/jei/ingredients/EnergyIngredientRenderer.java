package tk.themcbros.uselessmod.compat.jei.ingredients;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.util.ITooltipFlag;
import tk.themcbros.uselessmod.helper.TextUtils;

public class EnergyIngredientRenderer implements IIngredientRenderer<EnergyIngredient> {

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
