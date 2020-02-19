package tk.themcbros.uselessmod.compat.jei.ingredients;

import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.util.ITooltipFlag;
import tk.themcbros.uselessmod.helper.TextUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class EnergyIngredientRenderer implements IIngredientRenderer<EnergyIngredient> {

	private final IDrawable overlay;

	public EnergyIngredientRenderer(@Nullable IDrawable overlay) {
		this.overlay = overlay;
	}

	@Override
	public void render(int xPosition, int yPosition, @Nullable EnergyIngredient ingredient) {
		if (ingredient != null) {
			if (this.overlay != null)
				this.overlay.draw(xPosition, yPosition);
			RenderSystem.color4f(1, 1, 1, 1);
		}
	}

    @Override
	public List<String> getTooltip(EnergyIngredient ingredient, ITooltipFlag tooltipFlag) {
		return Collections.singletonList(TextUtils.energy(ingredient.getEnergy()).getFormattedText());
	}

}