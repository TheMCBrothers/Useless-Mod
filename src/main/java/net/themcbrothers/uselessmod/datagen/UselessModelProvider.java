package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.PaintTintSource;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessFluids;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;

import java.util.Optional;
import java.util.stream.Stream;

public class UselessModelProvider extends ModelProvider {
    public UselessModelProvider(PackOutput packOutput) {
        super(packOutput, UselessMod.MOD_ID);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(UselessBlocks.USELESS_BLOCK.value());
        blockModels.createTrivialCube(UselessBlocks.SUPER_USELESS_BLOCK.value());
        blockModels.createTrivialCube(UselessBlocks.RAW_USELESS_BLOCK.value());
        blockModels.createTrivialCube(UselessBlocks.RAW_SUPER_USELESS_BLOCK.value());
        blockModels.createTrivialCube(UselessBlocks.USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.DEEPSLATE_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.NETHER_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.END_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.SUPER_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.NETHER_SUPER_USELESS_ORE.value());
        blockModels.createTrivialCube(UselessBlocks.END_SUPER_USELESS_ORE.value());
        blockModels.createPassiveRail(UselessBlocks.USELESS_RAIL.value());
        blockModels.createActiveRail(UselessBlocks.USELESS_POWERED_RAIL.value());
        blockModels.createActiveRail(UselessBlocks.USELESS_DETECTOR_RAIL.value());
        blockModels.createActiveRail(UselessBlocks.USELESS_ACTIVATOR_RAIL.value());
        blockModels.createDoor(UselessBlocks.USELESS_DOOR.value());
        blockModels.createDoor(UselessBlocks.SUPER_USELESS_DOOR.value());
        blockModels.createTrapdoor(UselessBlocks.USELESS_TRAPDOOR.value());
        blockModels.createTrapdoor(UselessBlocks.SUPER_USELESS_TRAPDOOR.value());
        blockModels.createBarsAndItem(UselessBlocks.USELESS_BARS.value());
        blockModels.createBarsAndItem(UselessBlocks.SUPER_USELESS_BARS.value());
        blockModels.family(UselessBlocks.USELESS_OAK_PLANKS.value())
                .button(UselessBlocks.USELESS_OAK_BUTTON.value())
                .fence(UselessBlocks.USELESS_OAK_FENCE.value())
                .fenceGate(UselessBlocks.USELESS_OAK_FENCE_GATE.value())
                .pressurePlate(UselessBlocks.USELESS_OAK_PRESSURE_PLATE.value())
//                .sign(UselessBlocks.USELESS_OAK_SIGN.value())
                .slab(UselessBlocks.USELESS_OAK_SLAB.value())
                .stairs(UselessBlocks.USELESS_OAK_STAIRS.value())
                .door(UselessBlocks.USELESS_OAK_DOOR.value())
                .trapdoor(UselessBlocks.USELESS_OAK_TRAPDOOR.value())
        ;

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
        itemModels.generateFlatItem(UselessItems.USELESS_WHEAT_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_WHEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.COFFEE_SEEDS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.COFFEE_BEANS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_BONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_FEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SHEEP_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_PIG_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_CHICKEN_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_COW_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(UselessItems.USELESS_SKELETON_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

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
                                itemModels.modelOutput),
                        ItemModelGenerators.BLANK_LAYER, PaintTintSource.INSTANCE
                ));
    }
}
