package net.themcbrothers.uselessmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.fluids.IFluidTank;
import net.themcbrothers.lib.client.model.fluid.FluidCuboid;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import org.joml.Vector3f;

import static net.themcbrothers.lib.client.model.fluid.FluidCuboid.DEFAULT_FACES;

public class CoffeeMachineRenderer implements BlockEntityRenderer<CoffeeMachineBlockEntity, BlockEntityRenderState> {
    public CoffeeMachineRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(CoffeeMachineBlockEntity coffeeMachine, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Direction facing = Direction.NORTH;

        if (coffeeMachine.getLevel() != null && coffeeMachine.getBlockPos() != BlockPos.ZERO) {
            facing = coffeeMachine.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        }

        renderFluidTanks(coffeeMachine, poseStack, buffer, packedLight, facing);
        renderCupItem(coffeeMachine, poseStack, buffer, packedLight, packedOverlay, facing);
    }

    private static void renderFluidTanks(CoffeeMachineBlockEntity coffeeMachine, PoseStack poseStack, MultiBufferSource buffer, int packedLight, Direction facing) {
        final IFluidTank waterTank = coffeeMachine.tankHandler.getWaterTank();
        final IFluidTank milkTank = coffeeMachine.tankHandler.getMilkTank();

        if (!waterTank.getFluid().isEmpty()) {
            final FluidCuboid waterCuboid = switch (facing) {
                case SOUTH -> new FluidCuboid(new Vector3f(8.01F, 0.01F, 1.01F),
                        new Vector3f(10.99F, 9.99F, 2.99F), DEFAULT_FACES);
                case WEST -> new FluidCuboid(new Vector3f(13.01F, 0.01F, 8.01F),
                        new Vector3f(14.99F, 9.99F, 10.99F), DEFAULT_FACES);
                case EAST -> new FluidCuboid(new Vector3f(1.01F, 0.01F, 5.01F),
                        new Vector3f(2.99F, 9.99F, 7.99F), DEFAULT_FACES);
                default -> new FluidCuboid(new Vector3f(5.01F, 0.01F, 13.01F),
                        new Vector3f(7.99F, 9.99F, 14.99F), DEFAULT_FACES);
            };

//            RenderUtils.renderFluidTank(poseStack, buffer, waterCuboid, waterTank, packedLight);
        }
        if (!milkTank.getFluid().isEmpty()) {
            final FluidCuboid milkCuboid = switch (facing) {
                case SOUTH -> new FluidCuboid(new Vector3f(5.01F, 0.01F, 1.01F),
                        new Vector3f(7.99F, 9.99F, 2.99F), DEFAULT_FACES);
                case WEST -> new FluidCuboid(new Vector3f(13.01F, 0.01F, 5.01F),
                        new Vector3f(14.99F, 9.99F, 7.99F), DEFAULT_FACES);
                case EAST -> new FluidCuboid(new Vector3f(1.01F, 0.01F, 8.01F),
                        new Vector3f(2.99F, 9.99F, 10.99F), DEFAULT_FACES);
                default -> new FluidCuboid(new Vector3f(8.01F, 0.01F, 13.01F),
                        new Vector3f(10.99F, 9.99F, 14.99F), DEFAULT_FACES);
            };

//            RenderUtils.renderFluidTank(poseStack, buffer, milkCuboid, milkTank, packedLight);
        }
    }

    private static void renderCupItem(CoffeeMachineBlockEntity coffeeMachine, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, Direction facing) {
        final ItemStack resultStack = coffeeMachine.getItem(3);
        final ItemStack renderStack = resultStack.isEmpty() ? coffeeMachine.getItem(0) : resultStack;
        if (!renderStack.isEmpty()) {
            poseStack.pushPose();

            Vector3f translation = getTranslation(facing);
            poseStack.translate(translation.x(), translation.y(), translation.z());
            poseStack.scale(.6F, .6F, .6F);
            poseStack.mulPose(facing.getCounterClockWise().getRotation());

//            ItemRenderer.renderItem(ItemDisplayContext.FIXED, poseStack, buffer, packedLight, packedOverlay, new int[0], null, RenderTypes.entitySolid(TextureAtlas.LOCATION_BLOCKS), ItemStackRenderState.FoilType.NONE);

            poseStack.popPose();
        }
    }

    private static Vector3f getTranslation(Direction facing) {
        final float yOffset = 1F / 16F;
        final float cupOffset1 = 8F / 16F;
        final float cupOffset2 = 10F / 16F;

        return switch (facing) {
            case SOUTH -> new Vector3f(cupOffset1, yOffset, cupOffset2);
            case WEST -> new Vector3f(1F - cupOffset2, yOffset, cupOffset1);
            case EAST -> new Vector3f(cupOffset2, yOffset, cupOffset1);
            default -> new Vector3f(cupOffset1, yOffset, 1F - cupOffset2);
        };
    }

    @Override
    public BlockEntityRenderState createRenderState() {
        return new BlockEntityRenderState();
    }

    @Override
    public void submit(BlockEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        // TODO: render coffee machine
    }
}
