package net.themcbrothers.uselessmod.world.level.block.entity;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.themcbrothers.uselessmod.api.LampRegistry;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.world.level.block.LightSwitchBlock;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LightSwitchBlockEntity extends BlockEntity {
    private final List<BlockPos> blockPositions = Lists.newArrayList();

    public LightSwitchBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.LIGHT_SWITCH.get(), pos, state);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        builder.set(UselessDataComponents.LIGHTS.get(), this.getBlockPositions());
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        this.setBlockPositions(componentGetter.getOrDefault(UselessDataComponents.LIGHTS.get(), Collections.emptyList()));
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        if (!this.blockPositions.isEmpty()) {
            ValueOutput.TypedOutputList<Long> lights = output.list("lights", Codec.LONG);
            this.blockPositions.stream().map(BlockPos::asLong).forEach(lights::add);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        input.list("lights", Codec.LONG).ifPresent(longs -> {
            this.blockPositions.clear();
            longs.stream().map(BlockPos::of).forEach(this.blockPositions::add);
        });
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

    public List<BlockPos> getBlockPositions() {
        return this.blockPositions;
    }

    public void setBlockPositions(Collection<BlockPos> blockPositions) {
        this.blockPositions.clear();
        this.blockPositions.addAll(blockPositions);
        this.setChanged();
    }
}
