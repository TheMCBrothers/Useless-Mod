package net.themcbrothers.uselessmod.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.themcbrothers.lib.network.PacketMessage;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.network.packets.BlockEntitySyncPacket;
import net.themcbrothers.uselessmod.network.packets.CoffeeMachineMilkUpdatePacket;
import net.themcbrothers.uselessmod.network.packets.CoffeeMachineStartPacket;
import org.apache.maven.artifact.versioning.ArtifactVersion;

public class UselessPacketHandler {
    private final String version;

    public UselessPacketHandler(ModContainer modContainer) {
        ArtifactVersion version = modContainer.getModInfo().getVersion();
        this.version = "%d.%d".formatted(version.getMajorVersion(), version.getMinorVersion());
    }

    @SubscribeEvent
    private void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(this.version);

        registrar.playToServer(CoffeeMachineStartPacket.TYPE, CoffeeMachineStartPacket.STREAM_CODEC, PacketMessage::handle);
        registrar.playToServer(CoffeeMachineMilkUpdatePacket.TYPE, CoffeeMachineMilkUpdatePacket.STREAM_CODEC, PacketMessage::handle);

        registrar.playBidirectional(BlockEntitySyncPacket.TYPE, BlockEntitySyncPacket.STREAM_CODEC, PacketMessage::handle);

        UselessMod.LOGGER.info("Registered common payloads");
    }

    @SubscribeEvent
    private void registerClientPayloadHandlers(RegisterClientPayloadHandlersEvent event) {
        event.register(BlockEntitySyncPacket.TYPE, PacketMessage::handle);
        UselessMod.LOGGER.info("Registered client payloads");
    }
}
