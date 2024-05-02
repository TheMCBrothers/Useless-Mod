package net.themcbrothers.uselessmod.world.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessRecipeSerializers;

public class LightSwitchConvertRecipe extends CustomRecipe {
    public LightSwitchConvertRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean flag = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);

            if (!stack.isEmpty()) {
                if ((stack.is(UselessBlocks.LIGHT_SWITCH.asItem()) || stack.is(UselessBlocks.LIGHT_SWITCH_BLOCK.asItem())) && !flag) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, HolderLookup.Provider lookupProvider) {
        ItemStack stack = ItemStack.EMPTY;
        boolean isBlock = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if (!itemStack.isEmpty() && (itemStack.is(UselessBlocks.LIGHT_SWITCH.asItem()) ||
                    (isBlock = itemStack.is(UselessBlocks.LIGHT_SWITCH_BLOCK.asItem())))) {
                stack = itemStack;
                break;
            }
        }

        ItemStack returnStack = new ItemStack(isBlock ? UselessBlocks.LIGHT_SWITCH : UselessBlocks.LIGHT_SWITCH_BLOCK);
        returnStack.applyComponents(stack.getComponents());
        return returnStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UselessRecipeSerializers.LIGHT_SWITCH_CONVERT.get();
    }
}
