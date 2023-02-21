package net.themcbrothers.uselessmod.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;

import static net.themcbrothers.uselessmod.init.Registration.CUSTOM_STATS;

public final class ModStats {
    static void register() {
    }

    public static final RegistryObject<ResourceLocation> OPEN_WALL_CLOSET = customStat("open_wall_closet");
    public static final RegistryObject<ResourceLocation> INTERACT_WITH_COFFEE_MACHINE = customStat("interact_with_coffee_machine");

    private static RegistryObject<ResourceLocation> customStat(String key) {
        return CUSTOM_STATS.register(key, () -> UselessMod.rl(key));
    }
}
