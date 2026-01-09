package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

import static net.minecraft.client.data.models.model.ModelTemplates.createItem;

public class UselessModelProvider extends ModelProvider {
    public static final ModelTemplate TWO_LAYERED_HANDHELD_ITEM = createItem("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

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
        blockModels.createBarsAndItem(UselessBlocks.USELESS_BARS.value());
        blockModels.createBarsAndItem(UselessBlocks.SUPER_USELESS_BARS.value());
        blockModels.createDoor(UselessBlocks.USELESS_DOOR.value());
        blockModels.createDoor(UselessBlocks.SUPER_USELESS_DOOR.value());
        blockModels.createTrapdoor(UselessBlocks.USELESS_TRAPDOOR.value());
        blockModels.createTrapdoor(UselessBlocks.SUPER_USELESS_TRAPDOOR.value());
        blockModels.createPlantWithDefaultItem(UselessBlocks.RED_ROSE.value(), UselessBlocks.POTTED_RED_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(UselessBlocks.BLUE_ROSE.value(), UselessBlocks.POTTED_BLUE_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(UselessBlocks.USELESS_ROSE.value(), UselessBlocks.POTTED_USELESS_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(UselessBlocks.USELESS_OAK_SAPLING.value(), UselessBlocks.POTTED_USELESS_OAK_SAPLING.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createTrivialCube(UselessBlocks.USELESS_OAK_LEAVES.value());
        blockModels.woodProvider(UselessBlocks.USELESS_OAK_LOG.value()).logWithHorizontal(UselessBlocks.USELESS_OAK_LOG.value()).wood(UselessBlocks.USELESS_OAK_WOOD.value());
        blockModels.woodProvider(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value()).logWithHorizontal(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value()).wood(UselessBlocks.STRIPPED_USELESS_OAK_WOOD.value());
        blockModels.createTrapdoor(UselessBlocks.USELESS_OAK_TRAPDOOR.value());
        blockModels.createHangingSign(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value(), UselessBlocks.USELESS_OAK_HANGING_SIGN.value(), UselessBlocks.USELESS_OAK_WALL_HANGING_SIGN.value());
        blockModels.family(UselessBlocks.USELESS_OAK_PLANKS.value())
                .button(UselessBlocks.USELESS_OAK_BUTTON.value())
                .fence(UselessBlocks.USELESS_OAK_FENCE.value())
                .fenceGate(UselessBlocks.USELESS_OAK_FENCE_GATE.value())
                .pressurePlate(UselessBlocks.USELESS_OAK_PRESSURE_PLATE.value())
//                .sign(UselessBlocks.USELESS_OAK_SIGN.value())
                .slab(UselessBlocks.USELESS_OAK_SLAB.value())
                .stairs(UselessBlocks.USELESS_OAK_STAIRS.value())
                .door(UselessBlocks.USELESS_OAK_DOOR.value())
        ;
        blockModels.createFullAndCarpetBlocks(UselessBlocks.USELESS_WOOL.value(), UselessBlocks.USELESS_CARPET.value());
        blockModels.createBed(UselessBlocks.USELESS_BED.value(), UselessBlocks.USELESS_WOOL.value(), DyeColor.LIME); // TODO: custom bed

        this.createPaintedWool(blockModels);
        this.createLamp(blockModels, UselessBlocks.WHITE_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.ORANGE_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.MAGENTA_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.LIGHT_BLUE_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.YELLOW_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.LIME_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.PINK_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.GRAY_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.LIGHT_GRAY_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.CYAN_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.PURPLE_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.BLUE_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.BROWN_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.GREEN_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.RED_LAMP.value());
        this.createLamp(blockModels, UselessBlocks.BLACK_LAMP.value());

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
                        TWO_LAYERED_HANDHELD_ITEM.create(item,
                                TextureMapping.layered(
                                        TextureMapping.getItemTexture(item, "_0"),
                                        TextureMapping.getItemTexture(item, "_1")),
                                itemModels.modelOutput),
                        ItemModelGenerators.BLANK_LAYER, PaintTintSource.INSTANCE
                ));
    }


    private void createLamp(BlockModelGenerators blockModels, Block lamp) {
        MultiVariant multivariant = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(lamp, blockModels.modelOutput));
        MultiVariant multivariant1 = BlockModelGenerators.plainVariant(blockModels.createSuffixedVariant(lamp, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(lamp).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, multivariant1, multivariant))
                );
    }

    private void createPaintedWool(BlockModelGenerators blockModels) {
        Identifier blockModel = ModelLocationUtils.getModelLocation(UselessBlocks.PAINTED_WOOL.value());
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(UselessBlocks.PAINTED_WOOL.value(), BlockModelGenerators.plainVariant(blockModel)));
        blockModels.itemModelOutput.accept(UselessBlocks.PAINTED_WOOL.asItem(), ItemModelUtils.tintedModel(blockModel, PaintTintSource.INSTANCE));
    }
}
