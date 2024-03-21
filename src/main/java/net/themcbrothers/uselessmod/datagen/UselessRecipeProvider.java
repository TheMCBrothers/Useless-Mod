package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessCoffeeTypes;
import net.themcbrothers.uselessmod.core.UselessItems;
import net.themcbrothers.uselessmod.datagen.builder.CoffeeRecipeBuilder;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.world.item.crafting.LightSwitchConvertRecipe;
import net.themcbrothers.uselessmod.world.item.crafting.PaintBrushDyeRecipe;

import java.util.concurrent.CompletableFuture;

import static net.themcbrothers.uselessmod.UselessMod.rl;

public class UselessRecipeProvider extends RecipeProvider {
    public UselessRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        // WOOD
        planksFromLogs(consumer, UselessBlocks.USELESS_OAK_PLANKS, UselessTags.Items.USELESS_OAK_LOGS, 4);
        woodFromLogs(consumer, UselessBlocks.USELESS_OAK_WOOD, UselessBlocks.USELESS_OAK_LOG);
        woodFromLogs(consumer, UselessBlocks.STRIPPED_USELESS_OAK_WOOD, UselessBlocks.STRIPPED_USELESS_OAK_LOG);
        stairBuilder(UselessBlocks.USELESS_OAK_STAIRS, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_stairs").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, UselessBlocks.USELESS_OAK_SLAB, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_slab").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        fenceBuilder(UselessBlocks.USELESS_OAK_FENCE, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_fence").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        fenceGateBuilder(UselessBlocks.USELESS_OAK_FENCE_GATE, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_fence_gate").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        doorBuilder(UselessBlocks.USELESS_OAK_DOOR, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_door").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        trapdoorBuilder(UselessBlocks.USELESS_OAK_TRAPDOOR, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_trapdoor").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        pressurePlateBuilder(RecipeCategory.REDSTONE, UselessBlocks.USELESS_OAK_PRESSURE_PLATE, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_pressure_plate").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        buttonBuilder(UselessBlocks.USELESS_OAK_BUTTON, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_button").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        signBuilder(UselessBlocks.USELESS_OAK_SIGN, Ingredient.of(UselessBlocks.USELESS_OAK_PLANKS)).group("wooden_sign").unlockedBy("has_planks", has(UselessBlocks.USELESS_OAK_WOOD.get())).save(consumer);
        hangingSign(consumer, UselessBlocks.USELESS_OAK_HANGING_SIGN, UselessBlocks.STRIPPED_USELESS_OAK_LOG);

        // METAL
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.RAW_USELESS.get(), 9).requires(UselessBlocks.RAW_USELESS_BLOCK.get()).group(null).unlockedBy("has_raw_useless_block", has(UselessBlocks.RAW_USELESS_BLOCK.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.RAW_SUPER_USELESS.get(), 9).requires(UselessBlocks.RAW_SUPER_USELESS_BLOCK.get()).group(null).unlockedBy("has_raw_super_useless_block", has(UselessBlocks.RAW_SUPER_USELESS_BLOCK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.RAW_USELESS_BLOCK.get()).define('#', UselessItems.RAW_USELESS.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_raw_useless", has(UselessItems.RAW_USELESS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.RAW_SUPER_USELESS_BLOCK.get()).define('#', UselessItems.RAW_SUPER_USELESS.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_raw_super_useless", has(UselessItems.RAW_SUPER_USELESS.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.USELESS_NUGGET.get(), 9).requires(UselessItems.USELESS_INGOT.get()).group(null).unlockedBy("has_useless_ingot", has(UselessItems.USELESS_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.SUPER_USELESS_NUGGET.get(), 9).requires(UselessItems.SUPER_USELESS_INGOT.get()).group(null).unlockedBy("has_super_useless_ingot", has(UselessItems.SUPER_USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessItems.USELESS_INGOT.get()).define('#', UselessItems.USELESS_NUGGET.get()).pattern("###").pattern("###").pattern("###").group("useless_ingot").unlockedBy("has_useless_nugget", has(UselessItems.USELESS_NUGGET.get())).save(consumer, rl("useless_ingot_from_nuggets"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT.get()).define('#', UselessItems.SUPER_USELESS_NUGGET.get()).pattern("###").pattern("###").pattern("###").group("super_useless_ingot").unlockedBy("has_super_useless_nugget", has(UselessItems.SUPER_USELESS_NUGGET.get())).save(consumer, rl("super_useless_ingot_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.USELESS_INGOT.get(), 9).requires(UselessBlocks.USELESS_BLOCK.get()).group("useless_ingot").unlockedBy("has_useless_block", has(UselessBlocks.USELESS_BLOCK.get())).save(consumer, rl("useless_ingot_from_useless_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT.get(), 9).requires(UselessBlocks.SUPER_USELESS_BLOCK.get()).group("super_useless_ingot").unlockedBy("has_super_useless_block", has(UselessBlocks.SUPER_USELESS_BLOCK.get())).save(consumer, rl("super_useless_ingot_from_super_useless_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.USELESS_BLOCK.get()).define('#', UselessItems.USELESS_INGOT.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(UselessItems.USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.SUPER_USELESS_BLOCK.get()).define('#', UselessItems.SUPER_USELESS_INGOT.get()).pattern("###").pattern("###").pattern("###").group(null).unlockedBy("has_super_useless_ingot", has(UselessItems.SUPER_USELESS_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.USELESS_BARS.get(), 16).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.SUPER_USELESS_BARS.get(), 16).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("###").group(null).unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);

        doorBuilder(UselessBlocks.USELESS_DOOR, Ingredient.of(UselessTags.Items.INGOTS_USELESS)).unlockedBy(getHasName(UselessItems.USELESS_INGOT), has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        doorBuilder(UselessBlocks.SUPER_USELESS_DOOR, Ingredient.of(UselessTags.Items.INGOTS_SUPER_USELESS)).unlockedBy(getHasName(UselessItems.SUPER_USELESS_INGOT), has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.USELESS_TRAPDOOR).define('#', UselessTags.Items.INGOTS_USELESS).pattern("##").pattern("##").unlockedBy(getHasName(UselessItems.USELESS_INGOT), has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.SUPER_USELESS_TRAPDOOR).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("##").pattern("##").unlockedBy(getHasName(UselessItems.SUPER_USELESS_INGOT), has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), RecipeCategory.MISC, UselessItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), RecipeCategory.MISC, UselessItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_blasting"));

        // TOOLS ARMOR
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.USELESS_SHEARS).define('#', UselessTags.Items.INGOTS_USELESS).pattern(" #").pattern("# ").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_SHIELD).define('W', UselessTags.Items.INGOTS_USELESS).define('o', Items.SHIELD).pattern("WoW").pattern("WWW").pattern(" W ").unlockedBy("has_shield", has(Items.SHIELD)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_SHIELD).define('W', UselessTags.Items.INGOTS_SUPER_USELESS).define('o', Items.SHIELD).pattern("WoW").pattern("WWW").pattern(" W ").unlockedBy("has_shield", has(Items.SHIELD)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_SWORD).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_SWORD).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.USELESS_PICKAXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.SUPER_USELESS_PICKAXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.USELESS_AXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.SUPER_USELESS_AXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.USELESS_SHOVEL).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.SUPER_USELESS_SHOVEL).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.USELESS_HOE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, UselessItems.SUPER_USELESS_HOE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_HELMET).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_HELMET).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_CHESTPLATE).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_CHESTPLATE).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_LEGGINGS).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_LEGGINGS).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_BOOTS).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_BOOTS).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.USELESS_ELYTRA).define('#', UselessTags.Items.INGOTS_USELESS).define('X', Items.ELYTRA).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, UselessItems.SUPER_USELESS_ELYTRA).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).define('X', Items.ELYTRA).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);

        // Rails
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, UselessBlocks.USELESS_RAIL, 16).define('#', Items.STICK).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X X").pattern("X#X").pattern("X X").unlockedBy("has_minecart", has(Items.MINECART)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, UselessBlocks.USELESS_POWERED_RAIL, 6).define('R', Items.REDSTONE).define('#', Items.STICK).define('U', UselessTags.Items.INGOTS_USELESS).define('X', Tags.Items.INGOTS_GOLD).pattern("X X").pattern("U#U").pattern("XRX").unlockedBy("has_rail", has(UselessBlocks.USELESS_RAIL)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, UselessBlocks.USELESS_DETECTOR_RAIL, 6).define('R', Items.REDSTONE).define('#', Blocks.STONE_PRESSURE_PLATE).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X X").pattern("X#X").pattern("XRX").unlockedBy("has_rail", has(UselessBlocks.USELESS_RAIL)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, UselessBlocks.USELESS_ACTIVATOR_RAIL, 6).define('#', Blocks.REDSTONE_TORCH).define('S', Items.STICK).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XSX").pattern("X#X").pattern("XSX").unlockedBy("has_rail", has(UselessBlocks.USELESS_RAIL)).save(consumer);

        // OTHER
        carpet(consumer, UselessBlocks.USELESS_CARPET, UselessBlocks.USELESS_WOOL.get());
        bedFromPlanksAndWool(consumer, UselessBlocks.USELESS_BED, UselessBlocks.USELESS_WOOL.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, UselessBlocks.LIGHT_SWITCH).requires(Items.LEVER).requires(Tags.Items.DYES_WHITE).unlockedBy("has_lever", has(Items.LEVER)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.CUP, 2).define('#', Blocks.QUARTZ_SLAB).pattern("# #").pattern(" # ").unlockedBy("has_quartz_slab", has(Blocks.QUARTZ_SLAB)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.MACHINE_SUPPLIER).define('#', Tags.Items.COBBLESTONE_NORMAL).define('X', Tags.Items.GLASS).define('R', Tags.Items.DUSTS_REDSTONE).pattern("#X#").pattern("XRX").pattern("#X#").unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, UselessBlocks.COFFEE_MACHINE).define('#', Tags.Items.GLASS).define('X', UselessBlocks.MACHINE_SUPPLIER).define('R', Items.REDSTONE_TORCH).define('U', UselessTags.Items.INGOTS_USELESS).pattern("#U#").pattern("RXR").unlockedBy("has_supplier", has(UselessBlocks.MACHINE_SUPPLIER)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UselessBlocks.PAINT_BUCKET).define('#', UselessTags.Items.INGOTS_USELESS).define('X', Tags.Items.RODS_WOODEN).pattern("#X#").pattern(" # ").unlockedBy(getHasName(UselessItems.USELESS_INGOT), has(UselessTags.Items.INGOTS_USELESS)).save(consumer);

        // Lamps
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.WHITE_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_WHITE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.ORANGE_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_ORANGE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.MAGENTA_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_MAGENTA).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.LIGHT_BLUE_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_LIGHT_BLUE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.YELLOW_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_YELLOW).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.LIME_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_LIME).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.PINK_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_PINK).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.GRAY_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_GRAY).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.LIGHT_GRAY_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_LIGHT_GRAY).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.CYAN_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_CYAN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.PURPLE_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_PURPLE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.BLUE_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_BLUE).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.BROWN_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_BROWN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.GREEN_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_GREEN).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.RED_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_RED).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UselessBlocks.BLACK_LAMP).requires(Ingredient.of(UselessTags.Items.LAMPS)).requires(Tags.Items.DYES_BLACK).group("uselessmod:lamps").unlockedBy("has_lamp", has(UselessTags.Items.LAMPS)).unlockedBy("has_redstone_lamp", has(Items.REDSTONE_LAMP)).save(consumer);

        // Coffee
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(UselessCoffeeTypes.BLACK.get()), Ingredient.of(UselessBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(Fluids.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_black"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(UselessCoffeeTypes.MILK.get()), Ingredient.of(UselessBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(Fluids.WATER, 250), FluidIngredient.of(NeoForgeMod.MILK.value(), 100), 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_milk"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(UselessCoffeeTypes.SUGAR.get()), Ingredient.of(UselessBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(Fluids.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_sugar"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(UselessCoffeeTypes.MILK_SUGAR.get()), Ingredient.of(UselessBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(Fluids.WATER, 250), FluidIngredient.of(NeoForgeMod.MILK.value(), 100), 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_milk_sugar"));

        // Special Recipes
        SpecialRecipeBuilder.special(LightSwitchConvertRecipe::new).save(consumer, UselessMod.rl("light_switch_convert").toString());
        SpecialRecipeBuilder.special(PaintBrushDyeRecipe::new).save(consumer, UselessMod.rl("paint_brush_dye").toString());

        // Usages of Items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.RED_DYE).requires(UselessBlocks.RED_ROSE).group("red_dye").unlockedBy("has_red_flower", has(UselessBlocks.RED_ROSE)).save(consumer, rl("red_dye_from_rose"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.BLUE_DYE).requires(UselessBlocks.BLUE_ROSE).group("blue_dye").unlockedBy("has_blue_flower", has(UselessBlocks.BLUE_ROSE)).save(consumer, rl("blue_dye_from_rose"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.BONE_MEAL, 3).requires(UselessItems.USELESS_BONE).group("bonemeal").unlockedBy("has_bone", has(UselessItems.USELESS_BONE)).save(consumer, rl("bone_meal"));
    }
}
