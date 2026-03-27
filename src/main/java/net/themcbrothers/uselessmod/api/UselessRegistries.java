package net.themcbrothers.uselessmod.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.themcbrothers.uselessmod.UselessMod;

public class UselessRegistries {
    public static final ResourceKey<Registry<CoffeeType>> COFFEE_KEY = ResourceKey.createRegistryKey(UselessMod.id("coffee"));

    public static final Registry<CoffeeType> COFFEE_REGISTRY = new RegistryBuilder<>(COFFEE_KEY).create();
}
