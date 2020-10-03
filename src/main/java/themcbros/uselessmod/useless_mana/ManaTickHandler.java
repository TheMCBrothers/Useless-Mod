package themcbros.uselessmod.useless_mana;

import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;

/**
 * @author TheMCBrothers
 */
@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaTickHandler {

    @SubscribeEvent
    public static void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.START || !(event.world instanceof ServerWorld)) return;

        ServerWorld world = (ServerWorld) event.world;
        WorldMana.get(world).tick(world);
    }

}
