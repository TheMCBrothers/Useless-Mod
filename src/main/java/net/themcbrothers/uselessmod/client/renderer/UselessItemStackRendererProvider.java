package net.themcbrothers.uselessmod.client.renderer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.IItemRenderProperties;

public class UselessItemStackRendererProvider {
    public static IItemRenderProperties useless() {
        return new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return new UselessBlockEntityWithoutLevelRenderer();
            }
        };
    }
}
