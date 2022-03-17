package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import net.themcbrothers.uselessmod.UselessMod;

import javax.annotation.Nonnull;

@JeiPlugin
public class UselessJEI implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return UselessMod.rl("jei_plugin");
    }
}
