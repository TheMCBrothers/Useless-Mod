package net.themcbrothers.uselessmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.IFluidTank;
import net.themcbrothers.lib.client.model.fluid.FluidCuboid;
import net.themcbrothers.lib.util.RenderUtils;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

import static net.themcbrothers.lib.client.model.fluid.FluidCuboid.DEFAULT_FACES;

public class CoffeeMachineRenderer implements BlockEntityRenderer<CoffeeMachineBlockEntity> {
    public CoffeeMachineRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CoffeeMachineBlockEntity coffeeMachine, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Direction facing = Direction.NORTH;
        if (coffeeMachine.getLevel() != null && coffeeMachine.getBlockPos() != BlockPos.ZERO) {
            facing = coffeeMachine.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        }

        final IFluidTank waterTank = coffeeMachine.tankHandler.getWaterTank();
        final IFluidTank milkTank = coffeeMachine.tankHandler.getMilkTank();

        if (!waterTank.getFluid().isEmpty()) {
            final FluidCuboid waterCuboid =
                    facing == Direction.SOUTH ? new FluidCuboid(new Vector3f(8.01F, 0.01F, 1.01F),
                            new Vector3f(10.99F, 9.99F, 2.99F), DEFAULT_FACES) :
                            facing == Direction.EAST ? new FluidCuboid(new Vector3f(1.01F, 0.01F, 5.01F),
                                    new Vector3f(2.99F, 9.99F, 7.99F), DEFAULT_FACES) :
                                    facing == Direction.WEST ? new FluidCuboid(new Vector3f(13.01F, 0.01F, 8.01F),
                                            new Vector3f(14.99F, 9.99F, 10.99F), DEFAULT_FACES) :
                                            new FluidCuboid(new Vector3f(5.01F, 0.01F, 13.01F),
                                                    new Vector3f(7.99F, 9.99F, 14.99F), DEFAULT_FACES);
            RenderUtils.renderFluidTank(poseStack, buffer, waterCuboid, waterTank, packedLight);
        }
        if (!milkTank.getFluid().isEmpty()) {
            final FluidCuboid milkCuboid =
                    facing == Direction.SOUTH ? new FluidCuboid(new Vector3f(5.01F, 0.01F, 1.01F),
                            new Vector3f(7.99F, 9.99F, 2.99F), DEFAULT_FACES) :
                            facing == Direction.EAST ? new FluidCuboid(new Vector3f(1.01F, 0.01F, 8.01F),
                                    new Vector3f(2.99F, 9.99F, 10.99F), DEFAULT_FACES) :
                                    facing == Direction.WEST ? new FluidCuboid(new Vector3f(13.01F, 0.01F, 5.01F),
                                            new Vector3f(14.99F, 9.99F, 7.99F), DEFAULT_FACES) :
                                            new FluidCuboid(new Vector3f(8.01F, 0.01F, 13.01F),
                                                    new Vector3f(10.99F, 9.99F, 14.99F), DEFAULT_FACES);
            RenderUtils.renderFluidTank(poseStack, buffer, milkCuboid, milkTank, packedLight);
        }

        final ItemStack resultStack = coffeeMachine.getItem(3);
        final ItemStack renderStack = resultStack.isEmpty() ? coffeeMachine.getItem(0) : resultStack;
        if (!renderStack.isEmpty()) {
            poseStack.pushPose();

            final float yOffset = 1F / 16F;
            final float cupOffset1 = 8F / 16F;
            final float cupOffset2 = 10F / 16F;

            Vector3f translation = facing == Direction.SOUTH ? new Vector3f(cupOffset1, yOffset, cupOffset2) :
                    facing == Direction.EAST ? new Vector3f(cupOffset2, yOffset, cupOffset1) :
                            facing == Direction.WEST ? new Vector3f(1F - cupOffset2, yOffset, cupOffset1) :
                                    new Vector3f(cupOffset1, yOffset, 1F - cupOffset2);
            poseStack.translate(translation.x(), translation.y(), translation.z());
            poseStack.scale(.6F, .6F, .6F);
            poseStack.mulPose(facing.getCounterClockWise().getRotation());

            final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel model = itemRenderer.getModel(renderStack, null, null, -1);
            itemRenderer.render(renderStack, ItemTransforms.TransformType.FIXED, false,
                    poseStack, buffer, packedLight, packedOverlay, model);

            poseStack.popPose();
        }
    }
}
