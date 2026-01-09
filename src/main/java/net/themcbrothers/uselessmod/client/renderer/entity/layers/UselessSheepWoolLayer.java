package net.themcbrothers.uselessmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.animal.sheep.SheepFurModel;
import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class UselessSheepWoolLayer extends RenderLayer<SheepRenderState, SheepModel> {
    private static final Identifier SHEEP_WOOL_LOCATION = Identifier.withDefaultNamespace("textures/entity/sheep/sheep_wool.png");
    private final EntityModel<SheepRenderState> adultModel;
    private final EntityModel<SheepRenderState> babyModel;

    public UselessSheepWoolLayer(RenderLayerParent<SheepRenderState, SheepModel> sheepRenderer, EntityModelSet entityModelSet) {
        super(sheepRenderer);
        this.adultModel = new SheepFurModel(entityModelSet.bakeLayer(ModelLayers.SHEEP_WOOL));
        this.babyModel = new SheepFurModel(entityModelSet.bakeLayer(ModelLayers.SHEEP_BABY_WOOL));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, SheepRenderState sheep, float yRot, float xRot) {
        if (!sheep.isSheared) {
            EntityModel<SheepRenderState> entitymodel = sheep.isBaby ? this.babyModel : this.adultModel;
            if (sheep.isInvisible) {
                if (sheep.appearsGlowing()) {
                    nodeCollector.submitModel(
                            entitymodel,
                            sheep,
                            poseStack,
                            RenderTypes.outline(SHEEP_WOOL_LOCATION),
                            packedLight,
                            LivingEntityRenderer.getOverlayCoords(sheep, 0.0F),
                            -16777216,
                            null,
                            sheep.outlineColor,
                            null
                    );
                }
            } else {
                int color = ARGB.color(0xFF, 70, 139, 68);
                coloredCutoutModelCopyLayerRender(entitymodel, SHEEP_WOOL_LOCATION, poseStack, nodeCollector, packedLight, sheep, color, 0);
            }
        }
    }
}
