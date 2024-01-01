package net.themcbrothers.uselessmod.util;

import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class WallClosetRecipeManager implements ResourceManagerReloadListener {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void addReloadListeners(final AddReloadListenerEvent event) {
        event.addListener(this);
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        BuiltInRegistries.BLOCK.stream()
                .filter(WallClosetRecipeManager::isValidMaterial)
                .map(WallClosetRecipeManager::createWallClosetRecipe)
                .filter(Objects::nonNull)
                .forEach(RecipeHelper::addRecipe);
    }

    public static boolean isValidMaterial(Block block) {
        return BuiltInRegistries.BLOCK.getTag(UselessTags.Blocks.WALL_CLOSET_MATERIALS)
                .map(HolderSet.Named::unwrap)
                .flatMap(tag -> tag.right())
                .map(holders -> holders.isEmpty() || holders.stream().anyMatch(blockHolder -> blockHolder.is(BuiltInRegistries.BLOCK.getKey(block))))
                .orElse(true);
    }

    private static RecipeHolder<ShapedRecipe> createWallClosetRecipe(Block material) {
        Ingredient planks = Ingredient.of(material);
        Ingredient slab = Ingredient.of(getSlab(material));

        if (slab.isEmpty()) {
            return null;
        }

        NonNullList<Ingredient> ingredients = NonNullList.of(Ingredient.EMPTY,
                planks, planks, planks,
                slab, Ingredient.EMPTY, slab,
                planks, planks, planks
        );

        ItemStack output = new ItemStack(ModBlocks.WALL_CLOSET);
        CompoundTag tag = new CompoundTag();
        String reg = String.valueOf(BuiltInRegistries.BLOCK.getKey(material));
        tag.putString("Material", reg);
        BlockItem.setBlockEntityData(output, ModBlockEntityTypes.WALL_CLOSET.get(), tag);

        ShapedRecipePattern pattern = new ShapedRecipePattern(3, 3, ingredients, Optional.empty());
        ResourceLocation id = UselessMod.rl("closet." + reg.replace(':', '.'));
        ShapedRecipe recipe = new ShapedRecipe("uselessmod:closets", CraftingBookCategory.MISC, pattern, output);

        return new RecipeHolder<>(id, recipe);
    }

    @NotNull
    public static Block getSlab(Block block) {
        ResourceLocation blockReg = BuiltInRegistries.BLOCK.getKey(block);
        Block result = Blocks.AIR;

        String namespace = blockReg.getNamespace();
        String path = blockReg.getPath();

        if (path.endsWith("_planks")) {
            String newPath = path.substring(0, path.length() - "_planks".length()) + "_slab";
            ResourceLocation newReg = new ResourceLocation(namespace, newPath);

            result = BuiltInRegistries.BLOCK.get(newReg);
        } else if (path.endsWith("s")) {
            String newPath = path.substring(0, path.length() - 1) + "_slab";
            ResourceLocation newReg = new ResourceLocation(namespace, newPath);

            result = BuiltInRegistries.BLOCK.get(newReg);
        }

        if (result != Blocks.AIR) {
            return result;
        }

        ResourceLocation newReg = new ResourceLocation(namespace, path + "_slab");
        return BuiltInRegistries.BLOCK.get(newReg);
    }
}
