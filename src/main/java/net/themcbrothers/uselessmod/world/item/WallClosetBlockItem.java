package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.themcbrothers.uselessmod.core.UselessDataComponents;

import java.util.function.Consumer;

public class WallClosetBlockItem extends BlockItem {
    public WallClosetBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        Holder<Block> material = stack.get(UselessDataComponents.WALL_CLOSET_MATERIAL.get());

        if (material != null) {
            tooltipAdder.accept(material.value().getName().withStyle(ChatFormatting.GRAY));
        }
    }
}
