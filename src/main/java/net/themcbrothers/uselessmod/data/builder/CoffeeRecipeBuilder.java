package net.themcbrothers.uselessmod.data.builder;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;

import java.util.Objects;
import java.util.function.Consumer;

public class CoffeeRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final Ingredient cupIngredient;
    private final Ingredient beanIngredient;
    private final Ingredient extraIngredient;
    private final FluidIngredient waterIngredient;
    private final FluidIngredient milkIngredient;
    private final int cookingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
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
    public @NotNull CoffeeRecipeBuilder unlockedBy(@NotNull String criteriaName, @NotNull CriterionTriggerInstance triggerInstance) {
        this.advancement.addCriterion(criteriaName, triggerInstance);
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
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, this.group == null ? "" : this.group, this.cupIngredient, this.beanIngredient,
                this.extraIngredient, this.waterIngredient, this.milkIngredient, this.result, this.cookingTime, this.advancement,
                new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getItem().getItemCategory().getRecipeFolderName() + "/" + id.getPath())));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient cupIngredient;
        private final Ingredient beanIngredient;
        private final Ingredient extraIngredient;
        private final FluidIngredient waterIngredient;
        private final FluidIngredient milkIngredient;
        private final ItemStack result;
        private final int cookingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, String group, Ingredient cupIngredient, Ingredient beanIngredient, Ingredient extraIngredient,
                      FluidIngredient waterIngredient, FluidIngredient milkIngredient, ItemStack result, int cookingTime,
                      Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.group = group;
            this.cupIngredient = cupIngredient;
            this.beanIngredient = beanIngredient;
            this.extraIngredient = extraIngredient;
            this.waterIngredient = waterIngredient;
            this.milkIngredient = milkIngredient;
            this.result = result;
            this.cookingTime = cookingTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("cup", this.cupIngredient.toJson());
            json.add("bean", this.beanIngredient.toJson());
            if (!this.extraIngredient.isEmpty()) {
                json.add("extra", this.extraIngredient.toJson());
            }
            json.add("water", this.waterIngredient.serialize());
            if (!Objects.equals(this.milkIngredient, FluidIngredient.EMPTY)) {
                json.add("milk", this.milkIngredient.serialize());
            }

            JsonObject resultObject = new JsonObject();
            resultObject.addProperty("item", String.valueOf(ForgeRegistries.ITEMS.getKey(this.result.getItem())));
            if (this.result.hasTag() && this.result.getTag() != null) {
                resultObject.addProperty("nbt", this.result.getTag().toString());
            }

            json.add("result", resultObject);
            json.addProperty("cookingtime", this.cookingTime);
        }

        @Override
        public @NotNull ResourceLocation getId() {
            return this.id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return ModRecipeSerializers.COFFEE.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
