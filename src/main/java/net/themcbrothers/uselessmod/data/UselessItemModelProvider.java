package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;

public class UselessItemModelProvider extends ItemModelProvider {
    public UselessItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RAW_USELESS);
        simpleItem(ModItems.USELESS_DUST);
        simpleItem(ModItems.USELESS_INGOT);
        simpleItem(ModItems.USELESS_NUGGET);
        simpleItem(ModBlocks.USELESS_OAK_WOOD.getDoor());
        simpleTool(ModItems.USELESS_SWORD);
        simpleTool(ModItems.USELESS_SHOVEL);
        simpleTool(ModItems.USELESS_PICKAXE);
        simpleTool(ModItems.USELESS_AXE);
        simpleTool(ModItems.USELESS_HOE);
        simpleItem(ModItems.USELESS_HELMET);
        simpleItem(ModItems.USELESS_CHESTPLATE);
        simpleItem(ModItems.USELESS_LEGGINGS);
        simpleItem(ModItems.USELESS_BOOTS);
        this.generatedModels.put(modLoc("useless_skeleton_spawn_egg"),
                this.withExistingParent("useless_skeleton_spawn_egg", mcLoc("item/template_spawn_egg")));
    }

    private void simpleItem(ItemLike item) {
        final ResourceLocation id = item.asItem().getRegistryName();
        this.generatedModels.put(id, this.singleTexture(id.getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + id.getPath())));
    }

    private void simpleTool(ItemLike item) {
        final ResourceLocation id = item.asItem().getRegistryName();
        this.generatedModels.put(id, this.singleTexture(id.getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/" + id.getPath())));
    }
}