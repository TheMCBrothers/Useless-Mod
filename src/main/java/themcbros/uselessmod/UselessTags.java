package themcbros.uselessmod;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;

import static themcbros.uselessmod.helpers.TagUtils.*;

/**
 * @author TheMCBrothers
 */
public class UselessTags {

    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> ORES_USELESS = forgeItemTag("ores/useless");
        public static final Tags.IOptionalNamedTag<Item> ORES_SUPER_USELESS = forgeItemTag("ores/super_useless");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_USELESS = forgeItemTag("storage_blocks/useless");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_SUPER_USELESS = forgeItemTag("storage_blocks/super_useless");

        public static final Tags.IOptionalNamedTag<Item> INGOTS_USELESS = forgeItemTag("ingots/useless");
        public static final Tags.IOptionalNamedTag<Item> INGOTS_SUPER_USELESS = forgeItemTag("ingots/super_useless");
        public static final Tags.IOptionalNamedTag<Item> NUGGETS_USELESS = forgeItemTag("nuggets/useless");
        public static final Tags.IOptionalNamedTag<Item> NUGGETS_SUPER_USELESS = forgeItemTag("nuggets/super_useless");
        public static final Tags.IOptionalNamedTag<Item> DUSTS_USELESS = forgeItemTag("dusts/useless");
        public static final Tags.IOptionalNamedTag<Item> DUSTS_SUPER_USELESS = forgeItemTag("dusts/super_useless");
        public static final Tags.IOptionalNamedTag<Item> PLATES_USELESS = forgeItemTag("plates/useless");
        public static final Tags.IOptionalNamedTag<Item> PLATES_SUPER_USELESS = forgeItemTag("plates/super_useless");
        public static final Tags.IOptionalNamedTag<Item> GEARS_USELESS = forgeItemTag("gears/useless");
        public static final Tags.IOptionalNamedTag<Item> GEARS_SUPER_USELESS = forgeItemTag("gears/super_useless");
        public static final Tags.IOptionalNamedTag<Item> RODS_USELESS = forgeItemTag("rods/useless");
        public static final Tags.IOptionalNamedTag<Item> RODS_SUPER_USELESS = forgeItemTag("rods/super_useless");

        public static final Tags.IOptionalNamedTag<Item> CROPS_USELESS_WHEAT = forgeItemTag("crops/useless_wheat");
        public static final Tags.IOptionalNamedTag<Item> CROPS_COFFEEBEAN = forgeItemTag("crops/coffeebean");
        public static final Tags.IOptionalNamedTag<Item> CROPS_ENDER = forgeItemTag("crops/ender");

        public static final Tags.IOptionalNamedTag<Item> TOOLS_WRENCH = forgeItemTag("tools/wrench");
        public static final Tags.IOptionalNamedTag<Item> WRENCH = forgeItemTag("wrench");

        public static final Tags.IOptionalNamedTag<Item> USELESS_LOGS = uselessItemTag("useless_logs");
        public static final Tags.IOptionalNamedTag<Item> MACHINE_FRAMES = uselessItemTag("machine_frames");
        public static final Tags.IOptionalNamedTag<Item> LAMPS = uselessItemTag("lamps");
    }

    public static class Blocks {
        // FORGE
        public static final Tags.IOptionalNamedTag<Block> ORES_USELESS = forgeBlockTag("ores/useless");
        public static final Tags.IOptionalNamedTag<Block> ORES_SUPER_USELESS = forgeBlockTag("ores/super_useless");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_USELESS = forgeBlockTag("storage_blocks/useless");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_SUPER_USELESS = forgeBlockTag("storage_blocks/super_useless");
        // USELESS MOD
        public static final Tags.IOptionalNamedTag<Block> USELESS_LOGS = uselessBlockTag("useless_logs");
        public static final Tags.IOptionalNamedTag<Block> LAMPS = uselessBlockTag("lamps");
    }

    public static class Fluids {
        public static final Tags.IOptionalNamedTag<Fluid> GAS = uselessFluidTag("gas");
    }

}
