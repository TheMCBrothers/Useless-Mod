package tk.themcbros.uselessmod.tileentity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.*;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.themcbros.uselessmod.tileentity.UselessSignTileEntity;

import java.util.List;

public class UselessSignTileEntityRenderer extends TileEntityRenderer<UselessSignTileEntity> {
    /** The ModelSign instance for use in this renderer */
    private final SignTileEntityRenderer.SignModel model = new SignTileEntityRenderer.SignModel();

    public UselessSignTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public void render(UselessSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockState blockstate = tileEntityIn.getBlockState();
        matrixStackIn.push();
        float f = 0.6666667F;
        if (blockstate.getBlock() instanceof StandingSignBlock) {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f1 = -((float)(blockstate.get(StandingSignBlock.ROTATION) * 360) / 16.0F);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
            this.model.signStick.showModel = true;
        } else {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f4 = -blockstate.get(WallSignBlock.FACING).getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f4));
            matrixStackIn.translate(0.0D, -0.3125D, -0.4375D);
            this.model.signStick.showModel = false;
        }

        matrixStackIn.push();
        matrixStackIn.scale(f, -f, -f);
        Material material = getMaterial(blockstate.getBlock());
        IVertexBuilder ivertexbuilder = material.getBuffer(bufferIn, this.model::getRenderType);
        this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        float f2 = 0.010416667F;
        matrixStackIn.translate(0.0D, (double)0.33333334F, (double)0.046666667F);
        matrixStackIn.scale(f2, -f2, f2);
        int i = tileEntityIn.getTextColor().getTextColor();
        double d0 = 0.4D;
        int j = (int)((double) NativeImage.getRed(i) * d0);
        int k = (int)((double)NativeImage.getGreen(i) * d0);
        int l = (int)((double)NativeImage.getBlue(i) * d0);
        int i1 = NativeImage.getCombined(0, l, k, j);

        for(int j1 = 0; j1 < 4; ++j1) {
            String s = tileEntityIn.getRenderText(j1, (p_212491_1_) -> {
                List<ITextComponent> list = RenderComponentsUtil.splitText(p_212491_1_, 90, fontrenderer, false, true);
                return list.isEmpty() ? "" : list.get(0).getFormattedText();
            });
            if (s != null) {
                float f3 = (float)(-fontrenderer.getStringWidth(s) / 2);
                fontrenderer.renderString(s, f3, (float)(j1 * 10 - tileEntityIn.signText.length * 5), i1, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
            }
        }

        matrixStackIn.pop();
    }

    private static Material getMaterial(Block blockIn) {
        WoodType woodtype;
        if (blockIn instanceof AbstractSignBlock) {
            woodtype = ((AbstractSignBlock)blockIn).getWoodType();
        } else {
            woodtype = WoodType.OAK;
        }

        return getSignMaterial(woodtype);
    }

    private static Material getSignMaterial(WoodType p_228773_0_) {
        return new Material(Atlases.SIGN_ATLAS, new ResourceLocation("entity/signs/" + p_228773_0_.getName()));
    }
}
