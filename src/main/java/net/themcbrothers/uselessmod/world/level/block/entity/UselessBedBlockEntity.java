package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;

public class UselessBedBlockEntity extends BedBlockEntity {
    public UselessBedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return UselessBlockEntityTypes.BED.get();
    }
}
