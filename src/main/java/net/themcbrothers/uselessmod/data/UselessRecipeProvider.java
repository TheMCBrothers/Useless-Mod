package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.themcbrothers.lib.crafting.FluidIngredient;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.data.builder.CoffeeRecipeBuilder;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModCoffeeTypes;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.init.ModRecipeSerializers;
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), ModItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_USELESS), ModItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_raw_useless", has(UselessTags.Items.RAW_MATERIALS_USELESS)).save(consumer, rl("useless_ingot_from_raw_useless_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_raw_super_useless", has(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_raw_super_useless_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_USELESS), ModItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_USELESS), ModItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_ore", has(UselessTags.Items.ORES_USELESS)).save(consumer, rl("useless_ingot_from_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.ORES_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_ore", has(UselessTags.Items.ORES_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_ore_blasting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), ModItems.USELESS_INGOT, 0.7F, 200).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 200).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_USELESS), ModItems.USELESS_INGOT, 0.7F, 100).group("useless_ingot").unlockedBy("has_useless_dust", has(UselessTags.Items.DUSTS_USELESS)).save(consumer, rl("useless_ingot_from_useless_dust_blasting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(UselessTags.Items.DUSTS_SUPER_USELESS), ModItems.SUPER_USELESS_INGOT, 1.0F, 100).group("super_useless_ingot").unlockedBy("has_super_useless_dust", has(UselessTags.Items.DUSTS_SUPER_USELESS)).save(consumer, rl("super_useless_ingot_from_super_useless_dust_blasting"));

        // TOOLS ARMOR
        ShapedRecipeBuilder.shaped(ModItems.PAINT_BRUSH).define('W', Tags.Items.RODS_WOODEN).define('o', ItemTags.WOOL).pattern("WoW").pattern("WWW").pattern(" W ").unlockedBy("has_wool", has(ItemTags.WOOL)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SHEARS).define('#', UselessTags.Items.INGOTS_USELESS).pattern(" #").pattern("# ").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SHIELD).define('W', UselessTags.Items.INGOTS_USELESS).define('o', Items.SHIELD).pattern("WoW").pattern("WWW").pattern(" W ").unlockedBy("has_shield", has(Items.SHIELD)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_SHIELD).define('W', UselessTags.Items.INGOTS_SUPER_USELESS).define('o', Items.SHIELD).pattern("WoW").pattern("WWW").pattern(" W ").unlockedBy("has_shield", has(Items.SHIELD)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SWORD).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_SWORD).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("X").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_PICKAXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_PICKAXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_AXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_AXE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_SHOVEL).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_SHOVEL).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("X").pattern("#").pattern("#").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_HOE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_HOE).define('#', Tags.Items.RODS_WOODEN).define('X', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_HELMET).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_HELMET).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_CHESTPLATE).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_CHESTPLATE).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("###").pattern("###").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_LEGGINGS).define('#', UselessTags.Items.INGOTS_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_LEGGINGS).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("###").pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_BOOTS).define('#', UselessTags.Items.INGOTS_USELESS).pattern("# #").pattern("# #").unlockedBy("has_useless_ingot", has(UselessTags.Items.INGOTS_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_BOOTS).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).pattern("# #").pattern("# #").unlockedBy("has_super_useless_ingot", has(UselessTags.Items.INGOTS_SUPER_USELESS)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.USELESS_ELYTRA).define('#', UselessTags.Items.INGOTS_USELESS).define('X', Items.ELYTRA).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);
        ShapedRecipeBuilder.shaped(ModItems.SUPER_USELESS_ELYTRA).define('#', UselessTags.Items.INGOTS_SUPER_USELESS).define('X', Items.ELYTRA).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_elytra", has(Items.ELYTRA)).save(consumer);

        // Rails
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_RAIL, 16).define('#', Items.STICK).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X X").pattern("X#X").pattern("X X").unlockedBy("has_minecart", has(Items.MINECART)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_POWERED_RAIL, 6).define('R', Items.REDSTONE).define('#', Items.STICK).define('U', UselessTags.Items.INGOTS_USELESS).define('X', Tags.Items.INGOTS_GOLD).pattern("X X").pattern("U#U").pattern("XRX").unlockedBy("has_rail", has(ModBlocks.USELESS_RAIL)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_DETECTOR_RAIL, 6).define('R', Items.REDSTONE).define('#', Blocks.STONE_PRESSURE_PLATE).define('X', UselessTags.Items.INGOTS_USELESS).pattern("X X").pattern("X#X").pattern("XRX").unlockedBy("has_rail", has(ModBlocks.USELESS_RAIL)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.USELESS_ACTIVATOR_RAIL, 6).define('#', Blocks.REDSTONE_TORCH).define('S', Items.STICK).define('X', UselessTags.Items.INGOTS_USELESS).pattern("XSX").pattern("X#X").pattern("XSX").unlockedBy("has_rail", has(ModBlocks.USELESS_RAIL)).save(consumer);

        // OTHER
        carpet(consumer, ModBlocks.USELESS_CARPET, ModBlocks.USELESS_WOOL.get());
        bedFromPlanksAndWool(consumer, ModBlocks.USELESS_BED, ModBlocks.USELESS_WOOL.get());
        ShapelessRecipeBuilder.shapeless(ModBlocks.LIGHT_SWITCH).requires(Items.LEVER).requires(Tags.Items.DYES_WHITE).unlockedBy("has_lever", has(Items.LEVER)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.CUP, 2).define('#', Blocks.QUARTZ_SLAB).pattern("# #").pattern(" # ").unlockedBy("has_quartz_slab", has(Blocks.QUARTZ_SLAB)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.MACHINE_SUPPLIER).define('#', Tags.Items.COBBLESTONE_NORMAL).define('X', Tags.Items.GLASS).define('R', Tags.Items.DUSTS_REDSTONE).pattern("#X#").pattern("XRX").pattern("#X#").unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE)).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.COFFEE_MACHINE).define('#', Tags.Items.GLASS).define('X', ModBlocks.MACHINE_SUPPLIER).define('R', Items.REDSTONE_TORCH).define('U', UselessTags.Items.INGOTS_USELESS).pattern("#U#").pattern("RXR").unlockedBy("has_supplier", has(ModBlocks.MACHINE_SUPPLIER)).save(consumer);

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
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(ModCoffeeTypes.BLACK.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_black"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(ModCoffeeTypes.MILK.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.EMPTY, FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.of(Tags.Fluids.MILK, 100), 100).unlockedBy("has_coffee_bean", has(UselessTags.Items.CROPS_COFFEEBEAN)).save(consumer, rl("coffee_milk"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(ModCoffeeTypes.SUGAR.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.EMPTY, 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_sugar"));
        CoffeeRecipeBuilder.coffee(CoffeeUtils.createCoffeeStack(ModCoffeeTypes.MILK_SUGAR.get()), Ingredient.of(ModBlocks.CUP), Ingredient.of(UselessTags.Items.CROPS_COFFEEBEAN), Ingredient.of(Items.SUGAR), FluidIngredient.of(FluidTags.WATER, 250), FluidIngredient.of(Tags.Fluids.MILK, 100), 100).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer, rl("coffee_milk_sugar"));

        // Special Recipes
        SpecialRecipeBuilder.special(ModRecipeSerializers.LIGHT_SWITCH_CONVERT.get()).save(consumer, UselessMod.rl("light_switch_convert").toString());
        SpecialRecipeBuilder.special(ModRecipeSerializers.PAINT_BRUSH_DYE.get()).save(consumer, UselessMod.rl("paint_brush_dye").toString());

        // Usages of Items
        ShapelessRecipeBuilder.shapeless(Items.RED_DYE).requires(ModBlocks.RED_ROSE).group("red_dye").unlockedBy("has_red_flower", has(ModBlocks.RED_ROSE)).save(consumer, rl("red_dye_from_rose"));
        ShapelessRecipeBuilder.shapeless(Items.BLUE_DYE).requires(ModBlocks.BLUE_ROSE).group("blue_dye").unlockedBy("has_blue_flower", has(ModBlocks.BLUE_ROSE)).save(consumer, rl("blue_dye_from_rose"));
        ShapelessRecipeBuilder.shapeless(Items.BONE_MEAL, 3).requires(ModItems.USELESS_BONE).group("bonemeal").unlockedBy("has_bone", has(ModItems.USELESS_BONE)).save(consumer, rl("bone_meal"));
    }

    @Override
    public String getName() {
        return "Recipes: uselessmod";
    }
}
