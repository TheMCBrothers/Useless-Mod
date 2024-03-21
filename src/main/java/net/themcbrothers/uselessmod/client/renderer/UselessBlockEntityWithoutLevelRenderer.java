package net.themcbrothers.uselessmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.world.level.block.UselessBedBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.UselessBedBlockEntity;

import java.util.function.Supplier;

public class UselessBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {
    private final Supplier<UselessBedBlockEntity> bed = () -> new UselessBedBlockEntity(BlockPos.ZERO, UselessBlocks.USELESS_BED.get().defaultBlockState());
    private final Supplier<CoffeeMachineBlockEntity> coffeeMachine = () -> new CoffeeMachineBlockEntity(BlockPos.ZERO, UselessBlocks.COFFEE_MACHINE.get().defaultBlockState());

    public UselessBlockEntityWithoutLevelRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext type, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            BlockEntity blockEntity;

            if (block instanceof UselessBedBlock) {
                blockEntity = this.bed.get();
            } else {
                blockEntity = this.coffeeMachine.get();
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(UselessBlocks.COFFEE_MACHINE.get().defaultBlockState(),
                        poseStack, buffer, combinedLight, combinedOverlay, ModelData.EMPTY, null);
            }

            this.blockEntityRenderDispatcher.renderItem(blockEntity, poseStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
