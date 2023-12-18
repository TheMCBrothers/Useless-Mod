package net.themcbrothers.uselessmod.data.builder;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.Util;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoffeeRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final FluidIngredient waterIngredient;
    private final FluidIngredient milkIngredient;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private CoffeeRecipeBuilder(ItemStack result, Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                                FluidIngredient waterIngredient, FluidIngredient milkIngredient, int cookingTime) {
        this.result = result;
        this.cupIngredient = cupIngredient;
        this.beanIngredient = beanIngredient;
        this.extraIngredient = extraIngredient;
        this.waterIngredient = waterIngredient;
        this.milkIngredient = milkIngredient;
        this.cookingTime = cookingTime;
    }

    public static CoffeeRecipeBuilder coffee(ItemStack result, Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                                             FluidIngredient waterIngredient, FluidIngredient milkIngredient, int cookingTime) {
        return new CoffeeRecipeBuilder(result, cupIngredient, beanIngredient, extraIngredient, waterIngredient, milkIngredient, cookingTime);
    }

    @Override
    public @NotNull CoffeeRecipeBuilder unlockedBy(@NotNull String criteriaName, @NotNull Criterion<?> criterion) {
        this.criteria.put(criteriaName, criterion);
        return this;
    }

    @Override
    public @NotNull CoffeeRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput consumer, @NotNull ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement = consumer.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        consumer.accept(new Result(id, this.group == null ? "" : this.group, this.cupIngredient, this.beanIngredient,
                this.extraIngredient, this.waterIngredient, this.milkIngredient, this.result, this.cookingTime,
                advancement.build(id.withPath("recipes/coffee/"))));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public record Result(
            ResourceLocation id,
            String group,
            Ingredient cupIngredient,
            Ingredient beanIngredient,
            Ingredient extraIngredient,
            FluidIngredient waterIngredient,
            FluidIngredient milkIngredient,
            ItemStack result,
            int cookingTime,
            AdvancementHolder advancement
    ) implements FinishedRecipe {
        @Override
        public void serializeRecipeData(@NotNull JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("cup", this.cupIngredient.toJson(false));
            json.add("bean", this.beanIngredient.toJson(false));
            if (!this.extraIngredient.isEmpty()) {
                json.add("extra", this.extraIngredient.toJson(true));
            }
            json.add("water", this.waterIngredient.toJson(false));
            if (!this.milkIngredient.isEmpty()) {
                json.add("milk", this.milkIngredient.toJson(true));
            }

            json.add("result", Util.getOrThrow(CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.encodeStart(JsonOps.INSTANCE, this.result), IllegalStateException::new));
            json.addProperty("cookingtime", this.cookingTime);
        }

        @Override
        public @NotNull RecipeSerializer<?> type() {
            return ModRecipeSerializers.COFFEE.get();
        }
    }
}
