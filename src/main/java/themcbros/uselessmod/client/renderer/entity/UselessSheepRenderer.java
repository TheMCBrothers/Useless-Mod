package themcbros.uselessmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.util.ResourceLocation;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.client.renderer.entity.layer.UselessWoolLayerRenderer;
import themcbros.uselessmod.entity.UselessSheepEntity;

public class UselessSheepRenderer extends MobRenderer<UselessSheepEntity, SheepModel<UselessSheepEntity>> {

    private static final ResourceLocation TEXTURE = UselessMod.rl("textures/entity/useless_sheep.png");

    public UselessSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SheepModel<>(), 0.5F);
        this.addLayer(new UselessWoolLayerRenderer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(UselessSheepEntity entity) {
        return TEXTURE;
    }

}
