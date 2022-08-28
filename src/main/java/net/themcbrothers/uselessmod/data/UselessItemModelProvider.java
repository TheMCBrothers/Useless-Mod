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
        simpleItem(ModItems.RAW_SUPER_USELESS);
        simpleItem(ModItems.USELESS_DUST);
        simpleItem(ModItems.SUPER_USELESS_DUST);
        simpleItem(ModItems.USELESS_INGOT);
        simpleItem(ModItems.SUPER_USELESS_INGOT);
        simpleItem(ModItems.USELESS_NUGGET);
        simpleItem(ModItems.SUPER_USELESS_NUGGET);
        simpleItem(ModBlocks.USELESS_OAK_WOOD.getDoor());
        simpleTool(ModItems.USELESS_SWORD);
        simpleTool(ModItems.SUPER_USELESS_SWORD);
        simpleTool(ModItems.USELESS_SHOVEL);
        simpleTool(ModItems.SUPER_USELESS_SHOVEL);
        simpleTool(ModItems.USELESS_PICKAXE);
        simpleTool(ModItems.SUPER_USELESS_PICKAXE);
        simpleTool(ModItems.USELESS_AXE);
        simpleTool(ModItems.SUPER_USELESS_AXE);
        simpleTool(ModItems.USELESS_HOE);
        simpleTool(ModItems.SUPER_USELESS_HOE);
        simpleItem(ModItems.USELESS_HELMET);
        simpleItem(ModItems.SUPER_USELESS_HELMET);
        simpleItem(ModItems.USELESS_CHESTPLATE);
        simpleItem(ModItems.SUPER_USELESS_CHESTPLATE);
        simpleItem(ModItems.USELESS_LEGGINGS);
        simpleItem(ModItems.SUPER_USELESS_LEGGINGS);
        simpleItem(ModItems.USELESS_BOOTS);
        simpleItem(ModItems.SUPER_USELESS_BOOTS);
        simpleItem(ModItems.USELESS_ELYTRA);
        simpleItem(ModItems.SUPER_USELESS_ELYTRA);
        simpleItem(ModItems.USELESS_WHEAT_SEEDS);
        simpleItem(ModItems.USELESS_WHEAT);
        simpleItem(ModItems.USELESS_BONE);
        simpleItem(ModItems.USELESS_LEATHER);
        simpleItem(ModItems.USELESS_FEATHER);

        withExistingParent("paint_brush", mcLoc(ITEM_FOLDER + "/handheld"))
                .texture("layer0", modLoc(ITEM_FOLDER + "/paint_brush_0"))
                .texture("layer1", modLoc(ITEM_FOLDER + "/paint_brush_1"));

        this.withExistingParent("useless_sheep_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_pig_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_chicken_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_cow_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_skeleton_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
    }

    private void simpleItem(ItemLike item) {
        final ResourceLocation id = item.asItem().getRegistryName();
        this.singleTexture(id.getPath(), mcLoc(ITEM_FOLDER + "/generated"), "layer0", modLoc(ITEM_FOLDER + "/" + id.getPath()));
    }

    private void simpleTool(ItemLike item) {
        final ResourceLocation id = item.asItem().getRegistryName();
        this.singleTexture(id.getPath(), mcLoc(ITEM_FOLDER + "/handheld"), "layer0", modLoc(ITEM_FOLDER + "/" + id.getPath()));
    }
}
