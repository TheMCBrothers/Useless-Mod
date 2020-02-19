package tk.themcbros.uselessmod.compat.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.compat.jei.RecipeCategoryUid;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipe> {

    private final ResourceLocation TEXTURES = UselessMod.getId("textures/gui/container/jei_machines.png");
	
	private static final int input = 0;
	private static final int output = 2;

    private final IDrawableAnimated animatedFlame;
	private final IDrawableAnimated animatedArrow;
	
	private final IDrawable background, icon;
	
	public CrusherRecipeCategory(IGuiHelper helper) {
        IDrawableStatic staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
		
		background = helper.createDrawable(TEXTURES, 55, 16, 82, 54);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.CRUSHER));
	}
	
	@Override
	public void draw(CrusherRecipe recipe, double mouseX, double mouseY) {
		animatedFlame.draw(1, 20);
		animatedArrow.draw(24, 18);
		
		float experience = recipe.getExperience();
		if (experience > 0) {
			String experienceString = new TranslationTextComponent("gui.jei.category.smelting.experience", experience).getFormattedText();
			Minecraft minecraft = Minecraft.getInstance();
			FontRenderer fontRenderer = minecraft.fontRenderer;
			int stringWidth = fontRenderer.getStringWidth(experienceString);
			fontRenderer.drawString(experienceString, background.getWidth() - stringWidth, 0, 0xFF808080);
		}
	}
	
	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.CRUSHER;
	}

	@Override
	public Class<? extends CrusherRecipe> getRecipeClass() {
		return CrusherRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("container.uselessmod.crusher").getFormattedText();
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
	public void setIngredients(CrusherRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, 0, 0);
		stacks.init(output, false, 60, 18);
		stacks.set(ingredients);
	}
	
}
