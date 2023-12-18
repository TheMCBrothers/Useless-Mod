package net.themcbrothers.uselessmod.network;

import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.MessageFunctions;
import net.neoforged.neoforge.network.simple.SimpleChannel;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.packets.StartCoffeeMachinePacket;
import net.themcbrothers.uselessmod.network.packets.SyncTileEntityPacket;
import net.themcbrothers.uselessmod.network.packets.UpdateMilkCoffeeMachinePacket;

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

    private static <MSG extends PacketMessage> void registerMessage(Class<MSG> packetType, MessageFunctions.MessageDecoder<MSG> decoder) {
        INSTANCE.registerMessage(nextID(), packetType, PacketMessage::toBytes, decoder, (msg, ctx) -> {
            msg.process(ctx);
            ctx.setPacketHandled(true);
        });
    }

    public static void init() {
    }
}
