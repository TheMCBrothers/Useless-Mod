package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.UselessCropBlock;

import static net.themcbrothers.uselessmod.init.ModBlocks.*;

public class UselessBlockStateProvider extends BlockStateProvider {
    public UselessBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, UselessMod.MOD_ID, exFileHelper);
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
        axisBlock((RotatedPillarBlock) USELESS_OAK_WOOD.getLog(), modLoc("block/useless_oak_log"), modLoc("block/useless_oak_log_top"));
        axisBlock((RotatedPillarBlock) USELESS_OAK_WOOD.getWood(), modLoc("block/useless_oak_log"), modLoc("block/useless_oak_log"));
        axisBlock((RotatedPillarBlock) USELESS_OAK_WOOD.getStrippedLog(), modLoc("block/stripped_useless_oak_log"), modLoc("block/stripped_useless_oak_log_top"));
        axisBlock((RotatedPillarBlock) USELESS_OAK_WOOD.getStrippedWood(), modLoc("block/stripped_useless_oak_log"), modLoc("block/stripped_useless_oak_log"));
        crossPlant(RED_ROSE.get());
        crossPlant(BLUE_ROSE.get());
        crossPlant(USELESS_ROSE.get());
        pottedPlant(POTTED_RED_ROSE.get(), RED_ROSE.get());
        pottedPlant(POTTED_BLUE_ROSE.get(), BLUE_ROSE.get());
        pottedPlant(POTTED_USELESS_ROSE.get(), USELESS_ROSE.get());
        pottedPlant(POTTED_USELESS_OAK_SAPLING.get(), USELESS_OAK_SAPLING.get());
        wildCropPlant((UselessCropBlock) USELESS_WHEAT.get(), (UselessCropBlock) WILD_USELESS_WHEAT.get(), "useless_wheat");
        crossPlant(USELESS_OAK_SAPLING.get());
        simpleBlockWithItem(USELESS_OAK_LEAVES.get());
        simpleBlockWithItem(USELESS_OAK_WOOD.get());
        stairsBlock(USELESS_OAK_WOOD.getStairs(), blockTexture(USELESS_OAK_WOOD.get()));
        slabBlock(USELESS_OAK_WOOD.getSlab(), blockTexture(USELESS_OAK_WOOD.get()), blockTexture(USELESS_OAK_WOOD.get()));
        fenceBlock(USELESS_OAK_WOOD.getFence(), blockTexture(USELESS_OAK_WOOD.get()));
        fenceGateBlock(USELESS_OAK_WOOD.getFenceGate(), blockTexture(USELESS_OAK_WOOD.get()));
        simpleBlock(PAINT_BUCKET.get(), models().getExistingFile(blockTexture(PAINT_BUCKET.get())));
        doorBlock(USELESS_OAK_WOOD.getDoor(), modLoc("block/useless_oak_door_bottom"), modLoc("block/useless_oak_door_top"));
        trapdoorBlock(USELESS_OAK_WOOD.getTrapdoor(), blockTexture(USELESS_OAK_WOOD.getTrapdoor()), true);
        buttonBlock(USELESS_OAK_WOOD.getButton(), blockTexture(USELESS_OAK_WOOD.get()));
        pressurePlateBlock(USELESS_OAK_WOOD.getPressurePlate(), blockTexture(USELESS_OAK_WOOD.get()));
        signBlock(USELESS_OAK_WOOD.getSign(), USELESS_OAK_WOOD.getWallSign(), blockTexture(USELESS_OAK_WOOD.get()));
        ironBars((IronBarsBlock) USELESS_BARS.get(), "useless_bars");
        ironBars((IronBarsBlock) SUPER_USELESS_BARS.get(), "super_useless_bars");

        // Block Item Models
        simpleBlockItem(USELESS_OAK_WOOD.getLog());
        simpleBlockItem(USELESS_OAK_WOOD.getWood());
        simpleBlockItem(USELESS_OAK_WOOD.getStrippedLog());
        simpleBlockItem(USELESS_OAK_WOOD.getStrippedWood());
        simpleBlockItem(USELESS_OAK_WOOD.getStairs());
        simpleBlockItem(USELESS_OAK_WOOD.getSlab());
        itemModels().getBuilder("useless_oak_trapdoor").parent(models().getExistingFile(modLoc("block/useless_oak_trapdoor_bottom")));
        itemModels().buttonInventory("useless_oak_button", blockTexture(USELESS_OAK_WOOD.get()));
        simpleBlockItem(USELESS_OAK_WOOD.getPressurePlate());
        itemModels().fenceInventory("useless_oak_fence", blockTexture(USELESS_OAK_WOOD.get()));
        simpleBlockItem(USELESS_OAK_WOOD.getFenceGate());
        simpleItemItem(USELESS_OAK_WOOD.getDoor());
        simpleItemItem(USELESS_OAK_WOOD.getSign());
        simpleBlockItem(PAINT_BUCKET.get());

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

    private void simpleItem(Block block) {
        final ResourceLocation id = block.getRegistryName();
        this.itemModels().singleTexture(id.getPath(), mcLoc("item/generated"), "layer0", blockTexture(block));
    }

    private void crossPlant(Block block) {
        ResourceLocation id = block.getRegistryName();
        simpleItem(block);
        simpleBlock(block, this.models().cross(id.getPath(), blockTexture(block)));
    }

    private void pottedPlant(Block block, Block plant) {
        ResourceLocation id = block.getRegistryName();
        simpleBlock(block, this.models().withExistingParent(id.getPath(), mcLoc("block/flower_pot_cross")).texture("plant", blockTexture(plant)));
    }

    private void simpleItemItem(Block block) {
        final ResourceLocation id = block.getRegistryName();
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
                .texture("crop", modLoc("block/" + name + "_stage0"));
        final ModelFile stage1 = models().withExistingParent(id("block/" + name + "_stage1"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage1"));
        final ModelFile stage2 = models().withExistingParent(id("block/" + name + "_stage2"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage2"));
        final ModelFile stage3 = models().withExistingParent(id("block/" + name + "_stage3"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage3"));
        final ModelFile stage4 = models().withExistingParent(id("block/" + name + "_stage4"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage4"));
        final ModelFile stage5 = models().withExistingParent(id("block/" + name + "_stage5"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage5"));
        final ModelFile stage6 = models().withExistingParent(id("block/" + name + "_stage6"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage6"));
        final ModelFile stage7 = models().withExistingParent(id("block/" + name + "_stage7"), "block/crop")
                .texture("crop", modLoc("block/" + name + "_stage7"));

        getVariantBuilder(crop).forAllStates(state -> ConfiguredModel.builder().modelFile(
                switch (state.getValue(crop.getAgeProperty())) {
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
                switch (state.getValue(wildCrop.getAgeProperty())) {
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
        final ModelFile ironBarsPostEnds = models().getBuilder(id("block/" + name + "_post_ends")).parent(models().getExistingFile(mcLoc("block/iron_bars_post_ends"))).texture("edge", texture).texture("particle", texture);
        final ModelFile ironBarsPost = models().getBuilder(id("block/" + name + "_post")).parent(models().getExistingFile(mcLoc("block/iron_bars_post"))).texture("bars", texture).texture("particle", texture);
        final ModelFile ironBarsCap = models().getBuilder(id("block/" + name + "_cap")).parent(models().getExistingFile(mcLoc("block/iron_bars_cap"))).texture("bars", texture).texture("edge", texture).texture("particle", texture);
        final ModelFile ironBarsCapAlt = models().getBuilder(id("block/" + name + "_cap_alt")).parent(models().getExistingFile(mcLoc("block/iron_bars_cap_alt"))).texture("bars", texture).texture("edge", texture).texture("particle", texture);
        final ModelFile ironBarsSide = models().getBuilder(id("block/" + name + "_side")).parent(models().getExistingFile(mcLoc("block/iron_bars_side"))).texture("bars", texture).texture("edge", texture).texture("particle", texture);
        final ModelFile ironBarsSideAlt = models().getBuilder(id("block/" + name + "_side_alt")).parent(models().getExistingFile(mcLoc("block/iron_bars_side_alt"))).texture("bars", texture).texture("edge", texture).texture("particle", texture);

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
            final ModelFile rail = models().getBuilder(id("block/" + name)).parent(models().getExistingFile(mcLoc("block/rail_flat"))).texture("rail", modLoc("block/" + name));
            final ModelFile railRaisedNE = models().getBuilder(id("block/" + name + "_raised_ne")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_ne"))).texture("rail", modLoc("block/" + name));
            final ModelFile railRaisedSW = models().getBuilder(id("block/" + name + "_raised_sw")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_sw"))).texture("rail", modLoc("block/" + name));
            ModelFile railOn = null;
            ModelFile railRaisedOnNE = null;
            ModelFile railRaisedOnSW = null;
            ModelFile railCorner = null;
            if (state.hasProperty(BlockStateProperties.POWERED)) {
                railOn = models().getBuilder(id("block/" + name + "_on")).parent(models().getExistingFile(mcLoc("block/rail_flat"))).texture("rail", modLoc("block/" + name + "_on"));
                railRaisedOnNE = models().getBuilder(id("block/" + name + "_on_raised_ne")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_ne"))).texture("rail", modLoc("block/" + name + "_on"));
                railRaisedOnSW = models().getBuilder(id("block/" + name + "_on_raised_sw")).parent(models().getExistingFile(mcLoc("block/template_rail_raised_sw"))).texture("rail", modLoc("block/" + name + "_on"));
            }
            if (state.hasProperty(BlockStateProperties.RAIL_SHAPE)) {
                railCorner = models().getBuilder(id("block/useless_rail_corner")).parent(models().getExistingFile(mcLoc("block/rail_curved"))).texture("rail", modLoc("block/useless_rail_corner"));
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
}
