package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;

import java.util.List;

public class PaintBrushItem extends Item {
    public PaintBrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> hoverText, TooltipFlag tooltipFlag) {
        Integer color = stack.get(UselessDataComponents.COLOR.get());

        if (color != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            hoverText.add(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final ItemStack stack = context.getItemInHand();
        final Level level = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        final Player player = context.getPlayer();

        if (stack.getDamageValue() < stack.getMaxDamage()) {
            if (level.getBlockState(pos).is(BlockTags.WOOL)) {
                level.setBlockAndUpdate(pos, UselessBlocks.PAINTED_WOOL.get().defaultBlockState());
            }

            if (level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity paintedWool
                    && paintedWool.getColor() != stack.getBarColor()) {
                paintedWool.setColor(stack.getBarColor());
                level.scheduleTick(pos, paintedWool.getBlockState().getBlock(), 2);

                if (level instanceof ServerLevel serverLevel && (player == null || !player.getAbilities().instabuild)) {
                    stack.hurtAndBreak(1, serverLevel, player instanceof ServerPlayer ? (ServerPlayer) player : null, item -> {
                    });
                }

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);

        if (damage >= this.getMaxDamage(stack)) {
            stack.remove(UselessDataComponents.COLOR.get());
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.has(UselessDataComponents.COLOR.get());
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return stack.getOrDefault(UselessDataComponents.COLOR.get(), -1);
    }
}
