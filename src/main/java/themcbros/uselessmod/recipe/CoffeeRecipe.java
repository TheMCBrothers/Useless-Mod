package themcbros.uselessmod.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import slimeknights.mantle.recipe.FluidIngredient;
import slimeknights.mantle.recipe.ICommonRecipe;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.RecipeSerializerInit;
import themcbros.uselessmod.init.RecipeTypeInit;

import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class CoffeeRecipe implements ICommonRecipe<IInventory> {

    private final ResourceLocation id;
    private final FluidIngredient waterIngredient;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final ItemStack recipeOutput;

    public CoffeeRecipe(ResourceLocation id, FluidIngredient waterIngredient, Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient, ItemStack recipeOutput) {
        this.id = id;
        this.waterIngredient = waterIngredient;
        this.cupIngredient = cupIngredient;
        this.beanIngredient = beanIngredient;
        this.extraIngredient = extraIngredient;
        this.recipeOutput = recipeOutput;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(BlockInit.COFFEE_MACHINE.get());
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(Ingredient.EMPTY, this.cupIngredient, this.beanIngredient, this.extraIngredient);
    }

    public FluidIngredient getWaterIngredient() {
        return this.waterIngredient;
    }

    public Ingredient getCupIngredient() {
        return this.cupIngredient;
    }

    public Ingredient getBeanIngredient() {
        return this.beanIngredient;
    }

    public Ingredient getExtraIngredient() {
        return this.extraIngredient;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.COFFEE_MACHINE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeTypeInit.COFFEE_MACHINE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CoffeeRecipe> {
        @Override
        public CoffeeRecipe read(ResourceLocation recipeId, JsonObject json) {
            FluidIngredient waterIngredient = FluidIngredient.deserialize(json, "water");
            JsonElement cupElement = JSONUtils.isJsonArray(json, "cup") ? JSONUtils.getJsonArray(json, "cup") : JSONUtils.getJsonObject(json, "cup");
            Ingredient cupIngredient = Ingredient.deserialize(cupElement);
            JsonElement beanElement = JSONUtils.isJsonArray(json, "bean") ? JSONUtils.getJsonArray(json, "bean") : JSONUtils.getJsonObject(json, "bean");
            Ingredient beanIngredient = Ingredient.deserialize(beanElement);
            JsonElement extraElement = JSONUtils.isJsonArray(json, "extra") ? JSONUtils.getJsonArray(json, "extra") : JSONUtils.getJsonObject(json, "extra");
            Ingredient extraIngredient = Ingredient.deserialize(extraElement);
            if (!json.has("result"))
                throw new JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (json.get("result").isJsonObject())
                itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            else {
                String s1 = JSONUtils.getString(json, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
                if (item == null) throw new IllegalStateException("Item: " + s1 + " does not exist");
                itemstack = new ItemStack(item);
            }
            return new CoffeeRecipe(recipeId, waterIngredient, cupIngredient, beanIngredient, extraIngredient, itemstack);
        }

        @Nullable
        @Override
        public CoffeeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            FluidIngredient waterIngredient = FluidIngredient.read(buffer);
            Ingredient cupIngredient = Ingredient.read(buffer);
            Ingredient beanIngredient = Ingredient.read(buffer);
            Ingredient extraIngredient = Ingredient.read(buffer);
            ItemStack recipeOutput = buffer.readItemStack();
            return new CoffeeRecipe(recipeId, waterIngredient, cupIngredient, beanIngredient, extraIngredient, recipeOutput);
        }

        @Override
        public void write(PacketBuffer buffer, CoffeeRecipe recipe) {
            recipe.waterIngredient.write(buffer);
            recipe.cupIngredient.write(buffer);
            recipe.beanIngredient.write(buffer);
            recipe.extraIngredient.write(buffer);
            buffer.writeItemStack(recipe.recipeOutput);
        }
    }

}
