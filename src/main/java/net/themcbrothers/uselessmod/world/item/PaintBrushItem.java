package net.themcbrothers.uselessmod.world.item;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.Random;

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
        if (this.allowdedIn(tab)) {
            for (DyeColor color : DyeColor.values()) {
                final ItemStack stack = new ItemStack(this);
                final CompoundTag tag = new CompoundTag();
                tag.putInt("Damage", 0);
                tag.putInt("Color", color.getFireworkColor());
                stack.setTag(tag);
                items.add(stack);
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final ItemStack stack = context.getItemInHand();
        final Level level = context.getLevel();
        if (stack.getDamageValue() < stack.getMaxDamage() &&
                level.getBlockState(context.getClickedPos()).is(Tags.Blocks.GLASS)) {
            stack.hurt(1, new Random(), null);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useOn(context);
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
        final CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        final CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("Color") : -1;
    }
}
