package net.themcbrothers.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class Config {
    public static final ServerConfig SERVER_CONFIG;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ClientConfig CLIENT_CONFIG;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> serverConfigPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        final Pair<ClientConfig, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);

        SERVER_CONFIG = serverConfigPair.getLeft();
        SERVER_SPEC = serverConfigPair.getRight();
        CLIENT_CONFIG = clientConfigPair.getLeft();
        CLIENT_SPEC = clientConfigPair.getRight();
    }

    public static void init() {
    }
}
