package net.themcbrothers.uselessmod.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.themcbrothers.lib.crafting.CommonRecipe;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessRecipeSerializers;
import net.themcbrothers.uselessmod.core.UselessRecipeTypes;

import java.util.Optional;

public class CoffeeRecipe implements CommonRecipe<Container> {
    private final String group;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final SizedFluidIngredient waterIngredient;
    private final Optional<SizedFluidIngredient> milkIngredient;
    private final ItemStack result;
    private final int cookingTime;

    public CoffeeRecipe(String group,
                        Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                        SizedFluidIngredient waterIngredient, Optional<SizedFluidIngredient> milkIngredient,
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

    public SizedFluidIngredient getWaterIngredient() {
        return this.waterIngredient;
    }

    public Optional<SizedFluidIngredient> getMilkIngredient() {
        return this.milkIngredient;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(UselessBlocks.COFFEE_MACHINE);
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
    public ItemStack getResultItem(HolderLookup.Provider lookupProvider) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UselessRecipeSerializers.COFFEE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return UselessRecipeTypes.COFFEE.get();
    }

    public static class Serializer implements RecipeSerializer<CoffeeRecipe> {
        private static final MapCodec<CoffeeRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("cup").forGetter(recipe -> recipe.cupIngredient),
                        Ingredient.CODEC_NONEMPTY.fieldOf("bean").forGetter(recipe -> recipe.beanIngredient),
                        Ingredient.CODEC.optionalFieldOf("extra", Ingredient.EMPTY).forGetter(recipe -> recipe.extraIngredient),
                        SizedFluidIngredient.FLAT_CODEC.fieldOf("water").forGetter(recipe -> recipe.waterIngredient),
                        SizedFluidIngredient.FLAT_CODEC.optionalFieldOf("milk").forGetter(recipe -> recipe.milkIngredient),
                        ItemStack.SINGLE_ITEM_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.INT.fieldOf("cookingtime").orElse(150).forGetter(recipe -> recipe.cookingTime)
                ).apply(instance, CoffeeRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, CoffeeRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<CoffeeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoffeeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static CoffeeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            Ingredient cupIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient beanIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient extraIngredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            SizedFluidIngredient waterIngredient = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            SizedFluidIngredient milkIngredient = null;

            if (buffer.readBoolean()) {
                milkIngredient = SizedFluidIngredient.STREAM_CODEC.decode(buffer);
            }

            ItemStack recipeOutput = ItemStack.STREAM_CODEC.decode(buffer);
            int cookingTime = buffer.readInt();

            return new CoffeeRecipe(group, cupIngredient, beanIngredient, extraIngredient,
                    waterIngredient, Optional.ofNullable(milkIngredient), recipeOutput, cookingTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, CoffeeRecipe recipe) {
            buffer.writeUtf(recipe.group);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.cupIngredient);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.beanIngredient);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.extraIngredient);
            SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.waterIngredient);

            if (recipe.milkIngredient.isPresent()) {
                buffer.writeBoolean(true);
                SizedFluidIngredient.STREAM_CODEC.encode(buffer, recipe.milkIngredient.get());
            } else {
                buffer.writeBoolean(false);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeInt(recipe.cookingTime);
        }
    }
}
