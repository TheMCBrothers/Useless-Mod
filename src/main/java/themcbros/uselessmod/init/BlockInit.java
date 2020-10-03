package themcbros.uselessmod.init;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.block.*;
import themcbros.uselessmod.world.feature.UselessTree;

public class BlockInit {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, UselessMod.MOD_ID);

    // METAL
    public static final RegistryObject<Block> USELESS_BLOCK = registerBlock("useless_block",
            new Block(Block.Properties.from(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SUPER_USELESS_BLOCK = registerBlock("super_useless_block",
            new Block(Block.Properties.from(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> USELESS_ORE = registerBlock("useless_ore",
            new Block(Block.Properties.from(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> USELESS_ORE_NETHER = registerBlock("useless_ore_nether",
            new Block(Block.Properties.from(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> USELESS_ORE_END = registerBlock("useless_ore_end",
            new Block(Block.Properties.from(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> SUPER_USELESS_ORE = registerBlock("super_useless_ore",
            new Block(Block.Properties.from(Blocks.GOLD_ORE)));
    public static final RegistryObject<Block> SUPER_USELESS_ORE_NETHER = registerBlock("super_useless_ore_nether",
            new Block(Block.Properties.from(Blocks.GOLD_ORE)));
    public static final RegistryObject<Block> SUPER_USELESS_ORE_END = registerBlock("super_useless_ore_end",
            new Block(Block.Properties.from(Blocks.GOLD_ORE)));

    // NATURAL
    public static final RegistryObject<FlowerBlock> RED_ROSE = registerBlock("red_rose",
            new FlowerBlock(Effects.NIGHT_VISION, 6, Block.Properties.from(Blocks.POPPY)));
    public static final RegistryObject<FlowerBlock> BLUE_ROSE = registerBlock("blue_rose",
            new FlowerBlock(Effects.SATURATION, 10, Block.Properties.from(Blocks.POPPY)));
    public static final RegistryObject<FlowerBlock> USELESS_ROSE = registerBlock("useless_rose",
            new FlowerBlock(Effects.STRENGTH, 20, Block.Properties.from(Blocks.POPPY)));
    public static final RegistryObject<FlowerPotBlock> POTTED_RED_ROSE = REGISTER.register("potted_red_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), RED_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_ROSE = REGISTER.register("potted_blue_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), BLUE_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final RegistryObject<FlowerPotBlock> POTTED_USELESS_ROSE = REGISTER.register("potted_useless_rose",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), USELESS_ROSE, Block.Properties.from(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> USELESS_SAPLING = registerBlock("useless_sapling",
            new SaplingBlock(new UselessTree(), Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> POTTED_USELESS_SAPLING = REGISTER.register("potted_useless_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), USELESS_SAPLING, Block.Properties.from(Blocks.POTTED_OAK_SAPLING)));
    public static final RegistryObject<Block> USELESS_LEAVES = registerBlock("useless_leaves",
            new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> USELESS_LOG = registerBlock("useless_log",
            createLogBlock(MaterialColor.GREEN, MaterialColor.GREEN));
    public static final RegistryObject<Block> USELESS_WOOD = registerBlock("useless_wood",
            new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_USELESS_LOG = registerBlock("stripped_useless_log",
            createLogBlock(MaterialColor.LIME, MaterialColor.LIME));
    public static final RegistryObject<Block> STRIPPED_USELESS_WOOD = registerBlock("stripped_useless_wood",
            new RotatedPillarBlock(Block.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> USELESS_PLANKS = registerBlock("useless_planks",
            new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> USELESS_STAIRS = registerBlock("useless_stairs",
            new StairsBlock(() -> USELESS_PLANKS.get().getDefaultState(), Block.Properties.from(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> USELESS_SLAB = registerBlock("useless_slab",
            new SlabBlock(Block.Properties.from(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> USELESS_FENCE = registerBlock("useless_fence",
            new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> USELESS_FENCE_GATE = registerBlock("useless_fence_gate",
            new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)));
    public static final RegistryObject<Block> WOODEN_USELESS_DOOR = registerBlock("wooden_useless_door",
            new UselessDoorBlock(Block.Properties.from(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> WOODEN_USELESS_TRAPDOOR = registerBlock("wooden_useless_trapdoor",
            new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> USELESS_PRESSURE_PLATE = registerBlock("useless_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> USELESS_BUTTON = registerBlock("useless_button",
            new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> USELESS_SIGN = REGISTER.register("useless_sign",
            () -> new UselessStandingSignBlock(Block.Properties.from(Blocks.OAK_SIGN), WoodType.OAK));
    public static final RegistryObject<Block> USELESS_WALL_SIGN = REGISTER.register("useless_wall_sign",
            () -> new UselessWallSignBlock(Block.Properties.from(Blocks.OAK_WALL_SIGN), WoodType.OAK));

    public static final RegistryObject<Block> USELESS_GENERATOR = registerBlock("useless_generator",
            new UselessGeneratorBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()));
    public static final RegistryObject<Block> COFFEE_MACHINE = registerBlock("coffee_machine",
            new CoffeeMachineBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()));
    public static final RegistryObject<Block> COFFEE_MACHINE_SUPPLIER = registerBlock("coffee_machine_supplier",
            new MachineSupplierBlock(Block.Properties.from(Blocks.IRON_BLOCK).notSolid()));
    public static final RegistryObject<Block> USELESS_WOOL = registerBlock("useless_wool",
            new Block(Block.Properties.from(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> USELESS_CARPET = registerBlock("useless_carpet",
            new CarpetBlock(DyeColor.LIME, Block.Properties.from(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> USELESS_BED = registerBlock("useless_bed",
            new UselessBedBlock(DyeColor.LIME, Block.Properties.from(Blocks.WHITE_BED)));

    public static final RegistryObject<FireBlock> USELESS_FIRE = REGISTER.register("useless_fire",
            () -> new FireBlock(Block.Properties.from(Blocks.FIRE)));
    public static final RegistryObject<UselessPoweredRailBlock> POWERED_USELESS_RAIL = registerBlock("powered_useless_rail",
            new UselessPoweredRailBlock(AbstractBlock.Properties.from(Blocks.POWERED_RAIL), true));
    public static final RegistryObject<UselessDetectorRailBlock> USELESS_DETECTOR_RAIL = registerBlock("useless_detector_rail",
            new UselessDetectorRailBlock(AbstractBlock.Properties.from(Blocks.DETECTOR_RAIL)));
    public static final RegistryObject<RailBlock> USELESS_RAIL = registerBlock("useless_rail",
            new UselessRailBlock(AbstractBlock.Properties.from(Blocks.RAIL)));
    public static final RegistryObject<UselessPoweredRailBlock> USELESS_ACTIVATOR_RAIL = registerBlock("useless_activator_rail",
            new UselessPoweredRailBlock(AbstractBlock.Properties.from(Blocks.ACTIVATOR_RAIL), false));
    public static final RegistryObject<UselessCrossoverRailBlock> USELESS_CROSSOVER_RAIL = registerBlock("useless_crossover_rail",
            new UselessCrossoverRailBlock(AbstractBlock.Properties.from(Blocks.RAIL)));
    public static final RegistryObject<CupBlock> CUP = REGISTER.register("cup",
            () -> new CupBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()));
    public static final RegistryObject<CupBlock> COFFEE_CUP = REGISTER.register("coffee_cup",
            () -> new CupBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()));

    // Crops
    public static final RegistryObject<CropsBlock> USELESS_WHEAT = REGISTER.register("useless_wheat",
            () -> new UselessCropsBlock(Block.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<CropsBlock> COFFEE_CROP = REGISTER.register("coffee_crop",
            () -> new CoffeeCropsBlock(Block.Properties.from(Blocks.WHEAT), false));
    public static final RegistryObject<CropsBlock> WILD_COFFEE_CROP = REGISTER.register("wild_coffee_crop",
            () -> new CoffeeCropsBlock(Block.Properties.from(Blocks.WHEAT), true));
    public static final RegistryObject<CropsBlock> ENDER_SEEDS = REGISTER.register("ender_seeds",
            () -> new EnderSeedsBlock(Block.Properties.from(Blocks.WHEAT)));

    // Storage
    public static final RegistryObject<WallClosetBlock> WALL_CLOSET = registerBlock("wall_closet",
            new WallClosetBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(.5f).sound(SoundType.WOOD)));
    public static final RegistryObject<UselessChestBlock> USELESS_CHEST = registerBlock("useless_chest",
            new UselessChestBlock(Block.Properties.from(Blocks.CHEST)));

    // Decorative
    public static final RegistryObject<Block> USELESS_STONE = registerBlock("useless_stone",
            new Block(AbstractBlock.Properties.from(Blocks.STONE)));
    public static final RegistryObject<Block> USELESS_COBBLESTONE = registerBlock("useless_cobblestone",
            new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE)));
    public static final RegistryObject<StairsBlock> USELESS_STONE_STAIRS = registerBlock("useless_stone_stairs",
            new StairsBlock(() -> USELESS_STONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE_STAIRS)));
    public static final RegistryObject<StairsBlock> USELESS_COBBLESTONE_STAIRS = registerBlock("useless_cobblestone_stairs",
            new StairsBlock(() -> USELESS_COBBLESTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.COBBLESTONE_STAIRS)));
    public static final RegistryObject<SlabBlock> USELESS_STONE_SLAB = registerBlock("useless_stone_slab",
            new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE_SLAB)));
    public static final RegistryObject<SlabBlock> USELESS_COBBLESTONE_SLAB = registerBlock("useless_cobblestone_slab",
            new SlabBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_SLAB)));
    public static final RegistryObject<WallBlock> USELESS_COBBLESTONE_WALL = registerBlock("useless_cobblestone_wall",
            new WallBlock(AbstractBlock.Properties.from(Blocks.COBBLESTONE_WALL)));
    public static final RegistryObject<PressurePlateBlock> USELESS_STONE_PRESSURE_PLATE = registerBlock("useless_stone_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(Blocks.STONE_PRESSURE_PLATE)));
    public static final RegistryObject<StoneButtonBlock> USELESS_STONE_BUTTON = registerBlock("useless_stone_button",
            new StoneButtonBlock(AbstractBlock.Properties.from(Blocks.STONE_BUTTON)));

    // Misc
    public static final RegistryObject<LampBlock> WHITE_LAMP = registerBlock("white_lamp", new LampBlock(DyeColor.WHITE));
    public static final RegistryObject<LampBlock> ORANGE_LAMP = registerBlock("orange_lamp", new LampBlock(DyeColor.ORANGE));
    public static final RegistryObject<LampBlock> MAGENTA_LAMP = registerBlock("magenta_lamp", new LampBlock(DyeColor.MAGENTA));
    public static final RegistryObject<LampBlock> LIGHT_BLUE_LAMP = registerBlock("light_blue_lamp", new LampBlock(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<LampBlock> YELLOW_LAMP = registerBlock("yellow_lamp", new LampBlock(DyeColor.YELLOW));
    public static final RegistryObject<LampBlock> LIME_LAMP = registerBlock("lime_lamp", new LampBlock(DyeColor.LIME));
    public static final RegistryObject<LampBlock> PINK_LAMP = registerBlock("pink_lamp", new LampBlock(DyeColor.PINK));
    public static final RegistryObject<LampBlock> GRAY_LAMP = registerBlock("gray_lamp", new LampBlock(DyeColor.GRAY));
    public static final RegistryObject<LampBlock> LIGHT_GRAY_LAMP = registerBlock("light_gray_lamp", new LampBlock(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<LampBlock> CYAN_LAMP = registerBlock("cyan_lamp", new LampBlock(DyeColor.CYAN));
    public static final RegistryObject<LampBlock> PURPLE_LAMP = registerBlock("purple_lamp", new LampBlock(DyeColor.PURPLE));
    public static final RegistryObject<LampBlock> BLUE_LAMP = registerBlock("blue_lamp", new LampBlock(DyeColor.BLUE));
    public static final RegistryObject<LampBlock> BROWN_LAMP = registerBlock("brown_lamp", new LampBlock(DyeColor.BROWN));
    public static final RegistryObject<LampBlock> GREEN_LAMP = registerBlock("green_lamp", new LampBlock(DyeColor.GREEN));
    public static final RegistryObject<LampBlock> RED_LAMP = registerBlock("red_lamp", new LampBlock(DyeColor.RED));
    public static final RegistryObject<LampBlock> BLACK_LAMP = registerBlock("black_lamp", new LampBlock(DyeColor.BLACK));
    public static final RegistryObject<PaintBucketBlock> PAINT_BUCKET = registerBlock("paint_bucket",
            new PaintBucketBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(.25f).notSolid()));
    public static final RegistryObject<CanvasBlock> CANVAS = registerBlock("canvas",
            new CanvasBlock(Block.Properties.create(Material.WOOL).hardnessAndResistance(.5f).sound(SoundType.CLOTH)));

    public static final RegistryObject<UselessSkullBlock> USELESS_SKELETON_SKULL = REGISTER.register("useless_skeleton_skull",
            () -> new UselessSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
    public static final RegistryObject<UselessWallSkullBlock> USELESS_SKELETON_WALL_SKULL = REGISTER.register("useless_skeleton_wall_skull",
            () -> new UselessWallSkullBlock(UselessSkullBlock.Types.USELESS_SKELETON, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(USELESS_SKELETON_SKULL.get())));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, T block) {
        Item.Properties props = new Item.Properties().group(UselessMod.GROUP);
        BlockItem blockItem = block instanceof IBlockItemProvider ? ((IBlockItemProvider) block).provideBlockItem(block, props) : new BlockItem(block, props);
        ItemInit.REGISTER.register(name, () -> blockItem);
        return REGISTER.register(name, () -> block);
    }

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, state -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }

    static {
        if (Blocks.FLOWER_POT != null) {
            FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
            flowerPot.addPlant(UselessMod.rl("useless_sapling"), POTTED_USELESS_SAPLING);
            flowerPot.addPlant(UselessMod.rl("red_rose"), POTTED_RED_ROSE);
            flowerPot.addPlant(UselessMod.rl("blue_rose"), POTTED_BLUE_ROSE);
            flowerPot.addPlant(UselessMod.rl("useless_rose"), POTTED_USELESS_ROSE);
        }
    }

}
