package net.themcbrothers.uselessmod.world.item.crafting;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessRecipeSerializers;
import net.themcbrothers.uselessmod.world.item.PaintBrushItem;

import java.util.List;

public class PaintBrushDyeRecipe extends CustomRecipe {
    public PaintBrushDyeRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack brushStack = ItemStack.EMPTY;
        List<ItemStack> dyes = Lists.newArrayList();

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof PaintBrushItem) {
                    if (!brushStack.isEmpty()) {
                        return false;
                    }

                    brushStack = stack;
                } else {
                    DyeColor dyeColor = DyeColor.getColor(stack);
                    if (dyeColor == null) {
                        return false;
                    }

                    dyes.add(stack);
                }
            }
        }

        return !brushStack.isEmpty() && !dyes.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer container, HolderLookup.Provider lookupProvider) {
        ItemStack brushStack = ItemStack.EMPTY;
        List<DyeColor> dyes = Lists.newArrayList();

        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof PaintBrushItem) {
                    if (!brushStack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    brushStack = stack.copy();
                } else {
                    DyeColor dyeColor = DyeColor.getColor(stack);
                    if (dyeColor == null) {
                        return ItemStack.EMPTY;
                    }

                    dyes.add(dyeColor);
                }
            }
        }

        return !brushStack.isEmpty() && !dyes.isEmpty() ? PaintBrushItem.dye(brushStack, dyes) : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UselessRecipeSerializers.PAINT_BRUSH_DYE.get();
    }
}
