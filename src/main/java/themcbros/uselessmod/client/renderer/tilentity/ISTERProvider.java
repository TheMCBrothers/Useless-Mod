package themcbros.uselessmod.client.renderer.tilentity;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;

import java.util.concurrent.Callable;

public class ISTERProvider {

    public static Callable<ItemStackTileEntityRenderer> useless() {
        return UselessItemStackTileEntityRenderer::new;
    }

}
