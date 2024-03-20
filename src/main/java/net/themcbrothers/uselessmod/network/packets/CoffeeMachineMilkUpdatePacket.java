package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.lib.network.PacketUtils;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

public record CoffeeMachineMilkUpdatePacket(boolean useMilk) implements PacketMessage<PlayPayloadContext> {
    public static final Type<CoffeeMachineMilkUpdatePacket> TYPE = new Type<>(UselessMod.rl("coffee_machine_milk_update"));
    public static final StreamCodec<FriendlyByteBuf, CoffeeMachineMilkUpdatePacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public CoffeeMachineMilkUpdatePacket decode(FriendlyByteBuf buf) {
            return new CoffeeMachineMilkUpdatePacket(buf.readBoolean());
        }

        @Override
        public void encode(FriendlyByteBuf buf, CoffeeMachineMilkUpdatePacket packet) {
            buf.writeBoolean(packet.useMilk);
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void handle(PlayPayloadContext context) {
        PacketUtils.container(context, CoffeeMachineMenu.class).ifPresent(menu -> {
            CoffeeMachineBlockEntity coffeeMachine = menu.blockEntity;
            coffeeMachine.updateUseMilk(this.useMilk);
        });
    }
}
