package net.themcbrothers.uselessmod.world.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;

public class LightSwitchConvertRecipe extends CustomRecipe {
    public LightSwitchConvertRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean flag = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);

            if (!stack.isEmpty()) {
                if ((stack.is(ModBlocks.LIGHT_SWITCH.asItem()) || stack.is(ModBlocks.LIGHT_SWITCH_BLOCK.asItem())) && !flag) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack stack = ItemStack.EMPTY;
        boolean isBlock = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if (!itemStack.isEmpty() && (itemStack.is(ModBlocks.LIGHT_SWITCH.asItem()) ||
                    (isBlock = itemStack.is(ModBlocks.LIGHT_SWITCH_BLOCK.asItem())))) {
                stack = itemStack;
                break;
            }
        }

        ItemStack returnStack = new ItemStack(isBlock ? ModBlocks.LIGHT_SWITCH : ModBlocks.LIGHT_SWITCH_BLOCK);
        returnStack.setTag(stack.getTag());
        return returnStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.LIGHT_SWITCH_CONVERT.get();
    }
}
