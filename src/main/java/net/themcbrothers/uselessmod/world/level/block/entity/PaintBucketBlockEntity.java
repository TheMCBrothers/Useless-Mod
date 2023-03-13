package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;

public class PaintBucketBlockEntity extends BlockEntity {
    public PaintBucketBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINT_BUCKET.get(), pos, state);
    }
}
