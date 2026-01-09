package net.themcbrothers.uselessmod.core;

import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.UselessMod;

import static net.themcbrothers.uselessmod.core.Registration.CUSTOM_STATS;

public final class UselessStats {
    static void register() {
    }

    public static final DeferredHolder<Identifier, Identifier> OPEN_WALL_CLOSET = customStat("open_wall_closet");
    public static final DeferredHolder<Identifier, Identifier> INTERACT_WITH_COFFEE_MACHINE = customStat("interact_with_coffee_machine");

    private static DeferredHolder<Identifier, Identifier> customStat(String key) {
        return CUSTOM_STATS.register(key, () -> UselessMod.id(key));
    }
}
