package net.themcbrothers.uselessmod.data;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.UselessCropBlock;

import java.util.Objects;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;
import static net.themcbrothers.uselessmod.init.ModBlocks.*;

public class UselessBlockStateProvider extends BlockStateProvider {
    public UselessBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
        super(packOutput, UselessMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Block and Item Models
        simpleBlockWithItem(USELESS_ORE.get());
        simpleBlockWithItem(SUPER_USELESS_ORE.get());
        simpleBlockWithItem(DEEPSLATE_USELESS_ORE.get());
        simpleBlockWithItem(DEEPSLATE_SUPER_USELESS_ORE.get());
        simpleBlockWithItem(NETHER_USELESS_ORE.get());
        simpleBlockWithItem(NETHER_SUPER_USELESS_ORE.get());
        simpleBlockWithItem(END_USELESS_ORE.get());
        simpleBlockWithItem(END_SUPER_USELESS_ORE.get());
        simpleBlockWithItem(USELESS_BLOCK.get());
        simpleBlockWithItem(SUPER_USELESS_BLOCK.get());
        simpleBlockWithItem(RAW_USELESS_BLOCK.get());
        simpleBlockWithItem(RAW_SUPER_USELESS_BLOCK.get());
        axisBlock((RotatedPillarBlock) USELESS_OAK_LOG.get(), modLoc("block/useless_oak_log"), modLoc("block/useless_oak_log_top"));
        axisBlock((RotatedPillarBlock) USELESS_OAK_WOOD.get(), modLoc("block/useless_oak_log"), modLoc("block/useless_oak_log"));
        axisBlock((RotatedPillarBlock) STRIPPED_USELESS_OAK_LOG.get(), modLoc("block/stripped_useless_oak_log"), modLoc("block/stripped_useless_oak_log_top"));
        axisBlock((RotatedPillarBlock) STRIPPED_USELESS_OAK_WOOD.get(), modLoc("block/stripped_useless_oak_log"), modLoc("block/stripped_useless_oak_log"));
        crossPlant(RED_ROSE.get());
        crossPlant(BLUE_ROSE.get());
        crossPlant(USELESS_ROSE.get());
        pottedPlant(POTTED_RED_ROSE.get(), RED_ROSE.get());
        pottedPlant(POTTED_BLUE_ROSE.get(), BLUE_ROSE.get());
        pottedPlant(POTTED_USELESS_ROSE.get(), USELESS_ROSE.get());
        pottedPlant(POTTED_USELESS_OAK_SAPLING.get(), USELESS_OAK_SAPLING.get());
        wildCropPlant((UselessCropBlock) USELESS_WHEAT.get(), (UselessCropBlock) WILD_USELESS_WHEAT.get(), "useless_wheat");
        wildCropPlant((UselessCropBlock) COFFEE_BEANS.get(), (UselessCropBlock) WILD_COFFEE_BEANS.get(), "coffee_beans");
        crossPlant(USELESS_OAK_SAPLING.get());
        simpleBlockWithItem(USELESS_OAK_LEAVES.get());
        simpleBlockWithItem(USELESS_OAK_PLANKS.get());
        stairsBlock((StairBlock) USELESS_OAK_STAIRS.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        slabBlock((SlabBlock) USELESS_OAK_SLAB.get(), blockTexture(USELESS_OAK_PLANKS.get()), blockTexture(USELESS_OAK_PLANKS.get()));
        fenceBlock((FenceBlock) USELESS_OAK_FENCE.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) USELESS_OAK_FENCE_GATE.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        simpleBlock(PAINT_BUCKET.get(), models().getExistingFile(blockTexture(PAINT_BUCKET.get())));
        simpleBlock(PAINTED_WOOL.get(), models().getExistingFile(blockTexture(PAINTED_WOOL.get())));
        doorBlockWithRenderType((DoorBlock) USELESS_OAK_DOOR.get(), modLoc("block/useless_oak_door_bottom"), modLoc("block/useless_oak_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) USELESS_OAK_TRAPDOOR.get(), blockTexture(USELESS_OAK_TRAPDOOR.get()), true, "cutout");
        buttonBlock((ButtonBlock) USELESS_OAK_BUTTON.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) USELESS_OAK_PRESSURE_PLATE.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        signBlock((StandingSignBlock) USELESS_OAK_SIGN.get(), (WallSignBlock) USELESS_OAK_WALL_SIGN.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        signBlock((CeilingHangingSignBlock) USELESS_OAK_HANGING_SIGN.get(), (WallHangingSignBlock) USELESS_OAK_WALL_HANGING_SIGN.get(), blockTexture(USELESS_OAK_PLANKS.get()));
        ironBars((IronBarsBlock) USELESS_BARS.get(), "useless_bars");
        ironBars((IronBarsBlock) SUPER_USELESS_BARS.get(), "super_useless_bars");
        doorBlockWithRenderType((DoorBlock) USELESS_DOOR.get(), modLoc("block/useless_door_bottom"), modLoc("block/useless_door_top"), "cutout");
        doorBlockWithRenderType((DoorBlock) SUPER_USELESS_DOOR.get(), modLoc("block/super_useless_door_bottom"), modLoc("block/super_useless_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) USELESS_TRAPDOOR.get(), blockTexture(USELESS_TRAPDOOR.get()), false, "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) SUPER_USELESS_TRAPDOOR.get(), blockTexture(SUPER_USELESS_TRAPDOOR.get()), false, "cutout");
        simpleBlockWithItem(USELESS_WOOL.get());
        simpleBlock(USELESS_CARPET.get(), models().carpet("useless_carpet", modLoc("block/useless_wool")));
        simpleBlock(USELESS_BED.get(), models().getExistingFile(mcLoc("block/bed")));
        simpleBlock(USELESS_SKELETON_SKULL.get(), this.models().getExistingFile(mcLoc(BLOCK_FOLDER + "/skull")));
        simpleBlock(USELESS_SKELETON_WALL_SKULL.get(), this.models().getExistingFile(mcLoc(BLOCK_FOLDER + "/skull")));
        lampBlock(WHITE_LAMP.get());
        lampBlock(ORANGE_LAMP.get());
        lampBlock(MAGENTA_LAMP.get());
        lampBlock(LIGHT_BLUE_LAMP.get());
        lampBlock(YELLOW_LAMP.get());
        lampBlock(LIME_LAMP.get());
        lampBlock(PINK_LAMP.get());
        lampBlock(GRAY_LAMP.get());
        lampBlock(LIGHT_GRAY_LAMP.get());
        lampBlock(CYAN_LAMP.get());
        lampBlock(PURPLE_LAMP.get());
        lampBlock(BLUE_LAMP.get());
        lampBlock(BROWN_LAMP.get());
        lampBlock(GREEN_LAMP.get());
        lampBlock(RED_LAMP.get());
        lampBlock(BLACK_LAMP.get());
        getVariantBuilder(LIGHT_SWITCH_BLOCK.get())
                .partialState().with(BlockStateProperties.POWERED, Boolean.FALSE).modelForState().modelFile(models().cubeAll("light_switch_block", modLoc(BLOCK_FOLDER + "/light_switch"))).addModel()
                .partialState().with(BlockStateProperties.POWERED, Boolean.TRUE).modelForState().modelFile(models().cubeAll("light_switch_block_powered", modLoc(BLOCK_FOLDER + "/light_switch_powered"))).addModel();

        // Special Blocks
        waterloggedHorizontalFacingBlock(COFFEE_MACHINE.get(), models().getExistingFile(blockTexture(COFFEE_MACHINE.get())));
        waterloggedHorizontalFacingBlock(CUP.get(), models().getExistingFile(blockTexture(CUP.get())));
        waterloggedHorizontalFacingBlock(CUP_COFFEE.get(), models().getExistingFile(blockTexture(CUP_COFFEE.get())));
        lightSwitch(LIGHT_SWITCH.get());

        // Lantern
        final ModelFile lantern = models().withExistingParent("lantern", mcLoc(BLOCK_FOLDER + "/template_lantern"))
                .texture("lantern", mcLoc(BLOCK_FOLDER + "/lantern")).renderType(mcLoc("cutout"));
        final ModelFile lanternHanging = models().withExistingParent("lantern_hanging", mcLoc(BLOCK_FOLDER + "/template_hanging_lantern"))
                .texture("lantern", mcLoc(BLOCK_FOLDER + "/lantern")).renderType(mcLoc("cutout"));
        getVariantBuilder(LANTERN.get())
                .partialState().with(BlockStateProperties.HANGING, Boolean.FALSE).modelForState().modelFile(lantern).addModel()
                .partialState().with(BlockStateProperties.HANGING, Boolean.TRUE).modelForState().modelFile(lanternHanging).addModel();

        getVariantBuilder(WALL_CLOSET.get())
                .forAllStatesExcept(state -> {
                    final boolean isOpen = state.getValue(BlockStateProperties.OPEN);
                    final ModelFile model = models().getExistingFile(blockTexture(WALL_CLOSET.get()));
                    final ModelFile modelOpen = models().getExistingFile(extend(blockTexture(WALL_CLOSET.get()), "_open"));

                    return ConfiguredModel.builder()
                            .modelFile(isOpen ? modelOpen : model)
                            .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                            .build();
                }, BlockStateProperties.WATERLOGGED);

        // Machine Supplier
        final ModelFile machineSupplierModelBase = models().cubeAll("machine_supplier_base", modLoc(BLOCK_FOLDER + "/machine_supplier"));
        final ModelFile machineSupplierModel = models().getBuilder("machine_supplier")
                .parent(machineSupplierModelBase).customLoader((blockModelBuilder, existingFileHelper) ->
                        new CustomLoaderBuilder<BlockModelBuilder>(UselessMod.rl("machine_supplier"), blockModelBuilder, existingFileHelper) {
                        }).end();
        simpleBlock(MACHINE_SUPPLIER.get(), machineSupplierModel);
        simpleBlockItem(MACHINE_SUPPLIER.get());

        // Block Item Models
        simpleBlockItem(USELESS_OAK_LOG.get());
        simpleBlockItem(USELESS_OAK_WOOD.get());
        simpleBlockItem(STRIPPED_USELESS_OAK_LOG.get());
        simpleBlockItem(STRIPPED_USELESS_OAK_WOOD.get());
        simpleBlockItem(USELESS_OAK_STAIRS.get());
        simpleBlockItem(USELESS_OAK_SLAB.get());
        itemModels().getBuilder("useless_oak_trapdoor").parent(models().getExistingFile(modLoc("block/useless_oak_trapdoor_bottom")));
        itemModels().buttonInventory("useless_oak_button", blockTexture(USELESS_OAK_PLANKS.get()));
        simpleBlockItem(USELESS_OAK_PRESSURE_PLATE.get());
        itemModels().fenceInventory("useless_oak_fence", blockTexture(USELESS_OAK_PLANKS.get()));
        simpleBlockItem(USELESS_OAK_FENCE_GATE.get());
        simpleItemItem(USELESS_OAK_DOOR.get());
        simpleItemItem(USELESS_OAK_SIGN.get());
        simpleItemItem(USELESS_OAK_HANGING_SIGN.get());
        simpleItemItem(USELESS_DOOR.get());
        simpleItemItem(SUPER_USELESS_DOOR.get());
        itemModels().getBuilder("useless_trapdoor").parent(models().getExistingFile(modLoc("block/useless_trapdoor_bottom")));
        itemModels().getBuilder("super_useless_trapdoor").parent(models().getExistingFile(modLoc("block/super_useless_trapdoor_bottom")));
        simpleBlockItem(CUP.get());
        simpleBlockItem(CUP_COFFEE.get());
        simpleBlockItem(PAINT_BUCKET.get(), models().getExistingFile(extend(blockTexture(PAINT_BUCKET.get()), "_inventory")));
        simpleBlockItem(PAINTED_WOOL.get());
        simpleBlockItem(USELESS_CARPET.get());
        itemModels().withExistingParent("useless_bed", mcLoc("item/template_bed")).texture("particle", modLoc("block/useless_wool"));
        simpleBlockItem(LIGHT_SWITCH_BLOCK.get());

        // rail pain
        simpleItem(USELESS_RAIL.get());
        simpleItem(USELESS_POWERED_RAIL.get());
        simpleItem(USELESS_DETECTOR_RAIL.get());
        simpleItem(USELESS_ACTIVATOR_RAIL.get());
        rail(USELESS_RAIL.get(), "useless_rail");
        rail(USELESS_POWERED_RAIL.get(), "useless_powered_rail");
        rail(USELESS_DETECTOR_RAIL.get(), "useless_detector_rail");
        rail(USELESS_ACTIVATOR_RAIL.get(), "useless_activator_rail");
    }

    private String id(String path) {
        return modLoc(path).toString();
    }

    private ResourceLocation key(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation extend(ResourceLocation loc, String extension) {
        return new ResourceLocation(loc.getNamespace(), loc.getPath() + extension);
    }

    private void simpleItem(Block block) {
        final ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        this.itemModels().singleTexture(id.getPath(), mcLoc("item/generated"), "layer0", blockTexture(block));
    }

    private void crossPlant(Block block) {
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        simpleItem(block);
        simpleBlock(block, this.models().cross(id.getPath(), blockTexture(block)).renderType(mcLoc("cutout")));
    }

    private void pottedPlant(Block block, Block plant) {
        ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        simpleBlock(block, this.models().withExistingParent(id.getPath(), mcLoc("block/flower_pot_cross")).texture("plant", blockTexture(plant)).renderType(mcLoc("cutout")));
    }

    private void waterloggedHorizontalFacingBlock(Block block, ModelFile model) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> ConfiguredModel.builder()
                        .modelFile(model)
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                        .build(), BlockStateProperties.WATERLOGGED);
    }

    private void lightSwitch(Block block) {
        final ResourceLocation blockLoc = blockTexture(block);
        final ModelFile model = models().getExistingFile(blockLoc);
        final ModelFile modelPowered = models().getExistingFile(extend(blockLoc, "_pressed"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            AttachFace face = state.getValue(BlockStateProperties.ATTACH_FACE);
            boolean powered = state.getValue(BlockStateProperties.POWERED);

            return ConfiguredModel.builder()
                    .modelFile(powered ? modelPowered : model)
                    .rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
                    .rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
                    .uvLock(face == AttachFace.WALL)
                    .build();
        });

        itemModels().withExistingParent(String.valueOf(ForgeRegistries.BLOCKS.getKey(block)), blockLoc);
    }

    private void lampBlock(Block block) {
        ResourceLocation blockLoc = blockTexture(block);
        ModelFile modelOff = models().cubeAll(blockLoc.toString(), blockLoc);
        ModelFile modelOn = models().cubeAll(blockLoc + "_on", extend(blockLoc, "_on"));

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.LIT, Boolean.TRUE).modelForState().modelFile(modelOn).addModel()
                .partialState().with(BlockStateProperties.LIT, Boolean.FALSE).modelForState().modelFile(modelOff).addModel();

        itemModels().withExistingParent(String.valueOf(ForgeRegistries.BLOCKS.getKey(block)), blockLoc);
    }

    private void simpleItemItem(Block block) {
        final ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
        this.itemModels().singleTexture(id.getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + id.getPath()));
    }

    private void simpleBlockWithItem(Block block) {
        simpleBlock(block);
        simpleBlockItem(block);
    }

    private void simpleBlockItem(Block block) {
        simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
    }

    private void wildCropPlant(UselessCropBlock crop, UselessCropBlock wildCrop, String name) {
        final ModelFile stage0 = models().withExistingParent(id("block/" + name + "_stage0"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage0")).renderType(mcLoc("cutout"));
        final ModelFile stage1 = models().withExistingParent(id("block/" + name + "_stage1"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage1")).renderType(mcLoc("cutout"));
        final ModelFile stage2 = models().withExistingParent(id("block/" + name + "_stage2"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage2")).renderType(mcLoc("cutout"));
        final ModelFile stage3 = models().withExistingParent(id("block/" + name + "_stage3"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage3")).renderType(mcLoc("cutout"));
        final ModelFile stage4 = models().withExistingParent(id("block/" + name + "_stage4"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage4")).renderType(mcLoc("cutout"));
        final ModelFile stage5 = models().withExistingParent(id("block/" + name + "_stage5"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage5")).renderType(mcLoc("cutout"));
        final ModelFile stage6 = models().withExistingParent(id("block/" + name + "_stage6"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage6")).renderType(mcLoc("cutout"));
        final ModelFile stage7 = models().withExistingParent(id("block/" + name + "_stage7"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage7")).renderType(mcLoc("cutout"));

        getVariantBuilder(crop).forAllStates(state -> ConfiguredModel.builder().modelFile(
                switch (state.getValue(CropBlock.AGE)) {
                    case 0 -> stage0;
                    case 1 -> stage1;
                    case 2 -> stage2;
                    case 3 -> stage3;
                    case 4 -> stage4;
                    case 5 -> stage5;
                    case 6 -> stage6;
                    default -> stage7;
                }).build());
        getVariantBuilder(wildCrop).forAllStates(state -> ConfiguredModel.builder().modelFile(
                switch (state.getValue(CropBlock.AGE)) {
                    case 0 -> stage0;
                    case 1 -> stage1;
                    case 2 -> stage2;
                    case 3 -> stage3;
                    case 4 -> stage4;
                    case 5 -> stage5;
                    case 6 -> stage6;
                    default -> stage7;
                }).build());
    }

    private void ironBars(IronBarsBlock block, String name) {
        simpleItem(block);

        final ResourceLocation texture = modLoc("block/" + name);
        final ModelFile ironBarsPostEnds = models().getBuilder(id("block/" + name + "_post_ends")).parent(models().getExistingFile(mcLoc("block/iron_bars_post_ends"))).texture("edge", texture).texture("particle", texture).renderType("cutout_mipped");
        final ModelFile ironBarsPost = models().getBuilder(id("block/" + name + "_post")).parent(models().getExistingFile(mcLoc("block/iron_bars_post"))).texture("bars", texture).texture("particle", texture).renderType("cutout_mipped");
        final ModelFile ironBarsCap = models().getBuilder(id("block/" + name + "_cap")).parent(models().getExistingFile(mcLoc("block/iron_bars_cap"))).texture("bars", texture).texture("edge", texture).texture("particle", texture).renderType("cutout_mipped");
        final ModelFile ironBarsCapAlt = models().getBuilder(id("block/" + name + "_cap_alt")).parent(models().getExistingFile(mcLoc("block/iron_bars_cap_alt"))).texture("bars", texture).texture("edge", texture).texture("particle", texture).renderType("cutout_mipped");
        final ModelFile ironBarsSide = models().getBuilder(id("block/" + name + "_side")).parent(models().getExistingFile(mcLoc("block/iron_bars_side"))).texture("bars", texture).texture("edge", texture).texture("particle", texture).renderType("cutout_mipped");
        final ModelFile ironBarsSideAlt = models().getBuilder(id("block/" + name + "_side_alt")).parent(models().getExistingFile(mcLoc("block/iron_bars_side_alt"))).texture("bars", texture).texture("edge", texture).texture("particle", texture).renderType("cutout_mipped");

        getMultipartBuilder(block)
                .part().modelFile(ironBarsPostEnds).addModel().end()
                .part().modelFile(ironBarsPost).addModel().condition(IronBarsBlock.NORTH, false).condition(IronBarsBlock.WEST, false).condition(IronBarsBlock.SOUTH, false).condition(IronBarsBlock.EAST, false).end()
                .part().modelFile(ironBarsCap).addModel().condition(IronBarsBlock.NORTH, true).condition(IronBarsBlock.WEST, false).condition(IronBarsBlock.SOUTH, false).condition(IronBarsBlock.EAST, false).end()
                .part().modelFile(ironBarsCap).rotationY(90).addModel().condition(IronBarsBlock.NORTH, false).condition(IronBarsBlock.WEST, false).condition(IronBarsBlock.SOUTH, false).condition(IronBarsBlock.EAST, true).end()
                .part().modelFile(ironBarsCapAlt).addModel().condition(IronBarsBlock.NORTH, false).condition(IronBarsBlock.WEST, false).condition(IronBarsBlock.SOUTH, true).condition(IronBarsBlock.EAST, false).end()
                .part().modelFile(ironBarsCapAlt).rotationY(90).addModel().condition(IronBarsBlock.NORTH, false).condition(IronBarsBlock.WEST, true).condition(IronBarsBlock.SOUTH, false).condition(IronBarsBlock.EAST, false).end()
                .part().modelFile(ironBarsSide).addModel().condition(IronBarsBlock.NORTH, true).end()
                .part().modelFile(ironBarsSide).rotationY(90).addModel().condition(IronBarsBlock.EAST, true).end()
                .part().modelFile(ironBarsSideAlt).addModel().condition(IronBarsBlock.SOUTH, true).end()
                .part().modelFile(ironBarsSideAlt).rotationY(90).addModel().condition(IronBarsBlock.WEST, true).end();
    }

    private void rail(Block block, String name) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            final ModelFile rail = models().getBuilder(id("block/" + name)).parent(models().getExistingFile(mcLoc("block/rail_flat"))).texture("rail", modLoc("block/" + name)).renderType(mcLoc("cutout"));
            final ModelFile railRaisedNE = models().getBuilder(id("block/" + name + "_raised_ne")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_ne"))).texture("rail", modLoc("block/" + name)).renderType(mcLoc("cutout"));
            final ModelFile railRaisedSW = models().getBuilder(id("block/" + name + "_raised_sw")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_sw"))).texture("rail", modLoc("block/" + name)).renderType(mcLoc("cutout"));
            ModelFile railOn = null;
            ModelFile railRaisedOnNE = null;
            ModelFile railRaisedOnSW = null;
            ModelFile railCorner = null;
            if (state.hasProperty(BlockStateProperties.POWERED)) {
                railOn = models().getBuilder(id("block/" + name + "_on")).parent(models().getExistingFile(mcLoc("block/rail_flat"))).texture("rail", modLoc("block/" + name + "_on")).renderType(mcLoc("cutout"));
                railRaisedOnNE = models().getBuilder(id("block/" + name + "_on_raised_ne")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_ne"))).texture("rail", modLoc("block/" + name + "_on")).renderType(mcLoc("cutout"));
                railRaisedOnSW = models().getBuilder(id("block/" + name + "_on_raised_sw")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_sw"))).texture("rail", modLoc("block/" + name + "_on")).renderType(mcLoc("cutout"));
            }
            if (state.hasProperty(BlockStateProperties.RAIL_SHAPE)) {
                railCorner = models().getBuilder(id("block/useless_rail_corner")).parent(models().getExistingFile(mcLoc("block/rail_curved"))).texture("rail", modLoc("block/useless_rail_corner")).renderType(mcLoc("cutout"));
            }
            final RailShape shape = state.hasProperty(BlockStateProperties.RAIL_SHAPE_STRAIGHT) ? state.getValue(BlockStateProperties.RAIL_SHAPE_STRAIGHT) : state.getValue(BlockStateProperties.RAIL_SHAPE);
            final boolean powered = state.hasProperty(BlockStateProperties.POWERED) && state.getValue(BlockStateProperties.POWERED);

            if (shape == RailShape.EAST_WEST) {
                return ConfiguredModel.builder().modelFile(powered ? railOn : rail).rotationY(90).build();
            } else if (shape == RailShape.ASCENDING_EAST) {
                return ConfiguredModel.builder().modelFile(powered ? railRaisedOnNE : railRaisedNE).rotationY(90).build();
            } else if (shape == RailShape.ASCENDING_WEST) {
                return ConfiguredModel.builder().modelFile(powered ? railRaisedOnSW : railRaisedSW).rotationY(90).build();
            } else if (shape == RailShape.ASCENDING_NORTH) {
                return ConfiguredModel.builder().modelFile(powered ? railRaisedOnNE : railRaisedNE).build();
            } else if (shape == RailShape.ASCENDING_SOUTH) {
                return ConfiguredModel.builder().modelFile(powered ? railRaisedOnSW : railRaisedSW).build();
            } else if (shape == RailShape.SOUTH_EAST) {
                return ConfiguredModel.builder().modelFile(railCorner).build();
            } else if (shape == RailShape.SOUTH_WEST) {
                return ConfiguredModel.builder().modelFile(railCorner).rotationY(90).build();
            } else if (shape == RailShape.NORTH_WEST) {
                return ConfiguredModel.builder().modelFile(railCorner).rotationY(180).build();
            } else if (shape == RailShape.NORTH_EAST) {
                return ConfiguredModel.builder().modelFile(railCorner).rotationY(270).build();
            }
            return ConfiguredModel.builder().modelFile(powered ? railOn : rail).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    private void signBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        signBlock(signBlock, wallSignBlock, sign);
    }

    private void signBlock(CeilingHangingSignBlock signBlock, WallHangingSignBlock wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }
}
