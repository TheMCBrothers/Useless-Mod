package net.themcbrothers.uselessmod.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface CreativeTabFiller {
    void fillCreativeTab(NonNullList<ItemStack> items);
}
