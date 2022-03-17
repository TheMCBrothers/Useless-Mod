package net.themcbrothers.uselessmod.world.item.crafting;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;
import net.themcbrothers.uselessmod.world.CoffeeContainer;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;

public class CoffeeRecipe implements Recipe<CoffeeContainer> {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final FluidIngredient waterIngredient;
    private final FluidIngredient milkIngredient;
    private final ItemStack result;
    private final int cookingTime;

    public CoffeeRecipe(ResourceLocation id, String group, Ingredient ingredient, FluidIngredient waterIngredient,
                        FluidIngredient milkIngredient, ItemStack result, int cookingTime) {
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.waterIngredient = waterIngredient;
        this.milkIngredient = milkIngredient;
        this.result = result;
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.COFFEE_MACHINE.get());
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> stacks = NonNullList.create();
        stacks.add(this.ingredient);
        return stacks;
    }

    @Override
    public boolean matches(CoffeeContainer container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(CoffeeContainer container) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int p_44000_height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
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
        return ModRecipeTypes.COFFEE;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CoffeeRecipe> {
        @Override
        public CoffeeRecipe fromJson(ResourceLocation id, JsonObject json) {
            return null;
        }

        @Nullable
        @Override
        public CoffeeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeRecipe recipe) {

        }
    }
}
