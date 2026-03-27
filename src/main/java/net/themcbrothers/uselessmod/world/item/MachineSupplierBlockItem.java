package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.themcbrothers.uselessmod.core.UselessDataComponents;

import java.util.function.Consumer;

public class MachineSupplierBlockItem extends BlockItem {
    public MachineSupplierBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        BlockState mimic = stack.get(UselessDataComponents.MIMIC.get());
        ClientLevel clientLevel = Minecraft.getInstance().level;

        if (mimic != null && clientLevel != null) {
            tooltipAdder.accept(mimic.getBlock().getName().withStyle(ChatFormatting.GRAY));
        }
    }
}
