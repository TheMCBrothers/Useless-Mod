package themcbros.uselessmod.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.network.packets.IMessage;
import themcbros.uselessmod.network.packets.OpenUselessSignEditor;
import themcbros.uselessmod.network.packets.StartCoffeeMachinePacket;
import themcbros.uselessmod.network.packets.SyncTileEntityPacket;
import themcbros.uselessmod.useless_mana.SendManaPacket;

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

        registerMessage(SendManaPacket.class, SendManaPacket::new);
        registerMessage(StartCoffeeMachinePacket.class, StartCoffeeMachinePacket::new);
        registerMessage(SyncTileEntityPacket.class, SyncTileEntityPacket::new);
        registerMessage(OpenUselessSignEditor.class, OpenUselessSignEditor::new);
    }

    private static int ID = 0;
    private static int nextID() {
        return ID++;
    }

    private static <T extends IMessage> void registerMessage(Class<T> packetType, Function<PacketBuffer, T> decoder) {
        INSTANCE.registerMessage(nextID(), packetType, IMessage::toBytes, decoder, ((t, ctx) -> {
            t.process(ctx);
            ctx.get().setPacketHandled(true);
        }));
    }

    public static void init() {}

}
