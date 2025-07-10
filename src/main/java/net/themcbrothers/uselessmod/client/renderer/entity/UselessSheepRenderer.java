package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.renderer.entity.layers.UselessSheepWoolLayer;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;

public class UselessSheepRenderer extends AgeableMobRenderer<UselessSheep, SheepRenderState, SheepModel> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_sheep.png");

    public UselessSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel(context.bakeLayer(ModelLayers.SHEEP)), new SheepModel(context.bakeLayer(ModelLayers.SHEEP_BABY)), 0.7F);
        this.addLayer(new UselessSheepWoolLayer(this, context.getModelSet()));
    }

    @Override
    public SheepRenderState createRenderState() {
        return new SheepRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(SheepRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(UselessSheep sheep, SheepRenderState renderState, float partialTick) {
        super.extractRenderState(sheep, renderState, partialTick);
        renderState.headEatAngleScale = sheep.getHeadEatAngleScale(partialTick);
        renderState.headEatPositionScale = sheep.getHeadEatPositionScale(partialTick);
        renderState.isSheared = sheep.isSheared();
        renderState.woolColor = sheep.getColor();
        renderState.id = sheep.getId();
    }
}
