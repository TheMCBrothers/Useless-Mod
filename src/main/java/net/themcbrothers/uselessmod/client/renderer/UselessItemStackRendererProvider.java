package net.themcbrothers.uselessmod.client.renderer;

import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;

public class UselessItemStackRendererProvider {
    private static UselessBlockEntityWithoutLevelRenderer blockEntityWithoutLevelRenderer;
    private static UselessShieldItemRenderer shieldItemRenderer;

    public static void initialize(final RegisterClientReloadListenersEvent event) {
        blockEntityWithoutLevelRenderer = new UselessBlockEntityWithoutLevelRenderer();
        shieldItemRenderer = new UselessShieldItemRenderer();

        event.registerReloadListener(blockEntityWithoutLevelRenderer);
        event.registerReloadListener(shieldItemRenderer);
    }

    public static IItemRenderProperties blockEntity() {
        return new IItemRenderProperties() {
            @Override
            public UselessBlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return blockEntityWithoutLevelRenderer;
            }
        };
    }

    public static IItemRenderProperties shield() {
        return new IItemRenderProperties() {
            @Override
            public UselessShieldItemRenderer getItemStackRenderer() {
                return shieldItemRenderer;
            }
        };
    }
}
