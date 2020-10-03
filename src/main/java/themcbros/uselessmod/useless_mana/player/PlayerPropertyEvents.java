package themcbros.uselessmod.useless_mana.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;

/**
 * @author TheMCBrothers
 */
@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerPropertyEvents {

    @SubscribeEvent
    public static void onEntityConstructing(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            if (!event.getObject().getCapability(PlayerProperties.PLAYER_MANA, null).isPresent()) {
                event.addCapability(UselessMod.rl("mana"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(final PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            if (event.getOriginal().getCapability(PlayerProperties.PLAYER_MANA, null).isPresent()) {
                PlayerMana oldStore = PlayerProperties.getPlayerMana(event.getOriginal());
                PlayerMana newStore = PlayerProperties.getPlayerMana(event.getPlayer());
                newStore.copyFrom(oldStore);
            }
        }
    }

}
