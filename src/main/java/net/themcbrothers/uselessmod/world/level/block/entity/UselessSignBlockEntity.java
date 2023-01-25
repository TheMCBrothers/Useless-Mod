package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;

public class UselessSignBlockEntity extends SignBlockEntity {
    public UselessSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.SIGN.get();
    }
}
