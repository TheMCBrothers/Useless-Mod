package themcbros.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;

public class UselessCowRenderer extends CowRenderer {

    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_cow.png");

    public UselessCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(CowEntity entity) {
        return TEXTURE;
    }

}
