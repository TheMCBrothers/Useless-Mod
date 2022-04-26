package net.themcbrothers.uselessmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;

public class UselessSheepFurLayer extends RenderLayer<UselessSheep, SheepModel<UselessSheep>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = new ResourceLocation("textures/entity/sheep/sheep_fur.png");

    private final SheepFurModel<UselessSheep> model;

    public UselessSheepFurLayer(RenderLayerParent<UselessSheep, SheepModel<UselessSheep>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new SheepFurModel<>(entityModelSet.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    @Override
    public void render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, UselessSheep sheep, float p_117425_, float p_117426_, float p_117427_, float p_117428_, float p_117429_, float p_117430_) {
        if (!sheep.isSheared()) {
            if (sheep.isInvisible()) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = minecraft.shouldEntityAppearGlowing(sheep);
                if (flag) {
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.prepareMobModel(sheep, p_117425_, p_117426_, p_117427_);
                    this.model.setupAnim(sheep, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_);
                    VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
                    this.model.renderToBuffer(p_117421_, vertexconsumer, p_117423_, LivingEntityRenderer.getOverlayCoords(sheep, 0.0F), 0.0F, 0.0F, 0.0F, 1.0F);
                }

            } else {
                // render useless color
                float f = 70f / 255f;
                float f1 = 139f / 255f;
                float f2 = 68f / 255f;

                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SHEEP_FUR_LOCATION, p_117421_, p_117422_, p_117423_, sheep, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_, p_117427_, f, f1, f2);
            }
        }
    }
}
