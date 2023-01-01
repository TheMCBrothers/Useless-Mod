package net.themcbrothers.uselessmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.themcbrothers.uselessmod.world.level.block.entity.SyncableBlockEntity;

public class MessageProxy {
    public static Runnable receiveServerUpdates(BlockPos pos, CompoundTag nbt) {
        return () -> {
            net.minecraft.client.multiplayer.ClientLevel level = net.minecraft.client.Minecraft.getInstance().level;
            if (level != null) {
                if (level.getBlockEntity(pos) instanceof SyncableBlockEntity blockEntity)
                    blockEntity.receiveMessageFromServer(nbt);
            }
        };
    }
}
