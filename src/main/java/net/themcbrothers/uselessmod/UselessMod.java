package net.themcbrothers.uselessmod;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;
import net.themcbrothers.uselessmod.setup.ClientSetup;
import net.themcbrothers.uselessmod.setup.CommonSetup;
import net.themcbrothers.uselessmod.setup.ServerSetup;

@Mod(UselessMod.MOD_ID)
public class UselessMod {
    public static final String MOD_ID = "uselessmod";

    public static CommonSetup setup;

    public UselessMod() {
        setup = DistExecutor.unsafeRunForDist(() -> ClientSetup::new, () -> ServerSetup::new);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static MutableComponent translate(String prefix, String suffix, Object... args) {
        return Component.translatable(String.format("%s.%s.%s", prefix, MOD_ID, suffix), args);
    }
}
