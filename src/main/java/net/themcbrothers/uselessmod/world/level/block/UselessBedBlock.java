package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;

public class UselessBedBlock extends BedBlock {
    public UselessBedBlock(DyeColor dyeColor, Properties properties) {
        super(dyeColor, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.BED.get().create(pos, state);
    }
}
