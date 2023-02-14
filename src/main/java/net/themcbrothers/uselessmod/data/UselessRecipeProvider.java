package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.data.builder.CoffeeRecipeBuilder;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModCoffeeTypes;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.util.CoffeeUtils;

import java.util.List;
import java.util.function.Consumer;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessRecipeProvider extends RecipeProvider {
    public UselessRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // WOOD
        planksFromLogs(consumer, ModBlocks.USELESS_OAK_PLANKS, UselessTags.Items.USELESS_OAK_LOGS);
        woodFromLogs(consumer, ModBlocks.USELESS_OAK_WOOD, ModBlocks.USELESS_OAK_LOG);
        woodFromLogs(consumer, ModBlocks.STRIPPED_USELESS_OAK_WOOD, ModBlocks.STRIPPED_USELESS_OAK_LOG);
        stairBuilder(ModBlocks.USELESS_OAK_STAIRS, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_stairs").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        slabBuilder(ModBlocks.USELESS_OAK_SLAB, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_slab").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        fenceBuilder(ModBlocks.USELESS_OAK_FENCE, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_fence").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        fenceGateBuilder(ModBlocks.USELESS_OAK_FENCE_GATE, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_fence_gate").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        doorBuilder(ModBlocks.USELESS_OAK_DOOR, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_door").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        trapdoorBuilder(ModBlocks.USELESS_OAK_TRAPDOOR, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_trapdoor").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        pressurePlateBuilder(ModBlocks.USELESS_OAK_PRESSURE_PLATE, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_pressure_plate").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        buttonBuilder(ModBlocks.USELESS_OAK_BUTTON, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_button").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        signBuilder(ModBlocks.USELESS_OAK_SIGN, Ingredient.of(ModBlocks.USELESS_OAK_PLANKS)).group("wooden_sign").unlockedBy("has_planks", has(ModBlocks.USELESS_OAK_WOOD.get())).save(consumer);

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
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_HELMET.get()).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_CHESTPLATE.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_CHESTPLATE.get()).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_LEGGINGS.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_LEGGINGS.get()).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_BOOTS.get()).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_BOOTS.get()).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);

        // OTHER
        carpet(consumer, ModBlocks.USELESS_CARPET.get(), ModBlocks.USELESS_WOOL.get());
        bedFromPlanksAndWool(consumer, ModBlocks.USELESS_BED.get(), ModBlocks.USELESS_WOOL.get());

        // Lamps
        ShapelessRecipeBuilder.shapeless(ModBlocks.WHITE_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_WHITE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.ORANGE_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_ORANGE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.MAGENTA_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_MAGENTA).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.LIGHT_BLUE_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_LIGHT_BLUE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.YELLOW_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_YELLOW).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.LIME_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_LIME).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.PINK_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_PINK).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.GRAY_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_GRAY).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.LIGHT_GRAY_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_LIGHT_GRAY).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.CYAN_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_CYAN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.PURPLE_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_PURPLE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BLUE_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_BLUE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BROWN_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_BROWN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.GREEN_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_GREEN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.RED_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_RED).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModBlocks.BLACK_LAMP).requires(Ingredient.merge(List.of(Ingredient.of(Items.REDSTONE_LAMP), Ingredient.of(UselessTags.Items.LAMPS)))).requires(Tags.Items.DYES_BLACK).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);

        // Coffee
        CoffeeRecipeBuilder.coffee(CoffeeUtils.getCoffeeStack(ModCoffeeTypes.BLACK.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_black"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.getCoffeeStack(ModCoffeeTypes.MILK.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.of(Tags.Fluids.MILK, 100), 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_milk"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.getCoffeeStack(ModCoffeeTypes.SUGAR.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_sugar"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.getCoffeeStack(ModCoffeeTypes.MILK_SUGAR.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.of(Tags.Fluids.MILK, 100), 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_milk_sugar"));
    }

    @Override
    public String getName() {
        return "Recipes: uselessmod";
    }
}
