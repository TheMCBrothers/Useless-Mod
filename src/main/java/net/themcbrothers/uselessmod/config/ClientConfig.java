package net.themcbrothers.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        SPEC = BUILDER.build();
    }
}
