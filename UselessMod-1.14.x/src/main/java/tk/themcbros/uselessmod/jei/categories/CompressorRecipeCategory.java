package tk.themcbros.uselessmod.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
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
import tk.themcbros.uselessmod.jei.RecipeCategoryUid;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.recipes.CompressorRecipe;
import tk.themcbros.uselessmod.tileentity.CompressorTileEntity;

public class CompressorRecipeCategory implements IRecipeCategory<CompressorRecipe> {

private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID + ":textures/gui/container/compressor.png");
	
	private static final int input = 0;
	private static final int output = 1;
	
	private final IDrawableStatic staticEnergyBar;
	private final IDrawableAnimated animatedEnergyBar;
	private final IDrawableAnimated animatedArrow;
	
	private final IDrawable background, icon;
	private final String name;
	
	public CompressorRecipeCategory(IGuiHelper helper) {
		staticEnergyBar = helper.createDrawable(TEXTURES, 193, 31, 15, 60);
		animatedEnergyBar = helper.createAnimatedDrawable(staticEnergyBar, 300, StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);
		
		background = helper.createDrawable(TEXTURES, 55, 8, 107, 62);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.COMPRESSOR));
		name = new TranslationTextComponent("container.uselessmod.compressor").getFormattedText();
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void draw(CompressorRecipe recipe, double mouseX, double mouseY) {
		animatedEnergyBar.draw(91, 1);
		animatedArrow.draw(24, 26);
		
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontRenderer = minecraft.fontRenderer;
		
		float experience = recipe.getExperience();
		if (experience > 0) {
			String experienceString = new TranslationTextComponent("gui.jei.category.smelting.experience", experience).getFormattedText();
			fontRenderer.drawString(experienceString, 0, 0, 0xFF808080);
		}
		
		int powerusage = CompressorTileEntity.RF_PER_TICK * recipe.getCompressTime();
		String powerstring = powerusage + " FE";
		fontRenderer.drawString(powerstring, 0, background.getHeight() - 8, 0xFF808080);
		
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.COMPRESSOR;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CompressorRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, 0, 26);
		stacks.init(output, false, 60, 26);
		stacks.set(ingredients);
	}

	@Override
	public Class<? extends CompressorRecipe> getRecipeClass() {
		return CompressorRecipe.class;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(CompressorRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
}
