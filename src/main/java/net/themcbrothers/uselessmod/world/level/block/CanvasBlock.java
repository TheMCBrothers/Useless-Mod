package net.themcbrothers.uselessmod.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.themcbrothers.uselessmod.init.ModBlockEntityTypes;
import net.themcbrothers.uselessmod.world.level.block.entity.CanvasBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CanvasBlock extends BaseEntityBlock {
    public CanvasBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack stack) {
        final BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CanvasBlockEntity) {
            blockEntity.requestModelDataUpdate();
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        final ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);

        if (level.getBlockEntity(pos) instanceof CanvasBlockEntity canvasBlockEntity) {
            BlockItem.setBlockEntityData(stack, canvasBlockEntity.getType(), canvasBlockEntity.getUpdateTag());
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompoundTag tag = BlockItem.getBlockEntityData(stack);
        if (tag != null) {
            int color = tag.getInt("Color");
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            tooltip.add(new TranslatableComponent("misc.uselessmod.color", hexColor));
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.CANVAS.get().create(pos, state);
    }
}
