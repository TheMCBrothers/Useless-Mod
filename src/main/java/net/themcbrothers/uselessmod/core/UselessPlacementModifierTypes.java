package net.themcbrothers.uselessmod.core;

import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.world.worldgen.ConfigFeaturePlacement;

public final class UselessPlacementModifierTypes {
    static void register() {
    }

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<ConfigFeaturePlacement>> CONFIG = Registration.PLACEMENT_MODIFIER_TYPES.register("config", () -> () -> ConfigFeaturePlacement.CODEC);
}
