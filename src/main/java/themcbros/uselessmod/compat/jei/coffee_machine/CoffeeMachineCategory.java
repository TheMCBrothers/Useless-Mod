package themcbros.uselessmod.compat.jei.coffee_machine;

import com.mojang.blaze3d.matrix.MatrixStack;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.compat.jei.UselessRecipeCategoryUid;
import themcbros.uselessmod.helpers.TextUtils;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.recipe.CoffeeRecipe;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CoffeeMachineCategory implements IRecipeCategory<CoffeeRecipe> {

    private static final ResourceLocation BACKGROUND_TEXTURE = UselessMod.rl("textures/gui/container/coffee_machine.png");

    private final IDrawable background, icon;
    private final IDrawableAnimated progressBar;

    public CoffeeMachineCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(BACKGROUND_TEXTURE, 11, 15, 104, 54);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.COFFEE_MACHINE.get()));
        final IDrawableStatic staticProgressBar = guiHelper.createDrawable(BACKGROUND_TEXTURE, 176, 0, 42, 6);
        this.progressBar = guiHelper.createAnimatedDrawable(staticProgressBar, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public ResourceLocation getUid() {
        return UselessRecipeCategoryUid.COFFEE_MACHINE;
    }

    @Override
    public Class<? extends CoffeeRecipe> getRecipeClass() {
        return CoffeeRecipe.class;
    }

    @Override
    public String getTitle() {
        return TextUtils.translate("container", "coffee_machine").getString();
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
    public void draw(CoffeeRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        this.progressBar.draw(matrixStack, 56, 24);
    }

    @Override
    public void setIngredients(CoffeeRecipe coffeeRecipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.FLUID, coffeeRecipe.getWaterIngredient().getFluids());
        ingredients.setInputIngredients(coffeeRecipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, coffeeRecipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CoffeeRecipe coffeeRecipe, IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        fluidStackGroup.init(0, true, 1, 3, 8, 48, 2000, true, null);
        fluidStackGroup.set(ingredients);
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        itemStackGroup.init(0, true, 50, 0);
        itemStackGroup.init(1, true, 68, 0);
        itemStackGroup.init(2, true, 86, 0);
        itemStackGroup.init(3, false, 86, 36);
        itemStackGroup.set(ingredients);
    }
}
