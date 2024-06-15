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
import net.minecraft.util.FastColor;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;

public class UselessSheepFurLayer extends RenderLayer<UselessSheep, SheepModel<UselessSheep>> {
    private static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep_fur.png");

    private final SheepFurModel<UselessSheep> model;

    public UselessSheepFurLayer(RenderLayerParent<UselessSheep, SheepModel<UselessSheep>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.model = new SheepFurModel<>(entityModelSet.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, UselessSheep sheep, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!sheep.isSheared()) {
            if (sheep.isInvisible()) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = minecraft.shouldEntityAppearGlowing(sheep);
                if (flag) {
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.prepareMobModel(sheep, pLimbSwing, pLimbSwingAmount, pPartialTick);
                    this.model.setupAnim(sheep, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
                    this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(sheep, 0.0F), -16777216);
                }

            } else {
                // render useless color
                int color = FastColor.ARGB32.color(0xFF, 70, 139, 68);

                coloredCutoutModelCopyLayerRender(
                        this.getParentModel(),
                        this.model,
                        SHEEP_FUR_LOCATION,
                        poseStack,
                        buffer,
                        packedLight,
                        sheep,
                        pLimbSwing,
                        pLimbSwingAmount,
                        pAgeInTicks,
                        pNetHeadYaw,
                        pHeadPitch,
                        pPartialTick,
                        color
                );
            }
        }
    }
}
