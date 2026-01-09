package net.themcbrothers.uselessmod;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UselessMod {
    public static final String MOD_ID = "uselessmod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private UselessMod() {
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static MutableComponent translate(String prefix, String suffix, Object... args) {
        return Component.translatable(String.format("%s.%s.%s", prefix, MOD_ID, suffix), args);
    }
}
