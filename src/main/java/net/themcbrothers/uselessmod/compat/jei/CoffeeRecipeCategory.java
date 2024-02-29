package net.themcbrothers.uselessmod.compat.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidType;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.lib.util.RecipeHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;

public class CoffeeRecipeCategory implements IRecipeCategory<CoffeeRecipe> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/gui/container/coffee_machine.png");
    static final RecipeType<CoffeeRecipe> TYPE = new RecipeType<>(UselessMod.rl("coffee"), CoffeeRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public CoffeeRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 11, 15, 104, 54);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.COFFEE_MACHINE.get()));
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

    private IDrawableAnimated getArrow(CoffeeRecipe recipe) {
        return this.cachedArrows.getUnchecked(recipe.getCookingTime());
    }

    @Override
    public void draw(CoffeeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(guiGraphics, 56, 24);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CoffeeRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 1).addIngredients(recipe.getCupIngredient());
        builder.addSlot(RecipeIngredientRole.INPUT, 69, 1).addIngredients(recipe.getBeanIngredient());
        builder.addSlot(RecipeIngredientRole.INPUT, 87, 1).addIngredients(recipe.getExtraIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 87, 37).addItemStack(RecipeHelper.getResultItem(recipe));

        FluidIngredient waterIngredient = recipe.getWaterIngredient();
        FluidIngredient milkIngredient = recipe.getMilkIngredient();

        int waterAmount = waterIngredient.getFluids().isEmpty() ? FluidType.BUCKET_VOLUME :
                waterIngredient.getAmount(waterIngredient.getFluids().get(0).getFluid());
        int milkAmount = milkIngredient.getFluids().isEmpty() ? FluidType.BUCKET_VOLUME :
                milkIngredient.getAmount(milkIngredient.getFluids().get(0).getFluid());

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 3)
                .setFluidRenderer(waterAmount, false, 8, 48)
                .addIngredients(ForgeTypes.FLUID_STACK, waterIngredient.getFluids());
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 3)
                .setFluidRenderer(milkAmount, false, 8, 48)
                .addIngredients(ForgeTypes.FLUID_STACK, milkIngredient.getFluids());
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
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
    public RecipeType<CoffeeRecipe> getRecipeType() {
        return TYPE;
    }
}
