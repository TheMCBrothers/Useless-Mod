package net.themcbrothers.uselessmod.util;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WallClosetRecipeManager implements ResourceManagerReloadListener {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void addReloadListeners(final AddServerReloadListenersEvent event) {
        event.addListener(UselessMod.id("wall_closet_recipes"), this);
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
        return BuiltInRegistries.BLOCK.get(UselessTags.Blocks.WALL_CLOSET_MATERIALS)
                .map(HolderSet.Named::unwrap)
                .flatMap(tag -> tag.right())
                .map(holders -> holders.isEmpty() || holders.stream().anyMatch(blockHolder -> blockHolder.is(BuiltInRegistries.BLOCK.getKey(block))))
                .orElse(true);
    }

    private static RecipeHolder<ShapedRecipe> createWallClosetRecipe(Block material) {
        if (material.defaultBlockState().isAir()) {
            return null;
        }

        Block slabBlock = getSlab(material);

        if (slabBlock.defaultBlockState().isAir()) {
            return null;
        }

        Ingredient planks = Ingredient.of(material);
        Ingredient slab = Ingredient.of(slabBlock);

        List<Optional<Ingredient>> ingredients = List.of(
                Optional.of(planks), Optional.of(planks), Optional.of(planks),
                Optional.of(slab), Optional.empty(), Optional.of(slab),
                Optional.of(planks), Optional.of(planks), Optional.of(planks)
        );

        Holder<Block> blockHolder = BuiltInRegistries.BLOCK.wrapAsHolder(material);
        ItemStack output = new ItemStack(UselessBlocks.WALL_CLOSET);
        output.set(UselessDataComponents.WALL_CLOSET_MATERIAL, blockHolder);

        ShapedRecipePattern pattern = new ShapedRecipePattern(3, 3, ingredients, Optional.empty());
        Identifier id = UselessMod.id("closet." + blockHolder.getRegisteredName().replace(':', '.'));
        ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, id);
        ShapedRecipe recipe = new ShapedRecipe("uselessmod:closets", CraftingBookCategory.MISC, pattern, output);

        return new RecipeHolder<>(key, recipe);
    }

    @NotNull
    public static Block getSlab(Block block) {
        Identifier blockReg = BuiltInRegistries.BLOCK.getKey(block);
        Block result = Blocks.AIR;

        String namespace = blockReg.getNamespace();
        String path = blockReg.getPath();

        if (path.endsWith("_planks")) {
            String newPath = path.substring(0, path.length() - "_planks".length()) + "_slab";
            Identifier newReg = Identifier.fromNamespaceAndPath(namespace, newPath);

            result = BuiltInRegistries.BLOCK.getValue(newReg);
        } else if (path.endsWith("s")) {
            String newPath = path.substring(0, path.length() - 1) + "_slab";
            Identifier newReg = Identifier.fromNamespaceAndPath(namespace, newPath);

            result = BuiltInRegistries.BLOCK.getValue(newReg);
        }

        if (result != Blocks.AIR) {
            return result;
        }

        Identifier newReg = Identifier.fromNamespaceAndPath(namespace, path + "_slab");
        return BuiltInRegistries.BLOCK.getValue(newReg);
    }
}
