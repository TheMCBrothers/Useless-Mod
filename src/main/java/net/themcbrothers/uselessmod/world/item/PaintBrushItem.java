package net.themcbrothers.uselessmod.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.world.level.block.entity.CanvasBlockEntity;

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
        final BlockPos pos = context.getClickedPos();
        final Player player = context.getPlayer();

        if (stack.getDamageValue() < stack.getMaxDamage()) {
            if (level.getBlockState(pos).is(BlockTags.WOOL)) {
                level.setBlockAndUpdate(pos, ModBlocks.CANVAS.get().defaultBlockState());
            }

            if (level.getBlockEntity(pos) instanceof CanvasBlockEntity canvas
                    && canvas.getColor() != stack.getBarColor()) {
                canvas.setColor(stack.getBarColor());
                level.scheduleTick(pos, canvas.getBlockState().getBlock(), 2);

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
        final CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        final CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("Color") : -1;
    }
}
