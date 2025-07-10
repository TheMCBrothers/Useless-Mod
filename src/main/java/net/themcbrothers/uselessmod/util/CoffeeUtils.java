package net.themcbrothers.uselessmod.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CoffeeUtils {
    public static Optional<CoffeeType> getCoffeeType(final ItemStack stack) {
        return Optional.ofNullable(stack.get(UselessDataComponents.COFFEE_TYPE.get()));
    }

    @NotNull
    public static ItemStack createCoffeeStack(final CoffeeType type) {
        final ItemStack stack = new ItemStack(UselessBlocks.CUP_COFFEE);
        stack.set(UselessDataComponents.COFFEE_TYPE.get(), type);
        stack.set(DataComponents.CONSUMABLE, type.getConsumable());
        return stack;
    }
}
