package themcbros.uselessmod.network.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import themcbros.uselessmod.container.CoffeeMachineContainer;
import themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

import java.util.function.Supplier;

public class StartCoffeeMachinePacket implements IMessage {

    private final boolean start;

    public StartCoffeeMachinePacket(boolean start) {
        this.start = start;
    }

    public StartCoffeeMachinePacket(PacketBuffer packetBuffer) {
        this.start = packetBuffer.readBoolean();
    }

    public void toBytes(PacketBuffer packetBuffer) {
        packetBuffer.writeBoolean(this.start);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        ServerPlayerEntity serverPlayerEntity = context.get().getSender();
        context.get().enqueueWork(() ->{
            if (serverPlayerEntity != null) {
                if (serverPlayerEntity.openContainer instanceof CoffeeMachineContainer) {
                    CoffeeMachineTileEntity coffeeMachine = ((CoffeeMachineContainer) serverPlayerEntity.openContainer).tileEntity;
                    coffeeMachine.startMachine(this.start);
                }
            }
        });
    }
}
