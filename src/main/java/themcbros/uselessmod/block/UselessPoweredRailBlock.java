package themcbros.uselessmod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UselessPoweredRailBlock extends PoweredRailBlock {

    public UselessPoweredRailBlock(Properties builder, boolean isPoweredRail) {
        super(builder, isPoweredRail);
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        return 0.75F;
    }
}
