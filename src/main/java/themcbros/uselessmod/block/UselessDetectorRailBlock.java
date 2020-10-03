package themcbros.uselessmod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UselessDetectorRailBlock extends DetectorRailBlock {
    public UselessDetectorRailBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        return 0.75F;
    }
}
