package tk.themcbros.uselessmod.jei.categories;

import java.util.List;

import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.ingredient.IGuiIngredientGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.jei.RecipeCategoryUid;
import tk.themcbros.uselessmod.jei.ingredients.EnergyIngredient;
import tk.themcbros.uselessmod.jei.ingredients.EnergyIngredientRenderer;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.recipes.GlowstoneGeneratorRecipe;

public class GlowstoneGeneratorRecipeCategory implements IRecipeCategory<GlowstoneGeneratorRecipe> {

	private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID, "textures/gui/container/glowstone_generator.png");

	private final IDrawable icon, background;
	private final IDrawableAnimated animatedArrow, animatedEnergyBar;
	private final String title;

	public GlowstoneGeneratorRecipeCategory(IGuiHelper helper) {
		this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.GLOWSTONE_GENERATOR));
		this.background = helper.createDrawable(TEXTURES, 79, 15, 83, 47);

		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 0, 24, 17);
		this.animatedArrow = helper.createAnimatedDrawable(staticArrow, 100, StartDirection.LEFT, false);

		IDrawableStatic staticEnergyBar = helper.createDrawable(TEXTURES, 177, 18, 16, 45);
		this.animatedEnergyBar = helper.createAnimatedDrawable(staticEnergyBar, 100, StartDirection.BOTTOM, false);

		this.title = new TranslationTextComponent("container.uselessmod.glowstone_generator").getFormattedText();
	}

	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.GENERATOR;
	}

	@Override
	public Class<? extends GlowstoneGeneratorRecipe> getRecipeClass() {
		return GlowstoneGeneratorRecipe.class;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void draw(GlowstoneGeneratorRecipe recipe, double mouseX, double mouseY) {
		this.animatedArrow.draw(29, 17);
	}

	@Override
	public void setIngredients(GlowstoneGeneratorRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(EnergyIngredient.TYPE, new EnergyIngredient(recipe.getEnergyProduced(), false));
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, GlowstoneGeneratorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(0, true, 0, 17);
		stacks.set(ingredients);

		IGuiIngredientGroup<EnergyIngredient> energyGroup = recipeLayout.getIngredientsGroup(EnergyIngredient.TYPE);
		energyGroup.init(0, false, new EnergyIngredientRenderer(this.animatedEnergyBar), 66, 1, 16, 45, 0, 0);
		List<List<EnergyIngredient>> energyInputs = ingredients.getOutputs(EnergyIngredient.TYPE);
		energyGroup.set(0, energyInputs.get(0));
	}

}
