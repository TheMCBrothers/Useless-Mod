package themcbros.uselessmod;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import themcbros.uselessmod.helpers.TagUtils;

/**
 * @author TheMCBrothers
 */
public class UselessTags {

    public static class Items {
        public static final ITag.INamedTag<Item> ORES_USELESS = TagUtils.forgeItemTag("ores/useless");
        public static final ITag.INamedTag<Item> ORES_SUPER_USELESS = TagUtils.forgeItemTag("ores/super_useless");
        public static final ITag.INamedTag<Item> INGOTS_USELESS = TagUtils.forgeItemTag("ingots/useless");
        public static final ITag.INamedTag<Item> INGOTS_SUPER_USELESS = TagUtils.forgeItemTag("ingots/super_useless");

        public static final ITag.INamedTag<Item> CROPS_USELESS_WHEAT = TagUtils.forgeItemTag("crops/useless_wheat");
        public static final ITag.INamedTag<Item> CROPS_COFFEEBEAN = TagUtils.forgeItemTag("crops/coffeebean");

        public static final ITag.INamedTag<Item> TOOLS_WRENCH = TagUtils.forgeItemTag("tools/wrench");
        public static final ITag.INamedTag<Item> WRENCHES = TagUtils.forgeItemTag("wrenches");
        public static final ITag.INamedTag<Item> WRENCH = TagUtils.forgeItemTag("wrench");

        public static final ITag.INamedTag<Item> MACHINE_FRAMES = TagUtils.uselessItemTag("machine_frames");
    }

    public static class Fluids {
        public static final ITag.INamedTag<Fluid> GAS = TagUtils.uselessFluidTag("gas");
    }

}
