package themcbros.uselessmod.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.entity.*;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, UselessMod.MOD_ID);

    // CREATURES
    public static final RegistryObject<EntityType<UselessSheepEntity>> USELESS_SHEEP = REGISTER.register("useless_sheep",
            () -> EntityType.Builder.create(UselessSheepEntity::new, EntityClassification.CREATURE).size(0.9F, 1.3F).build("useless_sheep"));
    public static final RegistryObject<EntityType<UselessPigEntity>> USELESS_PIG = REGISTER.register("useless_pig",
            () -> EntityType.Builder.create(UselessPigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).build("useless_pig"));
    public static final RegistryObject<EntityType<UselessChickenEntity>> USELESS_CHICKEN = REGISTER.register("useless_chicken",
            () -> EntityType.Builder.create(UselessChickenEntity::new, EntityClassification.CREATURE).size(0.4F, 0.7F).build("useless_chicken"));
    public static final RegistryObject<EntityType<UselessCowEntity>> USELESS_COW = REGISTER.register("useless_cow",
            () -> EntityType.Builder.create(UselessCowEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build("useless_cow"));

    // MONSTERS
    public static final RegistryObject<EntityType<UselessSkeletonEntity>> USELESS_SKELETON = REGISTER.register("useless_skeleton",
            () -> EntityType.Builder.create(UselessSkeletonEntity::new, EntityClassification.MONSTER).size(0.6F, 1.99F).build("useless_skeleton"));

    // MISC
    public static final RegistryObject<EntityType<UselessBoatEntity>> USELESS_BOAT = REGISTER.register("useless_boat",
            () -> EntityType.Builder.<UselessBoatEntity>create(UselessBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3)
                    .setShouldReceiveVelocityUpdates(true).size(1.375F, 0.5625F).setCustomClientFactory(UselessBoatEntity::new)
                    .build("useless_boat"));
}
