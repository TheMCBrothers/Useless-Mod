package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.worldgen.ConfigFeaturePlacement;

public final class UselessPlacementModifierTypes {
    static void register() {
    }

    public static final RegistryObject<PlacementModifierType<ConfigFeaturePlacement>> CONFIG = Registration.PLACEMENT_MODIFIER_TYPES.register("config", () -> () -> ConfigFeaturePlacement.CODEC);
}
