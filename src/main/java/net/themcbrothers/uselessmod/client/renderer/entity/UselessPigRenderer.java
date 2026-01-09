package net.themcbrothers.uselessmod.client.renderer.entity;

import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.entity.animal.UselessPig;

public class UselessPigRenderer extends AgeableMobRenderer<UselessPig, PigRenderState, PigModel> {
    private static final Identifier TEXTURE = UselessMod.id("textures/entity/useless_pig.png");

    public UselessPigRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), new PigModel(context.bakeLayer(ModelLayers.PIG_BABY)), 0.7F);
        this.addLayer(
                new SimpleEquipmentLayer<>(
                        this,
                        context.getEquipmentRenderer(),
                        EquipmentClientInfo.LayerType.PIG_SADDLE,
                        pigRenderState -> pigRenderState.saddle,
                        new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)),
                        new PigModel(context.bakeLayer(ModelLayers.PIG_BABY_SADDLE))
                )
        );
    }

    @Override
    public PigRenderState createRenderState() {
        return new PigRenderState();
    }

    @Override
    public Identifier getTextureLocation(PigRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(UselessPig pig, PigRenderState renderState, float partialTick) {
        super.extractRenderState(pig, renderState, partialTick);
        renderState.saddle = pig.getItemBySlot(EquipmentSlot.SADDLE).copy();
        renderState.variant = pig.getVariant().value();
    }
}
