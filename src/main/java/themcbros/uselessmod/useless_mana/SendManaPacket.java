package themcbros.uselessmod.useless_mana;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import themcbros.uselessmod.client.rendering.OverlayRenderer;
import themcbros.uselessmod.item.UselessItemItem;
import themcbros.uselessmod.network.packets.IMessage;

import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class SendManaPacket implements IMessage {

    private final float mana;
    private final float influence;
    private final float playerMana;

    public SendManaPacket(float mana, float influence, float playerMana) {
        this.mana = mana;
        this.influence = influence;
        this.playerMana = playerMana;
    }

    public SendManaPacket(PacketBuffer packetBuffer) {
        this.mana = packetBuffer.readFloat();
        this.influence = packetBuffer.readFloat();
        this.playerMana = packetBuffer.readFloat();
    }

    public void toBytes(PacketBuffer packetBuffer) {
        packetBuffer.writeFloat(this.mana);
        packetBuffer.writeFloat(this.influence);
        packetBuffer.writeFloat(this.playerMana);
    }

    public void process(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            OverlayRenderer.INSTANCE.setMana(this.mana, this.influence, this.playerMana);
            UselessItemItem.playerMana = this.playerMana;
        });
    }

}
