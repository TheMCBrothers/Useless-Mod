package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessCow;

public class UselessCowRenderer extends AgeableMobRenderer<UselessCow, LivingEntityRenderState, CowModel> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_cow.png");

    public UselessCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), new CowModel(context.bakeLayer(ModelLayers.COW_BABY)), 0.7F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public void extractRenderState(UselessCow cow, LivingEntityRenderState renderState, float partialTick) {
        super.extractRenderState(cow, renderState, partialTick);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {
        return TEXTURE;
    }
}
