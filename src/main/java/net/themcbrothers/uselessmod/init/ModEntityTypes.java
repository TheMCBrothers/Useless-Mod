package net.themcbrothers.uselessmod.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.world.entity.animal.UselessChicken;
import net.themcbrothers.uselessmod.world.entity.animal.UselessCow;
import net.themcbrothers.uselessmod.world.entity.animal.UselessPig;
import net.themcbrothers.uselessmod.world.entity.animal.UselessSheep;
import net.themcbrothers.uselessmod.world.entity.monster.UselessSkeleton;

import static net.themcbrothers.uselessmod.init.Registration.ENTITY_TYPES;

public final class ModEntityTypes {
    static void register() {
    }

    // CREATURES
    public static final DeferredHolder<EntityType<?>, EntityType<UselessSheep>> USELESS_SHEEP = ENTITY_TYPES.registerWithEgg("useless_sheep", () ->
            EntityType.Builder.of(UselessSheep::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(10), 0x5EAF5B, 0xABD5A9);
    public static final DeferredHolder<EntityType<?>, EntityType<UselessPig>> USELESS_PIG = ENTITY_TYPES.registerWithEgg("useless_pig", () ->
            EntityType.Builder.of(UselessPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(10), 0x5DAF5A, 0x41823F);
    public static final DeferredHolder<EntityType<?>, EntityType<UselessChicken>> USELESS_CHICKEN = ENTITY_TYPES.registerWithEgg("useless_chicken", () ->
            EntityType.Builder.of(UselessChicken::new, MobCategory.CREATURE).sized(0.4F, 0.7F).clientTrackingRange(10), 0xBFDFBE, 0x82C180);
    public static final DeferredHolder<EntityType<?>, EntityType<UselessCow>> USELESS_COW = ENTITY_TYPES.registerWithEgg("useless_cow", () ->
            EntityType.Builder.of(UselessCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10), 0x183017, 0x478D44);

    // MONSTERS
    public static final DeferredHolder<EntityType<?>, EntityType<UselessSkeleton>> USELESS_SKELETON = ENTITY_TYPES.registerWithEgg("useless_skeleton", () ->
            EntityType.Builder.of(UselessSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8), 0x66B363, 0x285027);
}
