package themcbros.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;

public class UselessChickenRenderer extends ChickenRenderer {

    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_chicken.png");

    public UselessChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(ChickenEntity chickenEntity) {
        return TEXTURE;
    }
}
