package net.themcbrothers.uselessmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class UselessTags {
    public static class Blocks {
        public static final TagKey<Block> ORES_USELESS = forgeTag("ores/useless");
        public static final TagKey<Block> STORAGE_BLOCKS_USELESS = forgeTag("storage_blocks/useless");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_USELESS = forgeTag("storage_blocks/raw_useless");

        public static final TagKey<Block> USELESS_OAK_LOGS = modTag("useless_oak_logs");

        private static TagKey<Block> forgeTag(String tag) {
            return BlockTags.create(new ResourceLocation("forge", tag));
        }

        private static TagKey<Block> modTag(String tag) {
            return BlockTags.create(UselessMod.rl(tag));
        }
    }

    public static class Items {
        public static final TagKey<Item> INGOTS_USELESS = forgeTag("ingots/useless");
        public static final TagKey<Item> DUSTS_USELESS = forgeTag("dusts/useless");
        public static final TagKey<Item> RAW_MATERIALS_USELESS = forgeTag("raw_materials/useless");

        public static final TagKey<Item> ORES_USELESS = forgeTag("ores/useless");
        public static final TagKey<Item> STORAGE_BLOCKS_USELESS = forgeTag("storage_blocks/useless");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_USELESS = forgeTag("storage_blocks/raw_useless");

        public static final TagKey<Item> USELESS_OAK_LOGS = modTag("useless_oak_logs");

        private static TagKey<Item> forgeTag(String tag) {
            return ItemTags.create(new ResourceLocation("forge", tag));
        }

        private static TagKey<Item> modTag(String tag) {
            return ItemTags.create(UselessMod.rl(tag));
        }
    }
}
