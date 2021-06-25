package themcbros.uselessmod.init;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.registration.deferred.BlockDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.block.*;
import themcbros.uselessmod.client.renderer.tilentity.ISTERProvider;
import themcbros.uselessmod.world.feature.UselessTree;

import java.util.function.Function;

public class BlockInit {

    public static final BlockDeferredRegister REGISTER = new BlockDeferredRegister(UselessMod.MOD_ID);

    private static final Item.Properties GENERAL_PROPS = new Item.Properties().group(UselessMod.GROUP);
    private static final Function<Block, ? extends BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);

    // METAL
    public static final ItemObject<Block> USELESS_BLOCK = REGISTER.register("useless_block",
            () -> new Block(Block.Properties.from(Blocks.IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_BLOCK = REGISTER.register("super_useless_block",
            () -> new Block(Block.Properties.from(Blocks.GOLD_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ORE = REGISTER.register("useless_ore",
            () -> new Block(Block.Properties.from(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ORE_NETHER = REGISTER.register("useless_ore_nether",
            () -> new Block(Block.Properties.from(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ORE_END = REGISTER.register("useless_ore_end",
            () -> new Block(Block.Properties.from(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_ORE = REGISTER.register("super_useless_ore",
            () -> new Block(Block.Properties.from(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_ORE_NETHER = REGISTER.register("super_useless_ore_nether",
            () -> new Block(Block.Properties.from(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_ORE_END = REGISTER.register("super_useless_ore_end",
            () -> new Block(Block.Properties.from(Blocks.GOLD_ORE)), GENERAL_BLOCK_ITEM);

    // NATURAL
    public static final ItemObject<FlowerBlock> RED_ROSE = REGISTER.register("red_rose",
            () -> new FlowerBlock(Effects.NIGHT_VISION, 6, Block.Properties.from(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<FlowerBlock> BLUE_ROSE = REGISTER.register("blue_rose",
            () -> new FlowerBlock(Effects.SATURATION, 10, Block.Properties.from(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<FlowerBlock> USELESS_ROSE = REGISTER.register("useless_rose",
            () -> new FlowerBlock(Effects.STRENGTH, 20, Block.Properties.from(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<FlowerPotBlock> POTTED_RED_ROSE = REGISTER.registerNoItem("potted_red_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), RED_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_ROSE = REGISTER.registerNoItem("potted_blue_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), BLUE_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final RegistryObject<FlowerPotBlock> POTTED_USELESS_ROSE = REGISTER.registerNoItem("potted_useless_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), USELESS_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final ItemObject<Block> USELESS_OAK_SAPLING = REGISTER.register("useless_oak_sapling",
            () -> new SaplingBlock(new UselessTree(), Block.Properties.from(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<Block> POTTED_USELESS_OAK_SAPLING = REGISTER.registerNoItem("potted_useless_oak_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), USELESS_OAK_SAPLING, Block.Properties.from(Blocks.POTTED_OAK_SAPLING)));
    public static final ItemObject<Block> USELESS_OAK_LEAVES = REGISTER.register("useless_oak_leaves",
            () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_LOG = REGISTER.register("useless_oak_log",
            () -> createLogBlock(MaterialColor.GREEN, MaterialColor.GREEN), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_WOOD = REGISTER.register("useless_oak_wood",
            () -> new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> STRIPPED_USELESS_OAK_LOG = REGISTER.register("stripped_useless_oak_log",
            () -> createLogBlock(MaterialColor.LIME, MaterialColor.LIME), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> STRIPPED_USELESS_OAK_WOOD = REGISTER.register("stripped_useless_oak_wood",
            () -> new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_PLANKS = REGISTER.register("useless_oak_planks",
            () -> new Block(Block.Properties.from(Blocks.OAK_PLANKS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_STAIRS = REGISTER.register("useless_oak_stairs",
            () -> new StairsBlock(() -> USELESS_OAK_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_SLAB = REGISTER.register("useless_oak_slab",
            () -> new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_FENCE = REGISTER.register("useless_oak_fence",
            () -> new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_FENCE_GATE = REGISTER.register("useless_oak_fence_gate",
            () -> new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_DOOR = REGISTER.register("useless_oak_door",
            () -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)),
            block -> new TallBlockItem(block, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<Block> USELESS_OAK_TRAPDOOR = REGISTER.register("useless_oak_trapdoor",
            () -> new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_PRESSURE_PLATE = REGISTER.register("useless_oak_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_TRAPDOOR)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_BUTTON = REGISTER.register("useless_oak_button",
            () -> new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<Block> USELESS_OAK_SIGN = REGISTER.registerNoItem("useless_oak_sign",
            () -> new UselessStandingSignBlock(Block.Properties.from(Blocks.OAK_SIGN), WoodTypeInit.USELESS_OAK));
    public static final RegistryObject<Block> USELESS_OAK_WALL_SIGN = REGISTER.registerNoItem("useless_oak_wall_sign",
            () -> new UselessWallSignBlock(Block.Properties.from(Blocks.OAK_WALL_SIGN).lootFrom(USELESS_OAK_SIGN), WoodTypeInit.USELESS_OAK));

    public static final ItemObject<Block> MACHINE_FRAME = REGISTER.register("machine_frame",
            () -> new MachineFrameBlock(AbstractBlock.Properties.from(Blocks.GLASS).sound(SoundType.METAL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> CREATIVE_ENERGY_CELL = REGISTER.register("creative_energy_cell",
            () -> new CreativeEnergyCellBlock(Block.Properties.from(Blocks.IRON_BLOCK).noDrops()), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_GENERATOR = REGISTER.register("useless_generator",
            () -> new UselessGeneratorBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> COFFEE_MACHINE = REGISTER.register("coffee_machine",
            () -> new CoffeeMachineBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()),
            block -> new CoffeeMachineBlock.CoffeeMachineItem(block, new Item.Properties().group(UselessMod.GROUP).setISTER(ISTERProvider::useless)));
    public static final ItemObject<Block> COFFEE_MACHINE_SUPPLIER = REGISTER.register("coffee_machine_supplier",
            () -> new MachineSupplierBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_WOOL = REGISTER.register("useless_wool",
            () -> new Block(Block.Properties.from(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_CARPET = REGISTER.register("useless_carpet",
            () -> new CarpetBlock(DyeColor.LIME, Block.Properties.from(Blocks.WHITE_CARPET)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_BED = REGISTER.register("useless_bed",
            () -> new UselessBedBlock(DyeColor.LIME, Block.Properties.from(Blocks.WHITE_BED)),
            block -> new BedItem(block, new Item.Properties().group(UselessMod.GROUP).maxStackSize(1).setISTER(ISTERProvider::useless)));

    public static final RegistryObject<FireBlock> USELESS_FIRE = REGISTER.registerNoItem("useless_fire",
            () -> new FireBlock(Block.Properties.from(Blocks.FIRE)));
    public static final ItemObject<UselessPoweredRailBlock> POWERED_USELESS_RAIL = REGISTER.register("powered_useless_rail",
            () -> new UselessPoweredRailBlock(AbstractBlock.Properties.from(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final ItemObject<UselessDetectorRailBlock> USELESS_DETECTOR_RAIL = REGISTER.register("useless_detector_rail",
            () -> new UselessDetectorRailBlock(AbstractBlock.Properties.from(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<RailBlock> USELESS_RAIL = REGISTER.register("useless_rail",
            () -> new UselessRailBlock(AbstractBlock.Properties.from(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<UselessPoweredRailBlock> USELESS_ACTIVATOR_RAIL = REGISTER.register("useless_activator_rail",
            () -> new UselessPoweredRailBlock(AbstractBlock.Properties.from(Blocks.ACTIVATOR_RAIL), false), GENERAL_BLOCK_ITEM);
    public static final ItemObject<UselessCrossoverRailBlock> USELESS_CROSSOVER_RAIL = REGISTER.register("useless_crossover_rail",
            () -> new UselessCrossoverRailBlock(AbstractBlock.Properties.from(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final RegistryObject<CupBlock> CUP = REGISTER.registerNoItem("cup",
            () -> new CupBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()));
    public static final RegistryObject<CupBlock> COFFEE_CUP = REGISTER.registerNoItem("coffee_cup",
            () -> new CupBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()));

    // Crops
    public static final RegistryObject<CropsBlock> USELESS_WHEAT = REGISTER.registerNoItem("useless_wheat",
            () -> new UselessCropsBlock(Block.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<CropsBlock> COFFEE_CROP = REGISTER.registerNoItem("coffee_crop",
            () -> new CoffeeCropsBlock(Block.Properties.from(Blocks.WHEAT), false));
    public static final RegistryObject<CropsBlock> WILD_COFFEE_CROP = REGISTER.registerNoItem("wild_coffee_crop",
            () -> new CoffeeCropsBlock(Block.Properties.from(Blocks.WHEAT), true));
    public static final RegistryObject<CropsBlock> ENDER_SEEDS = REGISTER.registerNoItem("ender_seeds",
            () -> new EnderSeedsBlock(Block.Properties.from(Blocks.WHEAT)));

    // Storage
    public static final ItemObject<WallClosetBlock> WALL_CLOSET = REGISTER.register("wall_closet",
            () -> new WallClosetBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(.5f).sound(SoundType.WOOD)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<UselessChestBlock> USELESS_OAK_CHEST = REGISTER.register("useless_oak_chest",
            () -> new UselessChestBlock(Block.Properties.from(Blocks.CHEST)),
            uselessChestBlock -> new BlockItem(uselessChestBlock, new Item.Properties().group(UselessMod.GROUP).setISTER(ISTERProvider::useless)));

    // Decorative
    public static final ItemObject<Block> USELESS_STONE = REGISTER.register("useless_stone",
            () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_COBBLESTONE = REGISTER.register("useless_cobblestone",
            () -> new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<StairsBlock> USELESS_STONE_STAIRS = REGISTER.register("useless_stone_stairs",
            () -> new StairsBlock(() -> USELESS_STONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<StairsBlock> USELESS_COBBLESTONE_STAIRS = REGISTER.register("useless_cobblestone_stairs",
            () -> new StairsBlock(() -> USELESS_COBBLESTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.COBBLESTONE_STAIRS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<SlabBlock> USELESS_STONE_SLAB = REGISTER.register("useless_stone_slab",
            () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE_SLAB)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<SlabBlock> USELESS_COBBLESTONE_SLAB = REGISTER.register("useless_cobblestone_slab",
            () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_SLAB)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<WallBlock> USELESS_COBBLESTONE_WALL = REGISTER.register("useless_cobblestone_wall",
            () -> new WallBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_WALL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<PressurePlateBlock> USELESS_STONE_PRESSURE_PLATE = REGISTER.register("useless_stone_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(Blocks.STONE_PRESSURE_PLATE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<StoneButtonBlock> USELESS_STONE_BUTTON = REGISTER.register("useless_stone_button",
            () -> new StoneButtonBlock(AbstractBlock.Properties.from(Blocks.STONE_BUTTON)), GENERAL_BLOCK_ITEM);

    // Misc
    public static final ItemObject<LampBlock> WHITE_LAMP = REGISTER.register("white_lamp", () -> new LampBlock(DyeColor.WHITE), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> ORANGE_LAMP = REGISTER.register("orange_lamp", () -> new LampBlock(DyeColor.ORANGE), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> MAGENTA_LAMP = REGISTER.register("magenta_lamp", () -> new LampBlock(DyeColor.MAGENTA), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> LIGHT_BLUE_LAMP = REGISTER.register("light_blue_lamp", () -> new LampBlock(DyeColor.LIGHT_BLUE), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> YELLOW_LAMP = REGISTER.register("yellow_lamp", () -> new LampBlock(DyeColor.YELLOW), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> LIME_LAMP = REGISTER.register("lime_lamp", () -> new LampBlock(DyeColor.LIME), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> PINK_LAMP = REGISTER.register("pink_lamp", () -> new LampBlock(DyeColor.PINK), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> GRAY_LAMP = REGISTER.register("gray_lamp", () -> new LampBlock(DyeColor.GRAY), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> LIGHT_GRAY_LAMP = REGISTER.register("light_gray_lamp", () -> new LampBlock(DyeColor.LIGHT_GRAY), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> CYAN_LAMP = REGISTER.register("cyan_lamp", () -> new LampBlock(DyeColor.CYAN), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> PURPLE_LAMP = REGISTER.register("purple_lamp", () -> new LampBlock(DyeColor.PURPLE), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> BLUE_LAMP = REGISTER.register("blue_lamp", () -> new LampBlock(DyeColor.BLUE), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> BROWN_LAMP = REGISTER.register("brown_lamp", () -> new LampBlock(DyeColor.BROWN), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> GREEN_LAMP = REGISTER.register("green_lamp", () -> new LampBlock(DyeColor.GREEN), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> RED_LAMP = REGISTER.register("red_lamp", () -> new LampBlock(DyeColor.RED), GENERAL_BLOCK_ITEM);
    public static final ItemObject<LampBlock> BLACK_LAMP = REGISTER.register("black_lamp", () -> new LampBlock(DyeColor.BLACK), GENERAL_BLOCK_ITEM);
    public static final ItemObject<PaintBucketBlock> PAINT_BUCKET = REGISTER.register("paint_bucket",
            () -> new PaintBucketBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()), GENERAL_BLOCK_ITEM);
    public static final ItemObject<CanvasBlock> CANVAS = REGISTER.register("canvas",
            () -> new CanvasBlock(Block.Properties.create(Material.WOOL).hardnessAndResistance(.5f).sound(SoundType.CLOTH)),
            canvasBlock -> new CanvasBlock.CanvasBlockItem(canvasBlock, new Item.Properties().group(UselessMod.GROUP)));

    public static final RegistryObject<UselessSkullBlock> USELESS_SKELETON_SKULL = REGISTER.registerNoItem("useless_skeleton_skull",
            () -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
    public static final RegistryObject<UselessWallSkullBlock> USELESS_SKELETON_WALL_SKULL = REGISTER.registerNoItem("useless_skeleton_wall_skull",
            () -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(USELESS_SKELETON_SKULL)));

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, state -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }

    static {
        if (Blocks.FLOWER_POT != null) {
            FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
            flowerPot.addPlant(USELESS_OAK_SAPLING.getRegistryName(), POTTED_USELESS_OAK_SAPLING);
            flowerPot.addPlant(RED_ROSE.getRegistryName(), POTTED_RED_ROSE);
            flowerPot.addPlant(BLUE_ROSE.getRegistryName(), POTTED_BLUE_ROSE);
            flowerPot.addPlant(USELESS_ROSE.getRegistryName(), POTTED_USELESS_ROSE);
        }
    }

}
