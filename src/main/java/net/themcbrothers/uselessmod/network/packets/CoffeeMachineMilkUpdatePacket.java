package net.themcbrothers.uselessmod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.lib.network.PacketUtils;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

public record CoffeeMachineMilkUpdatePacket(boolean useMilk) implements PacketMessage<PlayPayloadContext> {
    public static final ResourceLocation ID = UselessMod.rl("coffee_machine_milk_update");

    public CoffeeMachineMilkUpdatePacket(FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.useMilk);
    }

    @Override
    public void handle(PlayPayloadContext context) {
        PacketUtils.container(context, CoffeeMachineMenu.class).ifPresent(menu -> {
            CoffeeMachineBlockEntity coffeeMachine = menu.blockEntity;
            coffeeMachine.updateUseMilk(this.useMilk);
        });
    }
}
