package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.NetworkEvent;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

import java.util.function.Supplier;

public class UpdateMilkCoffeeMachinePacket implements PacketMessage {
    private final boolean useMilk;

    public UpdateMilkCoffeeMachinePacket(boolean useMilk) {
        this.useMilk = useMilk;
    }

    public UpdateMilkCoffeeMachinePacket(FriendlyByteBuf buffer) {
        this.useMilk = buffer.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.useMilk);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        ServerPlayer serverPlayerEntity = context.get().getSender();
        context.get().enqueueWork(() -> {
            if (serverPlayerEntity != null) {
                if (serverPlayerEntity.containerMenu instanceof CoffeeMachineMenu) {
                    CoffeeMachineBlockEntity coffeeMachine = ((CoffeeMachineMenu) serverPlayerEntity.containerMenu).blockEntity;
                    coffeeMachine.updateUseMilk(this.useMilk);
                }
            }
        });
    }
}
