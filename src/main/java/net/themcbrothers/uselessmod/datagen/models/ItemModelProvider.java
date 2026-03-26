package net.themcbrothers.uselessmod.datagen.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.themcbrothers.lib.data.models.ModelSubProvider;
import net.themcbrothers.uselessmod.client.PaintTintSource;
import net.themcbrothers.uselessmod.core.UselessFluids;
import net.themcbrothers.uselessmod.core.UselessItems;

import java.util.Optional;

import static net.minecraft.client.data.models.model.ModelTemplates.createItem;

public class ItemModelProvider extends ModelSubProvider {
    public static final ModelTemplate TWO_LAYERED_HANDHELD_ITEM = createItem("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    public ItemModelProvider(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super(blockModels, itemModels);
    }

    @Override
    protected void register() {
        itemModels.generateFlatItem(UselessItems.RAW_USELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.RAW_SUPER_USELESS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.SUPER_USELESS_NUGGET.get(), ModelTemplates.FLAT_ITEM);
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
        itemModels.generateFlatItem(UselessItems.USELESS_WHEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.COFFEE_BEANS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_BONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SHEEP_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_PIG_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_CHICKEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_COW_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SKELETON_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        this.generatePaintBrush(UselessItems.PAINT_BRUSH.value());

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
    }

    private void generatePaintBrush(Item item) {
        itemModels.itemModelOutput.accept(item,
                ItemModelUtils.tintedModel(
                        TWO_LAYERED_HANDHELD_ITEM.create(item,
                                TextureMapping.layered(
                                        TextureMapping.getItemTexture(item, "_0"),
                                        TextureMapping.getItemTexture(item, "_1")),
                                itemModels.modelOutput),
                        ItemModelGenerators.BLANK_LAYER, PaintTintSource.INSTANCE
                ));
    }
}
