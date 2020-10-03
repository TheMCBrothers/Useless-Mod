package themcbros.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;

public class UselessSkeletonRenderer extends SkeletonRenderer {
    private static final ResourceLocation SKELETON_TEXTURES = UselessMod.rl("textures/entity/useless_skeleton.png");

    public UselessSkeletonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractSkeletonEntity entity) {
        return SKELETON_TEXTURES;
    }
}
