package net.themcbrothers.uselessmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;
import net.themcbrothers.uselessmod.init.ModBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public class CoffeeUtils {
    private static final String TAG_COFFEE = "Coffee";

    @Nullable
    public static CoffeeType getCoffeeType(final ItemStack stack) {
        final CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_COFFEE, Tag.TAG_STRING)) {
            return UselessRegistries.coffeeRegistry.get().getValue(ResourceLocation.tryParse(tag.getString(TAG_COFFEE)));
        }

        return null;
    }

    @NotNull
    public static Set<MobEffectInstance> getMobEffects(final ItemStack stack) {
        final CoffeeType type = getCoffeeType(stack);
        return type == null ? Collections.emptySet() : type.getEffects();
    }

    @NotNull
    public static ItemStack getCoffeeStack(final CoffeeType type) {
        final ItemStack stack = new ItemStack(ModBlocks.CUP_COFFEE);
        stack.getOrCreateTag().putString(TAG_COFFEE, String.valueOf(type.getRegistryName()));
        return stack;
    }
}
