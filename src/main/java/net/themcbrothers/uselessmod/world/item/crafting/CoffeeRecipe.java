package net.themcbrothers.uselessmod.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.themcbrothers.lib.crafting.CommonRecipe;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
import net.themcbrothers.uselessmod.init.ModRecipeTypes;

public class CoffeeRecipe implements CommonRecipe<Container> {
    private final String group;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final FluidIngredient waterIngredient;
    private final FluidIngredient milkIngredient;
    private final ItemStack result;
    private final int cookingTime;

    public CoffeeRecipe(String group,
                        Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                        FluidIngredient waterIngredient, FluidIngredient milkIngredient,
                        ItemStack result, int cookingTime) {
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
    public boolean matches(Container container, Level level) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
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
        private static final Codec<CoffeeRecipe> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(recipe -> recipe.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("cup").forGetter(recipe -> recipe.cupIngredient),
                        Ingredient.CODEC_NONEMPTY.fieldOf("bean").forGetter(recipe -> recipe.beanIngredient),
                        Ingredient.CODEC.fieldOf("extra").orElse(Ingredient.EMPTY).forGetter(recipe -> recipe.extraIngredient),
                        FluidIngredient.CODEC_NONEMPTY.fieldOf("water").forGetter(recipe -> recipe.waterIngredient),
                        FluidIngredient.CODEC.fieldOf("milk").orElse(FluidIngredient.EMPTY).forGetter(recipe -> recipe.milkIngredient),
                        CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.INT.fieldOf("cookingtime").orElse(150).forGetter(recipe -> recipe.cookingTime)
                ).apply(instance, CoffeeRecipe::new));

        @Override
        public Codec<CoffeeRecipe> codec() {
            return CODEC;
        }

        @Override
        public CoffeeRecipe fromNetwork(FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            Ingredient cupIngredient = Ingredient.fromNetwork(buffer);
            Ingredient beanIngredient = Ingredient.fromNetwork(buffer);
            Ingredient extraIngredient = Ingredient.fromNetwork(buffer);
            FluidIngredient waterIngredient = FluidIngredient.fromNetwork(buffer);
            FluidIngredient milkIngredient = FluidIngredient.fromNetwork(buffer);
            ItemStack recipeOutput = buffer.readItem();
            int cookingTime = buffer.readInt();

            return new CoffeeRecipe(group, cupIngredient, beanIngredient, extraIngredient,
                    waterIngredient, milkIngredient, recipeOutput, cookingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CoffeeRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.cupIngredient.toNetwork(buffer);
            recipe.beanIngredient.toNetwork(buffer);
            recipe.extraIngredient.toNetwork(buffer);
            recipe.waterIngredient.toNetwork(buffer);
            recipe.milkIngredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.cookingTime);
        }
    }
}
