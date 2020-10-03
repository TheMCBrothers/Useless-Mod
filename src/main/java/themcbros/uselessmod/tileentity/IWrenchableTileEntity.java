package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import themcbros.uselessmod.helpers.WrenchUtils;

/**
 * @author TheMCBrothers
 */
public interface IWrenchableTileEntity {

    default TileEntity getWrenchableTileEntity() {
        return (TileEntity) this;
    }

    default boolean tryWrench(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        World world = getWrenchableTileEntity().getWorld();
        BlockPos pos = getWrenchableTileEntity().getPos();

        assert world != null;
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty()) {
            if (WrenchUtils.isWrench(stack)) {
                if (player.isSneaking()) {
                    WrenchUtils.dismantleBlock(state, world, pos, getWrenchableTileEntity(), null, null);
                    return true;
                }
                BlockState state1 = state.rotate(world, pos, Rotation.CLOCKWISE_90);
                if (state1 != state) {
                    world.setBlockState(pos, state1, 1 | 3 | 16 | 32);
                    return true;
                }
            }
        }
        return false;
    }

}
