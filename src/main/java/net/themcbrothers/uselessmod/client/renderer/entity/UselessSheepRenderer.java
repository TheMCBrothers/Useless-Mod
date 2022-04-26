package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.client.renderer.entity.layers.UselessSheepFurLayer;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;

public class UselessSheepRenderer extends MobRenderer<UselessSheep, SheepModel<UselessSheep>> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_sheep.png");

    public UselessSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new UselessSheepFurLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(UselessSheep sheep) {
        return TEXTURE;
    }
}
