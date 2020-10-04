package themcbros.uselessmod.api.coffee;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import themcbros.uselessmod.api.UselessAPI;
import themcbros.uselessmod.api.UselessRegistries;

import java.util.Collections;

/**
 * @author TheMCBrothers
 */
public class CoffeeTypeInit {

    public static final DeferredRegister<CoffeeType> REGISTER = DeferredRegister.create(UselessRegistries.COFFEE_TYPES, UselessAPI.MOD_ID);

    public static final RegistryObject<CoffeeType> BLACK = REGISTER.register("black",
            () -> new CoffeeType(0x44211A, Collections.emptySet()));
    public static final RegistryObject<CoffeeType> MILK = REGISTER.register("milk",
            () -> new CoffeeType(0x96493A, Collections.emptySet()).curesPotionsEffects());
    public static final RegistryObject<CoffeeType> SUGAR = REGISTER.register("sugar",
            () -> new CoffeeType(0x7F3E31, Collections.emptySet()).glint());
    public static final RegistryObject<CoffeeType> MILK_SUGAR = REGISTER.register("milk_sugar",
            () -> new CoffeeType(0x99493B, Collections.emptySet()));

}
