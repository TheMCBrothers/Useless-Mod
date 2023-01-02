package net.themcbrothers.uselessmod.setup;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.themcbrothers.uselessmod.config.ServerConfig;

public class ServerSetup extends CommonSetup {
    public ServerSetup() {
        super();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
    }
}
