package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
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
    public static final ResourceLocation ID = UselessMod.rl("block_entity_sync");

    public BlockEntitySyncPacket(SyncableBlockEntity blockEntity, CompoundTag tag) {
        this(blockEntity.getSyncTile().getBlockPos(), tag);
    }

    public BlockEntitySyncPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), Objects.requireNonNull(buffer.readNbt()));
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf packetBuffer) {
        packetBuffer.writeBlockPos(this.pos);
        packetBuffer.writeNbt(this.tag);
    }

    @Override
    public void handle(PlayPayloadContext context) {
        if (context.flow().getReceptionSide() == LogicalSide.SERVER) {
            context.level().ifPresent(level -> {
                if (level.isAreaLoaded(this.pos, 1)) {
                    if (level.getBlockEntity(this.pos) instanceof SyncableBlockEntity blockEntity) {
                        blockEntity.receiveMessageFromClient(this.tag);
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
