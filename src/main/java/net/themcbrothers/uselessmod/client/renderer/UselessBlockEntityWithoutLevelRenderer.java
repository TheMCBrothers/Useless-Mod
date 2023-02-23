package net.themcbrothers.uselessmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.level.block.UselessBedBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.UselessBedBlockEntity;

public class UselessBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {
    private final UselessBedBlockEntity bed = new UselessBedBlockEntity(BlockPos.ZERO, ModBlocks.USELESS_BED.get().defaultBlockState());
    private final CoffeeMachineBlockEntity coffeeMachine = new CoffeeMachineBlockEntity(BlockPos.ZERO, ModBlocks.COFFEE_MACHINE.get().defaultBlockState());

    public UselessBlockEntityWithoutLevelRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType type, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            BlockEntity blockEntity;

            if (block instanceof UselessBedBlock) {
                blockEntity = this.bed;
            } else {
                blockEntity = this.coffeeMachine;
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ModBlocks.COFFEE_MACHINE.get().defaultBlockState(),
                        poseStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);
            }

            this.blockEntityRenderDispatcher.renderItem(blockEntity, poseStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
