package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.tileentity.WallClosetTileEntity;

public class WallClosetTileEntityRenderer extends TileEntityRenderer<WallClosetTileEntity> {

    public WallClosetTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(WallClosetTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        // TODO: render items inside

        IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (itemHandler == null) return;

        matrixStack.push();

        matrixStack.translate(.5, 1.5, .5);
        ItemStack itemStack = itemHandler.getStackInSlot(0);
        if (!itemStack.isEmpty())
            Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.GROUND, combinedLight, combinedOverlay, matrixStack, buffer);

        matrixStack.pop();

    }
}
