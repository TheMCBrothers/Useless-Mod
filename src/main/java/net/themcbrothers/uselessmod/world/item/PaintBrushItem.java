package net.themcbrothers.uselessmod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PaintBrushItem extends Item {
    public PaintBrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        if (this.allowedIn(tab)) {
            for (DyeColor color : DyeColor.values()) {
                final ItemStack stack = new ItemStack(this);
                float[] colors = color.getTextureDiffuseColors();
                int r = (int) (colors[0] * 255.0F);
                int g = (int) (colors[1] * 255.0F);
                int b = (int) (colors[2] * 255.0F);
                this.setColor(stack, (r << 16) + (g << 8) + b);
                stack.setDamageValue(0);
                items.add(stack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> hoverText, TooltipFlag tooltipFlag) {
        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC)) {
            int color = tag.getInt("Color");
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
                level.setBlockAndUpdate(pos, ModBlocks.PAINTED_WOOL.get().defaultBlockState());
            }

            if (level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity paintedWool
                    && paintedWool.getColor() != stack.getBarColor()) {
                paintedWool.setColor(stack.getBarColor());
                level.scheduleTick(pos, paintedWool.getBlockState().getBlock(), 2);

                if (player == null || !player.getAbilities().instabuild) {
                    stack.hurt(1, level.getRandom(), player instanceof ServerPlayer ? (ServerPlayer) player : null);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);

        if (damage >= this.getMaxDamage(stack)) {
            stack.getOrCreateTag().remove("Color");
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return this.hasCustomColor(stack);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return this.getColor(stack);
    }

    public boolean hasCustomColor(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("Color", 99);
    }

    public int getColor(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("Color", 99) ? tag.getInt("Color") : -1;
    }

    public void setColor(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("Color", color);
    }

    public static ItemStack dye(ItemStack stack, List<DyeColor> dyes) {
        ItemStack itemstack = ItemStack.EMPTY;
        int[] colors = new int[3];
        int i = 0;
        int j = 0;
        PaintBrushItem paintBrushItem = null;
        Item item = stack.getItem();
        if (item instanceof PaintBrushItem) {
            paintBrushItem = (PaintBrushItem) item;
            itemstack = stack.copy();
            itemstack.setCount(1);
            if (paintBrushItem.hasCustomColor(stack)) {
                int currentColor = paintBrushItem.getColor(itemstack);
                float r = (float) (currentColor >> 16 & 0xFF) / 255.0F;
                float g = (float) (currentColor >> 8 & 0xFF) / 255.0F;
                float b = (float) (currentColor & 0xFF) / 255.0F;
                i += (int) (Math.max(r, Math.max(g, b)) * 255.0F);
                colors[0] += (int) (r * 255.0F);
                colors[1] += (int) (g * 255.0F);
                colors[2] += (int) (b * 255.0F);
                ++j;
            }

            for (DyeColor dyeColor : dyes) {
                float[] dyeColors = dyeColor.getTextureDiffuseColors();
                int r = (int) (dyeColors[0] * 255.0F);
                int g = (int) (dyeColors[1] * 255.0F);
                int b = (int) (dyeColors[2] * 255.0F);
                i += Math.max(r, Math.max(g, b));
                colors[0] += r;
                colors[1] += g;
                colors[2] += b;
                ++j;
            }
        }

        if (paintBrushItem == null) {
            return ItemStack.EMPTY;
        } else {
            int r = colors[0] / j;
            int g = colors[1] / j;
            int b = colors[2] / j;
            float f3 = (float) i / (float) j;
            float f4 = (float) Math.max(r, Math.max(g, b));
            r = (int) ((float) r * f3 / f4);
            g = (int) ((float) g * f3 / f4);
            b = (int) ((float) b * f3 / f4);
            int color = (r << 8) + g;
            color = (color << 8) + b;
            paintBrushItem.setColor(itemstack, color);
            return itemstack;
        }
    }
}
