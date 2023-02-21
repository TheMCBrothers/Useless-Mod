package net.themcbrothers.uselessmod.compat;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;

public class VanillaCompatibility {
    public static void register() {
        // Flammable Blocks
        registerFlammable(ModBlocks.USELESS_OAK_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRIPPED_USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRIPPED_USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_FENCE_GATE.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_WOOL.get(), 30, 60);
        registerFlammable(ModBlocks.CANVAS.get(), 30, 60);

        // Strippable logs
        registerStrippable(ModBlocks.USELESS_OAK_LOG.get(), ModBlocks.STRIPPED_USELESS_OAK_LOG.get());
        registerStrippable(ModBlocks.USELESS_OAK_WOOD.get(), ModBlocks.STRIPPED_USELESS_OAK_WOOD.get());

        // Compostable blocks
        registerCompostable(0.3F, ModBlocks.USELESS_OAK_SAPLING.get());
        registerCompostable(0.3F, ModBlocks.USELESS_OAK_LEAVES.get());
        registerCompostable(0.3F, ModItems.USELESS_WHEAT_SEEDS.get());
        registerCompostable(0.65F, ModBlocks.RED_ROSE.get());
        registerCompostable(0.65F, ModBlocks.BLUE_ROSE.get());
        registerCompostable(0.65F, ModBlocks.USELESS_ROSE.get());
        registerCompostable(0.65F, ModItems.USELESS_WHEAT.get());
    }

    private static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, stripped_log);
    }

    private static void registerCompostable(float chance, ItemLike itemIn) {
        ComposterBlock.COMPOSTABLES.put(itemIn.asItem(), chance);
    }

    private static void registerFlammable(Block blockIn, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}