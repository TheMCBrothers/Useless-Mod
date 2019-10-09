package tk.themcbros.uselessmod.compat.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiIngredientGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.compat.jei.RecipeCategoryUid;
import tk.themcbros.uselessmod.compat.jei.ingredients.EnergyIngredient;
import tk.themcbros.uselessmod.compat.jei.ingredients.EnergyIngredientRenderer;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.recipes.CrusherRecipe;
import tk.themcbros.uselessmod.tileentity.ElectricCrusherTileEntity;

import java.util.Arrays;
import java.util.List;

public class ElectricCrusherRecipeCategory implements IRecipeCategory<CrusherRecipe> {

	private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID + ":textures/gui/container/jei_machines.png");
	
	private static final int input = 0;
	private static final int output = 1;
	private static final int second_output = 2;
	
	private final IDrawableAnimated animatedArrow;
	private final IDrawableAnimated animatedEnergyBar;
	
	private final IDrawable background, icon;
	
	public ElectricCrusherRecipeCategory(IGuiHelper helper) {
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 232, 65, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);

		IDrawableStatic staticEnergyBar = helper.createDrawable(TEXTURES, 239, 1, 16, 45);
		animatedEnergyBar = helper.createAnimatedDrawable(staticEnergyBar, 200, StartDirection.TOP, true);
		
		background = helper.createDrawable(TEXTURES, 0, 67, 105, 47);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ELECTRIC_CRUSHER));
	}
	
	@Override
	public void draw(CrusherRecipe recipe, double mouseX, double mouseY) {
		animatedArrow.draw(24, 16);
	}
	
	@Override
	public ResourceLocation getUid() {
		return RecipeCategoryUid.ELECTRIC_CRUSHER;
	}

	@Override
	public Class<? extends CrusherRecipe> getRecipeClass() {
		return CrusherRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("container.uselessmod.electric_crusher").getFormattedText();
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
		ingredients.setInput(EnergyIngredient.TYPE, new EnergyIngredient(recipe.getCrushTime() * ElectricCrusherTileEntity.RF_PER_TICK, true));
		if(recipe.getSecondRecipeOutput() == ItemStack.EMPTY)
			ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
		else
			ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(recipe.getRecipeOutput(), recipe.getSecondRecipeOutput()));
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		
		stacks.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
			if (slotIndex == second_output) {
				tooltip.add("Chance: " + (int) (recipe.getSecondChance() * 100.0f) + "%");
			}
		});
		
		stacks.init(input, true, 0, 16);
		stacks.init(output, false, 60, 5);
		stacks.init(second_output, false, 60, 29);
		stacks.set(ingredients);
		
		IGuiIngredientGroup<EnergyIngredient> energyGroup = recipeLayout.getIngredientsGroup(EnergyIngredient.TYPE);
		energyGroup.init(0, true, new EnergyIngredientRenderer(animatedEnergyBar), 88, 1, 16, 45, 0, 0);
		List<List<EnergyIngredient>> energyInputs = ingredients.getInputs(EnergyIngredient.TYPE);
		energyGroup.set(0, energyInputs.get(0));
	}
	
}
