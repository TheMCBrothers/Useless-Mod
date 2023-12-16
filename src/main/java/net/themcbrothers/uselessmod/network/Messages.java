package net.themcbrothers.uselessmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.SimpleChannel;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.packets.StartCoffeeMachinePacket;
import net.themcbrothers.uselessmod.network.packets.SyncTileEntityPacket;
import net.themcbrothers.uselessmod.network.packets.UpdateMilkCoffeeMachinePacket;

import java.util.function.Function;

public class Messages {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE;

    static {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                UselessMod.rl("main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        registerMessage(StartCoffeeMachinePacket.class, StartCoffeeMachinePacket::new);
        registerMessage(UpdateMilkCoffeeMachinePacket.class, UpdateMilkCoffeeMachinePacket::new);
        registerMessage(SyncTileEntityPacket.class, SyncTileEntityPacket::new);
    }

    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    private static <T extends PacketMessage> void registerMessage(Class<T> packetType, Function<FriendlyByteBuf, T> decoder) {
        INSTANCE.registerMessage(nextID(), packetType, PacketMessage::toBytes, decoder, ((t, ctx) -> {
            t.process(ctx);
            ctx.setPacketHandled(true);
        }));
    }

    public static void init() {
    }
}
