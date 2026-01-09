package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.world.level.block.PaintedWoolBlock;
import org.jetbrains.annotations.Nullable;

public class PaintedWoolBlockEntity extends BlockEntity {
    public static final String TAG_COLOR = "color";
    public static final int DEFAULT_COLOR = 0xFFFFFFFF;

    private int color = DEFAULT_COLOR;

    public PaintedWoolBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.PAINTED_WOOL.get(), pos, state);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        tag.putInt(TAG_COLOR, this.color);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr(TAG_COLOR, DEFAULT_COLOR);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(TAG_COLOR, this.color);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
        //noinspection DataFlowIssue
        level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(PaintedWoolBlock.PAINTED, Boolean.TRUE));
        this.requestModelDataUpdate();
        this.setChanged();
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter componentGetter) {
        this.color = componentGetter.getOrDefault(UselessDataComponents.COLOR.get(), DEFAULT_COLOR);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        builder.set(UselessDataComponents.COLOR.get(), this.color);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("color");
    }
}
