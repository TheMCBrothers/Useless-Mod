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
        basicItem(ModItems.RAW_USELESS.get());
        basicItem(ModItems.RAW_SUPER_USELESS.get());
        basicItem(ModItems.USELESS_DUST.get());
        basicItem(ModItems.SUPER_USELESS_DUST.get());
        basicItem(ModItems.USELESS_INGOT.get());
        basicItem(ModItems.SUPER_USELESS_INGOT.get());
        basicItem(ModItems.USELESS_NUGGET.get());
        basicItem(ModItems.SUPER_USELESS_NUGGET.get());
        basicItem(ModBlocks.USELESS_OAK_WOOD.getDoor().asItem());
        basicTool(ModItems.USELESS_SWORD.get());
        basicTool(ModItems.SUPER_USELESS_SWORD.get());
        basicTool(ModItems.USELESS_SHOVEL.get());
        basicTool(ModItems.SUPER_USELESS_SHOVEL.get());
        basicTool(ModItems.USELESS_PICKAXE.get());
        basicTool(ModItems.SUPER_USELESS_PICKAXE.get());
        basicTool(ModItems.USELESS_AXE.get());
        basicTool(ModItems.SUPER_USELESS_AXE.get());
        basicTool(ModItems.USELESS_HOE.get());
        basicTool(ModItems.SUPER_USELESS_HOE.get());
        basicItem(ModItems.USELESS_HELMET.get());
        basicItem(ModItems.SUPER_USELESS_HELMET.get());
        basicItem(ModItems.USELESS_CHESTPLATE.get());
        basicItem(ModItems.SUPER_USELESS_CHESTPLATE.get());
        basicItem(ModItems.USELESS_LEGGINGS.get());
        basicItem(ModItems.SUPER_USELESS_LEGGINGS.get());
        basicItem(ModItems.USELESS_BOOTS.get());
        basicItem(ModItems.SUPER_USELESS_BOOTS.get());
        basicItem(ModItems.USELESS_ELYTRA.get());
        basicItem(ModItems.SUPER_USELESS_ELYTRA.get());
        basicItem(ModItems.USELESS_WHEAT_SEEDS.get());
        basicItem(ModItems.USELESS_WHEAT.get());
        basicItem(ModItems.COFFEE_SEEDS.get());
        basicItem(ModItems.COFFEE_BEANS.get());
        basicItem(ModItems.USELESS_BONE.get());
        basicItem(ModItems.USELESS_LEATHER.get());
        basicItem(ModItems.USELESS_FEATHER.get());

        withExistingParent("paint_brush", mcLoc(ITEM_FOLDER + "/handheld"))
                .texture("layer0", modLoc(ITEM_FOLDER + "/paint_brush_0"))
                .texture("layer1", modLoc(ITEM_FOLDER + "/paint_brush_1"));

        getBuilder(String.valueOf(ModItems.USELESS_SKELETON_SKULL.getRegistryName())).parent(getExistingFile(mcLoc(ITEM_FOLDER + "/template_skull")));

        this.withExistingParent("useless_sheep_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_pig_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_chicken_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_cow_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
        this.withExistingParent("useless_skeleton_spawn_egg", mcLoc(ITEM_FOLDER + "/template_spawn_egg"));
    }

    private void basicTool(ItemLike item) {
        final ResourceLocation id = item.asItem().getRegistryName();
        this.singleTexture(id.getPath(), mcLoc(ITEM_FOLDER + "/handheld"), "layer0", modLoc(ITEM_FOLDER + "/" + id.getPath()));
    }
}
