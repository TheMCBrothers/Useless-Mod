package themcbros.uselessmod.useless_mana.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nonnull;

/**
 * @author TheMCBrothers
 */
public class PlayerProperties {

    @CapabilityInject(PlayerMana.class)
    public static Capability<PlayerMana> PLAYER_MANA;

    @Nonnull
    public static PlayerMana getPlayerMana(PlayerEntity playerEntity) {
        return playerEntity.getCapability(PLAYER_MANA, null).orElseGet(PlayerMana::new);
    }

}
