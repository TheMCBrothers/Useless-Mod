package net.themcbrothers.uselessmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessItems;

public class UselessWingsLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    private static final Identifier WINGS_USELESS_LOCATION = UselessMod.id("textures/entity/elytra/useless.png");
    private static final Identifier WINGS_SUPER_USELESS_LOCATION = UselessMod.id("textures/entity/elytra/super_useless.png");

    private final ElytraModel elytraModel;
    private final ElytraModel elytraBabyModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    public UselessWingsLayer(RenderLayerParent<S, M> renderLayerParent, EntityModelSet entityModelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderLayerParent);

        this.elytraModel = new ElytraModel(entityModelSet.bakeLayer(ModelLayers.ELYTRA));
        this.elytraBabyModel = new ElytraModel(entityModelSet.bakeLayer(ModelLayers.ELYTRA_BABY));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, S renderState, float yRot, float xRot) {
        ItemStack itemstack = renderState.chestEquipment;
        Equippable equippable = itemstack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && equippable.assetId().isPresent()) {
            Identifier identifier = getPlayerElytraTexture(renderState);
            ElytraModel elytramodel = renderState.isBaby ? this.elytraBabyModel : this.elytraModel;
            poseStack.pushPose();
            poseStack.translate(0.0F, 0.0F, 0.125F);
            this.equipmentRenderer
                    .renderLayers(
                            EquipmentClientInfo.LayerType.WINGS,
                            equippable.assetId().get(),
                            elytramodel,
                            renderState,
                            itemstack,
                            poseStack,
                            nodeCollector,
                            packedLight,
                            identifier,
                            renderState.outlineColor,
                            0
                    );
            poseStack.popPose();
        }
    }

    private static Identifier getPlayerElytraTexture(HumanoidRenderState renderState) {
        return renderState.chestEquipment.is(UselessItems.USELESS_ELYTRA) ? WINGS_USELESS_LOCATION : WINGS_SUPER_USELESS_LOCATION;
    }
}
