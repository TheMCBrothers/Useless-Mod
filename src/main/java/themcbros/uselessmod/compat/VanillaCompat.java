package themcbros.uselessmod.compat;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;

public class VanillaCompat {

    public static void register() {
        // Flammable Blocks
        registerFlammable(BlockInit.USELESS_OAK_LEAVES.get(), 30, 60);
        registerFlammable(BlockInit.USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(BlockInit.USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(BlockInit.STRIPPED_USELESS_OAK_LOG.get(), 5, 5);
        registerFlammable(BlockInit.STRIPPED_USELESS_OAK_WOOD.get(), 5, 5);
        registerFlammable(BlockInit.USELESS_OAK_PLANKS.get(), 5, 20);
        registerFlammable(BlockInit.USELESS_OAK_STAIRS.get(), 5, 20);
        registerFlammable(BlockInit.USELESS_OAK_SLAB.get(), 5, 20);
        registerFlammable(BlockInit.USELESS_OAK_FENCE.get(), 5, 20);
        registerFlammable(BlockInit.USELESS_OAK_FENCE_GATE.get(), 5, 20);
        registerFlammable(BlockInit.USELESS_WOOL.get(), 30, 60);
        registerFlammable(BlockInit.USELESS_CARPET.get(), 60, 20);

        // Strippable logs
        registerStrippable(BlockInit.USELESS_OAK_LOG.get(), BlockInit.STRIPPED_USELESS_OAK_LOG.get());
        registerStrippable(BlockInit.USELESS_OAK_WOOD.get(), BlockInit.STRIPPED_USELESS_OAK_WOOD.get());

        // Compostable blocks
        registerCompostable(0.3F, BlockInit.USELESS_OAK_SAPLING.get());
        registerCompostable(0.3F, BlockInit.USELESS_OAK_LEAVES.get());
        registerCompostable(0.3F, ItemInit.USELESS_WHEAT_SEEDS.get());
        registerCompostable(0.3F, ItemInit.COFFEE_SEEDS.get());
        registerCompostable(0.65F, BlockInit.RED_ROSE.get());
        registerCompostable(0.65F, BlockInit.BLUE_ROSE.get());
        registerCompostable(0.65F, BlockInit.USELESS_ROSE.get());
        registerCompostable(0.65F, ItemInit.USELESS_WHEAT.get());
        registerCompostable(0.65F, ItemInit.COFFEE_BEANS.get());
    }

    private static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }

    private static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }

    private static void registerFlammable(Block blockIn, int encouragement, int flammability) {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }

}
