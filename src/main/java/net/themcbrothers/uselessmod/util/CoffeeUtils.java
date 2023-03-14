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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class CoffeeUtils {
    private static final String TAG_COFFEE = "Coffee";

    public static Optional<CoffeeType> getCoffeeType(final ItemStack stack) {
        final CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_COFFEE, Tag.TAG_STRING)) {
            final ResourceLocation registryName = ResourceLocation.tryParse(tag.getString(TAG_COFFEE));
            return Optional.ofNullable(UselessRegistries.coffeeRegistry.get().getValue(registryName));
        }

        return Optional.empty();
    }

    @NotNull
    public static Set<MobEffectInstance> getMobEffects(final ItemStack stack) {
        return getCoffeeType(stack).map(CoffeeType::getEffects).orElse(Collections.emptySet());
    }

    @NotNull
    public static ItemStack createCoffeeStack(final CoffeeType type) {
        final ItemStack stack = new ItemStack(ModBlocks.CUP_COFFEE);
        stack.getOrCreateTag().putString(TAG_COFFEE, String.valueOf(UselessRegistries.coffeeRegistry.get().getKey(type)));
        return stack;
    }
}
