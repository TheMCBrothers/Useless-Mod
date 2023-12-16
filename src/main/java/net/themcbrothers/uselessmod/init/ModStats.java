package net.themcbrothers.uselessmod.init;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.UselessMod;

import static net.themcbrothers.uselessmod.init.Registration.CUSTOM_STATS;

public final class ModStats {
    static void register() {
    }

    public static final DeferredHolder<ResourceLocation, ResourceLocation> OPEN_WALL_CLOSET = customStat("open_wall_closet");
    public static final DeferredHolder<ResourceLocation, ResourceLocation> INTERACT_WITH_COFFEE_MACHINE = customStat("interact_with_coffee_machine");

    private static DeferredHolder<ResourceLocation, ResourceLocation> customStat(String key) {
        return CUSTOM_STATS.register(key, () -> UselessMod.rl(key));
    }
}
