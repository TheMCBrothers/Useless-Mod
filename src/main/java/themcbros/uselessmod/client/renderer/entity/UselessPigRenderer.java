package themcbros.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;

public class UselessPigRenderer extends PigRenderer {

    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_pig.png");

    public UselessPigRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(PigEntity entity) {
        return TEXTURE;
    }

}
