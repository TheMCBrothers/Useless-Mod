package themcbros.uselessmod.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.recipe.CoffeeRecipe;

public class RecipeSerializerInit {

    public static final DeferredRegister<IRecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, UselessMod.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<CoffeeRecipe>> COFFEE_MACHINE = REGISTER.register("coffee_machine",
            CoffeeRecipe.Serializer::new);
}
