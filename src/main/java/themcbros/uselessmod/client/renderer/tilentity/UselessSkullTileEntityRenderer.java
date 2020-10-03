package themcbros.uselessmod.client.renderer.tilentity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.block.UselessSkullBlock;
import themcbros.uselessmod.tileentity.UselessSkullTileEntity;

import javax.annotation.Nullable;
import java.util.Map;

public class UselessSkullTileEntityRenderer extends TileEntityRenderer<UselessSkullTileEntity> {
    private static final Map<SkullBlock.ISkullType, GenericHeadModel> MODELS = Util.make(Maps.newHashMap(), (map) -> {
        GenericHeadModel genericHeadModel = new GenericHeadModel(0, 0, 64, 32);
        map.put(UselessSkullBlock.Types.USELESS_SKELETON, genericHeadModel);
    });
    private static final Map<SkullBlock.ISkullType, ResourceLocation> SKINS = Util.make(Maps.newHashMap(), (map) -> {
        map.put(UselessSkullBlock.Types.USELESS_SKELETON, UselessMod.rl("textures/entity/useless_skeleton.png"));
    });

    public UselessSkullTileEntityRenderer(TileEntityRendererDispatcher p_i226015_1_) {
        super(p_i226015_1_);
    }

    @Override
    public void render(UselessSkullTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = tileEntityIn.getAnimationProgress(partialTicks);
        BlockState blockstate = tileEntityIn.getBlockState();
        boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
        Direction direction = flag ? blockstate.get(WallSkullBlock.FACING) : null;
        float f1 = 22.5F * (float)(flag ? (2 + direction.getHorizontalIndex()) * 4 : blockstate.get(SkullBlock.ROTATION));
        render(direction, f1, ((AbstractSkullBlock) blockstate.getBlock()).getSkullType(), f, matrixStackIn, bufferIn, combinedLightIn);
    }

    public static void render(@Nullable Direction directionIn, float p_228879_1_, SkullBlock.ISkullType skullType, float animationProgress, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLight) {
        GenericHeadModel genericheadmodel = MODELS.get(skullType);
        matrixStackIn.push();
        if (directionIn == null) {
            matrixStackIn.translate(0.5D, 0.0D, 0.5D);
        } else {
            switch (directionIn) {
                case NORTH:
                    matrixStackIn.translate(0.5D, 0.25D, 0.74F);
                    break;
                case SOUTH:
                    matrixStackIn.translate(0.5D, 0.25D, 0.26F);
                    break;
                case WEST:
                    matrixStackIn.translate(0.74F, 0.25D, 0.5D);
                    break;
                case EAST:
                default:
                    matrixStackIn.translate(0.26F, 0.25D, 0.5D);
            }
        }

        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(getRenderType(skullType));
        genericheadmodel.func_225603_a_(animationProgress, p_228879_1_, 0.0F);
        genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }

    private static RenderType getRenderType(SkullBlock.ISkullType skullType) {
        ResourceLocation resourcelocation = SKINS.get(skullType);
        return RenderType.getEntityCutoutNoCull(resourcelocation);
    }
}
