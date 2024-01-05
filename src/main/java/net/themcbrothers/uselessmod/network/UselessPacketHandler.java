package net.themcbrothers.uselessmod.network;

import net.neoforged.bus.api.IEventBus;
import net.themcbrothers.lib.network.BasePacketHandler;
import net.themcbrothers.lib.util.Version;
import net.themcbrothers.uselessmod.network.packets.BlockEntitySyncPacket;
import net.themcbrothers.uselessmod.network.packets.CoffeeMachineMilkUpdatePacket;
import net.themcbrothers.uselessmod.network.packets.CoffeeMachineStartPacket;

public class UselessPacketHandler extends BasePacketHandler {
    public UselessPacketHandler(IEventBus modEventBus, String modId, Version version) {
        super(modEventBus, modId, version);
    }

    @Override
    protected void registerClientToServer(PacketRegistrar registrar) {
        registrar.play(CoffeeMachineStartPacket.ID, CoffeeMachineStartPacket::new);
        registrar.play(CoffeeMachineMilkUpdatePacket.ID, CoffeeMachineMilkUpdatePacket::new);
    }

    @Override
    protected void registerServerToClient(PacketRegistrar registrar) {
        registrar.play(BlockEntitySyncPacket.ID, BlockEntitySyncPacket::new);
    }
}
