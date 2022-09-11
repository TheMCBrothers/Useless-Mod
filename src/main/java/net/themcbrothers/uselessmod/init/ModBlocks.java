package net.themcbrothers.uselessmod.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.CoffeeMachineBlockItem;
import net.themcbrothers.uselessmod.world.level.block.*;
import net.themcbrothers.uselessmod.world.level.block.grower.UselessOakTreeGrower;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.mantle.registration.object.WoodBlockObject;

import java.util.function.Function;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;
import static net.themcbrothers.uselessmod.init.Registration.BLOCKS;

public final class ModBlocks {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(UselessMod.TAB);
    private static final Function<Block, ? extends BlockItem> GENERAL_BLOCK_ITEM = (b) -> new BlockItem(b, GENERAL_PROPS);

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
    public static final WoodBlockObject USELESS_OAK_WOOD = BLOCKS.registerWood("useless_oak", variant -> BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD), true, UselessMod.TAB);
    public static final ItemObject<Block> USELESS_OAK_LEAVES = BLOCKS.register("useless_oak_leaves", () -> new LeavesBlock(copy(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_SAPLING = BLOCKS.register("useless_oak_sapling", () -> new SaplingBlock(new UselessOakTreeGrower(), copy(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RED_ROSE = BLOCKS.register("red_rose", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> BLUE_ROSE = BLOCKS.register("blue_rose", () -> new FlowerBlock(MobEffects.SATURATION, 7, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ROSE = BLOCKS.register("useless_rose", () -> new FlowerBlock(MobEffects.LUCK, 2, copy(Blocks.POPPY)), GENERAL_BLOCK_ITEM);

    // Rails
    public static final ItemObject<Block> USELESS_RAIL = BLOCKS.register("useless_rail", () -> new UselessRailBlock(copy(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_POWERED_RAIL = BLOCKS.register("useless_powered_rail", () -> new UselessPoweredRailBlock(copy(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_DETECTOR_RAIL = BLOCKS.register("useless_detector_rail", () -> new UselessDetectorRailBlock(copy(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ACTIVATOR_RAIL = BLOCKS.register("useless_activator_rail", () -> new UselessPoweredRailBlock(copy(Blocks.ACTIVATOR_RAIL)), GENERAL_BLOCK_ITEM);

    // Decoration Blocks
    public static final ItemObject<Block> USELESS_BARS = BLOCKS.register("useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> SUPER_USELESS_BARS = BLOCKS.register("super_useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);

    // todo useless skeleton skull

    // Functional Blocks
    public static final ItemObject<Block> COFFEE_MACHINE = BLOCKS.register("coffee_machine", () -> new CoffeeMachineBlock(of(Material.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL)), block -> new CoffeeMachineBlockItem(block, GENERAL_PROPS));
    public static final ItemObject<Block> PAINT_BUCKET = BLOCKS.register("paint_bucket", () -> new PaintBucketBlock(copy(Blocks.LANTERN)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> CANVAS = BLOCKS.register("canvas", () -> new CanvasBlock(copy(Blocks.WHITE_WOOL)), GENERAL_BLOCK_ITEM);

    // Blocks without Item
    public static final RegistryObject<Block> POTTED_RED_ROSE = BLOCKS.registerNoItem("potted_red_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, RED_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = BLOCKS.registerNoItem("potted_blue_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_USELESS_ROSE = BLOCKS.registerNoItem("potted_useless_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_ROSE, copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<Block> POTTED_USELESS_OAK_SAPLING = BLOCKS.registerNoItem("potted_useless_oak_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, USELESS_OAK_SAPLING, copy(Blocks.POTTED_OAK_SAPLING)));
    public static final RegistryObject<Block> USELESS_WHEAT = BLOCKS.registerNoItem("useless_wheat", () -> new UselessCropBlock(false, copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> WILD_USELESS_WHEAT = BLOCKS.registerNoItem("wild_useless_wheat", () -> new UselessCropBlock(true, copy(Blocks.WHEAT)));
}
