package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessFluids;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;

import java.util.Optional;

public class UselessItemModelProvider extends ModelProvider {
    public UselessItemModelProvider(PackOutput packOutput) {
        super(packOutput, UselessMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(UselessItems.RAW_USELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.RAW_SUPER_USELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessBlocks.USELESS_OAK_DOOR.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SHEARS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_BOOTS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_WHEAT_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_WHEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.COFFEE_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.COFFEE_BEANS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_BONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_FEATHER.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(UselessItems.USELESS_SHEEP_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(UselessItems.USELESS_PIG_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(UselessItems.USELESS_CHICKEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(UselessItems.USELESS_COW_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(UselessItems.USELESS_SKELETON_SKULL.get(), ModelTemplates.FLAT_ITEM);

        this.generatePaintBrush(itemModels, UselessItems.PAINT_BRUSH.value());

        itemModels.itemModelOutput.accept(UselessItems.BUCKET_PAINT.value(), new DynamicFluidContainerModel.Unbaked(new DynamicFluidContainerModel.Textures(
                Optional.of(Identifier.withDefaultNamespace("item/bucket")),
                Optional.of(Identifier.withDefaultNamespace("item/bucket")),
                Optional.of(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "item/mask/bucket_fluid")),
                Optional.of(Identifier.fromNamespaceAndPath(NeoForgeMod.MOD_ID, "item/mask/bucket_fluid_cover"))
        ), UselessFluids.PAINT.get(), false, true, true));

        // TODO: special renderers
        itemModels.generateShield(UselessItems.USELESS_SHIELD.value());
        itemModels.generateShield(UselessItems.SUPER_USELESS_SHIELD.value());
        itemModels.generateElytra(UselessItems.USELESS_ELYTRA.value());
        itemModels.generateElytra(UselessItems.SUPER_USELESS_ELYTRA.value());

        Identifier skullParent = ModelLocationUtils.decorateItemModelLocation("template_skull");
        blockModels.createHead(UselessBlocks.USELESS_SKELETON_SKULL.value(), UselessBlocks.USELESS_SKELETON_WALL_SKULL.value(), UselessSkullBlock.Types.USELESS_SKELETON, skullParent);
    }

    private void generatePaintBrush(ItemModelGenerators itemModels, Item item) {
        itemModels.itemModelOutput.accept(item,
                ItemModelUtils.tintedModel(
                        ModelTemplates.FLAT_HANDHELD_ITEM.create(item,
                                TextureMapping.layered(
                                        TextureMapping.getItemTexture(item, "_0"),
                                        TextureMapping.getItemTexture(item, "_1")),
                                itemModels.modelOutput)
                        // TODO: tint source
                ));
    }
}
