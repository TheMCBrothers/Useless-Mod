package net.themcbrothers.uselessmod.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.UselessDataComponents;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class CoffeeUtils {
    public static Optional<CoffeeType> getCoffeeType(final ItemStack stack) {
        return Optional.ofNullable(stack.get(UselessDataComponents.COFFEE_TYPE.get()));
    }

    @NotNull
    public static Set<MobEffectInstance> getMobEffects(final ItemStack stack) {
        return getCoffeeType(stack).map(CoffeeType::getEffects).orElse(Collections.emptySet());
    }

    @NotNull
    public static ItemStack createCoffeeStack(final CoffeeType type) {
        final ItemStack stack = new ItemStack(ModBlocks.CUP_COFFEE);
        stack.set(UselessDataComponents.COFFEE_TYPE.get(), type);
        return stack;
    }
}
