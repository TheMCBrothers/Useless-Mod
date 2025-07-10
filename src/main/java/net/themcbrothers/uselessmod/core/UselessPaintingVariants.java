package net.themcbrothers.uselessmod.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.Optional;

public final class UselessPaintingVariants {
    public static final ResourceKey<PaintingVariant> LARGE_LOGO_RED = createKey("large_logo_red");
    public static final ResourceKey<PaintingVariant> LARGE_LOGO_BLUE = createKey("large_logo_blue");
    public static final ResourceKey<PaintingVariant> SMALL_LOGO_RED = createKey("small_logo_red");
    public static final ResourceKey<PaintingVariant> SMALL_LOGO_BLUE = createKey("small_logo_blue");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, LARGE_LOGO_RED, 8, 2);
        register(context, LARGE_LOGO_BLUE, 8, 2);
        register(context, SMALL_LOGO_RED, 6, 1);
        register(context, SMALL_LOGO_BLUE, 6, 1);
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location(), Optional.empty(), Optional.empty()));
    }

    private static ResourceKey<PaintingVariant> createKey(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, UselessMod.rl(name));
    }
}
