package themcbros.uselessmod.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.registration.deferred.EntityTypeDeferredRegister;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.entity.*;

public class EntityInit {

    public static final EntityTypeDeferredRegister REGISTER = new EntityTypeDeferredRegister(UselessMod.MOD_ID);

    // CREATURES
    public static final RegistryObject<EntityType<UselessSheepEntity>> USELESS_SHEEP = REGISTER.registerWithEgg("useless_sheep",
            () -> EntityType.Builder.create(UselessSheepEntity::new, EntityClassification.CREATURE).size(0.9F, 1.3F),
            0x5EAF5B, 0xABD5A9);
    public static final RegistryObject<EntityType<UselessPigEntity>> USELESS_PIG = REGISTER.registerWithEgg("useless_pig",
            () -> EntityType.Builder.create(UselessPigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F),
            0x5DAF5A, 0x41823F);
    public static final RegistryObject<EntityType<UselessChickenEntity>> USELESS_CHICKEN = REGISTER.registerWithEgg("useless_chicken",
            () -> EntityType.Builder.create(UselessChickenEntity::new, EntityClassification.CREATURE).size(0.4F, 0.7F),
            0xBFDFBE, 0x82C180);
    public static final RegistryObject<EntityType<UselessCowEntity>> USELESS_COW = REGISTER.registerWithEgg("useless_cow",
            () -> EntityType.Builder.create(UselessCowEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F),
            0x183017, 0x478D44);

    // MONSTERS
    public static final RegistryObject<EntityType<UselessSkeletonEntity>> USELESS_SKELETON = REGISTER.register("useless_skeleton",
            () -> EntityType.Builder.create(UselessSkeletonEntity::new, EntityClassification.MONSTER).size(0.6F, 1.99F));

    // MISC
    public static final RegistryObject<EntityType<UselessBoatEntity>> USELESS_BOAT = REGISTER.register("useless_boat",
            () -> EntityType.Builder.<UselessBoatEntity>create(UselessBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3)
                    .setShouldReceiveVelocityUpdates(true).size(1.375F, 0.5625F).setCustomClientFactory(UselessBoatEntity::new));
}
