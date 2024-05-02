package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.core.UselessMenuTypes;
import net.themcbrothers.uselessmod.core.UselessRecipeTypes;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;

import javax.annotation.Nonnull;
import java.util.List;

@JeiPlugin
public class UselessJEI implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return UselessMod.rl("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(UselessBlocks.WALL_CLOSET.asItem(), UselessBlocks.CUP_COFFEE.asItem(), UselessBlocks.PAINTED_WOOL.asItem(), UselessItems.PAINT_BRUSH.asItem());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CoffeeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory vanillaRecipeFactory = registration.getVanillaRecipeFactory();

        registration.addRecipes(RecipeTypes.ANVIL, UselessRecipeMaker.getAnvilRecipes(vanillaRecipeFactory));
        registration.addRecipes(CoffeeRecipeCategory.TYPE, Minecraft.getInstance().level != null ? Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(UselessRecipeTypes.COFFEE.get()) : List.of());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CoffeeMachineMenu.class, UselessMenuTypes.COFFEE_MACHINE.get(), CoffeeRecipeCategory.TYPE, 0, 3, 7, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(UselessBlocks.COFFEE_MACHINE), CoffeeRecipeCategory.TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(CoffeeMachineScreen.class, new CoffeeMachineGuiHandler());
    }
}
