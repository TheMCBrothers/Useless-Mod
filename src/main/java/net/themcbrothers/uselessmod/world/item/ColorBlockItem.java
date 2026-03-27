package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessDataComponents;

import java.util.function.Consumer;

public class ColorBlockItem extends BlockItem {
    public ColorBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        Integer color = stack.get(UselessDataComponents.COLOR.get());

        if (color != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            tooltipAdder.accept(UselessMod.translate("misc", "color", hexColor).withStyle(ChatFormatting.GRAY));
        }
    }
}
