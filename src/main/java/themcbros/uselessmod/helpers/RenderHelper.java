package themcbros.uselessmod.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.vector.Quaternion;

public class RenderHelper {

    public static void applyBlockAngle(MatrixStack matrixStack, BlockState state) {
        applyBlockAngle(matrixStack, state, 180f);
    }

    public static void applyBlockAngle(MatrixStack matrixStack, BlockState state, float angleOffset) {
        assert state.hasProperty(BlockStateProperties.HORIZONTAL_FACING);
        float angle = state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle();
        matrixStack.translate(0.5, 0, 0.5);
        matrixStack.rotate(new Quaternion(0f, angleOffset - angle, 0f, true));
    }

    public static void renderItem(ItemStack itemStack, int combinedLight, MatrixStack matrixStack, IRenderTypeBuffer buffer) {
        Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED, combinedLight, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
    }

}
