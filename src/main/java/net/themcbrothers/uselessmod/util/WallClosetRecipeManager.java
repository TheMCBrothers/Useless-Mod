package net.themcbrothers.uselessmod.util;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.init.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class WallClosetRecipeManager implements ResourceManagerReloadListener {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void addReloadListeners(final AddReloadListenerEvent event) {
        event.addListener(this);
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(WallClosetRecipeManager::isValidMaterial)
                .map(Map.Entry::getValue)
                .map(WallClosetRecipeManager::createWallClosetRecipe)
                .filter(Objects::nonNull)
                .forEach(RecipeHelper::addRecipe);
    }

    public static boolean isValidMaterial(Map.Entry<ResourceKey<Block>, Block> entry) {
        ITagManager<Block> tags = ForgeRegistries.BLOCKS.tags();
        assert tags != null;
        ITag<Block> wallClosetMaterials = tags.getTag(UselessTags.Blocks.WALL_CLOSET_MATERIALS);
        return wallClosetMaterials.isEmpty() || wallClosetMaterials.contains(entry.getValue());
    }

    private static ShapedRecipe createWallClosetRecipe(Block material) {
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
        String reg = String.valueOf(ForgeRegistries.BLOCKS.getKey(material));
        tag.putString("Material", reg);
        BlockItem.setBlockEntityData(output, ModBlockEntityTypes.WALL_CLOSET.get(), tag);

        ResourceLocation name = UselessMod.rl("closet." + reg.replace(':', '.'));
        return new ShapedRecipe(name, "uselessmod:closets", CraftingBookCategory.MISC, 3, 3, ingredients, output);
    }

    @NotNull
    public static Block getSlab(Block block) {
        ResourceLocation blockReg = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
        Block result;

        String namespace = blockReg.getNamespace();
        String path = blockReg.getPath();

        if (path.endsWith("_planks")) {
            String newPath = path.substring(0, path.length() - "_planks".length()) + "_slab";
            ResourceLocation newReg = new ResourceLocation(namespace, newPath);

            result = ForgeRegistries.BLOCKS.getValue(newReg);

            if (result != null) {
                return result;
            }
        } else if (path.endsWith("s")) {
            String newPath = path.substring(0, path.length() - 1) + "_slab";
            ResourceLocation newReg = new ResourceLocation(namespace, newPath);

            result = ForgeRegistries.BLOCKS.getValue(newReg);

            if (result != null) {
                return result;
            }
        }

        ResourceLocation newReg = new ResourceLocation(namespace, path + "_slab");

        result = ForgeRegistries.BLOCKS.getValue(newReg);
        return result != null ? result : Blocks.AIR;
    }
}
