package net.themcbrothers.uselessmod.core;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.themcbrothers.uselessmod.world.item.CupBlockItem;
import net.themcbrothers.uselessmod.world.item.LightSwitchBlockItem;
import net.themcbrothers.uselessmod.world.level.block.*;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.grower.UselessOakTreeGrower;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;
import static net.themcbrothers.uselessmod.core.Registration.BLOCKS;

public final class UselessBlocks {
    static void register() {
    }

    // Metal
    public static final DeferredBlock<Block> USELESS_ORE = BLOCKS.registerBlock("useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_USELESS_ORE = BLOCKS.registerBlock("deepslate_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.DEEPSLATE_IRON_ORE));
    public static final DeferredBlock<Block> NETHER_USELESS_ORE = BLOCKS.registerBlock("nether_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.NETHER_QUARTZ_ORE));
    public static final DeferredBlock<Block> END_USELESS_ORE = BLOCKS.registerBlock("end_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.END_STONE));
    public static final DeferredBlock<Block> SUPER_USELESS_ORE = BLOCKS.registerBlock("super_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.GOLD_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_SUPER_USELESS_ORE = BLOCKS.registerBlock("deepslate_super_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE));
    public static final DeferredBlock<Block> NETHER_SUPER_USELESS_ORE = BLOCKS.registerBlock("nether_super_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.NETHER_QUARTZ_ORE));
    public static final DeferredBlock<Block> END_SUPER_USELESS_ORE = BLOCKS.registerBlock("end_super_useless_ore", props -> new DropExperienceBlock(ConstantInt.of(0), props), ofFullCopy(Blocks.END_STONE));
    public static final DeferredBlock<Block> USELESS_BLOCK = BLOCKS.registerSimpleBlock("useless_block", ofFullCopy(Blocks.IRON_BLOCK));
    public static final DeferredBlock<Block> RAW_USELESS_BLOCK = BLOCKS.registerSimpleBlock("raw_useless_block", ofFullCopy(Blocks.RAW_IRON_BLOCK));
    public static final DeferredBlock<Block> SUPER_USELESS_BLOCK = BLOCKS.registerSimpleBlock("super_useless_block", ofFullCopy(Blocks.GOLD_BLOCK));
    public static final DeferredBlock<Block> RAW_SUPER_USELESS_BLOCK = BLOCKS.registerSimpleBlock("raw_super_useless_block", ofFullCopy(Blocks.RAW_GOLD_BLOCK));
    public static final DeferredBlock<Block> USELESS_BARS = BLOCKS.registerBlock("useless_bars", IronBarsBlock::new, ofFullCopy(Blocks.IRON_BARS));
    public static final DeferredBlock<Block> SUPER_USELESS_BARS = BLOCKS.registerBlock("super_useless_bars", IronBarsBlock::new, ofFullCopy(Blocks.IRON_BARS));
    public static final DeferredBlock<Block> USELESS_DOOR = BLOCKS.registerBlock("useless_door", props -> new DoorBlock(BlockSetType.IRON, props), ofFullCopy(Blocks.IRON_DOOR), DoubleHighBlockItem::new);
    public static final DeferredBlock<Block> SUPER_USELESS_DOOR = BLOCKS.registerBlock("super_useless_door", props -> new DoorBlock(BlockSetType.IRON, props), ofFullCopy(Blocks.IRON_DOOR), DoubleHighBlockItem::new);
    public static final DeferredBlock<Block> USELESS_TRAPDOOR = BLOCKS.registerBlock("useless_trapdoor", props -> new TrapDoorBlock(BlockSetType.IRON, props), ofFullCopy(Blocks.IRON_TRAPDOOR));
    public static final DeferredBlock<Block> SUPER_USELESS_TRAPDOOR = BLOCKS.registerBlock("super_useless_trapdoor", props -> new TrapDoorBlock(BlockSetType.IRON, props), ofFullCopy(Blocks.IRON_TRAPDOOR));

    // Natural
    public static final DeferredBlock<Block> RED_ROSE = BLOCKS.registerBlock("red_rose", props -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, props), ofFullCopy(Blocks.POPPY));
    public static final DeferredBlock<Block> BLUE_ROSE = BLOCKS.registerBlock("blue_rose", props -> new FlowerBlock(MobEffects.SATURATION, 7, props), ofFullCopy(Blocks.POPPY));
    public static final DeferredBlock<Block> USELESS_ROSE = BLOCKS.registerBlock("useless_rose", props -> new FlowerBlock(MobEffects.LUCK, 2, props), ofFullCopy(Blocks.POPPY));
    public static final DeferredBlock<Block> USELESS_OAK_SAPLING = BLOCKS.registerBlock("useless_oak_sapling", props -> new SaplingBlock(UselessOakTreeGrower.USELESS_OAK_TREE_GROWER, props), ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> USELESS_OAK_LEAVES = BLOCKS.registerBlock("useless_oak_leaves", LeavesBlock::new, ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> USELESS_OAK_LOG = BLOCKS.registerBlock("useless_oak_log", RotatedPillarBlock::new, ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredBlock<Block> USELESS_OAK_WOOD = BLOCKS.registerBlock("useless_oak_wood", RotatedPillarBlock::new, ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_LOG = BLOCKS.registerBlock("stripped_useless_oak_log", RotatedPillarBlock::new, ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_WOOD = BLOCKS.registerBlock("stripped_useless_oak_wood", RotatedPillarBlock::new, ofFullCopy(Blocks.STRIPPED_OAK_WOOD));
    public static final DeferredBlock<Block> USELESS_OAK_PLANKS = BLOCKS.registerSimpleBlock("useless_oak_planks", ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> USELESS_OAK_STAIRS = BLOCKS.registerBlock("useless_oak_stairs", props -> new StairBlock(USELESS_OAK_PLANKS.get().defaultBlockState(), props), ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> USELESS_OAK_SLAB = BLOCKS.registerBlock("useless_oak_slab", SlabBlock::new, ofFullCopy(Blocks.OAK_SLAB));
    public static final DeferredBlock<Block> USELESS_OAK_FENCE = BLOCKS.registerBlock("useless_oak_fence", FenceBlock::new, ofFullCopy(Blocks.OAK_FENCE));
    public static final DeferredBlock<Block> USELESS_OAK_FENCE_GATE = BLOCKS.registerBlock("useless_oak_fence_gate", props -> new FenceGateBlock(UselessWoodTypes.USELESS_OAK, props), ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> USELESS_OAK_DOOR = BLOCKS.registerBlock("useless_oak_door", props -> new DoorBlock(BlockSetType.OAK, props), ofFullCopy(Blocks.OAK_DOOR), DoubleHighBlockItem::new);
    public static final DeferredBlock<Block> USELESS_OAK_TRAPDOOR = BLOCKS.registerBlock("useless_oak_trapdoor", props -> new TrapDoorBlock(BlockSetType.OAK, props), ofFullCopy(Blocks.OAK_TRAPDOOR));
    public static final DeferredBlock<Block> USELESS_OAK_PRESSURE_PLATE = BLOCKS.registerBlock("useless_oak_pressure_plate", props -> new PressurePlateBlock(BlockSetType.OAK, props), ofFullCopy(Blocks.OAK_PRESSURE_PLATE));
    public static final DeferredBlock<Block> USELESS_OAK_BUTTON = BLOCKS.registerBlock("useless_oak_button", props -> new ButtonBlock(BlockSetType.OAK, 30, props), ofFullCopy(Blocks.OAK_BUTTON));
    public static final DeferredBlock<Block> USELESS_OAK_SIGN = BLOCKS.registerBlock("useless_oak_sign", props -> new UselessStandingSignBlock(UselessWoodTypes.USELESS_OAK, props), ofFullCopy(Blocks.OAK_SIGN), (block, props) -> new SignItem(block, UselessBlocks.USELESS_OAK_WALL_SIGN.get(), props.stacksTo(16)));
    public static final DeferredBlock<Block> USELESS_OAK_WALL_SIGN = BLOCKS.registerNoItem("useless_oak_wall_sign", props -> new UselessWallSignBlock(UselessWoodTypes.USELESS_OAK, props.overrideLootTable(UselessBlocks.USELESS_OAK_SIGN.get().getLootTable()).overrideDescription(UselessBlocks.USELESS_OAK_SIGN.get().getDescriptionId())), ofFullCopy(Blocks.OAK_WALL_SIGN));
    public static final DeferredBlock<Block> USELESS_OAK_HANGING_SIGN = BLOCKS.registerBlock("useless_oak_hanging_sign", props -> new UselessCeilingHangingSignBlock(UselessWoodTypes.USELESS_OAK, props), ofFullCopy(Blocks.OAK_HANGING_SIGN), (block, props) -> new HangingSignItem(block, UselessBlocks.USELESS_OAK_WALL_HANGING_SIGN.get(), props.stacksTo(16)));
    public static final DeferredBlock<Block> USELESS_OAK_WALL_HANGING_SIGN = BLOCKS.registerNoItem("useless_oak_wall_hanging_sign", props -> new UselessWallHangingSignBlock(UselessWoodTypes.USELESS_OAK, props.overrideLootTable(UselessBlocks.USELESS_OAK_HANGING_SIGN.get().getLootTable()).overrideDescription(UselessBlocks.USELESS_OAK_HANGING_SIGN.get().getDescriptionId())), ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN));

    // Colorful Blocks
    public static final DeferredBlock<Block> USELESS_WOOL = BLOCKS.registerSimpleBlock("useless_wool", ofFullCopy(Blocks.WHITE_WOOL));
    public static final DeferredBlock<Block> USELESS_CARPET = BLOCKS.registerBlock("useless_carpet", props -> new WoolCarpetBlock(DyeColor.LIME, props), ofFullCopy(Blocks.WHITE_CARPET));
    public static final DeferredBlock<Block> USELESS_BED = BLOCKS.registerBlock("useless_bed", props -> new UselessBedBlock(DyeColor.LIME, props), ofFullCopy(Blocks.WHITE_CARPET), (block, properties) -> new BedItem(block, properties.stacksTo(1)));
    public static final DeferredBlock<Block> PAINTED_WOOL = BLOCKS.registerBlock("painted_wool", PaintedWoolBlock::new, ofFullCopy(Blocks.WHITE_WOOL));
    public static final DeferredBlock<Block> PAINT_BUCKET = BLOCKS.registerBlock("paint_bucket", PaintBucketBlock::new, of().strength(2.5F).sound(SoundType.LANTERN));

    // Rails
    public static final DeferredBlock<Block> USELESS_RAIL = BLOCKS.registerBlock("useless_rail", UselessRailBlock::new, ofFullCopy(Blocks.RAIL));
    public static final DeferredBlock<Block> USELESS_POWERED_RAIL = BLOCKS.registerBlock("useless_powered_rail", props -> new UselessPoweredRailBlock(props, true), ofFullCopy(Blocks.POWERED_RAIL));
    public static final DeferredBlock<Block> USELESS_DETECTOR_RAIL = BLOCKS.registerBlock("useless_detector_rail", UselessDetectorRailBlock::new, ofFullCopy(Blocks.DETECTOR_RAIL));
    public static final DeferredBlock<Block> USELESS_ACTIVATOR_RAIL = BLOCKS.registerBlock("useless_activator_rail", UselessPoweredRailBlock::new, ofFullCopy(Blocks.ACTIVATOR_RAIL));

    // Functional Blocks
    public static final DeferredBlock<Block> WALL_CLOSET = BLOCKS.registerBlock("wall_closet", WallClosetBlock::new, of().strength(.5F),
            (block, props) -> new BlockItem(block, props.component(UselessDataComponents.WALL_CLOSET_MATERIAL.get(), Holder.direct(Blocks.AIR))));
    public static final DeferredBlock<Block> MACHINE_SUPPLIER = BLOCKS.registerBlock("machine_supplier", MachineSupplierBlock::new, of().strength(0.5F).dynamicShape().noOcclusion());
    public static final DeferredBlock<Block> COFFEE_MACHINE = BLOCKS.registerBlock("coffee_machine", CoffeeMachineBlock::new, of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL),
            (block, props) -> new BlockItem(block, props.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).component(UselessDataComponents.COFFEE_MACHINE_CONTENTS.get(), CoffeeMachineBlockEntity.Contents.EMPTY)));
    public static final DeferredBlock<Block> CUP = BLOCKS.registerBlock("cup", CupBlock::new, of().strength(0.5F), (block, props) -> new CupBlockItem(block, props, false));
    public static final DeferredBlock<Block> CUP_COFFEE = BLOCKS.registerBlock("cup_coffee", CupCoffeeBlock::new, of().strength(0.5F), (block, props) -> new CupBlockItem(block, props.usingConvertsTo(CUP.asItem()), true));

    // Lights
    public static final DeferredBlock<Block> LIGHT_SWITCH = BLOCKS.registerBlock("light_switch", LightSwitchBlock::new, of().noCollission().strength(.25F), LightSwitchBlockItem::new);
    public static final DeferredBlock<Block> LIGHT_SWITCH_BLOCK = BLOCKS.registerBlock("light_switch_block", LightSwitchBlockBlock::new, of().strength(.5F), LightSwitchBlockItem::new);
    public static final DeferredBlock<Block> WHITE_LAMP = BLOCKS.registerBlock("white_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> ORANGE_LAMP = BLOCKS.registerBlock("orange_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> MAGENTA_LAMP = BLOCKS.registerBlock("magenta_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> LIGHT_BLUE_LAMP = BLOCKS.registerBlock("light_blue_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> YELLOW_LAMP = BLOCKS.registerBlock("yellow_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> LIME_LAMP = BLOCKS.registerBlock("lime_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> PINK_LAMP = BLOCKS.registerBlock("pink_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> GRAY_LAMP = BLOCKS.registerBlock("gray_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> LIGHT_GRAY_LAMP = BLOCKS.registerBlock("light_gray_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> CYAN_LAMP = BLOCKS.registerBlock("cyan_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> PURPLE_LAMP = BLOCKS.registerBlock("purple_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> BLUE_LAMP = BLOCKS.registerBlock("blue_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> BROWN_LAMP = BLOCKS.registerBlock("brown_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> GREEN_LAMP = BLOCKS.registerBlock("green_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> RED_LAMP = BLOCKS.registerBlock("red_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));
    public static final DeferredBlock<Block> BLACK_LAMP = BLOCKS.registerBlock("black_lamp", RedstoneLampBlock::new, ofFullCopy(Blocks.REDSTONE_LAMP));

    // Blocks without Item
    public static final DeferredBlock<Block> POTTED_RED_ROSE = BLOCKS.registerNoItem("potted_red_rose", props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RED_ROSE, props), ofFullCopy(Blocks.POTTED_POPPY));
    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = BLOCKS.registerNoItem("potted_blue_rose", props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, props), ofFullCopy(Blocks.POTTED_POPPY));
    public static final DeferredBlock<Block> POTTED_USELESS_ROSE = BLOCKS.registerNoItem("potted_useless_rose", props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_ROSE, props), ofFullCopy(Blocks.POTTED_POPPY));
    public static final DeferredBlock<Block> POTTED_USELESS_OAK_SAPLING = BLOCKS.registerNoItem("potted_useless_oak_sapling", props -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_OAK_SAPLING, props), ofFullCopy(Blocks.POTTED_OAK_SAPLING));
    public static final DeferredBlock<Block> USELESS_WHEAT = BLOCKS.registerNoItem("useless_wheat", props -> new UselessCropBlock(false, UselessItems.USELESS_WHEAT_SEEDS, props), ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> WILD_USELESS_WHEAT = BLOCKS.registerNoItem("wild_useless_wheat", props -> new UselessCropBlock(true, UselessItems.USELESS_WHEAT_SEEDS, props), ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> COFFEE_BEANS = BLOCKS.registerNoItem("coffee_beans", props -> new UselessCropBlock(false, UselessItems.COFFEE_SEEDS, props), ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> WILD_COFFEE_BEANS = BLOCKS.registerNoItem("wild_coffee_beans", props -> new UselessCropBlock(true, UselessItems.COFFEE_SEEDS, props), ofFullCopy(Blocks.WHEAT));
    public static final DeferredBlock<Block> USELESS_SKELETON_SKULL = BLOCKS.registerNoItem("useless_skeleton_skull", props -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, props), ofFullCopy(Blocks.SKELETON_SKULL));
    public static final DeferredBlock<Block> USELESS_SKELETON_WALL_SKULL = BLOCKS.registerNoItem("useless_skeleton_wall_skull", props -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, props.overrideLootTable(USELESS_SKELETON_SKULL.get().getLootTable())), ofFullCopy(Blocks.SKELETON_WALL_SKULL));
    public static final DeferredBlock<Block> LANTERN = BLOCKS.registerNoItem("lantern", UselessLanternBlock::new, ofFullCopy(Blocks.LANTERN).lightLevel(state -> 0));
}
