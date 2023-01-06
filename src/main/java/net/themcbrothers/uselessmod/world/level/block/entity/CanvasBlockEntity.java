package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.CanvasBlock;
import org.jetbrains.annotations.Nullable;

public class CanvasBlockEntity extends BlockEntity {
    private int color = 0xFFFFFFFF;

    public CanvasBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CANVAS.get(), pos, state);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("Color", this.color);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.color = tag.getInt("Color");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Color", this.color);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
        //noinspection DataFlowIssue
        level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(CanvasBlock.PAINTED, Boolean.TRUE));
        this.requestModelDataUpdate();
        this.setChanged();
    }
}
