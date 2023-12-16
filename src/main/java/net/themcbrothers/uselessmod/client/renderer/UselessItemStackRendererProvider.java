package net.themcbrothers.uselessmod.client.renderer;

import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class UselessItemStackRendererProvider {
    private static UselessBlockEntityWithoutLevelRenderer blockEntityWithoutLevelRenderer;
    private static UselessShieldItemRenderer shieldItemRenderer;

    public static void initialize(final RegisterClientReloadListenersEvent event) {
        blockEntityWithoutLevelRenderer = new UselessBlockEntityWithoutLevelRenderer();
        shieldItemRenderer = new UselessShieldItemRenderer();

        event.registerReloadListener(blockEntityWithoutLevelRenderer);
        event.registerReloadListener(shieldItemRenderer);
    }

    public static IClientItemExtensions blockEntity() {
        return new IClientItemExtensions() {
            @Override
            public UselessBlockEntityWithoutLevelRenderer getCustomRenderer() {
                return blockEntityWithoutLevelRenderer;
            }
        };
    }

    public static IClientItemExtensions shield() {
        return new IClientItemExtensions() {
            @Override
            public UselessShieldItemRenderer getCustomRenderer() {
                return shieldItemRenderer;
            }
        };
    }
}
