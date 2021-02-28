package themcbros.uselessmod.datagen;

import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.block.LampBlock;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.FluidInit;
import themcbros.uselessmod.init.ItemInit;

import java.util.function.Consumer;

import static themcbros.uselessmod.UselessMod.rl;
import static themcbros.uselessmod.helpers.TagUtils.forgeItemTag;
import static themcbros.uselessmod.helpers.TagUtils.uselessItemTag;

/**
 * @author TheMCBrothers
 */
public class UselessRecipeProvider extends RecipeProvider {

    public UselessRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        final ITag.INamedTag<Item> oresUseless = forgeItemTag("ores/useless");
        final ITag.INamedTag<Item> oresSuperUseless = forgeItemTag("ores/super_useless");
        final ITag.INamedTag<Item> dustsUseless = forgeItemTag("dusts/useless");
        final ITag.INamedTag<Item> dustsSuperUseless = forgeItemTag("dusts/super_useless");
        final ITag.INamedTag<Item> ingotsUseless = forgeItemTag("ingots/useless");
        final ITag.INamedTag<Item> ingotsSuperUseless = forgeItemTag("ingots/super_useless");
        final ITag.INamedTag<Item> nuggetsUseless = forgeItemTag("nuggets/useless");
        final ITag.INamedTag<Item> nuggetsSuperUseless = forgeItemTag("nuggets/super_useless");
        final ITag.INamedTag<Item> storageBlocksUseless = forgeItemTag("storage_blocks/useless");
        final ITag.INamedTag<Item> storageBlocksSuperUseless = forgeItemTag("storage_blocks/super_useless");
        final ITag.INamedTag<Item> gearsUseless = forgeItemTag("gears/useless");
        final ITag.INamedTag<Item> gearsSuperUseless = forgeItemTag("gears/super_useless");

        // Nugget recipes
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.USELESS_NUGGET.get(), 9).addIngredient(ingotsUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).setGroup("useless_nugget").build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.SUPER_USELESS_NUGGET.get(), 9).addIngredient(ingotsSuperUseless).addCriterion("has_super_useless_ingot", hasItem(ingotsSuperUseless)).setGroup("super_useless_nugget").build(consumer);

        // Ingot recipes
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.USELESS_INGOT.get(), 9).addIngredient(storageBlocksUseless).addCriterion("has_useless_nugget", hasItem(storageBlocksUseless)).setGroup("useless_ingot").build(consumer, rl("useless_ingot_from_useless_block"));
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.SUPER_USELESS_INGOT.get(), 9).addIngredient(storageBlocksSuperUseless).addCriterion("has_super_useless_nugget", hasItem(storageBlocksSuperUseless)).setGroup("super_useless_ingot").build(consumer, rl("super_useless_ingot_super_useless_block"));
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_INGOT.get()).patternLine("###").patternLine("###").patternLine("###").key('#', nuggetsUseless).addCriterion("has_useless_nugget", hasItem(nuggetsUseless)).setGroup("useless_ingot").build(consumer, rl("useless_ingot_from_nuggets"));
        ShapedRecipeBuilder.shapedRecipe(ItemInit.SUPER_USELESS_INGOT.get()).patternLine("###").patternLine("###").patternLine("###").key('#', nuggetsSuperUseless).addCriterion("has_super_useless_nugget", hasItem(nuggetsSuperUseless)).setGroup("super_useless_ingot").build(consumer, rl("super_useless_ingot_from_nuggets"));

        // Storage block recipes
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_BLOCK.get()).patternLine("###").patternLine("###").patternLine("###").key('#', ingotsUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).setGroup("useless_block").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.SUPER_USELESS_BLOCK.get()).patternLine("###").patternLine("###").patternLine("###").key('#', ingotsSuperUseless).addCriterion("has_super_useless_ingot", hasItem(ingotsSuperUseless)).setGroup("super_useless_block").build(consumer);

        // Gear recipes
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_GEAR.get()).patternLine(" # ").patternLine("# #").patternLine(" # ").key('#', ingotsUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).setGroup("useless_gear").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.SUPER_USELESS_GEAR.get()).patternLine(" # ").patternLine("# #").patternLine(" # ").key('#', ingotsSuperUseless).addCriterion("has_super_useless_ingot", hasItem(ingotsSuperUseless)).setGroup("super_useless_gear").build(consumer);

        // Rod recipes
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_ROD.get(), 4).patternLine("  #").patternLine(" # ").patternLine("#  ").key('#', ingotsUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).setGroup("useless_rod").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.SUPER_USELESS_ROD.get(), 4).patternLine("  #").patternLine(" # ").patternLine("#  ").key('#', ingotsSuperUseless).addCriterion("has_super_useless_ingot", hasItem(ingotsSuperUseless)).setGroup("super_useless_rod").build(consumer);

        // Tool Recipes
        this.addToolRecipes(ItemInit.USELESS_PAXEL.get(), ItemInit.USELESS_SWORD.get(), ItemInit.USELESS_PICKAXE.get(), ItemInit.USELESS_AXE.get(),
                ItemInit.USELESS_SHOVEL.get(), ItemInit.USELESS_HOE.get(), ingotsUseless, consumer);
        this.addToolRecipes(ItemInit.SUPER_USELESS_PAXEL.get(), ItemInit.SUPER_USELESS_SWORD.get(), ItemInit.SUPER_USELESS_PICKAXE.get(), ItemInit.SUPER_USELESS_AXE.get(),
                ItemInit.SUPER_USELESS_SHOVEL.get(), ItemInit.SUPER_USELESS_HOE.get(), ingotsSuperUseless, consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_WRENCH.get()).patternLine("X X").patternLine(" # ").patternLine(" X ").key('X', ingotsUseless).key('#', ingotsSuperUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_SHEARS.get()).key('#', ingotsUseless).patternLine(" #").patternLine("# ").addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ItemInit.USELESS_FLINT_AND_STEEL.get()).addIngredient(ingotsUseless).addIngredient(Items.FLINT).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);

        // Armor Recipes
        this.addArmorRecipes(ItemInit.USELESS_HELMET.get(), ItemInit.USELESS_CHESTPLATE.get(), ItemInit.USELESS_LEGGINGS.get(), ItemInit.USELESS_BOOTS.get(), ingotsUseless, consumer);
        this.addArmorRecipes(ItemInit.SUPER_USELESS_HELMET.get(), ItemInit.SUPER_USELESS_CHESTPLATE.get(), ItemInit.SUPER_USELESS_LEGGINGS.get(), ItemInit.SUPER_USELESS_BOOTS.get(), ingotsSuperUseless, consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_ELYTRA.get())
                .patternLine("# #").patternLine("#X#").patternLine("# #")
                .key('#', ingotsUseless).key('X', Items.ELYTRA)
                .addCriterion("has_elytra", hasItem(Items.ELYTRA)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.SUPER_USELESS_ELYTRA.get())
                .patternLine("# #").patternLine("#X#").patternLine("# #")
                .key('#', ingotsSuperUseless).key('X', ItemInit.USELESS_ELYTRA.get())
                .addCriterion("has_elytra", hasItem(ItemInit.USELESS_ELYTRA.get())).build(consumer);

        // Shield recipes
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_SHIELD.get()).patternLine("###").patternLine("#X#").patternLine(" # ").key('#', ingotsUseless).key('X', Items.SHIELD).addCriterion("has_shield", hasItem(Items.SHIELD)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.SUPER_USELESS_SHIELD.get()).patternLine("###").patternLine("#X#").patternLine(" # ").key('#', ingotsSuperUseless).key('X', Items.SHIELD).addCriterion("has_shield", hasItem(Items.SHIELD)).build(consumer);

        // Smelting/Blasting to ingot
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(oresUseless), ItemInit.USELESS_INGOT.get(), 0.7F, 200)
                .addCriterion("has_useless_ore", hasItem(oresUseless)).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(oresSuperUseless), ItemInit.SUPER_USELESS_INGOT.get(), 1.0F, 200)
                .addCriterion("has_super_useless_ore", hasItem(oresSuperUseless)).build(consumer);
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(oresUseless), ItemInit.USELESS_INGOT.get(), 0.7F, 100)
                .addCriterion("has_useless_ore", hasItem(oresUseless)).build(consumer, rl("useless_ingot_from_blasting"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(oresSuperUseless), ItemInit.SUPER_USELESS_INGOT.get(), 1.0F, 100)
                .addCriterion("has_super_useless_ore", hasItem(oresSuperUseless)).build(consumer, rl("super_useless_ingot_from_blasting"));
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(dustsUseless), ItemInit.USELESS_INGOT.get(), 0.7F, 200)
                .addCriterion("has_useless_dust", hasItem(dustsUseless)).build(consumer, rl("useless_ingot_from_dust"));
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(dustsSuperUseless), ItemInit.SUPER_USELESS_INGOT.get(), 1.0F, 200)
                .addCriterion("has_super_useless_dust", hasItem(dustsSuperUseless)).build(consumer, rl("super_useless_ingot_from_dust"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(dustsUseless), ItemInit.USELESS_INGOT.get(), 0.7F, 100)
                .addCriterion("has_useless_dust", hasItem(dustsUseless)).build(consumer, rl("useless_ingot_from_dust_blasting"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(dustsSuperUseless), ItemInit.SUPER_USELESS_INGOT.get(), 1.0F, 100)
                .addCriterion("has_super_useless_dust", hasItem(dustsSuperUseless)).build(consumer, rl("super_useless_ingot_from_dust_blasting"));

        this.cookingRecipesForMethod(consumer, "smelting", IRecipeSerializer.SMELTING, 200);
        this.cookingRecipesForMethod(consumer, "smoking", IRecipeSerializer.SMOKING, 100);
        this.cookingRecipesForMethod(consumer, "campfire_cooking", IRecipeSerializer.CAMPFIRE_COOKING, 600);

        // Wooden stuff
        final ITag<Item> useless_logs = uselessItemTag("useless_logs");
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_WOOD.get(), 3).patternLine("##").patternLine("##").key('#', BlockInit.USELESS_LOG.get()).setGroup("bark").addCriterion("has_log", hasItem(BlockInit.USELESS_LOG.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.STRIPPED_USELESS_WOOD.get(), 3).patternLine("##").patternLine("##").key('#', BlockInit.STRIPPED_USELESS_LOG.get()).setGroup("bark").addCriterion("has_log", hasItem(BlockInit.STRIPPED_USELESS_LOG.get())).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BlockInit.USELESS_PLANKS.get(), 4).addIngredient(useless_logs).addCriterion("has_useless_log", hasItem(useless_logs)).setGroup("planks").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_PLANKS.get()).key('#', BlockInit.USELESS_SLAB.get()).patternLine("#").patternLine("#").setGroup("planks").addCriterion("has_slab", hasItem(BlockInit.USELESS_SLAB.get())).build(consumer, rl("useless_planks_from_slabs"));
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_STAIRS.get(), 4).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("#  ").patternLine("## ").patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_SLAB.get(), 6).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("###").setGroup("wooden_slab").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_FENCE.get(), 3).key('#', Tags.Items.RODS_WOODEN).key('W', BlockInit.USELESS_PLANKS.get()).patternLine("W#W").patternLine("W#W").setGroup("wooden_fence").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_FENCE_GATE.get()).key('#', Tags.Items.RODS_WOODEN).key('W', BlockInit.USELESS_PLANKS.get()).patternLine("#W#").patternLine("#W#").setGroup("wooden_fence_gate").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.WOODEN_USELESS_DOOR.get(), 3).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("##").patternLine("##").patternLine("##").setGroup("wooden_door").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.WOODEN_USELESS_TRAPDOOR.get(), 2).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("###").patternLine("###").setGroup("wooden_trapdoor").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_BOAT.get()).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("# #").patternLine("###").setGroup("boat").addCriterion("in_water", enteredBlock(Blocks.WATER)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_CHEST.get()).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("###").patternLine("# #").patternLine("###").setGroup("chest").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_CHEST.get(), 4).key('#', useless_logs).patternLine("###").patternLine("# #").patternLine("###").setGroup("chest").addCriterion("has_useless_log", hasItem(useless_logs)).build(consumer, rl("useless_chest_logs"));
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_SIGN.get(), 3).key('#', BlockInit.USELESS_PLANKS.get()).key('X', Tags.Items.RODS_WOODEN).patternLine("###").patternLine("###").patternLine(" X ").setGroup("sign").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_PRESSURE_PLATE.get()).key('#', BlockInit.USELESS_PLANKS.get()).patternLine("##").setGroup("wooden_pressure_plate").addCriterion("has_planks", hasItem(BlockInit.USELESS_PLANKS.get())).build(consumer);

        // Decorative Block recipes
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.USELESS_COBBLESTONE.get()), BlockInit.USELESS_STONE.get(), 0.1F, 200).addCriterion("has_cobblestone", hasItem(BlockInit.USELESS_COBBLESTONE.get())).build(consumer);
//        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.USELESS_STONE.get()), BlockInit.USELESS_SMOOTH_STONE.get(), 0.1F, 200);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_STONE_STAIRS.get(), 4).key('#', BlockInit.USELESS_STONE.get()).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_stone", hasItem(BlockInit.USELESS_STONE.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_COBBLESTONE_STAIRS.get(), 4).key('#', BlockInit.USELESS_COBBLESTONE.get()).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_cobblestone", hasItem(BlockInit.USELESS_COBBLESTONE.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_STONE_SLAB.get(), 6).key('#', BlockInit.USELESS_STONE.get()).patternLine("###").addCriterion("has_stone", hasItem(BlockInit.USELESS_STONE.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_COBBLESTONE_SLAB.get(), 6).key('#', BlockInit.USELESS_COBBLESTONE.get()).patternLine("###").addCriterion("has_cobblestone", hasItem(BlockInit.USELESS_COBBLESTONE.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_COBBLESTONE_WALL.get(), 6).key('#', BlockInit.USELESS_COBBLESTONE.get()).patternLine("###").patternLine("###").addCriterion("has_cobblestone", hasItem(BlockInit.USELESS_COBBLESTONE.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_STONE_PRESSURE_PLATE.get()).key('#', BlockInit.USELESS_STONE.get()).patternLine("##").addCriterion("has_stone", hasItem(BlockInit.USELESS_STONE.get())).build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(BlockInit.USELESS_STONE_BUTTON.get()).addIngredient(BlockInit.USELESS_STONE.get()).addCriterion("has_stone", hasItem(BlockInit.USELESS_STONE.get())).build(consumer);

        // Other recipes
        ShapedRecipeBuilder.shapedRecipe(BlockInit.MACHINE_FRAME.get()).patternLine("#G#").patternLine("GXG").patternLine("#G#").key('#', ingotsSuperUseless).key('X', gearsUseless).key('G', Tags.Items.GLASS).addCriterion("has_useless_gear", hasItem(gearsUseless)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.COFFEE_MACHINE_SUPPLIER.get()).patternLine(" # ").patternLine("#X#").patternLine(" # ").key('#', ingotsUseless).key('X', UselessTags.Items.MACHINE_FRAMES).addCriterion("has_machine_frame", hasItem(UselessTags.Items.MACHINE_FRAMES)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_GENERATOR.get()).patternLine(" # ").patternLine("#X#").patternLine("GRG").key('#', ingotsUseless).key('X', UselessTags.Items.MACHINE_FRAMES).key('G', gearsSuperUseless).key('R', Tags.Items.DUSTS_REDSTONE).addCriterion("has_machine_frame", hasItem(UselessTags.Items.MACHINE_FRAMES)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.COFFEE_MACHINE.get()).patternLine(" G ").patternLine("#X#").patternLine("WRM").key('#', ingotsUseless).key('X', UselessTags.Items.MACHINE_FRAMES).key('G', gearsUseless).key('R', Tags.Items.DUSTS_REDSTONE).key('W', Ingredient.fromItems(Items.WATER_BUCKET, FluidInit.USELESS_WATER)).key('M', Items.MILK_BUCKET).addCriterion("has_machine_frame", hasItem(UselessTags.Items.MACHINE_FRAMES)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.PAINT_BUCKET.get()).patternLine("# #").patternLine("XXX").patternLine("###").key('#', ingotsUseless).key('X', Tags.Items.DYES).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_BED.get()).patternLine("WWW").patternLine("PPP").key('W', BlockInit.USELESS_WOOL.get()).key('P', ItemTags.PLANKS).addCriterion("has_useless_wool", hasItem(BlockInit.USELESS_WOOL.get())).setGroup("bed").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_CARPET.get(), 3).patternLine("XX").key('X', BlockInit.USELESS_WOOL.get()).addCriterion("has_useless_wool", hasItem(BlockInit.USELESS_WOOL.get())).setGroup("carpet").build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.USELESS_BUCKET.get()).patternLine("# #").patternLine(" # ").key('#', ingotsUseless).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemInit.CUP.get(), 2).patternLine("# #").patternLine(" # ").key('#', Items.QUARTZ_SLAB).addCriterion("has_quartz_slab", hasItem(Items.QUARTZ_SLAB)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.CANVAS.get(), 4).key('X', Items.WHITE_WOOL).patternLine("XX").patternLine("XX").addCriterion("has_wool", hasItem(Items.WHITE_WOOL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ColorModule.PAINT_BRUSH.get()).key('X', Items.WHITE_WOOL).key('#', Tags.Items.RODS_WOODEN).patternLine("#X#").patternLine("###").patternLine(" # ").addCriterion("has_wool", hasItem(Items.WHITE_WOOL)).build(consumer);
        // Rail Recipes
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_RAIL.get(), 16).patternLine("# #").patternLine("#X#").patternLine("# #").key('#', ingotsUseless).key('X', Tags.Items.RODS_WOODEN).addCriterion("has_useless_ingot", hasItem(ingotsUseless)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.POWERED_USELESS_RAIL.get(), 6).patternLine("#X#").patternLine("#X#").patternLine("#X#").key('#', Blocks.POWERED_RAIL).key('X', ingotsUseless).addCriterion("has_powered_rail", hasItem(Blocks.POWERED_RAIL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_ACTIVATOR_RAIL.get(), 6).patternLine("#X#").patternLine("#X#").patternLine("#X#").key('#', Blocks.ACTIVATOR_RAIL).key('X', ingotsUseless).addCriterion("has_activator_rail", hasItem(Blocks.ACTIVATOR_RAIL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_DETECTOR_RAIL.get(), 6).patternLine("#X#").patternLine("#X#").patternLine("#X#").key('#', Blocks.DETECTOR_RAIL).key('X', ingotsUseless).addCriterion("has_detector_rail", hasItem(Blocks.DETECTOR_RAIL)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(BlockInit.USELESS_CROSSOVER_RAIL.get()).patternLine("#X").patternLine("X#").key('#', BlockInit.USELESS_RAIL.get()).key('X', Tags.Items.RODS_WOODEN).addCriterion("has_useless_rail", hasItem(BlockInit.USELESS_RAIL.get())).build(consumer);

        // Lamp recipes
        for (DyeColor color : DyeColor.values()) {
            ShapedRecipeBuilder.shapedRecipe(LampBlock.LAMP_MAP.get(color)).key('L', Blocks.REDSTONE_LAMP).key('#', forgeItemTag("glass/" + color.getTranslationKey())).key('X', color.getTag()).patternLine("X#X").patternLine("#L#").patternLine("X#X").addCriterion("has_redstone_lamp", hasItem(Blocks.REDSTONE_LAMP)).build(consumer);
        }
    }

    private void addArmorRecipes(IItemProvider helmet, IItemProvider chestplate, IItemProvider leggings, IItemProvider boots, ITag<Item> tag, Consumer<IFinishedRecipe> consumer) {
        final Ingredient ingredient = Ingredient.fromTag(tag);

        ShapedRecipeBuilder.shapedRecipe(helmet).patternLine("###").patternLine("# #").key('#', ingredient)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(chestplate).patternLine("# #").patternLine("###").patternLine("###").key('#', ingredient)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(leggings).patternLine("###").patternLine("# #").patternLine("# #").key('#', ingredient)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(boots).patternLine("# #").patternLine("# #").key('#', ingredient)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
    }

    private void addToolRecipes(IItemProvider paxel, IItemProvider sword, IItemProvider pickaxe, IItemProvider axe, IItemProvider shovel, IItemProvider hoe, ITag<Item> tag, Consumer<IFinishedRecipe> consumer) {
        final Ingredient ingredient = Ingredient.fromTag(tag);
        final Ingredient stick = Ingredient.fromTag(Tags.Items.RODS_WOODEN);

        ShapedRecipeBuilder.shapedRecipe(sword).patternLine("#").patternLine("#").patternLine("S").key('#', ingredient).key('S', stick)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(pickaxe).patternLine("###").patternLine(" S ").patternLine(" S ").key('#', ingredient).key('S', stick)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(axe).patternLine("##").patternLine("#S").patternLine(" S").key('#', ingredient).key('S', stick)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(shovel).patternLine("#").patternLine("S").patternLine("S").key('#', ingredient).key('S', stick)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(hoe).patternLine("##").patternLine(" S").patternLine(" S").key('#', ingredient).key('S', stick)
                .addCriterion("has_ingredient", hasItem(tag)).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(paxel).patternLine("APS").patternLine(" X ").patternLine(" X ").key('A', axe).key('P', pickaxe).key('S', shovel).key('X', stick)
                .addCriterion("has_axe", hasItem(axe)).addCriterion("has_pickaxe", hasItem(pickaxe)).addCriterion("has_shovel", hasItem(shovel)).build(consumer);
    }

    private void cookingRecipesForMethod(Consumer<IFinishedRecipe> recipeConsumerIn, String cookingMethod, CookingRecipeSerializer<?> serializerIn, int cookingTimeIn) {
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemInit.USELESS_BEEF.get()), ItemInit.COOKED_USELESS_BEEF.get(), 0.35F, cookingTimeIn, serializerIn).addCriterion("has_useless_beef", hasItem(ItemInit.USELESS_BEEF.get())).build(recipeConsumerIn, rl("cooked_useless_beef_from_" + cookingMethod));
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemInit.USELESS_CHICKEN.get()), ItemInit.COOKED_USELESS_CHICKEN.get(), 0.35F, cookingTimeIn, serializerIn).addCriterion("has_useless_chicken", hasItem(ItemInit.USELESS_CHICKEN.get())).build(recipeConsumerIn, rl("cooked_useless_chicken_from_" + cookingMethod));
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemInit.USELESS_MUTTON.get()), ItemInit.COOKED_USELESS_MUTTON.get(), 0.35F, cookingTimeIn, serializerIn).addCriterion("has_useless_mutton", hasItem(ItemInit.USELESS_MUTTON.get())).build(recipeConsumerIn, rl("cooked_useless_mutton_from_" + cookingMethod));
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemInit.USELESS_PORKCHOP.get()), ItemInit.COOKED_USELESS_PORKCHOP.get(), 0.35F, cookingTimeIn, serializerIn).addCriterion("has_useless_porkchop", hasItem(ItemInit.USELESS_PORKCHOP.get())).build(recipeConsumerIn, rl("cooked_useless_porkchop_from_" + cookingMethod));
    }

    @Override
    public String getName() {
        return "Useless Recipes";
    }
}
