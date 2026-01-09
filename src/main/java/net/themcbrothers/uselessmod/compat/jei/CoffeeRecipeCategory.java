package net.themcbrothers.uselessmod.compat.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.themcbrothers.lib.util.RecipeHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessRecipeTypes;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

public class CoffeeRecipeCategory implements IRecipeCategory<RecipeHolder<CoffeeRecipe>> {
    private static final Identifier TEXTURE = UselessMod.id("textures/gui/container/coffee_machine.png");
    static final IRecipeType<RecipeHolder<CoffeeRecipe>> TYPE = IRecipeType.create(UselessRecipeTypes.COFFEE.get());

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public CoffeeRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 11, 15, 104, 54); // TODO: reduce magic numbers
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(UselessBlocks.COFFEE_MACHINE.get()));
        this.localizedName = UselessMod.translate("container", "coffee_machine");
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(43)
                .build(new CacheLoader<>() {
                    @Override
                    public IDrawableAnimated load(Integer cookTime) {
                        return helper.drawableBuilder(TEXTURE, 176, 0, 42, 6)
                                .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
                });
    }

    private IDrawableAnimated getArrow(RecipeHolder<CoffeeRecipe> recipe) {
        return this.cachedArrows.getUnchecked(recipe.value().getCookingTime());
    }

    @Override
    public void draw(RecipeHolder<CoffeeRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(guiGraphics, 56, 24);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<CoffeeRecipe> recipe, IFocusGroup focuses) {
        final CoffeeRecipe recipeValue = recipe.value();

        builder.addSlot(RecipeIngredientRole.INPUT, 51, 1).addIngredients(recipeValue.getCupIngredient());
        builder.addSlot(RecipeIngredientRole.INPUT, 69, 1).addIngredients(recipeValue.getBeanIngredient());
        recipeValue.getExtraIngredient().ifPresent(extraIngredient -> builder.addSlot(RecipeIngredientRole.INPUT, 87, 1).addIngredients(extraIngredient));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 37).addItemStack(RecipeHelper.getResultItem(recipeValue));

        SizedFluidIngredient waterIngredient = recipeValue.getWaterIngredient();

        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 1, 3)
                .setFluidRenderer(waterIngredient.amount(), false, 8, 48)
                .addIngredients(NeoForgeTypes.FLUID_STACK, waterIngredient.ingredient().fluids().stream().map(Holder::value).map(fluid -> new FluidStack(fluid, FluidType.BUCKET_VOLUME)).toList());

        recipeValue.getMilkIngredient().ifPresent(milkIngredient ->
                builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 19, 3)
                        .setFluidRenderer(milkIngredient.amount(), false, 8, 48)
                        .addIngredients(NeoForgeTypes.FLUID_STACK, milkIngredient.ingredient().fluids().stream().map(Holder::value).map(fluid -> new FluidStack(fluid, FluidType.BUCKET_VOLUME)).toList()));
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public int getWidth() {
        return 104; // TODO: reduce magic numbers
    }

    @Override
    public int getHeight() {
        return 54; // TODO: reduce magic numbers
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public IRecipeType<RecipeHolder<CoffeeRecipe>> getRecipeType() {
        return TYPE;
    }
}
