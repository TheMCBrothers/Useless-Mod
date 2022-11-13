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
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getLog(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getWood(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getStrippedLog(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getStrippedWood(), 5, 5);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.get(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getStairs(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getSlab(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getFence(), 5, 20);
        registerFlammable(ModBlocks.USELESS_OAK_WOOD.getFenceGate(), 5, 20);
        
        // Strippable logs
        registerStrippable(ModBlocks.USELESS_OAK_WOOD.getLog(), ModBlocks.USELESS_OAK_WOOD.getStrippedLog());
        registerStrippable(ModBlocks.USELESS_OAK_WOOD.getWood(), ModBlocks.USELESS_OAK_WOOD.getStrippedWood());

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
