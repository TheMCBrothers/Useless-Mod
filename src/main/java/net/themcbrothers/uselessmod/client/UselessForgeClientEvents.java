package net.themcbrothers.uselessmod.client;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.UselessElytraItem;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = UselessMod.MOD_ID, value = Dist.CLIENT)
public class UselessForgeClientEvents {
    private static boolean wasCloakVisible;

    @SubscribeEvent
    static void renderEntityPre(final RenderLivingEvent.Pre<?, ?> event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof UselessElytraItem
                && event.getRenderer().getModel() instanceof PlayerModel<?> playerModel) {
            wasCloakVisible = playerModel.cloak.visible;
            playerModel.cloak.visible = false;
        }
    }

    @SubscribeEvent
    static void renderEntityPre(final RenderLivingEvent.Post<?, ?> event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof UselessElytraItem
                && event.getRenderer().getModel() instanceof PlayerModel<?> playerModel) {
            playerModel.cloak.visible = wasCloakVisible;
        }
    }
}
