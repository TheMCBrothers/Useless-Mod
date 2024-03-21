package net.themcbrothers.uselessmod.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public final class UselessTiers {
    public static final Tier USELESS = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 300, 7.0F, 2.5F, 15, () -> Ingredient.of(UselessItems.USELESS_INGOT.get()));
    public static final Tier SUPER_USELESS = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1200, 9.0F, 3.0F, 14, () -> Ingredient.of(UselessItems.SUPER_USELESS_INGOT.get()));
}
