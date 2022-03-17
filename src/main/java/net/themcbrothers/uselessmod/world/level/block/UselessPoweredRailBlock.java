package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UselessPoweredRailBlock extends PoweredRailBlock {
    public UselessPoweredRailBlock(Properties properties) {
        this(properties, false);
    }

    public UselessPoweredRailBlock(Properties properties, boolean isPoweredRail) {
        super(properties, isPoweredRail);
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {
        return 0.6F;
    }
}
