package themcbros.uselessmod.helpers;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.versions.forge.ForgeVersion;
import themcbros.uselessmod.UselessMod;

/**
 * @author TheMCBrothers
 */
public class TagUtils {

    // ITEM TAGS
    public static ITag.INamedTag<Item> uselessItemTag(String path) {
        return ItemTags.makeWrapperTag(UselessMod.rl(path).toString());
    }

    public static ITag.INamedTag<Item> forgeItemTag(String path) {
        return ItemTags.makeWrapperTag(new ResourceLocation(ForgeVersion.MOD_ID, path).toString());
    }

    // BLOCK TAGS
    public static ITag.INamedTag<Block> uselessBlockTag(String path) {
        return BlockTags.makeWrapperTag(UselessMod.rl(path).toString());
    }

    public static ITag.INamedTag<Block> forgeBlockTag(String path) {
        return BlockTags.makeWrapperTag(new ResourceLocation(ForgeVersion.MOD_ID, path).toString());
    }

    public static ITag.INamedTag<Fluid> uselessFluidTag(String path) {
        return FluidTags.makeWrapperTag(UselessMod.rl(path).toString());
    }
}
