package themcbros.uselessmod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.helpers.BlockHelper;
import themcbros.uselessmod.helpers.RecipeHelper;
import themcbros.uselessmod.init.BlockInit;

import java.util.function.Predicate;

/**
 * @author TheMCBrothers
 */
public class WallClosetRecipeManager implements ISelectiveResourceReloadListener {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.addRecipes();
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        this.addRecipes();
    }

    private void addRecipes() {
        if (!Config.SERVER_CONFIG.disableWallClosetRecipes.get()) {
            for (ClosetMaterial material : UselessRegistries.CLOSET_MATERIALS) {
                ShapedRecipe recipe = createWallClosetRecipe(material);
                RecipeHelper.addRecipe(recipe);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void addReloadListeners(final AddReloadListenerEvent event) {
        event.addListener(this);
    }

    private static ShapedRecipe createWallClosetRecipe(ClosetMaterial material) {
        Ingredient ingredient = Ingredient.fromItems(material.getBlock());
        Ingredient slab = Ingredient.fromItems(BlockHelper.getSlab(material.getBlock()));
        NonNullList<Ingredient> inputs = NonNullList.from(Ingredient.EMPTY,
                ingredient, slab, ingredient,
                ingredient, slab, ingredient,
                ingredient, slab, ingredient
        );
        ItemStack output = new ItemStack(BlockInit.WALL_CLOSET.get());
        output.getOrCreateTag().putString("Material", String.valueOf(material.getRegistryName()));

        String reg = String.valueOf(material.getRegistryName());
        ResourceLocation name = new ResourceLocation(UselessMod.MOD_ID, "closet_" + reg.replace(':', '.'));
        return new ShapedRecipe(name, "uselessmod:closets", 3, 3, inputs, output);
    }

}
