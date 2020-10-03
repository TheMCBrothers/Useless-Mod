package themcbros.uselessmod.recipe;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.helpers.RecipeHelper;
import themcbros.uselessmod.init.ItemInit;

import java.util.Objects;
import java.util.function.Predicate;

public class CoffeeRecipeManager implements ISelectiveResourceReloadListener {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        for (CoffeeType type : UselessRegistries.COFFEE_TYPES) {
            CoffeeRecipe recipe = createCoffeeRecipe(type);
            RecipeHelper.addRecipe(recipe);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void addReloadListeners(final AddReloadListenerEvent event) {
        //event.addListener(this);
    }

    private static CoffeeRecipe createCoffeeRecipe(CoffeeType type) {
        return new CoffeeRecipe(Objects.requireNonNull(type.getRegistryName()), 250, Ingredient.fromItems(ItemInit.CUP.get()),
                Ingredient.fromTag(UselessTags.Items.CROPS_COFFEEBEAN), type.getExtraIngredient(), ItemInit.COFFEE_CUP.get().getStack(type));
    }

}
