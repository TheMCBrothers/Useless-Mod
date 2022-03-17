package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.themcbrothers.uselessmod.UselessMod;
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

    public static final ItemObject<Block> USELESS_ORE = BLOCKS.register("useless_ore", () -> new OreBlock(copy(Blocks.IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> DEEPSLATE_USELESS_ORE = BLOCKS.register("deepslate_useless_ore", () -> new OreBlock(copy(Blocks.DEEPSLATE_IRON_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> NETHER_USELESS_ORE = BLOCKS.register("nether_useless_ore", () -> new OreBlock(copy(Blocks.NETHER_QUARTZ_ORE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> END_USELESS_ORE = BLOCKS.register("end_useless_ore", () -> new OreBlock(of(Material.STONE)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_BLOCK = BLOCKS.register("useless_block", () -> new Block(copy(Blocks.IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> RAW_USELESS_BLOCK = BLOCKS.register("raw_useless_block", () -> new Block(copy(Blocks.RAW_IRON_BLOCK)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> PAINT_BUCKET = BLOCKS.register("paint_bucket", () -> new PaintBucketBlock(copy(Blocks.LANTERN)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_SAPLING = BLOCKS.register("useless_oak_sapling", () -> new SaplingBlock(new UselessOakTreeGrower(), copy(Blocks.OAK_SAPLING)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_OAK_LEAVES = BLOCKS.register("useless_oak_leaves", () -> new LeavesBlock(copy(Blocks.OAK_LEAVES)), GENERAL_BLOCK_ITEM);

    public static final WoodBlockObject USELESS_OAK_WOOD = BLOCKS.registerWood("useless_oak", variant -> BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD), true, UselessMod.TAB);
    public static final ItemObject<Block> USELESS_RAIL = BLOCKS.register("useless_rail", () -> new UselessRailBlock(copy(Blocks.RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_POWERED_RAIL = BLOCKS.register("useless_powered_rail", () -> new UselessPoweredRailBlock(copy(Blocks.POWERED_RAIL), true), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_DETECTOR_RAIL = BLOCKS.register("useless_detector_rail", () -> new UselessDetectorRailBlock(copy(Blocks.DETECTOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_ACTIVATOR_RAIL = BLOCKS.register("useless_activator_rail", () -> new UselessPoweredRailBlock(copy(Blocks.ACTIVATOR_RAIL)), GENERAL_BLOCK_ITEM);
    public static final ItemObject<Block> USELESS_BARS = BLOCKS.register("useless_bars", () -> new IronBarsBlock(copy(Blocks.IRON_BARS)), GENERAL_BLOCK_ITEM);
    // todo useless skeleton skull

    public static final ItemObject<Block> COFFEE_MACHINE = BLOCKS.register("coffee_machine", () -> new CoffeeMachineBlock(of(Material.METAL).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.METAL)), GENERAL_BLOCK_ITEM);
}
