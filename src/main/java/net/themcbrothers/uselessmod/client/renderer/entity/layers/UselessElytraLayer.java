package net.themcbrothers.uselessmod.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentModel;
import net.minecraft.world.item.equipment.Equippable;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessItems;

import javax.annotation.Nonnull;

public class UselessElytraLayer<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    private static final ResourceLocation WINGS_USELESS_LOCATION = UselessMod.rl("textures/entity/elytra/useless.png");
    private static final ResourceLocation WINGS_SUPER_USELESS_LOCATION = UselessMod.rl("textures/entity/elytra/super_useless.png");

    private final ElytraModel elytraModel;
    private final ElytraModel elytraBabyModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    public UselessElytraLayer(RenderLayerParent<S, M> renderLayerParent, EntityModelSet entityModelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderLayerParent);

        this.elytraModel = new ElytraModel(entityModelSet.bakeLayer(ModelLayers.ELYTRA));
        this.elytraBabyModel = new ElytraModel(entityModelSet.bakeLayer(ModelLayers.ELYTRA_BABY));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Nonnull
    private static ResourceLocation getPlayerElytraTexture(@Nonnull ItemStack stack) {
        // TODO: check if it is a player model
        return stack.getItem() == UselessItems.USELESS_ELYTRA.get() ? WINGS_USELESS_LOCATION : WINGS_SUPER_USELESS_LOCATION;
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int i, S renderState, float f, float v) {
        ItemStack itemstack = renderState.chestItem;
        Equippable equippable = itemstack.get(DataComponents.EQUIPPABLE);
        // TODO: check if it is a USELESS elytra
        if (equippable != null && equippable.model().isPresent()) {
            ResourceLocation resourcelocation = getPlayerElytraTexture(itemstack);
            ElytraModel elytramodel = renderState.isBaby ? this.elytraBabyModel : this.elytraModel;
            ResourceLocation resourcelocation1 = equippable.model().get();
            poseStack.pushPose();
            poseStack.translate(0.0F, 0.0F, 0.125F);
            elytramodel.setupAnim(renderState);
            this.equipmentRenderer.renderLayers(EquipmentModel.LayerType.WINGS, resourcelocation1, elytramodel, itemstack, poseStack, buffer, i, resourcelocation);
            poseStack.popPose();
        }
    }
}
