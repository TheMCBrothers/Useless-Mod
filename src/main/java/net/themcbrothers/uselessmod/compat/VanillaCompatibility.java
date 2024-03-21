package net.themcbrothers.uselessmod.compat;

import com.google.common.collect.Maps;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.core.UselessItems;

public class VanillaCompatibility {
    public static void register() {
        // Flammable Blocks
        registerFlammable(UselessBlocks.USELESS_OAK_LEAVES.get(), 30, 60);
        registerFlammable(UselessBlocks.USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(UselessBlocks.USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(UselessBlocks.STRIPPED_USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(UselessBlocks.STRIPPED_USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(UselessBlocks.USELESS_OAK_PLANKS.get(), 5, 20);
        registerFlammable(UselessBlocks.USELESS_OAK_STAIRS.get(), 5, 20);
        registerFlammable(UselessBlocks.USELESS_OAK_SLAB.get(), 5, 20);
        registerFlammable(UselessBlocks.USELESS_OAK_FENCE.get(), 5, 20);
        registerFlammable(UselessBlocks.USELESS_OAK_FENCE_GATE.get(), 5, 20);
        registerFlammable(UselessBlocks.USELESS_WOOL.get(), 30, 60);
        registerFlammable(UselessBlocks.PAINTED_WOOL.get(), 30, 60);

        // Strippable logs
        registerStrippable(UselessBlocks.USELESS_OAK_LOG.get(), UselessBlocks.STRIPPED_USELESS_OAK_LOG.get());
        registerStrippable(UselessBlocks.USELESS_OAK_WOOD.get(), UselessBlocks.STRIPPED_USELESS_OAK_WOOD.get());

        // Cauldron
        CauldronInteraction.WATER.map().put(UselessBlocks.PAINTED_WOOL.asItem(), (state, level, pos, player, hand, stack) -> {
            if (!level.isClientSide) {
                ItemStack itemStack = new ItemStack(Blocks.WHITE_WOOL);

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                if (stack.isEmpty()) {
                    player.setItemInHand(hand, itemStack);
                } else if (player.getInventory().add(itemStack)) {
                    player.inventoryMenu.sendAllDataToRemote();
                } else {
                    player.drop(itemStack, false);
                }

                LayeredCauldronBlock.lowerFillLevel(state, level, pos);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        });

        CauldronInteraction.WATER.map().put(UselessItems.PAINT_BRUSH.get(), (state, level, pos, player, hand, stack) -> {
            if (stack.has(UselessDataComponents.COLOR.get())) {
                if (!level.isClientSide) {
                    stack.remove(UselessDataComponents.COLOR.get());
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                }

                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        });
    }

    private static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(log, stripped_log);
    }

    private static void registerFlammable(Block blockIn, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFlammable(blockIn, encouragement, flammability);
    }
}
