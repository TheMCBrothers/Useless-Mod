package net.themcbrothers.uselessmod.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.entity.monster.UselessSkeleton;

import static net.themcbrothers.uselessmod.init.Registration.ENTITIES;

public final class ModEntityTypes {
    static void register() {
    }

    public static final RegistryObject<EntityType<UselessSkeleton>> USELESS_SKELETON = ENTITIES.registerWithEgg("useless_skeleton", () ->
            EntityType.Builder.of(UselessSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8), 0x4E9A4B, 0x285027);
}
