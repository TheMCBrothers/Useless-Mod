package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.themcbrothers.uselessmod.UselessMod;

import javax.annotation.Nonnull;

public class UselessSkeletonRenderer extends SkeletonRenderer {
    private static final ResourceLocation SKELETON_LOCATION = UselessMod.rl("textures/entity/useless_skeleton.png");

    public UselessSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull AbstractSkeleton skeleton) {
        return SKELETON_LOCATION;
    }
}
