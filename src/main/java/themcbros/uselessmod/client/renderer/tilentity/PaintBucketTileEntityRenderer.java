package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import themcbros.uselessmod.helpers.ColorUtils;
import themcbros.uselessmod.tileentity.PaintBucketTileEntity;

/**
 * @author TheMCBrothers
 */
public class PaintBucketTileEntityRenderer extends TileEntityRenderer<PaintBucketTileEntity> {

    private static final float BOTTOM_OFFSET = 1f / 16f;
    private static final float PAINT_HEIGHT = 6f / 16f;
    private static final float START = 5f / 16f;
    private static final float END = 11f / 16f;

    public PaintBucketTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v, int color) {
        float[] colors = ColorUtils.getRGBA(color);
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
                .color(colors[0], colors[1], colors[2], 1.0f)
                .tex(u, v)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    @Override
    public void render(PaintBucketTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        FluidStack fluidStack = tileEntityIn.color.getFluid();

        if (!fluidStack.isEmpty()) {
            TextureAtlasSprite sprite = getStillFluidSprite(fluidStack);
            int color = fluidStack.getFluid().getAttributes().getColor(fluidStack);
            float y = BOTTOM_OFFSET + (((float) fluidStack.getAmount() / (float) FluidAttributes.BUCKET_VOLUME) * PAINT_HEIGHT);

            IVertexBuilder vertexBuilder = bufferIn.getBuffer(RenderType.getTranslucent());

            matrixStackIn.push();
            add(vertexBuilder, matrixStackIn, START, y, END, sprite.getMinU(), sprite.getMaxV(), color);
            add(vertexBuilder, matrixStackIn, END, y, END, sprite.getMaxU(), sprite.getMaxV(), color);
            add(vertexBuilder, matrixStackIn, END, y, START, sprite.getMaxU(), sprite.getMinV(), color);
            add(vertexBuilder, matrixStackIn, START, y, START, sprite.getMinU(), sprite.getMinV(), color);
            matrixStackIn.pop();
        }
    }

    private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
        Minecraft minecraft = Minecraft.getInstance();
        Fluid fluid = fluidStack.getFluid();
        FluidAttributes attributes = fluid.getAttributes();
        ResourceLocation fluidStill = attributes.getStillTexture(fluidStack);
        return minecraft.getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(fluidStill);
    }

}
