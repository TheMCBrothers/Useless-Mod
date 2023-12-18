package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.NetworkEvent;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.network.MessageProxy;
import net.themcbrothers.uselessmod.world.level.block.entity.SyncableBlockEntity;

import java.util.Objects;
import java.util.function.Supplier;

public class SyncTileEntityPacket implements PacketMessage {
    private final BlockPos pos;
    private final CompoundTag tag;

    public SyncTileEntityPacket(SyncableBlockEntity blockEntity, CompoundTag tag) {
        this.pos = blockEntity.getSyncTile().getBlockPos();
        this.tag = tag;
    }

    public SyncTileEntityPacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
        this.tag = buffer.readNbt();
    }

    @Override
    public void toBytes(FriendlyByteBuf packetBuffer) {
        packetBuffer.writeBlockPos(this.pos);
        packetBuffer.writeNbt(this.tag);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        if (ctx.getDirection().getReceptionSide() == LogicalSide.SERVER) {
            ctx.enqueueWork(() -> {
                ServerLevel level = Objects.requireNonNull(ctx.getSender()).serverLevel();
                if (level.isAreaLoaded(this.pos, 1)) {
                    if (level.getBlockEntity(this.pos) instanceof SyncableBlockEntity blockEntity) {
                        blockEntity.receiveMessageFromClient(this.tag);
                    }
                }

            });
        } else {
            ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MessageProxy.receiveServerUpdates(this.pos, this.tag)));
        }
    }
}
