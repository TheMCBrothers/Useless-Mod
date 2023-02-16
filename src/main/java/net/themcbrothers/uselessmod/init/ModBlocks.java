package net.themcbrothers.uselessmod.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.lib.registration.object.ItemObject;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.CoffeeMachineBlockItem;
import net.themcbrothers.uselessmod.world.item.CupBlockItem;
import net.themcbrothers.uselessmod.world.item.LightSwitchBlockItem;
import net.themcbrothers.uselessmod.world.item.UselessBedItem;
import net.themcbrothers.uselessmod.world.level.block.*;
import net.themcbrothers.uselessmod.world.level.block.grower.UselessOakTreeGrower;

import java.util.function.Function;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.themcbrothers.uselessmod.init.Registration.BLOCKS;

public final class ModBlocks {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(UselessMod.TAB);
    private static final Item.Properties ONE_STACKING_PROPS = new Item.Properties().tab(UselessMod.TAB).stacksTo(1);
    private static final Item.Properties STACKS_TO_16_PROPS = new Item.Properties().tab(UselessMod.TAB).stacksTo(16);
    private static final Function<? super Block, BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);
    private static final Function<? super Block, DoubleHighBlockItem> DOUBLE_HIGH_BLOCK_ITEM = (b) -> new DoubleHighBlockItem(b, GENERAL_PROPS);

    // Metal
    public static final ItemObject<Block> USELESS_ORE = BLOCKS.register("useless_ore", () -> new OreBlock(copy(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> DEEPSLATE_USELESS_ORE = BLOCKS.register("deepslate_useless_ore", () -> new OreBlock(copy(Blocks.DEEPSLATE_IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> NETHER_USELESS_ORE = BLOCKS.register("nether_useless_ore", () -> new OreBlock(copy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> END_USELESS_ORE = BLOCKS.register("end_useless_ore", () -> new OreBlock(copy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_ORE = BLOCKS.register("super_useless_ore", () -> new OreBlock(copy(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> DEEPSLATE_SUPER_USELESS_ORE = BLOCKS.register("deepslate_super_useless_ore", () -> new OreBlock(copy(Blocks.DEEPSLATE_GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> NETHER_SUPER_USELESS_ORE = BLOCKS.register("nether_super_useless_ore", () -> new OreBlock(copy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> END_SUPER_USELESS_ORE = BLOCKS.register("end_super_useless_ore", () -> new OreBlock(copy(Blocks.END_STONE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_BLOCK = BLOCKS.register("useless_block", () -> new Block(copy(Blocks.IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RAW_USELESS_BLOCK = BLOCKS.register("raw_useless_block", () -> new Block(copy(Blocks.RAW_IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_BLOCK = BLOCKS.register("super_useless_block", () -> new Block(copy(Blocks.GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RAW_SUPER_USELESS_BLOCK = BLOCKS.register("raw_super_useless_block", () -> new Block(copy(Blocks.RAW_GOLD_BLOCK)), GENERAL_BLOCK_ITEM);

    // Wood and Plants
    public static final ItemObject<Block> USELESS_OAK_LOG = BLOCKS.register("useless_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_WOOD = BLOCKS.register("useless_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> STRIPPED_USELESS_OAK_LOG = BLOCKS.register("stripped_useless_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> STRIPPED_USELESS_OAK_WOOD = BLOCKS.register("stripped_useless_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_PLANKS = BLOCKS.register("useless_oak_planks", BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_STAIRS = BLOCKS.register("useless_oak_stairs", () -> new StairBlock(() -> USELESS_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_SLAB = BLOCKS.register("useless_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_FENCE = BLOCKS.register("useless_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_FENCE_GATE = BLOCKS.register("useless_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_DOOR = BLOCKS.register("useless_oak_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)), DOUBLE_HIGH_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_TRAPDOOR = BLOCKS.register("useless_oak_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_PRESSURE_PLATE = BLOCKS.register("useless_oak_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_BUTTON = BLOCKS.register("useless_oak_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<Block> USELESS_OAK_WALL_SIGN = BLOCKS.registerNoItem("useless_oak_wall_sign", () -> new UselessWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).lootFrom(ModBlocks.USELESS_OAK_SIGN), UselessWoodTypes.USELESS_OAK));
    public static final ItemObject<Block> USELESS_OAK_SIGN = BLOCKS.register("useless_oak_sign", () -> new UselessStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), UselessWoodTypes.USELESS_OAK), block -> new SignItem(STACKS_TO_16_PROPS, ModBlocks.USELESS_OAK_SIGN.get(), ModBlocks.USELESS_OAK_WALL_SIGN.get()));
    public static final ItemObject<Block> USELESS_OAK_LEAVES = BLOCKS.register("useless_oak_leaves", () -> new LeavesBlock(copy(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_SAPLING = BLOCKS.register("useless_oak_sapling", () -> new SaplingBlock(new UselessOakTreeGrower(), copy(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RED_ROSE = BLOCKS.register("red_rose", () -> new FlowerBlock(() -> MobEffects.NIGHT_VISION, 5, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> BLUE_ROSE = BLOCKS.register("blue_rose", () -> new FlowerBlock(() -> MobEffects.SATURATION, 7, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ROSE = BLOCKS.register("useless_rose", () -> new FlowerBlock(() -> MobEffects.LUCK, 2, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);

    // Rails
    public static final ItemObject<Block> USELESS_RAIL = BLOCKS.register("useless_rail", () -> new UselessRailBlock(copy(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_POWERED_RAIL = BLOCKS.register("useless_powered_rail", () -> new UselessPoweredRailBlock(copy(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_DETECTOR_RAIL = BLOCKS.register("useless_detector_rail", () -> new UselessDetectorRailBlock(copy(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ACTIVATOR_RAIL = BLOCKS.register("useless_activator_rail", () -> new UselessPoweredRailBlock(copy(Blocks.ACTIVATOR_RAIL)), GENERAL_BLOCK_ITEM);

    // Decoration Blocks
    public static final ItemObject<Block> USELESS_BARS = BLOCKS.register("useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_BARS = BLOCKS.register("super_useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_WOOL = BLOCKS.register("useless_wool", () -> new Block(copy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_CARPET = BLOCKS.register("useless_carpet", () -> new WoolCarpetBlock(DyeColor.LIME, copy(Blocks.WHITE_CARPET)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_BED = BLOCKS.register("useless_bed", () -> new UselessBedBlock(DyeColor.LIME, copy(Blocks.WHITE_CARPET)), block -> new UselessBedItem(block, ONE_STACKING_PROPS));

    // Functional Blocks
    public static final ItemObject<Block> MACHINE_SUPPLIER = BLOCKS.register("machine_supplier", () -> new MachineSupplierBlock(of(Material.DECORATION).strength(0.5F).dynamicShape().noOcclusion()), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> COFFEE_MACHINE = BLOCKS.register("coffee_machine", () -> new CoffeeMachineBlock(of(Material.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL)), block -> new CoffeeMachineBlockItem(block, GENERAL_PROPS));
    public static final ItemObject<Block> CUP = BLOCKS.register("cup", () -> new CupBlock(of(Material.DECORATION).strength(0.5F)), block -> new CupBlockItem(block, GENERAL_PROPS, false));
    public static final ItemObject<Block> CUP_COFFEE = BLOCKS.register("cup_coffee", () -> new CupCoffeeBlock(of(Material.DECORATION).strength(0.5F)), block -> new CupBlockItem(block, ONE_STACKING_PROPS, true));
    public static final ItemObject<Block> PAINT_BUCKET = BLOCKS.register("paint_bucket", () -> new PaintBucketBlock(copy(Blocks.LANTERN)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> CANVAS = BLOCKS.register("canvas", () -> new CanvasBlock(copy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> WALL_CLOSET = BLOCKS.register("wall_closet", () -> new WallClosetBlock(of(Material.WOOD).strength(.5F)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> LIGHT_SWITCH = BLOCKS.register("light_switch", () -> new LightSwitchBlock(of(Material.DECORATION).noCollission().strength(.25F)), block -> new LightSwitchBlockItem(block, GENERAL_PROPS));
    public static final ItemObject<Block> WHITE_LAMP = BLOCKS.register("white_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> ORANGE_LAMP = BLOCKS.register("orange_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> MAGENTA_LAMP = BLOCKS.register("magenta_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> LIGHT_BLUE_LAMP = BLOCKS.register("light_blue_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> YELLOW_LAMP = BLOCKS.register("yellow_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> LIME_LAMP = BLOCKS.register("lime_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> PINK_LAMP = BLOCKS.register("pink_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> GRAY_LAMP = BLOCKS.register("gray_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> LIGHT_GRAY_LAMP = BLOCKS.register("light_gray_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> CYAN_LAMP = BLOCKS.register("cyan_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> PURPLE_LAMP = BLOCKS.register("purple_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> BLUE_LAMP = BLOCKS.register("blue_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> BROWN_LAMP = BLOCKS.register("brown_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> GREEN_LAMP = BLOCKS.register("green_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RED_LAMP = BLOCKS.register("red_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> BLACK_LAMP = BLOCKS.register("black_lamp", () -> new RedstoneLampBlock(copy(Blocks.REDSTONE_LAMP)), GENERAL_BLOCK_ITEM);

    // Blocks without Item
    public static final RegistryObject<Block> POTTED_RED_ROSE = BLOCKS.registerNoItem("potted_red_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RED_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = BLOCKS.registerNoItem("potted_blue_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_USELESS_ROSE = BLOCKS.registerNoItem("potted_useless_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_USELESS_OAK_SAPLING = BLOCKS.registerNoItem("potted_useless_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_OAK_SAPLING, copy(Blocks.POTTED_OAK_SAPLING)));
    public static final RegistryObject<Block> USELESS_WHEAT = BLOCKS.registerNoItem("useless_wheat", () -> new UselessCropBlock(false, ModItems.USELESS_WHEAT_SEEDS, copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> WILD_USELESS_WHEAT = BLOCKS.registerNoItem("wild_useless_wheat", () -> new UselessCropBlock(true, ModItems.USELESS_WHEAT_SEEDS, copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> COFFEE_BEANS = BLOCKS.registerNoItem("coffee_beans", () -> new UselessCropBlock(false, ModItems.COFFEE_SEEDS, copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> WILD_COFFEE_BEANS = BLOCKS.registerNoItem("wild_coffee_beans", () -> new UselessCropBlock(true, ModItems.COFFEE_SEEDS, copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> USELESS_SKELETON_SKULL = BLOCKS.registerNoItem("useless_skeleton_skull", () -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, copy(Blocks.SKELETON_SKULL)));
    public static final RegistryObject<Block> USELESS_SKELETON_WALL_SKULL = BLOCKS.registerNoItem("useless_skeleton_wall_skull", () -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, copy(Blocks.SKELETON_WALL_SKULL).lootFrom(USELESS_SKELETON_SKULL)));
    public static final RegistryObject<Block> LANTERN = BLOCKS.registerNoItem("lantern", () -> new UselessLanternBlock(copy(Blocks.LANTERN).lightLevel(state -> 0)));
}
