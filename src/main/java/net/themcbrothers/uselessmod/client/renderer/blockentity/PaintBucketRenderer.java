package net.themcbrothers.uselessmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintBucketBlockEntity;

public class PaintBucketRenderer implements BlockEntityRenderer<PaintBucketBlockEntity> {
    private static final float BOTTOM_OFFSET = 1f / 16f;
    private static final float PAINT_HEIGHT = 6f / 16f;
    private static final float START = 5f / 16f;
    private static final float END = 11f / 16f;

    public PaintBucketRenderer(BlockEntityRendererProvider.Context context) {
    }

    private void add(VertexConsumer renderer, PoseStack stack, float x, float y, float z, float u, float v, int color) {
        int red = FastColor.ARGB32.red(color);
        int green = FastColor.ARGB32.green(color);
        int blue = FastColor.ARGB32.blue(color);

        renderer.vertex(stack.last().pose(), x, y, z)
                .color(red, green, blue, 255)
                .uv(u, v)
                .uv2(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    @Override
    public void render(PaintBucketBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (blockEntity.colorTank.isEmpty()) {
            return;
        }

        FluidStack fluidStack = blockEntity.colorTank.getFluid();

        TextureAtlasSprite sprite = getStillFluidSprite(fluidStack);
        int color = IClientFluidTypeExtensions.of(fluidStack.getFluid()).getTintColor(fluidStack);
        float y = BOTTOM_OFFSET + (((float) fluidStack.getAmount() / (float) FluidType.BUCKET_VOLUME) * PAINT_HEIGHT);

        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.translucent());

        poseStack.pushPose();
        add(vertexBuilder, poseStack, START, y, END, sprite.getU0(), sprite.getV1(), color);
        add(vertexBuilder, poseStack, END, y, END, sprite.getU1(), sprite.getV1(), color);
        add(vertexBuilder, poseStack, END, y, START, sprite.getU1(), sprite.getV0(), color);
        add(vertexBuilder, poseStack, START, y, START, sprite.getU0(), sprite.getV0(), color);
        poseStack.popPose();
    }

    private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
        Minecraft minecraft = Minecraft.getInstance();
        Fluid fluid = fluidStack.getFluid();
        ResourceLocation fluidStill = IClientFluidTypeExtensions.of(fluid).getStillTexture();
        return minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
    }
}
