package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

public class UselessCropBlock extends CropBlock {
    private final boolean isWild;
    private final ItemLike seedItem;

    public UselessCropBlock(boolean isWild, ItemLike seedItem, Properties properties) {
        super(properties);
        this.isWild = isWild;
        this.seedItem = seedItem;
        if (isWild) {
            this.registerDefaultState(this.stateDefinition.any().setValue(AGE, this.getMaxAge()));
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return this.isWild ? state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) : state.is(Blocks.FARMLAND);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return this.isWild ? PlantType.PLAINS : PlantType.CROP;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.seedItem;
    }
}
