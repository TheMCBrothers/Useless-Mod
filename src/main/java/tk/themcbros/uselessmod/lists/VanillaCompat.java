package tk.themcbros.uselessmod.lists;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

public class VanillaCompat {

	public static void register() {
		// Flammable block
		registerFlammable(ModBlocks.USELESS_LEAVES, 30, 60);
		registerFlammable(ModBlocks.USELESS_LOG, 5, 5);
        registerFlammable(ModBlocks.USELESS_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRIPPED_USELESS_LOG, 5, 5);
        registerFlammable(ModBlocks.STRIPPED_USELESS_WOOD, 5, 5);
		registerFlammable(ModBlocks.USELESS_PLANKS, 5, 20);
		registerFlammable(ModBlocks.USELESS_SLAB, 5, 20);
		registerFlammable(ModBlocks.USELESS_STAIRS, 5, 20);
		registerFlammable(ModBlocks.USELESS_FENCE, 5, 20);
		registerFlammable(ModBlocks.USELESS_FENCE_GATE, 5, 20);
		registerFlammable(ModBlocks.USELESS_GRASS, 60, 100);
		registerFlammable(ModBlocks.USELESS_FERN, 60, 100);
		registerFlammable(ModBlocks.TALL_USELESS_GRASS, 60, 100);
		registerFlammable(ModBlocks.LARGE_USELESS_FERN, 60, 100);
		registerFlammable(ModBlocks.RED_ROSE, 60, 100);
		registerFlammable(ModBlocks.BLUE_ROSE, 60, 100);

		// Strippable logs
		registerStrippable(ModBlocks.USELESS_LOG, ModBlocks.STRIPPED_USELESS_LOG);
		
		// Compostable blocks
		registerCompostable(0.3F, ModBlocks.USELESS_LEAVES);
		registerCompostable(0.3F, ModBlocks.USELESS_SAPLING);
		registerCompostable(0.3F, ModBlocks.USELESS_GRASS);
		registerCompostable(0.3F, ModItems.USELESS_WHEAT_SEEDS);
		registerCompostable(0.3F, ModItems.COFFEE_SEEDS);
		registerCompostable(0.4F, ModItems.COFFEE_BEANS);
		registerCompostable(0.5F, ModBlocks.TALL_USELESS_GRASS);
		registerCompostable(0.65F, ModBlocks.USELESS_FERN);
		registerCompostable(0.65F, ModBlocks.LARGE_USELESS_FERN);
		registerCompostable(0.65F, ModBlocks.RED_ROSE);
		registerCompostable(0.65F, ModBlocks.BLUE_ROSE);
		registerCompostable(0.65F, ModItems.USELESS_WHEAT);
		registerCompostable(0.85F, ModItems.USELESS_BREAD);	
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
