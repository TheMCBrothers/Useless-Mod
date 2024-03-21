package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.core.UselessBlockEntityTypes;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.world.level.block.PaintedWoolBlock;
import org.jetbrains.annotations.Nullable;

public class PaintedWoolBlockEntity extends BlockEntity {
    private int color = 0xFFFFFFFF;

    public PaintedWoolBlockEntity(BlockPos pos, BlockState state) {
        super(UselessBlockEntityTypes.PAINTED_WOOL.get(), pos, state);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lookupProvider) {
        CompoundTag tag = super.getUpdateTag(lookupProvider);
        tag.putInt("Color", this.color);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.load(tag, lookupProvider);
        this.color = tag.getInt("Color");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.saveAdditional(tag, lookupProvider);
        tag.putInt("Color", this.color);
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
    public void applyComponents(DataComponentMap components) {
        this.color = components.getOrDefault(UselessDataComponents.COLOR.get(), 0xFFFFFFFF);
    }

    @Override
    public void collectComponents(DataComponentMap.Builder builder) {
        builder.set(UselessDataComponents.COLOR.get(), this.color);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("Color");
    }
}
