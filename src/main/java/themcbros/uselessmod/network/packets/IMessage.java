package themcbros.uselessmod.network.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage {

    void toBytes(PacketBuffer packetBuffer);

    void process(Supplier<NetworkEvent.Context> context);

}
