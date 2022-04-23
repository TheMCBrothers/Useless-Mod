package net.themcbrothers.uselessmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themcbrothers.uselessmod.UselessMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID, value = Dist.CLIENT)
public class UselessClientEvents {
    @SubscribeEvent
    static void onEntityAddLayers(final EntityRenderersEvent.AddLayers event) {
        // TODO: apply elytra layer
    }
}
