package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.init.ModMenuTypes;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Collection;
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
        registration.useNbtForSubtypes(ModBlocks.CUP_COFFEE.asItem(), ModItems.PAINT_BRUSH.asItem());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CoffeeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory vanillaRecipeFactory = registration.getVanillaRecipeFactory();

        registration.addRecipes(RecipeTypes.ANVIL, UselessRecipeMaker.getAnvilRecipes(vanillaRecipeFactory));
        registration.addRecipes(CoffeeRecipeCategory.TYPE, Minecraft.getInstance().level != null ? Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.COFFEE.get()) : List.of());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CoffeeMachineMenu.class, ModMenuTypes.COFFEE_MACHINE.get(), CoffeeRecipeCategory.TYPE, 0, 3, 7, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COFFEE_MACHINE), CoffeeRecipeCategory.TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(CoffeeMachineScreen.class, new IGuiContainerHandler<>() {
            @Override
            public List<Rect2i> getGuiExtraAreas(CoffeeMachineScreen containerScreen) {
                return List.of(new Rect2i(containerScreen.getGuiLeft() - 16, containerScreen.getGuiTop() + 10, 16, 64));
            }

            @Override
            public @Nullable Object getIngredientUnderMouse(CoffeeMachineScreen containerScreen, double mouseX, double mouseY) {
                return containerScreen.getHoveredFluid();
            }

            @Override
            public Collection<IGuiClickableArea> getGuiClickableAreas(CoffeeMachineScreen containerScreen, double guiMouseX, double guiMouseY) {
                return List.of(IGuiClickableArea.createBasic(66, 38, 44, 8, CoffeeRecipeCategory.TYPE));
            }
        });
    }
}
