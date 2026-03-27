package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.layers.SheepWoolUndercoatLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.Identifier;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.renderer.entity.state.UselessSheepRenderState;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;

public class UselessSheepRenderer extends AgeableMobRenderer<UselessSheep, SheepRenderState, SheepModel> {
    private static final Identifier TEXTURE = UselessMod.id("textures/entity/sheep/sheep_useless.png");

    public UselessSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel(context.bakeLayer(ModelLayers.SHEEP)), new SheepModel(context.bakeLayer(ModelLayers.SHEEP_BABY)), 0.7F);
        this.addLayer(new SheepWoolUndercoatLayer(this, context.getModelSet()));
        this.addLayer(new SheepWoolLayer(this, context.getModelSet()));
    }

    @Override
    public SheepRenderState createRenderState() {
        return new UselessSheepRenderState();
    }

    @Override
    public Identifier getTextureLocation(SheepRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(UselessSheep sheep, SheepRenderState renderState, float partialTick) {
        super.extractRenderState(sheep, renderState, partialTick);
        renderState.headEatAngleScale = sheep.getHeadEatAngleScale(partialTick);
        renderState.headEatPositionScale = sheep.getHeadEatPositionScale(partialTick);
        renderState.isSheared = sheep.isSheared();
        renderState.woolColor = sheep.getColor();
    }
}
