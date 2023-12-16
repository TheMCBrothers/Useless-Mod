package net.themcbrothers.uselessmod.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.themcbrothers.uselessmod.UselessMod;

public class UselessRegistries {
    public static final ResourceKey<Registry<CoffeeType>> COFFEE_KEY = ResourceKey.createRegistryKey(UselessMod.rl("coffee"));

    public static Registry<CoffeeType> coffeeRegistry;
}
