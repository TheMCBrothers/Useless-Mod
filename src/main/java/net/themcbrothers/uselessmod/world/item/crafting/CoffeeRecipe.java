package net.themcbrothers.uselessmod.world.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.themcbrothers.lib.crafting.CommonRecipe;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;
import net.themcbrothers.uselessmod.world.CoffeeContainer;
import org.jetbrains.annotations.Nullable;

public class CoffeeRecipe implements CommonRecipe<CoffeeContainer> {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final FluidIngredient waterIngredient;
    private final FluidIngredient milkIngredient;
    private final ItemStack result;
    private final int cookingTime;

    public CoffeeRecipe(ResourceLocation id, String group,
                        Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                        FluidIngredient waterIngredient, FluidIngredient milkIngredient,
                        ItemStack result, int cookingTime) {
        this.id = id;
        this.group = group;
        this.cupIngredient = cupIngredient;
        this.beanIngredient = beanIngredient;
        this.extraIngredient = extraIngredient;
        this.waterIngredient = waterIngredient;
        this.milkIngredient = milkIngredient;
        this.result = result;
        this.cookingTime = cookingTime;
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

    public FluidIngredient getWaterIngredient() {
        return this.waterIngredient;
    }

    public FluidIngredient getMilkIngredient() {
        return this.milkIngredient;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.COFFEE_MACHINE);
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, this.cupIngredient, this.beanIngredient, this.extraIngredient);
    }

    @Override
    public boolean matches(CoffeeContainer container, Level level) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.COFFEE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.COFFEE.get();
    }

    public static class Serializer implements RecipeSerializer<CoffeeRecipe> {
        @Override
        public CoffeeRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");
            JsonElement jsonCup = GsonHelper.isArrayNode(json, "cup") ? GsonHelper.getAsJsonArray(json, "cup") : GsonHelper.getAsJsonObject(json, "cup");
            Ingredient cupIngredient = Ingredient.fromJson(jsonCup);
            JsonElement jsonBean = GsonHelper.isArrayNode(json, "bean") ? GsonHelper.getAsJsonArray(json, "bean") : GsonHelper.getAsJsonObject(json, "bean");
            Ingredient beanIngredient = Ingredient.fromJson(jsonBean);
            Ingredient extraIngredient = Ingredient.EMPTY;
            if (json.has("extra")) {
                JsonElement jsonExtra = GsonHelper.isArrayNode(json, "extra") ? GsonHelper.getAsJsonArray(json, "extra") : GsonHelper.getAsJsonObject(json, "extra");
                extraIngredient = Ingredient.fromJson(jsonExtra);
            }
            FluidIngredient waterIngredient = FluidIngredient.deserialize(json, "water");
            FluidIngredient milkIngredient = json.has("milk") ? FluidIngredient.deserialize(json, "milk") : FluidIngredient.EMPTY;
            ItemStack recipeOutput = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            int cookingTime = GsonHelper.getAsInt(json, "cookingtime", 150);

            return new CoffeeRecipe(id, group, cupIngredient, beanIngredient, extraIngredient,
                    waterIngredient, milkIngredient, recipeOutput, cookingTime);
        }

        @Nullable
        @Override
        public CoffeeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            Ingredient cupIngredient = Ingredient.fromNetwork(buffer);
            Ingredient beanIngredient = Ingredient.fromNetwork(buffer);
            Ingredient extraIngredient = Ingredient.fromNetwork(buffer);
            FluidIngredient waterIngredient = FluidIngredient.read(buffer);
            FluidIngredient milkIngredient = FluidIngredient.read(buffer);
            ItemStack recipeOutput = buffer.readItem();
            int cookingTime = buffer.readInt();

            return new CoffeeRecipe(id, group, cupIngredient, beanIngredient, extraIngredient,
                    waterIngredient, milkIngredient, recipeOutput, cookingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.cupIngredient.toNetwork(buffer);
            recipe.beanIngredient.toNetwork(buffer);
            recipe.extraIngredient.toNetwork(buffer);
            recipe.waterIngredient.write(buffer);
            recipe.milkIngredient.write(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.cookingTime);
        }
    }
}
