package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessChicken;

public class UselessChickenRenderer extends MobRenderer<UselessChicken, ChickenModel<UselessChicken>> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_chicken.png");

    public UselessChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(UselessChicken chicken) {
        return TEXTURE;
    }

    @Override
    protected float getBob(UselessChicken chicken, float v) {
        float f = Mth.lerp(v, chicken.oFlap, chicken.flap);
        float f1 = Mth.lerp(v, chicken.oFlapSpeed, chicken.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}
