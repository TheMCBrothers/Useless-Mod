package net.themcbrothers.uselessmod.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.IForgeRegistry;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.function.Supplier;

public class UselessRegistries {
    public static final ResourceKey<Registry<CoffeeType>> COFFEE_KEY = ResourceKey.createRegistryKey(UselessMod.rl("coffee"));

    public static Supplier<IForgeRegistry<CoffeeType>> coffeeRegistry;
}
