package themcbros.uselessmod.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import themcbros.uselessmod.UselessTags;

import javax.annotation.Nullable;

public class WrenchUtils {

    public static boolean isWrench(ItemStack stack) {
        Item item = stack.getItem();
        return item.isIn(UselessTags.Items.TOOLS_WRENCH)
                || item.isIn(UselessTags.Items.WRENCH);
    }

    public static void dismantleBlock(BlockState state, World world, BlockPos pos, @Nullable TileEntity tileEntity, @Nullable Entity entity, @Nullable ItemStack stack) {
        if (entity != null && stack != null) Block.spawnDrops(state, world, pos, tileEntity, entity, stack);
        else Block.spawnDrops(state, world, pos, tileEntity);
        world.removeBlock(pos, false);
    }

}
