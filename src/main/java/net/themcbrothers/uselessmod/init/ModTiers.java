package net.themcbrothers.uselessmod.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public final class ModTiers {
    public static final Tier USELESS = new ForgeTier(2, 300, 10.0F, 2.5F, 15,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.USELESS_INGOT.get()));
}
