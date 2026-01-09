package net.themcbrothers.uselessmod.datagen.builder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.themcbrothers.uselessmod.world.item.crafting.CoffeeRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CoffeeRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    @Nullable
    private final Ingredient extraIngredient;
    private final SizedFluidIngredient waterIngredient;
    @Nullable
    private final SizedFluidIngredient milkIngredient;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private CoffeeRecipeBuilder(ItemStack result, Ingredient cupIngredient, Ingredient beanIngredient, @Nullable Ingredient extraIngredient,
                                SizedFluidIngredient waterIngredient, @Nullable SizedFluidIngredient milkIngredient, int cookingTime) {
        this.result = result;
        this.cupIngredient = cupIngredient;
        this.beanIngredient = beanIngredient;
        this.extraIngredient = extraIngredient;
        this.waterIngredient = waterIngredient;
        this.milkIngredient = milkIngredient;
        this.cookingTime = cookingTime;
    }

    public static CoffeeRecipeBuilder coffee(ItemStack result, Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                                             SizedFluidIngredient waterIngredient, @Nullable SizedFluidIngredient milkIngredient, int cookingTime) {
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
    public void save(RecipeOutput output, @NotNull ResourceKey<Recipe<?>> resourceKey) {
        this.ensureValid(resourceKey);
        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
                .rewards(AdvancementRewards.Builder.recipe(resourceKey))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        output.accept(resourceKey,
                new CoffeeRecipe(this.group == null ? "" : this.group, this.cupIngredient, this.beanIngredient,
                        Optional.ofNullable(this.extraIngredient), this.waterIngredient, Optional.ofNullable(this.milkIngredient), this.result, this.cookingTime),
                advancement.build(resourceKey.identifier().withPrefix("recipes/coffee/")));
    }

    private void ensureValid(ResourceKey<Recipe<?>> id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id.identifier());
        }
    }
}
