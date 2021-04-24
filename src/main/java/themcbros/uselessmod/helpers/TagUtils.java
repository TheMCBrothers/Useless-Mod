package themcbros.uselessmod.helpers;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.versions.forge.ForgeVersion;
import themcbros.uselessmod.UselessMod;

/**
 * @author TheMCBrothers
 */
public class TagUtils {

    // ITEM TAGS
    public static Tags.IOptionalNamedTag<Item> uselessItemTag(String path) {
        return ItemTags.createOptional(UselessMod.rl(path));
    }

    public static Tags.IOptionalNamedTag<Item> forgeItemTag(String path) {
        return ItemTags.createOptional(new ResourceLocation(ForgeVersion.MOD_ID, path));
    }

    // BLOCK TAGS
    public static Tags.IOptionalNamedTag<Block> uselessBlockTag(String path) {
        return BlockTags.createOptional(UselessMod.rl(path));
    }

    public static Tags.IOptionalNamedTag<Block> forgeBlockTag(String path) {
        return BlockTags.createOptional(new ResourceLocation(ForgeVersion.MOD_ID, path));
    }

    public static Tags.IOptionalNamedTag<Fluid> uselessFluidTag(String path) {
        return FluidTags.createOptional(UselessMod.rl(path));
    }
}
