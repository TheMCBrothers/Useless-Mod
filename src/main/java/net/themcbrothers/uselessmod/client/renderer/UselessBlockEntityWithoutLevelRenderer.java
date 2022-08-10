package net.themcbrothers.uselessmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.themcbrothers.uselessmod.init.ModBlocks;

public class UselessBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {
    public UselessBlockEntityWithoutLevelRenderer() {
        super(null, null);
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType type, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ModBlocks.COFFEE_MACHINE.get().defaultBlockState(),
                poseStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);
    }
}
