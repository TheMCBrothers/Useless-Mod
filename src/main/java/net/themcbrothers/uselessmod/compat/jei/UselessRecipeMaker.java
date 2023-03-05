package net.themcbrothers.uselessmod.compat.jei;

import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.init.ModTiers;

import java.util.List;
import java.util.stream.Stream;

public class UselessRecipeMaker {
    public static List<IJeiAnvilRecipe> getAnvilRecipes(IVanillaRecipeFactory vanillaRecipeFactory) {
        return getRepairData().flatMap(repairData -> getRepairRecipes(repairData, vanillaRecipeFactory)).toList();
    }

    private static Stream<RepairData> getRepairData() {
        return Stream.of(
                new RepairData(ModTiers.USELESS.getRepairIngredient(),
                        new ItemStack(ModItems.USELESS_SWORD),
                        new ItemStack(ModItems.USELESS_PICKAXE),
                        new ItemStack(ModItems.USELESS_AXE),
                        new ItemStack(ModItems.USELESS_SHOVEL),
                        new ItemStack(ModItems.USELESS_HOE),
                        new ItemStack(ModItems.USELESS_SHIELD)
                ),
                new RepairData(ModTiers.SUPER_USELESS.getRepairIngredient(),
                        new ItemStack(ModItems.SUPER_USELESS_SWORD),
                        new ItemStack(ModItems.SUPER_USELESS_PICKAXE),
                        new ItemStack(ModItems.SUPER_USELESS_AXE),
                        new ItemStack(ModItems.SUPER_USELESS_SHOVEL),
                        new ItemStack(ModItems.SUPER_USELESS_HOE),
                        new ItemStack(ModItems.SUPER_USELESS_SHIELD)
                ),
                new RepairData(Ingredient.of(Items.PHANTOM_MEMBRANE),
                        new ItemStack(ModItems.USELESS_ELYTRA),
                        new ItemStack(ModItems.SUPER_USELESS_ELYTRA)
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
