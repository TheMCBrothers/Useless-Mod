package themcbros.uselessmod.client.renderer.tilentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import slimeknights.mantle.client.model.fluid.FluidCuboid;
import themcbros.uselessmod.client.renderer.RenderUtils;
import themcbros.uselessmod.tileentity.CoffeeMachineTileEntity;

import java.util.EnumMap;
import java.util.Map;

public class CoffeeMachineTileEntityRenderer extends TileEntityRenderer<CoffeeMachineTileEntity> {

    protected static final Map<Direction, FluidCuboid.FluidFace> DEFAULT_FACES;
    static {
        DEFAULT_FACES = new EnumMap<>(Direction.class);
        for (Direction direction : Direction.values()) {
            DEFAULT_FACES.put(direction, FluidCuboid.FluidFace.NORMAL);
        }

    }

    public CoffeeMachineTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CoffeeMachineTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        final Direction facing = tileEntityIn.getWorld() != null && tileEntityIn.getPos() != BlockPos.ZERO
                ? tileEntityIn.getBlockState().get(BlockStateProperties.HORIZONTAL_FACING) : Direction.NORTH;

        final FluidTank waterTank = tileEntityIn.tankHandler.getWaterTank();
        final FluidTank milkTank = tileEntityIn.tankHandler.getMilkTank();

        if (!waterTank.isEmpty()) {
            final FluidCuboid waterCuboid =
                    facing == Direction.SOUTH ? new FluidCuboid(new Vector3f(8.01F, 0.01F, 1.01F),
                            new Vector3f(10.99F, 9.99F, 2.99F), DEFAULT_FACES) :
                    facing == Direction.EAST ? new FluidCuboid(new Vector3f(1.01F, 0.01F, 5.01F),
                            new Vector3f(2.99F, 9.99F, 7.99F), DEFAULT_FACES) :
                    facing == Direction.WEST ? new FluidCuboid(new Vector3f(13.01F, 0.01F, 8.01F),
                            new Vector3f(14.99F, 9.99F, 10.99F), DEFAULT_FACES) :
                    new FluidCuboid(new Vector3f(5.01F, 0.01F, 13.01F),
                            new Vector3f(7.99F, 9.99F, 14.99F), DEFAULT_FACES);
            RenderUtils.renderFluidTank(matrixStackIn, bufferIn, waterCuboid, waterTank, combinedLightIn);
        }
        if (!milkTank.isEmpty()) {
            final FluidCuboid milkCuboid =
                    facing == Direction.SOUTH ? new FluidCuboid(new Vector3f(5.01F, 0.01F, 1.01F),
                            new Vector3f(7.99F, 9.99F, 2.99F), DEFAULT_FACES) :
                    facing == Direction.EAST ? new FluidCuboid(new Vector3f(1.01F, 0.01F, 8.01F),
                            new Vector3f(2.99F, 9.99F, 10.99F), DEFAULT_FACES) :
                    facing == Direction.WEST ? new FluidCuboid(new Vector3f(13.01F, 0.01F, 5.01F),
                            new Vector3f(14.99F, 9.99F, 7.99F), DEFAULT_FACES) :
                    new FluidCuboid(new Vector3f(8.01F, 0.01F, 13.01F),
                                    new Vector3f(10.99F, 9.99F, 14.99F), DEFAULT_FACES);
            RenderUtils.renderFluidTank(matrixStackIn, bufferIn, milkCuboid, milkTank, combinedLightIn);
        }

        final ItemStack cupStack = tileEntityIn.getStackInSlot(3).isEmpty() ? tileEntityIn.getStackInSlot(0)
                : tileEntityIn.getStackInSlot(3);
        if (!cupStack.isEmpty()) {
            matrixStackIn.push();
            Vector3f translation = facing == Direction.SOUTH ? new Vector3f(.5F, .34F, .65F) :
                    facing == Direction.EAST ? new Vector3f(.65F, .34F,  .5F) :
                            facing == Direction.WEST ? new Vector3f(.35F, .34F, .5F) :
                                    new Vector3f(.5F, .34F, .35F);
            matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
            matrixStackIn.scale(.75F, .75F, .75F);
            matrixStackIn.rotate(new Quaternion(Vector3f.YN, facing.getHorizontalAngle(), true));
            Minecraft.getInstance().getItemRenderer().renderItem(cupStack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn,
                    OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }
    }

}
