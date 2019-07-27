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
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

public class CoffeeRecipeCategory implements IRecipeCategory<CoffeeRecipe> {

private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID + ":textures/gui/container/coffee_machine.png");
	
	private static final int cup_input = 2;
	private static final int ingredient_input = 3;
	private static final int output = 1;
	
	private final IDrawableStatic staticEnergyBar;
	private final IDrawableAnimated animatedEnergyBar;
	private final IDrawableAnimated animatedArrow;
	
	private final IDrawable background, icon;
	private final String name;
	
	public CoffeeRecipeCategory(IGuiHelper helper) {
		staticEnergyBar = helper.createDrawable(TEXTURES, 177, 19, 16, 45);
		animatedEnergyBar = helper.createAnimatedDrawable(staticEnergyBar, 300, StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 0, 41, 18);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);
		
		background = helper.createDrawable(TEXTURES, 60, 5, 109, 67);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.COFFEE_MACHINE));
		name = new TranslationTextComponent("container.uselessmod.coffee_machine").getFormattedText();
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void draw(CoffeeRecipe recipe, double mouseX, double mouseY) {
		animatedEnergyBar.draw(92, 1);
		animatedArrow.draw(7, 38);
		
		Minecraft minecraft = Minecraft.getInstance();
		FontRenderer fontRenderer = minecraft.fontRenderer;
		
		int powerusage = CoffeeMachineTileEntity.RF_PER_TICK * recipe.getCookTime();
		String powerstring = powerusage + " RF";
		fontRenderer.drawString(powerstring, 0, background.getHeight() - 8, 0xFF808080);
		
	}
	
	@Override
	public String getTitle() {
		return name;
	}
	
	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.COFFEE;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CoffeeRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(cup_input, true, 0, 20);
		stacks.init(ingredient_input, true, 18, 20);
		stacks.init(output, false, 51, 39);
		stacks.set(ingredients);
	}

	@Override
	public Class<? extends CoffeeRecipe> getRecipeClass() {
		return CoffeeRecipe.class;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(CoffeeRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
}
