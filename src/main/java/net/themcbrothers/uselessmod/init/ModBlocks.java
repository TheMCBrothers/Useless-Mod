package net.themcbrothers.uselessmod.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.themcbrothers.uselessmod.init.Registration.BLOCKS;
import static net.themcbrothers.uselessmod.init.Registration.ITEMS;

public final class ModBlocks {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties();
    private static final Item.Properties ONE_STACKING_PROPS = new Item.Properties().stacksTo(1);
    private static final Item.Properties STACKS_TO_16_PROPS = new Item.Properties().stacksTo(16);
    private static final Function<? super Block, BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);
    private static final Function<? super Block, DoubleHighBlockItem> DOUBLE_HIGH_BLOCK_ITEM = (b) -> new DoubleHighBlockItem(b, GENERAL_PROPS);

    // Metal
    public static final DeferredBlock<Block> USELESS_ORE = register("useless_ore", () -> new DropExperienceBlock(copy(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> DEEPSLATE_USELESS_ORE = register("deepslate_useless_ore", () -> new DropExperienceBlock(copy(Blocks.DEEPSLATE_IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> NETHER_USELESS_ORE = register("nether_useless_ore", () -> new DropExperienceBlock(copy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> END_USELESS_ORE = register("end_useless_ore", () -> new DropExperienceBlock(copy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_ORE = register("super_useless_ore", () -> new DropExperienceBlock(copy(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> DEEPSLATE_SUPER_USELESS_ORE = register("deepslate_super_useless_ore", () -> new DropExperienceBlock(copy(Blocks.DEEPSLATE_GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> NETHER_SUPER_USELESS_ORE = register("nether_super_useless_ore", () -> new DropExperienceBlock(copy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> END_SUPER_USELESS_ORE = register("end_super_useless_ore", () -> new DropExperienceBlock(copy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BLOCK = register("useless_block", () -> new Block(copy(Blocks.IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RAW_USELESS_BLOCK = register("raw_useless_block", () -> new Block(copy(Blocks.RAW_IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_BLOCK = register("super_useless_block", () -> new Block(copy(Blocks.GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RAW_SUPER_USELESS_BLOCK = register("raw_super_useless_block", () -> new Block(copy(Blocks.RAW_GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BARS = register("useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_BARS = register("super_useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_DOOR = register("useless_door", () -> new DoorBlock(copy(Blocks.IRON_DOOR), BlockSetType.IRON), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_DOOR = register("super_useless_door", () -> new DoorBlock(copy(Blocks.IRON_DOOR), BlockSetType.IRON), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_TRAPDOOR = register("useless_trapdoor", () -> new TrapDoorBlock(copy(Blocks.IRON_TRAPDOOR), BlockSetType.IRON), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> SUPER_USELESS_TRAPDOOR = register("super_useless_trapdoor", () -> new TrapDoorBlock(copy(Blocks.IRON_TRAPDOOR), BlockSetType.IRON), GENERAL_BLOCK_ITEM);

    // Natural
    public static final DeferredBlock<Block> RED_ROSE = register("red_rose", () -> new FlowerBlock(() -> MobEffects.NIGHT_VISION, 5, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLUE_ROSE = register("blue_rose", () -> new FlowerBlock(() -> MobEffects.SATURATION, 7, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_ROSE = register("useless_rose", () -> new FlowerBlock(() -> MobEffects.LUCK, 2, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_SAPLING = register("useless_oak_sapling", () -> new SaplingBlock(new UselessOakTreeGrower(), copy(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_LEAVES = register("useless_oak_leaves", () -> new LeavesBlock(copy(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_LOG = register("useless_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_WOOD = register("useless_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_LOG = register("stripped_useless_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> STRIPPED_USELESS_OAK_WOOD = register("stripped_useless_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_PLANKS = register("useless_oak_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_STAIRS = register("useless_oak_stairs", () -> new StairBlock(() -> USELESS_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_SLAB = register("useless_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_FENCE = register("useless_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_FENCE_GATE = register("useless_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), UselessWoodTypes.USELESS_OAK), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_DOOR = register("useless_oak_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK), DOUBLE_HIGH_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_TRAPDOOR = register("useless_oak_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_PRESSURE_PLATE = register("useless_oak_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_BUTTON = register("useless_oak_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 30, true), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_OAK_WALL_SIGN = BLOCKS.register("useless_oak_wall_sign", () -> new UselessWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).lootFrom(ModBlocks.USELESS_OAK_SIGN), UselessWoodTypes.USELESS_OAK));
    public static final DeferredBlock<Block> USELESS_OAK_SIGN = register("useless_oak_sign", () -> new UselessStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), UselessWoodTypes.USELESS_OAK), block -> new SignItem(STACKS_TO_16_PROPS, block, ModBlocks.USELESS_OAK_WALL_SIGN.get()));
    public static final DeferredBlock<Block> USELESS_OAK_WALL_HANGING_SIGN = BLOCKS.register("useless_oak_wall_hanging_sign", () -> new UselessWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).lootFrom(ModBlocks.USELESS_OAK_SIGN), UselessWoodTypes.USELESS_OAK));
    public static final DeferredBlock<Block> USELESS_OAK_HANGING_SIGN = register("useless_oak_hanging_sign", () -> new UselessCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), UselessWoodTypes.USELESS_OAK), block -> new HangingSignItem(block, ModBlocks.USELESS_OAK_WALL_HANGING_SIGN.get(), STACKS_TO_16_PROPS));

    // Colorful Blocks
    public static final DeferredBlock<Block> USELESS_WOOL = register("useless_wool", () -> new Block(copy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_CARPET = register("useless_carpet", () -> new WoolCarpetBlock(DyeColor.LIME, copy(Blocks.WHITE_CARPET)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_BED = register("useless_bed", () -> new UselessBedBlock(DyeColor.LIME, copy(Blocks.WHITE_CARPET)), block -> new UselessBedItem(block, ONE_STACKING_PROPS));
    public static final DeferredBlock<Block> PAINTED_WOOL = register("painted_wool", () -> new PaintedWoolBlock(copy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PAINT_BUCKET = register("paint_bucket", () -> new PaintBucketBlock(of().strength(2.5F).sound(SoundType.LANTERN)), GENERAL_BLOCK_ITEM);

    // Rails
    public static final DeferredBlock<Block> USELESS_RAIL = register("useless_rail", () -> new UselessRailBlock(copy(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_POWERED_RAIL = register("useless_powered_rail", () -> new UselessPoweredRailBlock(copy(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_DETECTOR_RAIL = register("useless_detector_rail", () -> new UselessDetectorRailBlock(copy(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> USELESS_ACTIVATOR_RAIL = register("useless_activator_rail", () -> new UselessPoweredRailBlock(copy(Blocks.ACTIVATOR_RAIL)), GENERAL_BLOCK_ITEM);

    // Functional Blocks
    public static final DeferredBlock<Block> WALL_CLOSET = register("wall_closet", () -> new WallClosetBlock(of().strength(.5F)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> MACHINE_SUPPLIER = register("machine_supplier", () -> new MachineSupplierBlock(of().strength(0.5F).dynamicShape().noOcclusion()), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> COFFEE_MACHINE = register("coffee_machine", () -> new CoffeeMachineBlock(of().mapColor(MapColor.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL)), block -> new CoffeeMachineBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> CUP = register("cup", () -> new CupBlock(of().strength(0.5F)), block -> new CupBlockItem(block, GENERAL_PROPS, false));
    public static final DeferredBlock<Block> CUP_COFFEE = register("cup_coffee", () -> new CupCoffeeBlock(of().strength(0.5F)), block -> new CupBlockItem(block, ONE_STACKING_PROPS, true));

    // Lights
    public static final DeferredBlock<Block> LIGHT_SWITCH = register("light_switch", () -> new LightSwitchBlock(of().noCollission().strength(.25F)), block -> new LightSwitchBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> LIGHT_SWITCH_BLOCK = register("light_switch_block", () -> new LightSwitchBlockBlock(of().strength(.5F)), block -> new LightSwitchBlockItem(block, GENERAL_PROPS));
    public static final DeferredBlock<Block> WHITE_LAMP = register("white_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> ORANGE_LAMP = register("orange_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> MAGENTA_LAMP = register("magenta_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIGHT_BLUE_LAMP = register("light_blue_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> YELLOW_LAMP = register("yellow_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIME_LAMP = register("lime_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PINK_LAMP = register("pink_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> GRAY_LAMP = register("gray_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> LIGHT_GRAY_LAMP = register("light_gray_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> CYAN_LAMP = register("cyan_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> PURPLE_LAMP = register("purple_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLUE_LAMP = register("blue_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BROWN_LAMP = register("brown_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> GREEN_LAMP = register("green_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> RED_LAMP = register("red_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final DeferredBlock<Block> BLACK_LAMP = register("black_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);

    // Blocks without Item
    public static final DeferredBlock<Block> POTTED_RED_ROSE = BLOCKS.register("potted_red_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RED_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = BLOCKS.register("potted_blue_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_USELESS_ROSE = BLOCKS.register("potted_useless_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final DeferredBlock<Block> POTTED_USELESS_OAK_SAPLING = BLOCKS.register("potted_useless_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_OAK_SAPLING, copy(Blocks.POTTED_OAK_SAPLING)));
    public static final DeferredBlock<Block> USELESS_WHEAT = BLOCKS.register("useless_wheat", () -> new UselessCropBlock(false, ModItems.USELESS_WHEAT_SEEDS, copy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> WILD_USELESS_WHEAT = BLOCKS.register("wild_useless_wheat", () -> new UselessCropBlock(true, ModItems.USELESS_WHEAT_SEEDS, copy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> COFFEE_BEANS = BLOCKS.register("coffee_beans", () -> new UselessCropBlock(false, ModItems.COFFEE_SEEDS, copy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> WILD_COFFEE_BEANS = BLOCKS.register("wild_coffee_beans", () -> new UselessCropBlock(true, ModItems.COFFEE_SEEDS, copy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> USELESS_SKELETON_SKULL = BLOCKS.register("useless_skeleton_skull", () -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, copy(Blocks.SKELETON_SKULL)));
    public static final DeferredBlock<Block> USELESS_SKELETON_WALL_SKULL = BLOCKS.register("useless_skeleton_wall_skull", () -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, copy(Blocks.SKELETON_WALL_SKULL).lootFrom(USELESS_SKELETON_SKULL)));
    public static final DeferredBlock<Block> LANTERN = BLOCKS.register("lantern", () -> new UselessLanternBlock(copy(Blocks.LANTERN).lightLevel(state -> 0)));

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block, Function<? super T, ? extends BlockItem> blockItemFactory) {
        var holder = BLOCKS.register(name, block);
        ITEMS.register(name, () -> blockItemFactory.apply(holder.get()));
        return holder;
    }
}
