package themcbros.uselessmod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;

@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void stitchTextures(final TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().getPath().contains("blocks")) {
            event.addSprite(UselessMod.rl("block/useless_generator"));
            event.addSprite(UselessMod.rl("entity/shield_useless"));
            event.addSprite(UselessMod.rl("entity/shield_super_useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("beds")) {
            event.addSprite(UselessMod.rl("entity/bed/useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("boats")) {
            event.addSprite(UselessMod.rl("entity/boat/useless"));
            event.addSprite(UselessMod.rl("entity/boat/super_useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("signs")) {
            event.addSprite(UselessMod.rl("entity/signs/useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("chest")) {
            event.addSprite(UselessMod.rl("entity/chest/useless"));
            event.addSprite(UselessMod.rl("entity/chest/useless_left"));
            event.addSprite(UselessMod.rl("entity/chest/useless_right"));
        }
    }

}
