package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.core.*;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;

import java.util.List;

@JeiPlugin
public class UselessJEI implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return UselessMod.id("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(UselessBlocks.WALL_CLOSET.asItem(), (ingredient, context) -> ingredient.getOrDefault(UselessDataComponents.WALL_CLOSET_MATERIAL, Holder.direct(Blocks.AIR)).getRegisteredName());
        registration.registerSubtypeInterpreter(UselessBlocks.CUP_COFFEE.asItem(), (ingredient, context) -> ingredient.getOrDefault(UselessDataComponents.COFFEE_TYPE, UselessCoffeeTypes.BLACK.get()).getDescriptionId());

        ISubtypeInterpreter<ItemStack> colorSubtypeInterpreter = (ingredient, context) -> ingredient.getOrDefault(UselessDataComponents.COLOR, -1).toString();
        registration.registerSubtypeInterpreter(UselessBlocks.PAINTED_WOOL.asItem(), colorSubtypeInterpreter);
        registration.registerSubtypeInterpreter(UselessItems.PAINT_BRUSH.asItem(), colorSubtypeInterpreter);
        registration.registerSubtypeInterpreter(UselessItems.BUCKET_PAINT.asItem(), colorSubtypeInterpreter);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CoffeeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory vanillaRecipeFactory = registration.getVanillaRecipeFactory();

        registration.addRecipes(RecipeTypes.ANVIL, UselessRecipeMaker.getAnvilRecipes(vanillaRecipeFactory));
        registration.addRecipes(CoffeeRecipeCategory.TYPE, List.of()); // TODO: fix JEI
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CoffeeMachineMenu.class, UselessMenuTypes.COFFEE_MACHINE.get(), CoffeeRecipeCategory.TYPE, 0, 3, 7, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(CoffeeRecipeCategory.TYPE, new ItemStack(UselessBlocks.COFFEE_MACHINE));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGuiContainerHandler(CoffeeMachineScreen.class, new CoffeeMachineGuiHandler());
    }
}
