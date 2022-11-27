package net.themcbrothers.uselessmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.level.block.entity.UselessBedBlockEntity;

public class UselessBedRenderer implements BlockEntityRenderer<UselessBedBlockEntity> {
    private final ModelPart headRoot;
    private final ModelPart footRoot;

    public UselessBedRenderer(BlockEntityRendererProvider.Context context) {
        this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
        this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
    }

    @Override
    public void render(UselessBedBlockEntity bedBlockEntity, float idk, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Material material = new Material(Sheets.BED_SHEET, UselessMod.rl("entity/bed/useless"));
        Level level = bedBlockEntity.getLevel();
        if (level != null) {
            BlockState blockstate = bedBlockEntity.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<? extends BedBlockEntity> neighborCombineResult = DoubleBlockCombiner.combineWithNeigbour(BlockEntityType.BED, BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, level, bedBlockEntity.getBlockPos(), (levelAccessor, pos) -> false);
            int i = neighborCombineResult.apply(new BrightnessCombiner<>()).get(combinedLight);
            this.renderPiece(poseStack, buffer, blockstate.getValue(BedBlock.PART) == BedPart.HEAD ? this.headRoot : this.footRoot, blockstate.getValue(BedBlock.FACING), material, i, combinedOverlay, false);
        } else {
            this.renderPiece(poseStack, buffer, this.headRoot, Direction.SOUTH, material, combinedLight, combinedOverlay, false);
            this.renderPiece(poseStack, buffer, this.footRoot, Direction.SOUTH, material, combinedLight, combinedOverlay, true);
        }
    }

    private void renderPiece(PoseStack poseStack, MultiBufferSource buffer, ModelPart modelPart, Direction direction, Material material, int combinedLight, int combinedOverlay, boolean isFoot) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5625D, isFoot ? -1.0D : 0.0D);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F + direction.toYRot()));
        poseStack.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entitySolid);
        modelPart.render(poseStack, vertexconsumer, combinedLight, combinedOverlay);
        poseStack.popPose();
    }
}
