package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessCow;

public class UselessCowRenderer extends MobRenderer<UselessCow, CowModel<UselessCow>> {
    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_cow.png");

    public UselessCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(UselessCow cow) {
        return TEXTURE;
    }
}
