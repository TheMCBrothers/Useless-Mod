package net.themcbrothers.uselessmod.init;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.themcbrothers.uselessmod.world.item.CoffeeMachineBlockItem;
import net.themcbrothers.uselessmod.world.item.CupBlockItem;
import net.themcbrothers.uselessmod.world.item.LightSwitchBlockItem;
import net.themcbrothers.uselessmod.world.item.UselessBedItem;
import net.themcbrothers.uselessmod.world.level.block.*;
import net.themcbrothers.uselessmod.world.level.block.grower.UselessOakTreeGrower;

import java.util.function.Function;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.*;
import static net.themcbrothers.uselessmod.init.Registration.BLOCKS;

public final class ModBlocks {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties();
    private static final Item.Properties ONE_STACKING_PROPS = new Item.Properties().stacksTo(1);
    private static final Item.Properties STACKS_TO_16_PROPS = new Item.Properties().stacksTo(16);
    private static final Function<? super Block, BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);
    private static final Function<? super Block, DoubleHighBlockItem> DOUBLE_HIGH_BLOCK_ITEM = (b) -> new DoubleHighBlockItem(b, GENERAL_PROPS);

    // Metal
    public static final DeferredBlock<Block> USELESS_ORE = BLOCKS.register("useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> DEEPSLATE_USELESS_ORE = BLOCKS.register("deepslate_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> NETHER_USELESS_ORE = BLOCKS.register("nether_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> END_USELESS_ORE = BLOCKS.register("end_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_ORE = BLOCKS.register("super_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> DEEPSLATE_SUPER_USELESS_ORE = BLOCKS.register("deepslate_super_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> NETHER_SUPER_USELESS_ORE = BLOCKS.register("nether_super_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> END_SUPER_USELESS_ORE = BLOCKS.register("end_super_useless_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BLOCK = BLOCKS.register("useless_block", () -> new Block(ofFullCopy(Blocks.IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RAW_USELESS_BLOCK = BLOCKS.register("raw_useless_block", () -> new Block(ofFullCopy(Blocks.RAW_IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_BLOCK = BLOCKS.register("super_useless_block", () -> new Block(ofFullCopy(Blocks.GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RAW_SUPER_USELESS_BLOCK = BLOCKS.register("raw_super_useless_block", () -> new Block(ofFullCopy(Blocks.RAW_GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BARS = BLOCKS.register("useless_bars", () -> new IronBarsBlock(ofFullCopy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_BARS = BLOCKS.register("super_useless_bars", () -> new IronBarsBlock(ofFullCopy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_DOOR = BLOCKS.register("useless_door", () -> new DoorBlock(BlockSetType.IRON, ofFullCopy(Blocks.IRON_DOOR)), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_DOOR = BLOCKS.register("super_useless_door", () -> new DoorBlock(BlockSetType.IRON, ofFullCopy(Blocks.IRON_DOOR)), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_TRAPDOOR = BLOCKS.register("useless_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, ofFullCopy(Blocks.IRON_TRAPDOOR)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_TRAPDOOR = BLOCKS.register("super_useless_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, ofFullCopy(Blocks.IRON_TRAPDOOR)), GENERAL_BLOCK_ITEM);

    // Natural
    public static final DeferredBlock<Block> RED_ROSE = BLOCKS.register("red_rose", () -> new FlowerBlock(() -> MobEffects.NIGHT_VISION, 5, ofFullCopy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLUE_ROSE = BLOCKS.register("blue_rose", () -> new FlowerBlock(() -> MobEffects.SATURATION, 7, ofFullCopy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_ROSE = BLOCKS.register("useless_rose", () -> new FlowerBlock(() -> MobEffects.LUCK, 2, ofFullCopy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_SAPLING = BLOCKS.register("useless_oak_sapling", () -> new SaplingBlock(UselessOakTreeGrower.USELESS_OAK_TREE_GROWER, ofFullCopy(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_LEAVES = BLOCKS.register("useless_oak_leaves", () -> new LeavesBlock(ofFullCopy(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_LOG = BLOCKS.register("useless_oak_log", () -> new RotatedPillarBlock(ofFullCopy(Blocks.OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_WOOD = BLOCKS.register("useless_oak_wood", () -> new RotatedPillarBlock(ofFullCopy(Blocks.OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_LOG = BLOCKS.register("stripped_useless_oak_log", () -> new RotatedPillarBlock(ofFullCopy(Blocks.STRIPPED_OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_WOOD = BLOCKS.register("stripped_useless_oak_wood", () -> new RotatedPillarBlock(ofFullCopy(Blocks.STRIPPED_OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_PLANKS = BLOCKS.register("useless_oak_planks", () -> new Block(ofFullCopy(Blocks.OAK_PLANKS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_STAIRS = BLOCKS.register("useless_oak_stairs", () -> new StairBlock(() -> USELESS_OAK_PLANKS.get().defaultBlockState(), ofFullCopy(Blocks.OAK_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_SLAB = BLOCKS.register("useless_oak_slab", () -> new SlabBlock(ofFullCopy(Blocks.OAK_SLAB)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_FENCE = BLOCKS.register("useless_oak_fence", () -> new FenceBlock(ofFullCopy(Blocks.OAK_FENCE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_FENCE_GATE = BLOCKS.register("useless_oak_fence_gate", () -> new FenceGateBlock(UselessWoodTypes.USELESS_OAK, ofFullCopy(Blocks.OAK_FENCE_GATE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_DOOR = BLOCKS.register("useless_oak_door", () -> new DoorBlock(BlockSetType.OAK, ofFullCopy(Blocks.OAK_DOOR)), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_TRAPDOOR = BLOCKS.register("useless_oak_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, ofFullCopy(Blocks.OAK_TRAPDOOR)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_PRESSURE_PLATE = BLOCKS.register("useless_oak_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, ofFullCopy(Blocks.OAK_PRESSURE_PLATE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_BUTTON = BLOCKS.register("useless_oak_button", () -> new ButtonBlock(BlockSetType.OAK, 30, ofFullCopy(Blocks.OAK_BUTTON)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_WALL_SIGN = BLOCKS.registerNoItem("useless_oak_wall_sign", () -> new UselessWallSignBlock(UselessWoodTypes.USELESS_OAK, ofFullCopy(Blocks.OAK_WALL_SIGN).lootFrom(ModBlocks.USELESS_OAK_SIGN)));
    public static final DeferredBlock<Block> USELESS_OAK_SIGN = BLOCKS.register("useless_oak_sign", () -> new UselessStandingSignBlock(UselessWoodTypes.USELESS_OAK, ofFullCopy(Blocks.OAK_SIGN)), block -> new SignItem(STACKS_TO_16_PROPS, block, ModBlocks.USELESS_OAK_WALL_SIGN.get()));
    public static final DeferredBlock<Block> USELESS_OAK_WALL_HANGING_SIGN = BLOCKS.registerNoItem("useless_oak_wall_hanging_sign", () -> new UselessWallHangingSignBlock(UselessWoodTypes.USELESS_OAK, ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN).lootFrom(ModBlocks.USELESS_OAK_SIGN)));
    public static final DeferredBlock<Block> USELESS_OAK_HANGING_SIGN = BLOCKS.register("useless_oak_hanging_sign", () -> new UselessCeilingHangingSignBlock(UselessWoodTypes.USELESS_OAK, ofFullCopy(Blocks.OAK_HANGING_SIGN)), block -> new HangingSignItem(block, ModBlocks.USELESS_OAK_WALL_HANGING_SIGN.get(), STACKS_TO_16_PROPS));

    // Colorful Blocks
    public static final DeferredBlock<Block> USELESS_WOOL = BLOCKS.register("useless_wool", () -> new Block(ofFullCopy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_CARPET = BLOCKS.register("useless_carpet", () -> new WoolCarpetBlock(DyeColor.LIME, ofFullCopy(Blocks.WHITE_CARPET)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BED = BLOCKS.register("useless_bed", () -> new UselessBedBlock(DyeColor.LIME, ofFullCopy(Blocks.WHITE_CARPET)), block -> new UselessBedItem(block, ONE_STACKING_PROPS));
    public static final DeferredBlock<Block> PAINTED_WOOL = BLOCKS.register("painted_wool", () -> new PaintedWoolBlock(ofFullCopy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PAINT_BUCKET = BLOCKS.register("paint_bucket", () -> new PaintBucketBlock(of().strength(2.5F).sound(SoundType.LANTERN)), GENERAL_BLOCK_ITEM);

    // Rails
    public static final DeferredBlock<Block> USELESS_RAIL = BLOCKS.register("useless_rail", () -> new UselessRailBlock(ofFullCopy(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_POWERED_RAIL = BLOCKS.register("useless_powered_rail", () -> new UselessPoweredRailBlock(ofFullCopy(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_DETECTOR_RAIL = BLOCKS.register("useless_detector_rail", () -> new UselessDetectorRailBlock(ofFullCopy(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_ACTIVATOR_RAIL = BLOCKS.register("useless_activator_rail", () -> new UselessPoweredRailBlock(ofFullCopy(Blocks.ACTIVATOR_RAIL)), GENERAL_BLOCK_ITEM);

    // Functional Blocks
    public static final DeferredBlock<Block> WALL_CLOSET = BLOCKS.register("wall_closet", () -> new WallClosetBlock(of().strength(.5F)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> MACHINE_SUPPLIER = BLOCKS.register("machine_supplier", () -> new MachineSupplierBlock(of().strength(0.5F).dynamicShape().noOcclusion()), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> COFFEE_MACHINE = BLOCKS.register("coffee_machine", () -> new CoffeeMachineBlock(of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL)), block -> new CoffeeMachineBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> CUP = BLOCKS.register("cup", () -> new CupBlock(of().strength(0.5F)), block -> new CupBlockItem(block, GENERAL_PROPS, false));
    public static final DeferredBlock<Block> CUP_COFFEE = BLOCKS.register("cup_coffee", () -> new CupCoffeeBlock(of().strength(0.5F)), block -> new CupBlockItem(block, ONE_STACKING_PROPS, true));

    // Lights
    public static final DeferredBlock<Block> LIGHT_SWITCH = BLOCKS.register("light_switch", () -> new LightSwitchBlock(of().noCollission().strength(.25F)), block -> new LightSwitchBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> LIGHT_SWITCH_BLOCK = BLOCKS.register("light_switch_block", () -> new LightSwitchBlockBlock(of().strength(.5F)), block -> new LightSwitchBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> WHITE_LAMP = BLOCKS.register("white_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> ORANGE_LAMP = BLOCKS.register("orange_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> MAGENTA_LAMP = BLOCKS.register("magenta_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIGHT_BLUE_LAMP = BLOCKS.register("light_blue_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> YELLOW_LAMP = BLOCKS.register("yellow_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIME_LAMP = BLOCKS.register("lime_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PINK_LAMP = BLOCKS.register("pink_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> GRAY_LAMP = BLOCKS.register("gray_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIGHT_GRAY_LAMP = BLOCKS.register("light_gray_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> CYAN_LAMP = BLOCKS.register("cyan_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PURPLE_LAMP = BLOCKS.register("purple_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLUE_LAMP = BLOCKS.register("blue_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BROWN_LAMP = BLOCKS.register("brown_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> GREEN_LAMP = BLOCKS.register("green_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RED_LAMP = BLOCKS.register("red_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLACK_LAMP = BLOCKS.register("black_lamp", () -> new RedstoneLampBlock(ofFullCopy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);

    // Blocks without Item
    public static final DeferredBlock<Block> POTTED_RED_ROSE = BLOCKS.registerNoItem("potted_red_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RED_ROSE, ofFullCopy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = BLOCKS.registerNoItem("potted_blue_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, ofFullCopy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_USELESS_ROSE = BLOCKS.registerNoItem("potted_useless_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_ROSE, ofFullCopy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_USELESS_OAK_SAPLING = BLOCKS.registerNoItem("potted_useless_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_OAK_SAPLING, ofFullCopy(Blocks.POTTED_OAK_SAPLING)));
    public static final DeferredBlock<Block> USELESS_WHEAT = BLOCKS.registerNoItem("useless_wheat", () -> new UselessCropBlock(false, ModItems.USELESS_WHEAT_SEEDS, ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> WILD_USELESS_WHEAT = BLOCKS.registerNoItem("wild_useless_wheat", () -> new UselessCropBlock(true, ModItems.USELESS_WHEAT_SEEDS, ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> COFFEE_BEANS = BLOCKS.registerNoItem("coffee_beans", () -> new UselessCropBlock(false, ModItems.COFFEE_SEEDS, ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> WILD_COFFEE_BEANS = BLOCKS.registerNoItem("wild_coffee_beans", () -> new UselessCropBlock(true, ModItems.COFFEE_SEEDS, ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> USELESS_SKELETON_SKULL = BLOCKS.registerNoItem("useless_skeleton_skull", () -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, ofFullCopy(Blocks.SKELETON_SKULL)));
    public static final DeferredBlock<Block> USELESS_SKELETON_WALL_SKULL = BLOCKS.registerNoItem("useless_skeleton_wall_skull", () -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, ofFullCopy(Blocks.SKELETON_WALL_SKULL).lootFrom(USELESS_SKELETON_SKULL)));
    public static final DeferredBlock<Block> LANTERN = BLOCKS.registerNoItem("lantern", () -> new UselessLanternBlock(ofFullCopy(Blocks.LANTERN).lightLevel(state -> 0)));
}
