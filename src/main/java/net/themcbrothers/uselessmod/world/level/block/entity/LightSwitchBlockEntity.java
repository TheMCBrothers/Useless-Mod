package net.themcbrothers.uselessmod.world.level.block.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.api.LampRegistry;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.LightSwitchBlock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LightSwitchBlockEntity extends BlockEntity {
    private final List<BlockPos> blockPositions = Lists.newArrayList();

    public LightSwitchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.LIGHT_SWITCH.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (this.blockPositions.size() > 0) {
            List<Long> packedPositions = this.blockPositions.stream().map(BlockPos::asLong).toList();
            tag.putLongArray("Lights", packedPositions);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.blockPositions.clear();
        Arrays.stream(tag.getLongArray("Lights")).mapToObj(BlockPos::of).forEach(this.blockPositions::add);
    }

    public boolean switchLights() {
        final Level level = this.getLevel();
        final BlockPos pos = this.getBlockPos();
        final BlockState state = this.getBlockState();

        if (level != null && state.hasProperty(LightSwitchBlock.POWERED)) {
            boolean trigger = state.getValue(LightSwitchBlock.POWERED);
            level.setBlock(pos, state.cycle(LightSwitchBlock.POWERED), Block.UPDATE_CLIENTS);

            SoundEvent sound = trigger ? SoundEvents.STONE_BUTTON_CLICK_OFF : SoundEvents.STONE_BUTTON_CLICK_ON;
            this.playSound(level, pos, sound, !trigger);
            this.switchLights(!trigger);

            level.scheduleTick(pos, state.getBlock(), 20);
            return true;
        }

        return false;
    }

    public void switchLights(boolean turnOn) {
        if (this.level == null) {
            return;
        }

        for (BlockPos blockPos : this.blockPositions.toArray(new BlockPos[0])) {
            BlockState blockState = this.level.getBlockState(blockPos);
            BlockState newBlockState = LampRegistry.getLampState(blockState, turnOn);

            if (newBlockState == null) {
                this.blockPositions.remove(blockPos);
                continue;
            }

            this.level.setBlock(blockPos, newBlockState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE | Block.UPDATE_SUPPRESS_DROPS);
        }
    }

    private void playSound(LevelAccessor worldIn, BlockPos pos, SoundEvent sound, boolean isOn) {
        worldIn.playSound(null, pos, sound, SoundSource.BLOCKS, 0.3F, isOn ? 0.6F : 0.5F);
    }

    public void setBlockPositions(Collection<BlockPos> blockPositions) {
        this.blockPositions.clear();
        this.blockPositions.addAll(blockPositions);
        this.setChanged();
    }
}
