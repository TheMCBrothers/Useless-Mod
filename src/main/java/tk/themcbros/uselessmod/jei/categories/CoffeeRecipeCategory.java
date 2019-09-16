package tk.themcbros.uselessmod.jei.categories;

import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiIngredientGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.jei.RecipeCategoryUid;
import tk.themcbros.uselessmod.jei.ingredients.EnergyIngredient;
import tk.themcbros.uselessmod.jei.ingredients.EnergyIngredientRenderer;
import tk.themcbros.uselessmod.lists.ModBlocks;
import tk.themcbros.uselessmod.recipes.CoffeeRecipe;
import tk.themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

public class CoffeeRecipeCategory implements IRecipeCategory<CoffeeRecipe> {

	private final ResourceLocation TEXTURES = new ResourceLocation(UselessMod.MOD_ID + ":textures/gui/container/jei_machines.png");

	private static final int beans_input = 1;
	private static final int cup_input = 2;
	private static final int ingredient_input = 3;
	private static final int output = 1;
	
	private final IDrawableStatic staticEnergyBar;
	private final IDrawableAnimated animatedEnergyBar;
	private final IDrawableAnimated animatedArrow;
	
	private final IDrawable background, icon, tankOverlay;
	private final String name;
	
	public CoffeeRecipeCategory(IGuiHelper helper) {
		staticEnergyBar = helper.createDrawable(TEXTURES, 239, 1, 16, 45);
		animatedEnergyBar = helper.createAnimatedDrawable(staticEnergyBar, 300, StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 215, 47, 41, 18);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);
		
		background = helper.createDrawable(TEXTURES, 0, 0, 127, 67);
		icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.COFFEE_MACHINE));
		tankOverlay = helper.drawableBuilder(TEXTURES, 220, 0, 18, 47).addPadding(-1, -1, -1, -1).build();
		name = new TranslationTextComponent("container.uselessmod.coffee_machine").getFormattedText();
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void draw(CoffeeRecipe recipe, double mouseX, double mouseY) {
//		animatedEnergyBar.draw(92, 1);
		animatedArrow.draw(46, 38);
		
//		Minecraft minecraft = Minecraft.getInstance();
//		FontRenderer fontRenderer = minecraft.fontRenderer;
//		
//		int powerusage = CoffeeMachineTileEntity.RF_PER_TICK * recipe.getCookTime();
//		String powerstring = powerusage + " RF";
//		fontRenderer.drawString(powerstring, 0, background.getHeight() - 8, 0xFF808080);
		
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
		IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
		itemStacks.init(beans_input, true, 20, 49);
		itemStacks.init(cup_input, true, 38, 20);
		itemStacks.init(ingredient_input, true, 56, 20);
		itemStacks.init(output, false, 89, 39);
		itemStacks.set(ingredients);
		itemStacks.set(output, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
		
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
//		guiFluidStacks.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
//			if (input) {
//				tooltip.add(slotIndex + " Input fluidStack");
//			} else {
//				tooltip.add(slotIndex + " Output fluidStack");
//			}
//		});

		guiFluidStacks.init(0, true, 1, 1, 16, 45, (int) (CoffeeMachineTileEntity.WATER_PER_TICK * recipe.getCookTime()) * 2, false, tankOverlay);
//		guiFluidStacks.setBackground(0, tankBackground);
		List<List<FluidStack>> fluidInputs = ingredients.getInputs(VanillaTypes.FLUID);
		guiFluidStacks.set(0, fluidInputs.get(0));
		
		IGuiIngredientGroup<EnergyIngredient> energyGroup = recipeLayout.getIngredientsGroup(EnergyIngredient.TYPE);
		energyGroup.init(0, true, new EnergyIngredientRenderer(animatedEnergyBar), 110, 1, 16, 45, 0, 0);
		List<List<EnergyIngredient>> energyInputs = ingredients.getInputs(EnergyIngredient.TYPE);
		energyGroup.set(0, energyInputs.get(0));
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
		FluidStack water = new FluidStack(Fluids.WATER, (int) (recipe.getCookTime() * CoffeeMachineTileEntity.WATER_PER_TICK));

		int powerusage = CoffeeMachineTileEntity.RF_PER_TICK * recipe.getCookTime();
		ingredients.setInput(EnergyIngredient.TYPE, new EnergyIngredient(powerusage, true));
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setInput(VanillaTypes.FLUID, water);
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}
	
}
