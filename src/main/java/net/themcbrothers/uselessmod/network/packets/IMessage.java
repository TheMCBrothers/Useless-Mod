package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage {
    void toBytes(FriendlyByteBuf buffer);

    void process(Supplier<NetworkEvent.Context> context);
}
