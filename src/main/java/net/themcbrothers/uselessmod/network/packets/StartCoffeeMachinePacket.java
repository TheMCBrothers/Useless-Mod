package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.NetworkEvent;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

public class StartCoffeeMachinePacket implements PacketMessage {
    private final boolean start;

    public StartCoffeeMachinePacket(boolean start) {
        this.start = start;
    }

    public StartCoffeeMachinePacket(FriendlyByteBuf buffer) {
        this.start = buffer.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.start);
    }

    @Override
    public void process(NetworkEvent.Context context) {
        ServerPlayer serverPlayerEntity = context.getSender();
        context.enqueueWork(() -> {
            if (serverPlayerEntity != null) {
                if (serverPlayerEntity.containerMenu instanceof CoffeeMachineMenu) {
                    CoffeeMachineBlockEntity coffeeMachine = ((CoffeeMachineMenu) serverPlayerEntity.containerMenu).blockEntity;
                    coffeeMachine.startMachine(this.start);
                }
            }
        });
    }
}
