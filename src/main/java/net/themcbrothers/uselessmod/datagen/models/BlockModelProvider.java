package net.themcbrothers.uselessmod.datagen.models;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.themcbrothers.lib.data.models.ModelSubProvider;
import net.themcbrothers.uselessmod.client.CoffeeTintSource;
import net.themcbrothers.uselessmod.client.PaintTintSource;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;

import java.util.function.Function;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class BlockModelProvider extends ModelSubProvider {
    public BlockModelProvider(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super(blockModels, itemModels);
    }

    @Override
    protected void register() {
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
        this.createPassiveRail(UselessBlocks.USELESS_RAIL.value());
        this.createActiveRail(UselessBlocks.USELESS_POWERED_RAIL.value());
        this.createActiveRail(UselessBlocks.USELESS_DETECTOR_RAIL.value());
        this.createActiveRail(UselessBlocks.USELESS_ACTIVATOR_RAIL.value());
        this.createBarsAndItem(UselessBlocks.USELESS_BARS.value());
        this.createBarsAndItem(UselessBlocks.SUPER_USELESS_BARS.value());
        this.createPlantWithDefaultItem(UselessBlocks.RED_ROSE.value(), UselessBlocks.POTTED_RED_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(UselessBlocks.BLUE_ROSE.value(), UselessBlocks.POTTED_BLUE_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(UselessBlocks.USELESS_ROSE.value(), UselessBlocks.POTTED_USELESS_ROSE.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        this.createPlantWithDefaultItem(UselessBlocks.USELESS_OAK_SAPLING.value(), UselessBlocks.POTTED_USELESS_OAK_SAPLING.value(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createTrivialCube(UselessBlocks.USELESS_OAK_LEAVES.value());
        blockModels.woodProvider(UselessBlocks.USELESS_OAK_LOG.value()).logWithHorizontal(UselessBlocks.USELESS_OAK_LOG.value()).wood(UselessBlocks.USELESS_OAK_WOOD.value());
        blockModels.woodProvider(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value()).logWithHorizontal(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value()).wood(UselessBlocks.STRIPPED_USELESS_OAK_WOOD.value());
        blockModels.createHangingSign(UselessBlocks.STRIPPED_USELESS_OAK_LOG.value(), UselessBlocks.USELESS_OAK_HANGING_SIGN.value(), UselessBlocks.USELESS_OAK_WALL_HANGING_SIGN.value());
        blockModels.family(UselessBlocks.USELESS_OAK_PLANKS.value())
                .button(UselessBlocks.USELESS_OAK_BUTTON.value())
                .fence(UselessBlocks.USELESS_OAK_FENCE.value())
                .fenceGate(UselessBlocks.USELESS_OAK_FENCE_GATE.value())
                .pressurePlate(UselessBlocks.USELESS_OAK_PRESSURE_PLATE.value())
                .slab(UselessBlocks.USELESS_OAK_SLAB.value())
                .stairs(UselessBlocks.USELESS_OAK_STAIRS.value())
        ;
        blockModels.createFullAndCarpetBlocks(UselessBlocks.USELESS_WOOL.value(), UselessBlocks.USELESS_CARPET.value());
        blockModels.createBed(UselessBlocks.USELESS_BED.value(), UselessBlocks.USELESS_WOOL.value(), DyeColor.LIME); // TODO: custom bed
        this.createCropBlock(UselessBlocks.USELESS_WHEAT.value(), BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createCropBlock(UselessBlocks.COFFEE_BEANS.value(), BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createWildCropBlock(UselessBlocks.WILD_USELESS_WHEAT.value(), UselessBlocks.USELESS_WHEAT.value(), BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createWildCropBlock(UselessBlocks.WILD_COFFEE_BEANS.value(), UselessBlocks.COFFEE_BEANS.value(), BlockStateProperties.AGE_7, 0, 1, 2, 3, 4, 5, 6, 7);
        this.createSign(UselessBlocks.USELESS_OAK_SIGN.value(), UselessBlocks.USELESS_OAK_WALL_SIGN.value(), UselessBlocks.USELESS_OAK_PLANKS.value());
        this.createDoor(UselessBlocks.USELESS_DOOR.value());
        this.createDoor(UselessBlocks.SUPER_USELESS_DOOR.value());
        this.createDoor(UselessBlocks.USELESS_OAK_DOOR.value());
        this.createTrapdoor(UselessBlocks.USELESS_TRAPDOOR.value());
        this.createTrapdoor(UselessBlocks.SUPER_USELESS_TRAPDOOR.value());
        this.createTrapdoor(UselessBlocks.USELESS_OAK_TRAPDOOR.value());
        this.createPaintedWool();
        this.createLamp(UselessBlocks.WHITE_LAMP.value());
        this.createLamp(UselessBlocks.ORANGE_LAMP.value());
        this.createLamp(UselessBlocks.MAGENTA_LAMP.value());
        this.createLamp(UselessBlocks.LIGHT_BLUE_LAMP.value());
        this.createLamp(UselessBlocks.YELLOW_LAMP.value());
        this.createLamp(UselessBlocks.LIME_LAMP.value());
        this.createLamp(UselessBlocks.PINK_LAMP.value());
        this.createLamp(UselessBlocks.GRAY_LAMP.value());
        this.createLamp(UselessBlocks.LIGHT_GRAY_LAMP.value());
        this.createLamp(UselessBlocks.CYAN_LAMP.value());
        this.createLamp(UselessBlocks.PURPLE_LAMP.value());
        this.createLamp(UselessBlocks.BLUE_LAMP.value());
        this.createLamp(UselessBlocks.BROWN_LAMP.value());
        this.createLamp(UselessBlocks.GREEN_LAMP.value());
        this.createLamp(UselessBlocks.RED_LAMP.value());
        this.createLamp(UselessBlocks.BLACK_LAMP.value());
        this.createLantern(UselessBlocks.LANTERN.value());

        blockModels.createNonTemplateModelBlock(UselessBlocks.MACHINE_SUPPLIER.value());

        blockStateOutput.accept(MultiVariantGenerator.dispatch(UselessBlocks.COFFEE_MACHINE.value(), plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.COFFEE_MACHINE.value()))).with(ROTATION_HORIZONTAL_FACING));
        blockStateOutput.accept(MultiVariantGenerator.dispatch(UselessBlocks.CUP.value(), plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.CUP.value()))).with(ROTATION_HORIZONTAL_FACING));
        blockStateOutput.accept(MultiVariantGenerator.dispatch(UselessBlocks.CUP_COFFEE.value(), plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.CUP_COFFEE.value()))).with(ROTATION_HORIZONTAL_FACING));
        blockModels.itemModelOutput.accept(UselessBlocks.CUP_COFFEE.asItem(), ItemModelUtils.tintedModel(ModelLocationUtils.getModelLocation(UselessBlocks.CUP_COFFEE.value()), CoffeeTintSource.INSTANCE));

        Identifier skullParent = ModelLocationUtils.decorateItemModelLocation("template_skull");
        blockModels.createHead(UselessBlocks.USELESS_SKELETON_SKULL.value(), UselessBlocks.USELESS_SKELETON_WALL_SKULL.value(), UselessSkullBlock.Types.USELESS_SKELETON, skullParent);

        // Wall Closet
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(UselessBlocks.WALL_CLOSET.value())
                .with(PropertyDispatch.initial(BlockStateProperties.OPEN)
                        .select(false, plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.WALL_CLOSET.value())))
                        .select(true, plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.WALL_CLOSET.value(), "_open"))))
                .with(ROTATION_HORIZONTAL_FACING));

        // Light Switch
        MultiVariant lightSwitch = plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.LIGHT_SWITCH.value()));
        MultiVariant lightSwitchPressed = plainVariant(ModelLocationUtils.getModelLocation(UselessBlocks.LIGHT_SWITCH.value(), "_pressed"));
        blockStateOutput.accept(BlockModelGenerators.createButton(UselessBlocks.LIGHT_SWITCH.value(), lightSwitch, lightSwitchPressed));
        blockStateOutput.accept(MultiVariantGenerator.dispatch(UselessBlocks.LIGHT_SWITCH_BLOCK.value())
                .with(PropertyDispatch.initial(BlockStateProperties.POWERED)
                        .select(false, plainVariant(TexturedModel.CUBE
                                .updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, TextureMapping.getBlockTexture(UselessBlocks.LIGHT_SWITCH.value())))
                                .create(UselessBlocks.LIGHT_SWITCH_BLOCK.value(), modelOutput)))
                        .select(true, plainVariant(TexturedModel.CUBE
                                .updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, TextureMapping.getBlockTexture(UselessBlocks.LIGHT_SWITCH.value(), "_powered")))
                                .createWithSuffix(UselessBlocks.LIGHT_SWITCH_BLOCK.value(), "_powered", modelOutput)))));

        // Paint Bucket
        blockModels.createNonTemplateModelBlock(UselessBlocks.PAINT_BUCKET.value());
        Identifier paintBucketInventory = ModelLocationUtils.getModelLocation(UselessBlocks.PAINT_BUCKET.value(), "_inventory");
        blockModels.registerSimpleItemModel(UselessBlocks.PAINT_BUCKET.value(), paintBucketInventory);
    }

    private void createLamp(Block lamp) {
        MultiVariant multivariant = plainVariant(TexturedModel.CUBE.create(lamp, modelOutput));
        MultiVariant multivariant1 = plainVariant(blockModels.createSuffixedVariant(lamp, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(lamp).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, multivariant1, multivariant))
                );
    }

    private void createPaintedWool() {
        Identifier blockModel = ModelLocationUtils.getModelLocation(UselessBlocks.PAINTED_WOOL.value());
        blockStateOutput.accept(createSimpleBlock(UselessBlocks.PAINTED_WOOL.value(), plainVariant(blockModel)));
        blockModels.itemModelOutput.accept(UselessBlocks.PAINTED_WOOL.asItem(), ItemModelUtils.tintedModel(blockModel, PaintTintSource.INSTANCE));
    }

    private void createTrapdoor(Block trapdoorBlock) {
        TextureMapping texturemapping = TextureMapping.defaultTexture(trapdoorBlock);
        MultiVariant multivariant = plainVariant(cutout(ModelTemplates.TRAPDOOR_TOP).create(trapdoorBlock, texturemapping, modelOutput));
        Identifier identifier = cutout(ModelTemplates.TRAPDOOR_BOTTOM).create(trapdoorBlock, texturemapping, modelOutput);
        MultiVariant multivariant1 = plainVariant(cutout(ModelTemplates.TRAPDOOR_OPEN).create(trapdoorBlock, texturemapping, modelOutput));
        blockStateOutput.accept(BlockModelGenerators.createTrapdoor(trapdoorBlock, multivariant, plainVariant(identifier), multivariant1));
        blockModels.registerSimpleItemModel(trapdoorBlock, identifier);
    }

    private void createWildCropBlock(Block wildCropBlock, Block cropBlock, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2objectmap = new Int2ObjectOpenHashMap<>();
            blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(wildCropBlock)
                                    .with(
                                            PropertyDispatch.initial(ageProperty)
                                                    .generate(
                                                            stage -> {
                                                                int i = ageToVisualStageMapping[stage];
                                                                return plainVariant(
                                                                        int2objectmap.computeIfAbsent(
                                                                                i,
                                                                                stage1 -> createWildCropVariant(
                                                                                        wildCropBlock, cropBlock, "_stage" + stage1, cutout(ModelTemplates.CROP), TextureMapping::crop
                                                                                )
                                                                        )
                                                                );
                                                            }
                                                    )
                                    )
                    );
        }
    }

    private Identifier createWildCropVariant(Block wildCrop, Block crop, String suffix, ModelTemplate modelTemplate, Function<Identifier, TextureMapping> textureMappingGetter) {
        return modelTemplate.createWithSuffix(wildCrop, suffix, textureMappingGetter.apply(TextureMapping.getBlockTexture(crop, suffix)), this.modelOutput);
    }

    private void createCropBlock(Block cropBlock, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        this.registerSimpleFlatItemModel(cropBlock.asItem());
        if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2objectmap = new Int2ObjectOpenHashMap<>();
            blockStateOutput
                    .accept(
                            MultiVariantGenerator.dispatch(cropBlock)
                                    .with(
                                            PropertyDispatch.initial(ageProperty)
                                                    .generate(
                                                            stage -> {
                                                                int i = ageToVisualStageMapping[stage];
                                                                return plainVariant(
                                                                        int2objectmap.computeIfAbsent(
                                                                                i,
                                                                                stage1 -> blockModels.createSuffixedVariant(
                                                                                        cropBlock, "_stage" + stage1, cutout(ModelTemplates.CROP), TextureMapping::crop
                                                                                )
                                                                        )
                                                                );
                                                            }
                                                    )
                                    )
                    );
        }
    }

    private void createPassiveRail(Block railBlock) {
        TextureMapping textureMapping = TextureMapping.rail(railBlock);
        TextureMapping textureMapping1 = TextureMapping.rail(TextureMapping.getBlockTexture(railBlock, "_corner"));
        MultiVariant multiVariant = plainVariant(cutout(ModelTemplates.RAIL_FLAT).create(railBlock, textureMapping, modelOutput));
        MultiVariant multiVariant1 = plainVariant(cutout(ModelTemplates.RAIL_CURVED).create(railBlock, textureMapping1, modelOutput));
        MultiVariant multiVariant2 = plainVariant(cutout(ModelTemplates.RAIL_RAISED_NE).create(railBlock, textureMapping, modelOutput));
        MultiVariant multiVariant3 = plainVariant(cutout(ModelTemplates.RAIL_RAISED_SW).create(railBlock, textureMapping, modelOutput));
        this.registerSimpleFlatItemModel(railBlock);
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(railBlock)
                                .with(
                                        PropertyDispatch.initial(BlockStateProperties.RAIL_SHAPE)
                                                .select(RailShape.NORTH_SOUTH, multiVariant)
                                                .select(RailShape.EAST_WEST, multiVariant.with(Y_ROT_90))
                                                .select(RailShape.ASCENDING_EAST, multiVariant2.with(Y_ROT_90))
                                                .select(RailShape.ASCENDING_WEST, multiVariant3.with(Y_ROT_90))
                                                .select(RailShape.ASCENDING_NORTH, multiVariant2)
                                                .select(RailShape.ASCENDING_SOUTH, multiVariant3)
                                                .select(RailShape.SOUTH_EAST, multiVariant1)
                                                .select(RailShape.SOUTH_WEST, multiVariant1.with(Y_ROT_90))
                                                .select(RailShape.NORTH_WEST, multiVariant1.with(Y_ROT_180))
                                                .select(RailShape.NORTH_EAST, multiVariant1.with(Y_ROT_270))
                                )
                );
    }

    private void createActiveRail(Block railBlock) {
        MultiVariant multiVariant = plainVariant(blockModels.createSuffixedVariant(railBlock, "", cutout(ModelTemplates.RAIL_FLAT), TextureMapping::rail));
        MultiVariant multiVariant1 = plainVariant(blockModels.createSuffixedVariant(railBlock, "", cutout(ModelTemplates.RAIL_RAISED_NE), TextureMapping::rail));
        MultiVariant multiVariant2 = plainVariant(blockModels.createSuffixedVariant(railBlock, "", cutout(ModelTemplates.RAIL_RAISED_SW), TextureMapping::rail));
        MultiVariant multiVariant3 = plainVariant(blockModels.createSuffixedVariant(railBlock, "_on", cutout(ModelTemplates.RAIL_FLAT), TextureMapping::rail));
        MultiVariant multiVariant4 = plainVariant(blockModels.createSuffixedVariant(railBlock, "_on", cutout(ModelTemplates.RAIL_RAISED_NE), TextureMapping::rail));
        MultiVariant multiVariant5 = plainVariant(blockModels.createSuffixedVariant(railBlock, "_on", cutout(ModelTemplates.RAIL_RAISED_SW), TextureMapping::rail));
        this.registerSimpleFlatItemModel(railBlock);
        this.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(railBlock)
                                .with(PropertyDispatch.initial(BlockStateProperties.POWERED, BlockStateProperties.RAIL_SHAPE_STRAIGHT).generate((p_403947_, p_403948_) -> {
                                    return switch (p_403948_) {
                                        case NORTH_SOUTH -> p_403947_ ? multiVariant3 : multiVariant;
                                        case EAST_WEST -> (p_403947_ ? multiVariant3 : multiVariant).with(Y_ROT_90);
                                        case ASCENDING_EAST ->
                                                (p_403947_ ? multiVariant4 : multiVariant1).with(Y_ROT_90);
                                        case ASCENDING_WEST ->
                                                (p_403947_ ? multiVariant5 : multiVariant2).with(Y_ROT_90);
                                        case ASCENDING_NORTH -> p_403947_ ? multiVariant4 : multiVariant1;
                                        case ASCENDING_SOUTH -> p_403947_ ? multiVariant5 : multiVariant2;
                                        default -> throw new UnsupportedOperationException("Fix you generator!");
                                    };
                                }))
                );
    }

    private void createBarsAndItem(Block block) {
        TextureMapping textureMapping = TextureMapping.bars(block);
        blockModels.createBars(
                block,
                cutout(ModelTemplates.BARS_POST_ENDS).create(block, textureMapping, this.modelOutput),
                cutout(ModelTemplates.BARS_POST).create(block, textureMapping, this.modelOutput),
                cutout(ModelTemplates.BARS_CAP).create(block, textureMapping, this.modelOutput),
                cutout(ModelTemplates.BARS_CAP_ALT).create(block, textureMapping, this.modelOutput),
                cutout(ModelTemplates.BARS_POST_SIDE).create(block, textureMapping, this.modelOutput),
                cutout(ModelTemplates.BARS_POST_SIDE_ALT).create(block, textureMapping, this.modelOutput)
        );
        this.registerSimpleFlatItemModel(block);
    }

    private void createDoor(Block doorBlock) {
        TextureMapping textureMapping = TextureMapping.door(doorBlock);
        MultiVariant multiVariant = plainVariant(cutout(ModelTemplates.DOOR_BOTTOM_LEFT).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant1 = plainVariant(cutout(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant2 = plainVariant(cutout(ModelTemplates.DOOR_BOTTOM_RIGHT).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant3 = plainVariant(cutout(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant4 = plainVariant(cutout(ModelTemplates.DOOR_TOP_LEFT).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant5 = plainVariant(cutout(ModelTemplates.DOOR_TOP_LEFT_OPEN).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant6 = plainVariant(cutout(ModelTemplates.DOOR_TOP_RIGHT).create(doorBlock, textureMapping, this.modelOutput));
        MultiVariant multiVariant7 = plainVariant(cutout(ModelTemplates.DOOR_TOP_RIGHT_OPEN).create(doorBlock, textureMapping, this.modelOutput));
        this.registerSimpleFlatItemModel(doorBlock.asItem());
        this.blockStateOutput
                .accept(
                        BlockModelGenerators.createDoor(doorBlock, multiVariant, multiVariant1, multiVariant2, multiVariant3, multiVariant4, multiVariant5, multiVariant6, multiVariant7)
                );
    }

    private void createPlantWithDefaultItem(Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType) {
        this.registerSimpleItemModel(block.asItem(), plantType.createItemModel(this.blockModels, block));

        TextureMapping textureMapping = plantType.getTextureMapping(block);
        MultiVariant multiVariant = plainVariant(cutout(plantType.getCross()).create(block, textureMapping, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(block, multiVariant));

        TextureMapping textureMappingPotted = plantType.getPlantTextureMapping(block);
        MultiVariant multiVariantPotted = plainVariant(cutout(plantType.getCrossPot()).create(pottedBlock, textureMappingPotted, this.modelOutput));
        this.blockStateOutput.accept(createSimpleBlock(pottedBlock, multiVariantPotted));
    }

    private void createLantern(Block lanternBlock) {
        MultiVariant multivariant = plainVariant(TexturedModel.LANTERN.updateTemplate(this::cutout).create(lanternBlock, this.modelOutput));
        MultiVariant multivariant1 = plainVariant(TexturedModel.HANGING_LANTERN.updateTemplate(this::cutout).create(lanternBlock, this.modelOutput));
        this.registerSimpleFlatItemModel(lanternBlock.asItem());
        this.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(lanternBlock).with(createBooleanModelDispatch(BlockStateProperties.HANGING, multivariant1, multivariant)));
    }

    private void createSign(Block standingSignBlock, Block wallSignBlock, Block plankForParticlesBlock) {
        TextureMapping textureMapping = TextureMapping.cube(plankForParticlesBlock);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ModelTemplates.PARTICLE_ONLY.create(standingSignBlock, textureMapping, this.modelOutput)
        );
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(standingSignBlock, multivariant));
        this.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSignBlock, multivariant));
        this.registerSimpleFlatItemModel(standingSignBlock.asItem());
    }

    private void registerSimpleFlatItemModel(Block block) {
        this.blockModels.registerSimpleFlatItemModel(block);
    }

    private void registerSimpleFlatItemModel(Item item) {
        this.blockModels.registerSimpleFlatItemModel(item);
    }

    public void registerSimpleItemModel(Item item, Identifier model) {
        this.blockModels.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
    }

    private ModelTemplate cutout(ModelTemplate modelTemplate) {
        return modelTemplate.extend().renderType("cutout").build();
    }
}
