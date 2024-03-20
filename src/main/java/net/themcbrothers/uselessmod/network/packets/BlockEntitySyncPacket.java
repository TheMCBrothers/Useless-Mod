package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.MessageProxy;
import net.themcbrothers.uselessmod.world.level.block.entity.SyncableBlockEntity;

import java.util.Objects;

public record BlockEntitySyncPacket(BlockPos pos, CompoundTag tag) implements PacketMessage<PlayPayloadContext> {
    public static final Type<BlockEntitySyncPacket> TYPE = new Type<>(UselessMod.rl("block_entity_sync"));
    public static final StreamCodec<FriendlyByteBuf, BlockEntitySyncPacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public BlockEntitySyncPacket decode(FriendlyByteBuf buf) {
            return new BlockEntitySyncPacket(buf.readBlockPos(), Objects.requireNonNull(buf.readNbt()));
        }

        @Override
        public void encode(FriendlyByteBuf buf, BlockEntitySyncPacket packet) {
            buf.writeBlockPos(packet.pos);
            buf.writeNbt(packet.tag);
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void handle(PlayPayloadContext context) {
        if (context.flow().getReceptionSide() == LogicalSide.SERVER) {
            context.level().ifPresent(level -> {
                if (level.isAreaLoaded(this.pos, 1)) {
                    if (level.getBlockEntity(this.pos) instanceof SyncableBlockEntity blockEntity) {
                        blockEntity.receiveMessageFromClient(this.tag, level.registryAccess());
                    }
                }
            });
        } else {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                MessageProxy.receiveServerUpdates(this.pos, this.tag).run();
            }
        }
    }
}
