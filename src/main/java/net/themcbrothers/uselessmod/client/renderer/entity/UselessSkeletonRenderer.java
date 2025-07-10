package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.monster.UselessSkeleton;

public class UselessSkeletonRenderer extends AbstractSkeletonRenderer<UselessSkeleton, SkeletonRenderState> {
    private static final ResourceLocation SKELETON_LOCATION = UselessMod.rl("textures/entity/useless_skeleton.png");

    public UselessSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonRenderState renderState) {
        return SKELETON_LOCATION;
    }
}
