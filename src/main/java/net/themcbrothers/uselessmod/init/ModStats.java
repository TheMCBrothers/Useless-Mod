package net.themcbrothers.uselessmod.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;

import static net.themcbrothers.uselessmod.init.Registration.CUSTOM_STATS;

public final class ModStats {
    static void register() {
    }

    public static final RegistryObject<ResourceLocation> OPEN_WALL_CLOSET =
            CUSTOM_STATS.register("open_wall_closet", () -> UselessMod.rl("open_wall_closet"));
}
