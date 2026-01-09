package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessChicken;

public class UselessChickenRenderer extends AgeableMobRenderer<UselessChicken, ChickenRenderState, ChickenModel> {
    private static final Identifier TEXTURE = UselessMod.id("textures/entity/useless_chicken.png");

    public UselessChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN_BABY)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(ChickenRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public void extractRenderState(UselessChicken chicken, ChickenRenderState renderState, float partialTick) {
        super.extractRenderState(chicken, renderState, partialTick);
        renderState.flap = Mth.lerp(partialTick, chicken.oFlap, chicken.flap);
        renderState.flapSpeed = Mth.lerp(partialTick, chicken.oFlapSpeed, chicken.flapSpeed);
        renderState.variant = chicken.getVariant().value();
    }
}
