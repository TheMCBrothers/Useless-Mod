package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.core.UselessTiers;

import java.util.List;
import java.util.stream.Stream;

public class UselessRecipeMaker {
    public static List<IJeiAnvilRecipe> getAnvilRecipes(IVanillaRecipeFactory vanillaRecipeFactory) {
        return getRepairData().flatMap(repairData -> getRepairRecipes(repairData, vanillaRecipeFactory)).toList();
    }

    private static Stream<RepairData> getRepairData() {
        return Stream.of(
                new RepairData(UselessTiers.USELESS.getRepairIngredient(),
                        new ItemStack(UselessItems.USELESS_SWORD.value()),
                        new ItemStack(UselessItems.USELESS_PICKAXE.value()),
                        new ItemStack(UselessItems.USELESS_AXE.value()),
                        new ItemStack(UselessItems.USELESS_SHOVEL.value()),
                        new ItemStack(UselessItems.USELESS_HOE.value()),
                        new ItemStack(UselessItems.USELESS_SHIELD.value())
                ),
                new RepairData(UselessTiers.SUPER_USELESS.getRepairIngredient(),
                        new ItemStack(UselessItems.SUPER_USELESS_SWORD.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_PICKAXE.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_AXE.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_SHOVEL.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_HOE.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_SHIELD.value())
                ),
                new RepairData(Ingredient.of(Items.PHANTOM_MEMBRANE),
                        new ItemStack(UselessItems.USELESS_ELYTRA.value()),
                        new ItemStack(UselessItems.SUPER_USELESS_ELYTRA.value())
                )
        );
    }

    private static Stream<IJeiAnvilRecipe> getRepairRecipes(RepairData repairData, IVanillaRecipeFactory vanillaRecipeFactory) {
        Ingredient repairIngredient = repairData.getRepairIngredient();
        List<ItemStack> repairables = repairData.getRepairables();

        List<ItemStack> repairMaterials = List.of(repairIngredient.getItems());

        return repairables.stream()
                .mapMulti((itemStack, consumer) -> {
                    ItemStack damagedThreeQuarters = itemStack.copy();
                    damagedThreeQuarters.setDamageValue(damagedThreeQuarters.getMaxDamage() * 3 / 4);
                    ItemStack damagedHalf = itemStack.copy();
                    damagedHalf.setDamageValue(damagedHalf.getMaxDamage() / 2);

                    IJeiAnvilRecipe repairWithSame = vanillaRecipeFactory.createAnvilRecipe(List.of(damagedThreeQuarters), List.of(damagedThreeQuarters), List.of(damagedHalf));
                    consumer.accept(repairWithSame);

                    if (!repairMaterials.isEmpty()) {
                        ItemStack damagedFully = itemStack.copy();
                        damagedFully.setDamageValue(damagedFully.getMaxDamage());
                        IJeiAnvilRecipe repairWithMaterial = vanillaRecipeFactory.createAnvilRecipe(List.of(damagedFully), repairMaterials, List.of(damagedThreeQuarters));
                        consumer.accept(repairWithMaterial);
                    }
                });
    }

    private static class RepairData {
        private final Ingredient repairIngredient;
        private final List<ItemStack> repairables;

        public RepairData(Ingredient repairIngredient, ItemStack... repairables) {
            this.repairIngredient = repairIngredient;
            this.repairables = List.of(repairables);
        }

        public Ingredient getRepairIngredient() {
            return repairIngredient;
        }

        public List<ItemStack> getRepairables() {
            return repairables;
        }
    }
}
