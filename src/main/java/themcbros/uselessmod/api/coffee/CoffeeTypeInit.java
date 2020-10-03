package themcbros.uselessmod.api.coffee;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
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
            () -> new CoffeeType(0x44211A, Ingredient.EMPTY, Collections.emptySet()));
    public static final RegistryObject<CoffeeType> MILK = REGISTER.register("milk",
            () -> new CoffeeType(0x96493A, Ingredient.fromItems(Items.MILK_BUCKET), Collections.emptySet()).curesPotionsEffects());
    public static final RegistryObject<CoffeeType> SUGAR = REGISTER.register("sugar",
            () -> new CoffeeType(0x7F3E31, Ingredient.fromItems(Items.SUGAR), Collections.emptySet()).glint());
    public static final RegistryObject<CoffeeType> MILK_SUGAR = REGISTER.register("milk_sugar",
            () -> new CoffeeType(0x99493B, Ingredient.fromItems(UselessAPI.getInstance().getItems().sugaredMilk().get()), Collections.emptySet()));

}
