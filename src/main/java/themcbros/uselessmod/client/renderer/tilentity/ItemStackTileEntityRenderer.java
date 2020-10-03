package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.function.Supplier;

public class ItemStackTileEntityRenderer extends net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer {

    private final Supplier<TileEntity> tileEntitySupplier;

    public ItemStackTileEntityRenderer(Supplier<TileEntity> tileEntitySupplier) {
        this.tileEntitySupplier = tileEntitySupplier;
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        TileEntityRendererDispatcher.instance.renderItem(tileEntitySupplier.get(), matrixStack, buffer, combinedLight, combinedOverlay);
    }
}
