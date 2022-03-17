package net.themcbrothers.uselessmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class UselessTags {
    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> ORES_USELESS = forgeTag("ores/useless");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_USELESS = forgeTag("storage_blocks/useless");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_RAW_USELESS = forgeTag("storage_blocks/raw_useless");

        public static final Tags.IOptionalNamedTag<Block> USELESS_OAK_LOGS = modTag("useless_oak_logs");

        private static Tags.IOptionalNamedTag<Block> forgeTag(String tag) {
            return BlockTags.createOptional(new ResourceLocation("forge", tag));
        }

        private static Tags.IOptionalNamedTag<Block> modTag(String tag) {
            return BlockTags.createOptional(UselessMod.rl(tag));
        }
    }
    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> INGOTS_USELESS = forgeTag("ingots/useless");
        public static final Tags.IOptionalNamedTag<Item> DUSTS_USELESS = forgeTag("dusts/useless");
        public static final Tags.IOptionalNamedTag<Item> RAW_MATERIALS_USELESS = forgeTag("raw_materials/useless");

        public static final Tags.IOptionalNamedTag<Item> ORES_USELESS = forgeTag("ores/useless");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_USELESS = forgeTag("storage_blocks/useless");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_RAW_USELESS = forgeTag("storage_blocks/raw_useless");

        public static final Tags.IOptionalNamedTag<Item> USELESS_OAK_LOGS = modTag("useless_oak_logs");

        private static Tags.IOptionalNamedTag<Item> forgeTag(String tag) {
            return ItemTags.createOptional(new ResourceLocation("forge", tag));
        }

        private static Tags.IOptionalNamedTag<Item> modTag(String tag) {
            return ItemTags.createOptional(UselessMod.rl(tag));
        }
    }
}
