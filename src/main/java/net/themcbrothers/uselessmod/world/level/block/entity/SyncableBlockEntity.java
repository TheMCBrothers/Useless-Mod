package net.themcbrothers.uselessmod.world.level.block.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface SyncableBlockEntity {
    default BlockEntity getSyncTile() {
        return (BlockEntity) this;
    }

    default void sendSyncPacket(int type) {
    }

    default void receiveMessageFromServer(CompoundTag tag) {
    }

    default void receiveMessageFromClient(CompoundTag tag) {
    }
}
