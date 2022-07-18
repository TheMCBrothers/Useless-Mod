package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;

import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessRecipeProvider extends RecipeProvider {
    public UselessRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // WOOD
        ShapelessRecipeBuilder.shapeless(ModBlocks.USELESS_OAK_WOOD.get(), 4).requires(UselessTags.Items.USELESS_OAK_LOGS).group("planks").unlockedBy("has_logs", has(UselessTags.Items.USELESS_OAK_LOGS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getWood(), 3).define('#', ModBlocks.USELESS_OAK_WOOD.getLog()).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(ModBlocks.USELESS_OAK_WOOD.getLog())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getStrippedWood(), 3).define('#', ModBlocks.USELESS_OAK_WOOD.getStrippedLog()).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(ModBlocks.USELESS_OAK_WOOD.getStrippedLog())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getStairs(), 4).define('#', ModBlocks.USELESS_OAK_WOOD.get()).pattern("#  ").pattern("## ").pattern("###").group("wooden_stairs").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getSlab(), 6).define('#', ModBlocks.USELESS_OAK_WOOD.get()).pattern("###").group("wooden_slab").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getFence(), 3).define('W', ModBlocks.USELESS_OAK_WOOD.get()).define('#', Tags.Items.RODS_WOODEN).pattern("W#W").pattern("W#W").group("wooden_fence").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getFenceGate()).define('W', ModBlocks.USELESS_OAK_WOOD.get()).define('#', Tags.Items.RODS_WOODEN).pattern("#W#").pattern("#W#").group("wooden_fence_gate").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getDoor(), 3).define('#', ModBlocks.USELESS_OAK_WOOD.get()).pattern("##").pattern("##").pattern("##").group("wooden_door").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getTrapdoor(), 2).define('#', ModBlocks.USELESS_OAK_WOOD.get()).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getPressurePlate()).define('#', ModBlocks.USELESS_OAK_WOOD.get()).pattern("##").group("wooden_pressure_plate").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.USELESS_OAK_WOOD.getButton()).requires(ModBlocks.USELESS_OAK_WOOD.get()).group("wooden_button").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_OAK_WOOD.getSign(), 3).define('#', ModBlocks.USELESS_OAK_WOOD.get()).define('X', Tags.Items.RODS_WOODEN).pattern("###").pattern("###").pattern(" X ").group("wooden_sign").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);

        // METAL
        ShapelessRecipeBuilder.shapeless(ModItems.RAW_USELESS.get(), 9).requires(ModBlocks.RAW_USELESS_BLOCK.get()).group(null).unlockedBy("has_raw_useless_block", has(ModBlocks.RAW_USELESS_BLOCK.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.RAW_SUPER_USELESS.get(), 9).requires(ModBlocks.RAW_SUPER_USELESS_BLOCK.get()).group(null).unlockedBy("has_raw_super_useless_block", has(ModBlocks.RAW_SUPER_USELESS_BLOCK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.RAW_USELESS_BLOCK.get()).define('#', ModItems.RAW_USELESS.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_raw_useless", has(ModItems.RAW_USELESS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.RAW_SUPER_USELESS_BLOCK.get()).define('#', ModItems.RAW_SUPER_USELESS.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_raw_super_useless", has(ModItems.RAW_SUPER_USELESS.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.USELESS_NUGGET.get(), 9).requires(ModItems.USELESS_INGOT.get()).group(null).unlockedBy("has_useless_ingot", has(ModItems.USELESS_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.SUPER_USELESS_NUGGET.get(), 9).requires(ModItems.SUPER_USELESS_INGOT.get()).group(null).unlockedBy("has_super_useless_ingot", has(ModItems.SUPER_USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_INGOT.get()).define('#', ModItems.USELESS_NUGGET.get()).pattern("###").pattern("###").pattern("###").group("useless_ingot").unlockedBy("has_useless_nugget", has(ModItems.USELESS_NUGGET.get())).save(consumer, rl("useless_ingot_from_nuggets"));
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_INGOT.get()).define('#', ModItems.SUPER_USELESS_NUGGET.get()).pattern("###").pattern("###").pattern("###").group("super_useless_ingot").unlockedBy("has_super_useless_nugget", has(ModItems.SUPER_USELESS_NUGGET.get())).save(consumer, rl("super_useless_ingot_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(ModItems.USELESS_INGOT.get(), 9).requires(ModBlocks.USELESS_BLOCK.get()).group("useless_ingot").unlockedBy("has_useless_block", has(ModBlocks.USELESS_BLOCK.get())).save(consumer, rl("useless_ingot_from_useless_block"));
        ShapelessRecipeBuilder.shapeless(ModItems.SUPER_USELESS_INGOT.get(), 9).requires(ModBlocks.SUPER_USELESS_BLOCK.get()).group("super_useless_ingot").unlockedBy("has_super_useless_block", has(ModBlocks.SUPER_USELESS_BLOCK.get())).save(consumer, rl("super_useless_ingot_from_super_useless_block"));
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_BLOCK.get()).define('#', ModItems.USELESS_INGOT.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(ModItems.USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.SUPER_USELESS_BLOCK.get()).define('#', ModItems.SUPER_USELESS_INGOT.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_super_useless_ingot", has(ModItems.SUPER_USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_BARS.get(), 16).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.SUPER_USELESS_BARS.get(), 16).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 200).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 200).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 100).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 100).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), ModItems.USELESS_INGOT.get(), 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT.get(), 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_blasting"));

        // TOOLS ARMOR
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SWORD.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_SWORD.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_PICKAXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_PICKAXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_AXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_AXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SHOVEL.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_SHOVEL.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_HOE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_HOE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_HELMET.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_CHESTPLATE.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_LEGGINGS.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_BOOTS.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
    }

    @Override
    public String getName() {
        return "Recipes: uselessmod";
    }
}
