package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

public class CoffeeMachineTileEntityRenderer extends TileEntityRenderer<CoffeeMachineTileEntity> {

    public CoffeeMachineTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v, int color) {
        float[] colors = getRGBA(color);
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
                .color(colors[0], colors[1], colors[2], 1.0f)
                .tex(u, v)
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    @Override
    public void render(CoffeeMachineTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        FluidStack fluidStack = tileEntityIn.waterTank.getFluid();
        Direction facing = tileEntityIn.getWorld() != null && tileEntityIn.getPos() != BlockPos.ZERO
                ? tileEntityIn.getBlockState().get(BlockStateProperties.HORIZONTAL_FACING) : Direction.NORTH;

        if (!fluidStack.isEmpty()) {

            ItemStack stack = FluidUtil.getFilledBucket(fluidStack);

            matrixStackIn.push();
            matrixStackIn.translate(.5, 1.5, .5);
            matrixStackIn.rotate(new Quaternion(Vector3f.YP, facing.getHorizontalAngle(), true));
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn,
                    OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.pop();

            TextureAtlasSprite sprite = getStillFluidSprite(fluidStack);
            int color = fluidStack.getFluid().getAttributes().getColor(fluidStack);

            IVertexBuilder vertexBuilder = bufferIn.getBuffer(RenderType.getTranslucent());

            matrixStackIn.push();
            add(vertexBuilder, matrixStackIn, 0f, 1f, 1f, sprite.getMinU(), sprite.getMaxV(), color);
            add(vertexBuilder, matrixStackIn, 1f, 1f, 1f, sprite.getMaxU(), sprite.getMaxV(), color);
            add(vertexBuilder, matrixStackIn, 1f, 1f, 0f, sprite.getMaxU(), sprite.getMinV(), color);
            add(vertexBuilder, matrixStackIn, 0f, 1f, 0f, sprite.getMinU(), sprite.getMinV(), color);
            matrixStackIn.pop();
        }

        ItemStack cupStack = tileEntityIn.coffeeStacks.get(0);
        if (!cupStack.isEmpty()) {
            matrixStackIn.push();
            matrixStackIn.translate(.5, .25, .5);
            matrixStackIn.rotate(new Quaternion(Vector3f.YN, facing.getHorizontalAngle(), true));
            Minecraft.getInstance().getItemRenderer().renderItem(cupStack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn,
                    OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
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

    private static float[] getRGBA(int color) {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        float alpha = ((color >> 24) & 0xFF) / 255F;

        return new float[] {red, green, blue, alpha};
    }

}
