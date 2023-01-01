package net.themcbrothers.uselessmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.packets.IMessage;
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

    private static <T extends IMessage> void registerMessage(Class<T> packetType, Function<FriendlyByteBuf, T> decoder) {
        INSTANCE.registerMessage(nextID(), packetType, IMessage::toBytes, decoder, ((t, ctx) -> {
            t.process(ctx);
            ctx.get().setPacketHandled(true);
        }));
    }

    public static void init() {
    }

}
