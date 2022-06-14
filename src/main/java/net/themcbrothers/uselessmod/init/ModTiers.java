package net.themcbrothers.uselessmod.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public final class ModTiers {
    public static final Tier USELESS = new ForgeTier(2, 300, 7.0F, 2.5F, 15,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.USELESS_INGOT.get()));
    public static final Tier SUPER_USELESS = new ForgeTier(3, 1200, 9.0F, 3.0F, 14,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.SUPER_USELESS_INGOT.get()));
}
