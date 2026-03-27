package net.themcbrothers.uselessmod;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import static net.themcbrothers.lib.util.TagUtils.*;

public class UselessTags {
    public static class Blocks {
        public static final TagKey<Block> ORES_USELESS = commonBlockTag("ores/useless");
        public static final TagKey<Block> ORES_SUPER_USELESS = commonBlockTag("ores/super_useless");
        public static final TagKey<Block> STORAGE_BLOCKS_USELESS = commonBlockTag("storage_blocks/useless");
        public static final TagKey<Block> STORAGE_BLOCKS_SUPER_USELESS = commonBlockTag("storage_blocks/super_useless");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_USELESS = commonBlockTag("storage_blocks/raw_useless");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_SUPER_USELESS = commonBlockTag("storage_blocks/raw_super_useless");

        public static final TagKey<Block> USELESS_ORES = modTag("useless_ores");
        public static final TagKey<Block> SUPER_USELESS_ORES = modTag("super_useless_ores");
        public static final TagKey<Block> USELESS_OAK_LOGS = modTag("useless_oak_logs");
        public static final TagKey<Block> LAMPS = modTag("lamps");
        public static final TagKey<Block> WALL_CLOSET_MATERIALS = modTag("wall_closet_materials");

        private static TagKey<Block> modTag(String tag) {
            return BlockTags.create(UselessMod.id(tag));
        }
    }

    public static class Items {
        public static final TagKey<Item> INGOTS_USELESS = commonItemTag("ingots/useless");
        public static final TagKey<Item> INGOTS_SUPER_USELESS = commonItemTag("ingots/super_useless");
        public static final TagKey<Item> DUSTS_USELESS = commonItemTag("dusts/useless");
        public static final TagKey<Item> DUSTS_SUPER_USELESS = commonItemTag("dusts/super_useless");
        public static final TagKey<Item> RAW_MATERIALS_USELESS = commonItemTag("raw_materials/useless");
        public static final TagKey<Item> RAW_MATERIALS_SUPER_USELESS = commonItemTag("raw_materials/super_useless");

        public static final TagKey<Item> ORES_USELESS = commonItemTag("ores/useless");
        public static final TagKey<Item> ORES_SUPER_USELESS = commonItemTag("ores/super_useless");
        public static final TagKey<Item> STORAGE_BLOCKS_USELESS = commonItemTag("storage_blocks/useless");
        public static final TagKey<Item> STORAGE_BLOCKS_SUPER_USELESS = commonItemTag("storage_blocks/super_useless");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_USELESS = commonItemTag("storage_blocks/raw_useless");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SUPER_USELESS = commonItemTag("storage_blocks/raw_super_useless");

        public static final TagKey<Item> CROPS_USELESS_WHEAT = commonItemTag("crops/useless_wheat");
        public static final TagKey<Item> CROPS_COFFEEBEAN = commonItemTag("crops/coffeebean");
        public static final TagKey<Item> SEEDS_USELESS_WHEAT = commonItemTag("seeds/useless_wheat");
        public static final TagKey<Item> SEEDS_COFFEEBEAN = commonItemTag("seeds/coffeebean");

        public static final TagKey<Item> USELESS_ORES = modTag("useless_ores");
        public static final TagKey<Item> SUPER_USELESS_ORES = modTag("super_useless_ores");
        public static final TagKey<Item> USELESS_OAK_LOGS = modTag("useless_oak_logs");
        public static final TagKey<Item> LAMPS = modTag("lamps");

        private static TagKey<Item> modTag(String tag) {
            return ItemTags.create(UselessMod.id(tag));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> PAINT = commonFluidTag("paint");
    }
}
