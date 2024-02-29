package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.runtime.IClickableIngredient;
import net.minecraft.client.renderer.Rect2i;
import net.neoforged.neoforge.fluids.FluidStack;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CoffeeMachineGuiHandler implements IGuiContainerHandler<CoffeeMachineScreen> {
    @Override
    public List<Rect2i> getGuiExtraAreas(CoffeeMachineScreen containerScreen) {
        return List.of(new Rect2i(containerScreen.getGuiLeft() - 16, containerScreen.getGuiTop() + 10, 16, 64));
    }

    @Override
    public Optional<IClickableIngredient<?>> getClickableIngredientUnderMouse(CoffeeMachineScreen containerScreen, double mouseX, double mouseY) {
        return Optional.ofNullable(containerScreen.getHoveredFluid()).map(pair -> new IClickableIngredient<FluidStack>() {
            @Override
            public ITypedIngredient<FluidStack> getTypedIngredient() {
                return new ITypedIngredient<>() {
                    @Override
                    public IIngredientType<FluidStack> getType() {
                        return NeoForgeTypes.FLUID_STACK;
                    }

                    @Override
                    public FluidStack getIngredient() {
                        return pair.getRight();
                    }
                };
            }

            @Override
            public Rect2i getArea() {
                return pair.getLeft();
            }
        });
    }

    @Override
    public Collection<IGuiClickableArea> getGuiClickableAreas(CoffeeMachineScreen containerScreen, double guiMouseX, double guiMouseY) {
        return List.of(IGuiClickableArea.createBasic(66, 38, 44, 8, CoffeeRecipeCategory.TYPE));
    }
}
