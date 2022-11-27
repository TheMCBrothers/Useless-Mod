package net.themcbrothers.uselessmod.client;

import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themcbrothers.uselessmod.UselessMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID, value = Dist.CLIENT)
public class UselessClientEvents {
    @SubscribeEvent
    static void onAddSheets(final TextureStitchEvent.Pre event) {
        if (event.getAtlas().location() == Sheets.BED_SHEET) {
            event.addSprite(UselessMod.rl("entity/bed/useless"));
        }
    }

    @SubscribeEvent
    static void onEntityAddLayers(final EntityRenderersEvent.AddLayers event) {
        // TODO: apply elytra layer
    }
}
