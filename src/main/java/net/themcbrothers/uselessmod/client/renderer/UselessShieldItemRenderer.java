package net.themcbrothers.uselessmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.world.item.UselessShieldItem;

public class UselessShieldItemRenderer extends UselessBlockEntityWithoutLevelRenderer {
    private static final Material SHIELD_USELESS = new Material(InventoryMenu.BLOCK_ATLAS, UselessMod.rl("entity/shield/useless"));
    private static final Material SHIELD_SUPER_USELESS = new Material(InventoryMenu.BLOCK_ATLAS, UselessMod.rl("entity/shield/super_useless"));

    private ShieldModel shieldModel;

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        this.shieldModel = new ShieldModel(this.entityModelSet.bakeLayer(ModelLayers.SHIELD));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext type, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof UselessShieldItem) {
            boolean isUseless = stack.is(ModItems.USELESS_SHIELD.get());

            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);

            Material material = isUseless ? SHIELD_USELESS : SHIELD_SUPER_USELESS;
            TextureAtlasSprite sprite = material.sprite();
            VertexConsumer vertexConsumer = sprite.wrap(ItemRenderer.getFoilBufferDirect(buffer, this.shieldModel.renderType(material.atlasLocation()), true, stack.hasFoil()));

            this.shieldModel.handle().render(poseStack, vertexConsumer, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            this.shieldModel.plate().render(poseStack, vertexConsumer, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

            poseStack.popPose();
        }
    }
}
