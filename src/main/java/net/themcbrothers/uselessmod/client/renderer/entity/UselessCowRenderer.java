package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.resources.Identifier;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessCow;

public class UselessCowRenderer extends AgeableMobRenderer<UselessCow, CowRenderState, CowModel> {
    private static final Identifier TEXTURE = UselessMod.id("textures/entity/cow/cow_useless.png");

    public UselessCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), new CowModel(context.bakeLayer(ModelLayers.COW_BABY)), 0.7F);
    }

    @Override
    public CowRenderState createRenderState() {
        return new CowRenderState();
    }

    @Override
    public void extractRenderState(UselessCow cow, CowRenderState renderState, float partialTick) {
        super.extractRenderState(cow, renderState, partialTick);
        renderState.variant = cow.getVariant().value();
    }

    @Override
    public Identifier getTextureLocation(CowRenderState renderState) {
        return TEXTURE;
    }
}
