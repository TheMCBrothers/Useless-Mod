package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessPig;

public class UselessPigRenderer extends AgeableMobRenderer<UselessPig, PigRenderState, PigModel> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_pig.png");

    public UselessPigRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), new PigModel(context.bakeLayer(ModelLayers.PIG_BABY)), 0.7F);
        this.addLayer(
                new SaddleLayer<>(
                        this,
                        new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)),
                        new PigModel(context.bakeLayer(ModelLayers.PIG_BABY_SADDLE)),
                        ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")
                )
        );
    }

    @Override
    public PigRenderState createRenderState() {
        return new PigRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(PigRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(UselessPig pig, PigRenderState renderState, float partialTick) {
        super.extractRenderState(pig, renderState, partialTick);
        renderState.isSaddled = pig.isSaddled();
    }
}
