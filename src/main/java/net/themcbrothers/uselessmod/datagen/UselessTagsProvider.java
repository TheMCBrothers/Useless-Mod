package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.core.*;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class UselessTagsProvider {
    public static class Blocks extends BlockTagsProvider {
        public Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            this.tag(UselessTags.Blocks.USELESS_ORES).add(UselessBlocks.USELESS_ORE.get(), UselessBlocks.DEEPSLATE_USELESS_ORE.get(), UselessBlocks.NETHER_USELESS_ORE.get(), UselessBlocks.END_USELESS_ORE.get());
            this.tag(UselessTags.Blocks.SUPER_USELESS_ORES).add(UselessBlocks.SUPER_USELESS_ORE.get(), UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), UselessBlocks.NETHER_SUPER_USELESS_ORE.get(), UselessBlocks.END_SUPER_USELESS_ORE.get());
            this.tag(UselessTags.Blocks.ORES_USELESS).addTag(UselessTags.Blocks.USELESS_ORES);
            this.tag(UselessTags.Blocks.ORES_SUPER_USELESS).addTag(UselessTags.Blocks.SUPER_USELESS_ORES);
            this.tag(Tags.Blocks.ORES).addTag(UselessTags.Blocks.ORES_USELESS).addTag(UselessTags.Blocks.ORES_SUPER_USELESS);
            this.tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(UselessBlocks.USELESS_ORE.get(), UselessBlocks.SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(UselessBlocks.DEEPSLATE_USELESS_ORE.get(), UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK).add(UselessBlocks.NETHER_USELESS_ORE.get(), UselessBlocks.NETHER_SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORE_RATES_SINGULAR).add(UselessBlocks.USELESS_ORE.get(), UselessBlocks.DEEPSLATE_USELESS_ORE.get(), UselessBlocks.NETHER_USELESS_ORE.get(), UselessBlocks.END_USELESS_ORE.get(), UselessBlocks.SUPER_USELESS_ORE.get(), UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), UselessBlocks.NETHER_SUPER_USELESS_ORE.get(), UselessBlocks.END_SUPER_USELESS_ORE.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_USELESS).add(UselessBlocks.USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS).add(UselessBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_USELESS).add(UselessBlocks.RAW_USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_SUPER_USELESS).add(UselessBlocks.RAW_SUPER_USELESS_BLOCK.get());
            this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_SUPER_USELESS);

            this.tag(BlockTags.MINEABLE_WITH_AXE).add(UselessBlocks.USELESS_WHEAT.get(), UselessBlocks.WILD_USELESS_WHEAT.get(), UselessBlocks.COFFEE_BEANS.get(), UselessBlocks.WILD_COFFEE_BEANS.get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(UselessBlocks.USELESS_ORE.get(), UselessBlocks.DEEPSLATE_USELESS_ORE.get(), UselessBlocks.NETHER_USELESS_ORE.get(), UselessBlocks.END_USELESS_ORE.get(), UselessBlocks.SUPER_USELESS_ORE.get(), UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), UselessBlocks.NETHER_SUPER_USELESS_ORE.get(), UselessBlocks.END_SUPER_USELESS_ORE.get(), UselessBlocks.USELESS_BLOCK.get(), UselessBlocks.SUPER_USELESS_BLOCK.get(), UselessBlocks.RAW_USELESS_BLOCK.get(), UselessBlocks.RAW_SUPER_USELESS_BLOCK.get(), UselessBlocks.USELESS_BARS.get(), UselessBlocks.SUPER_USELESS_BARS.get(), UselessBlocks.USELESS_DOOR.get(), UselessBlocks.SUPER_USELESS_DOOR.get(), UselessBlocks.USELESS_TRAPDOOR.get(), UselessBlocks.SUPER_USELESS_TRAPDOOR.get(), UselessBlocks.PAINT_BUCKET.get(), UselessBlocks.COFFEE_MACHINE.get());
            this.tag(BlockTags.MINEABLE_WITH_HOE).add(UselessBlocks.USELESS_OAK_LEAVES.get());
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(UselessBlocks.USELESS_ORE.get(), UselessBlocks.DEEPSLATE_USELESS_ORE.get(), UselessBlocks.NETHER_USELESS_ORE.get(), UselessBlocks.END_USELESS_ORE.get(), UselessBlocks.USELESS_BLOCK.get());
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(UselessBlocks.SUPER_USELESS_ORE.get(), UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), UselessBlocks.NETHER_SUPER_USELESS_ORE.get(), UselessBlocks.END_SUPER_USELESS_ORE.get(), UselessBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(BlockTags.BEACON_BASE_BLOCKS).add(UselessBlocks.USELESS_BLOCK.get(), UselessBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(BlockTags.RAILS).add(UselessBlocks.USELESS_RAIL.get(), UselessBlocks.USELESS_POWERED_RAIL.get(), UselessBlocks.USELESS_DETECTOR_RAIL.get(), UselessBlocks.USELESS_ACTIVATOR_RAIL.get());
            this.tag(UselessTags.Blocks.USELESS_OAK_LOGS).add(UselessBlocks.USELESS_OAK_LOG.get(), UselessBlocks.USELESS_OAK_WOOD.get(), UselessBlocks.STRIPPED_USELESS_OAK_LOG.get(), UselessBlocks.STRIPPED_USELESS_OAK_WOOD.get());
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(UselessTags.Blocks.USELESS_OAK_LOGS);
            this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(UselessBlocks.USELESS_OAK_LOG.get());
            this.tag(BlockTags.SMALL_FLOWERS).add(UselessBlocks.RED_ROSE.get(), UselessBlocks.BLUE_ROSE.get(), UselessBlocks.USELESS_ROSE.get());
            this.tag(BlockTags.FLOWER_POTS).add(UselessBlocks.POTTED_RED_ROSE.get(), UselessBlocks.POTTED_BLUE_ROSE.get(), UselessBlocks.POTTED_USELESS_ROSE.get(), UselessBlocks.POTTED_USELESS_OAK_SAPLING.get());
            this.tag(BlockTags.SAPLINGS).add(UselessBlocks.USELESS_OAK_SAPLING.get());
            this.tag(BlockTags.CROPS).add(UselessBlocks.USELESS_WHEAT.get(), UselessBlocks.WILD_USELESS_WHEAT.get(), UselessBlocks.COFFEE_BEANS.get(), UselessBlocks.WILD_COFFEE_BEANS.get());
            this.tag(BlockTags.LEAVES).add(UselessBlocks.USELESS_OAK_LEAVES.get());
            this.tag(BlockTags.PLANKS).add(UselessBlocks.USELESS_OAK_PLANKS.get());
            this.tag(BlockTags.WOODEN_STAIRS).add(UselessBlocks.USELESS_OAK_STAIRS.get());
            this.tag(BlockTags.WOODEN_SLABS).add(UselessBlocks.USELESS_OAK_SLAB.get());
            this.tag(BlockTags.WOODEN_FENCES).add(UselessBlocks.USELESS_OAK_FENCE.get());
            this.tag(BlockTags.FENCE_GATES).add(UselessBlocks.USELESS_OAK_FENCE_GATE.get());
            this.tag(BlockTags.WOODEN_DOORS).add(UselessBlocks.USELESS_OAK_DOOR.get());
            this.tag(BlockTags.WOODEN_TRAPDOORS).add(UselessBlocks.USELESS_OAK_TRAPDOOR.get());
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(UselessBlocks.USELESS_OAK_PRESSURE_PLATE.get());
            this.tag(BlockTags.WOODEN_BUTTONS).add(UselessBlocks.USELESS_OAK_BUTTON.get());
            this.tag(BlockTags.STANDING_SIGNS).add(UselessBlocks.USELESS_OAK_SIGN.get());
            this.tag(BlockTags.WALL_SIGNS).add(UselessBlocks.USELESS_OAK_WALL_SIGN.get());
            this.tag(BlockTags.WOOL).add(UselessBlocks.USELESS_WOOL.get(), UselessBlocks.PAINTED_WOOL.get());
            this.tag(BlockTags.WOOL_CARPETS).add(UselessBlocks.USELESS_CARPET.get());
            this.tag(BlockTags.BEDS).add(UselessBlocks.USELESS_BED.get());
            this.tag(BlockTags.DOORS).add(UselessBlocks.USELESS_DOOR.get(), UselessBlocks.SUPER_USELESS_DOOR.get());
            this.tag(BlockTags.TRAPDOORS).add(UselessBlocks.USELESS_TRAPDOOR.get(), UselessBlocks.SUPER_USELESS_TRAPDOOR.get());
            this.tag(BlockTags.GUARDED_BY_PIGLINS).add(UselessBlocks.WALL_CLOSET.get());
            this.tag(UselessTags.Blocks.LAMPS).add(UselessBlocks.WHITE_LAMP.get(), UselessBlocks.ORANGE_LAMP.get(), UselessBlocks.MAGENTA_LAMP.get(), UselessBlocks.LIGHT_BLUE_LAMP.get(), UselessBlocks.YELLOW_LAMP.get(), UselessBlocks.LIME_LAMP.get(), UselessBlocks.PINK_LAMP.get(), UselessBlocks.GRAY_LAMP.get(), UselessBlocks.LIGHT_GRAY_LAMP.get(), UselessBlocks.CYAN_LAMP.get(), UselessBlocks.PURPLE_LAMP.get(), UselessBlocks.BLUE_LAMP.get(), UselessBlocks.BROWN_LAMP.get(), UselessBlocks.GREEN_LAMP.get(), UselessBlocks.RED_LAMP.get(), UselessBlocks.BLACK_LAMP.get());
        }
    }

    public static class Items extends ItemTagsProvider {
        public Items(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, blockTags, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            // ores
            this.copy(UselessTags.Blocks.USELESS_ORES, UselessTags.Items.USELESS_ORES);
            this.copy(UselessTags.Blocks.SUPER_USELESS_ORES, UselessTags.Items.SUPER_USELESS_ORES);
            this.copy(UselessTags.Blocks.ORES_USELESS, UselessTags.Items.ORES_USELESS);
            this.copy(UselessTags.Blocks.ORES_SUPER_USELESS, UselessTags.Items.ORES_SUPER_USELESS);
            this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
            this.copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
            this.copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);
            this.copy(Tags.Blocks.ORES_IN_GROUND_NETHERRACK, Tags.Items.ORES_IN_GROUND_NETHERRACK);
            this.copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR);
            // storage blocks
            this.copy(UselessTags.Blocks.STORAGE_BLOCKS_USELESS, UselessTags.Items.STORAGE_BLOCKS_USELESS);
            this.copy(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS, UselessTags.Items.STORAGE_BLOCKS_SUPER_USELESS);
            this.copy(UselessTags.Blocks.STORAGE_BLOCKS_RAW_USELESS, UselessTags.Items.STORAGE_BLOCKS_RAW_USELESS);
            this.copy(UselessTags.Blocks.STORAGE_BLOCKS_RAW_SUPER_USELESS, UselessTags.Items.STORAGE_BLOCKS_RAW_SUPER_USELESS);
            this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
            // other
            this.copy(BlockTags.RAILS, ItemTags.RAILS);
            this.copy(UselessTags.Blocks.USELESS_OAK_LOGS, UselessTags.Items.USELESS_OAK_LOGS);
            this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
            this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
            this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
            this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
            this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
            this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
            this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
            this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
            this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
            this.copy(BlockTags.WOOL, ItemTags.WOOL);
            this.copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
            this.copy(BlockTags.BEDS, ItemTags.BEDS);
            this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
            this.copy(UselessTags.Blocks.LAMPS, UselessTags.Items.LAMPS);

            // minecraft tags
            this.tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(UselessItems.USELESS_PICKAXE.get(), UselessItems.SUPER_USELESS_PICKAXE.get());
            this.tag(ItemTags.SWORDS).add(UselessItems.USELESS_SWORD.get(), UselessItems.SUPER_USELESS_SWORD.get());
            this.tag(ItemTags.SHOVELS).add(UselessItems.USELESS_SHOVEL.get(), UselessItems.SUPER_USELESS_SHOVEL.get());
            this.tag(ItemTags.PICKAXES).add(UselessItems.USELESS_PICKAXE.get(), UselessItems.SUPER_USELESS_PICKAXE.get());
            this.tag(ItemTags.AXES).add(UselessItems.USELESS_AXE.get(), UselessItems.SUPER_USELESS_AXE.get());
            this.tag(ItemTags.HOES).add(UselessItems.USELESS_HOE.get(), UselessItems.SUPER_USELESS_HOE.get());
            this.tag(ItemTags.TRIMMABLE_ARMOR).add(UselessItems.USELESS_HELMET.get(), UselessItems.USELESS_CHESTPLATE.get(), UselessItems.USELESS_LEGGINGS.get(), UselessItems.USELESS_BOOTS.get(), UselessItems.SUPER_USELESS_HELMET.get(), UselessItems.SUPER_USELESS_CHESTPLATE.get(), UselessItems.SUPER_USELESS_LEGGINGS.get(), UselessItems.SUPER_USELESS_BOOTS.get());

            // materials
            this.tag(UselessTags.Items.INGOTS_USELESS).add(UselessItems.USELESS_INGOT.get());
            this.tag(UselessTags.Items.INGOTS_SUPER_USELESS).add(UselessItems.SUPER_USELESS_INGOT.get());
            this.tag(Tags.Items.INGOTS).addTag(UselessTags.Items.INGOTS_USELESS).addTag(UselessTags.Items.INGOTS_SUPER_USELESS);
            this.tag(UselessTags.Items.DUSTS_USELESS).add(UselessItems.USELESS_DUST.get());
            this.tag(UselessTags.Items.DUSTS_SUPER_USELESS).add(UselessItems.SUPER_USELESS_DUST.get());
            this.tag(Tags.Items.DUSTS).addTag(UselessTags.Items.DUSTS_USELESS).addTag(UselessTags.Items.DUSTS_SUPER_USELESS);
            this.tag(UselessTags.Items.RAW_MATERIALS_USELESS).add(UselessItems.RAW_USELESS.get());
            this.tag(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS).add(UselessItems.RAW_SUPER_USELESS.get());
            this.tag(Tags.Items.RAW_MATERIALS).addTag(UselessTags.Items.RAW_MATERIALS_USELESS).addTag(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS);

            // other forge tags
            this.tag(Tags.Items.SHEARS).add(UselessItems.USELESS_SHEARS.get());
            this.tag(Tags.Items.TOOLS_SHIELDS).add(UselessItems.USELESS_SHIELD.get(), UselessItems.SUPER_USELESS_SHIELD.get());
            this.tag(Tags.Items.ARMORS_HELMETS).add(UselessItems.USELESS_HELMET.get(), UselessItems.SUPER_USELESS_HELMET.get());
            this.tag(Tags.Items.ARMORS_CHESTPLATES).add(UselessItems.USELESS_CHESTPLATE.get(), UselessItems.SUPER_USELESS_CHESTPLATE.get());
            this.tag(Tags.Items.ARMORS_LEGGINGS).add(UselessItems.USELESS_LEGGINGS.get(), UselessItems.SUPER_USELESS_LEGGINGS.get());
            this.tag(Tags.Items.ARMORS_BOOTS).add(UselessItems.USELESS_BOOTS.get(), UselessItems.SUPER_USELESS_BOOTS.get());
            this.tag(UselessTags.Items.CROPS_USELESS_WHEAT).add(UselessItems.USELESS_WHEAT.get());
            this.tag(UselessTags.Items.CROPS_COFFEEBEAN).add(UselessItems.COFFEE_BEANS.get());
            this.tag(Tags.Items.CROPS).addTag(UselessTags.Items.CROPS_USELESS_WHEAT).addTag(UselessTags.Items.CROPS_COFFEEBEAN);
            this.tag(UselessTags.Items.SEEDS_USELESS_WHEAT).add(UselessItems.USELESS_WHEAT_SEEDS.get());
            this.tag(UselessTags.Items.SEEDS_COFFEEBEAN).add(UselessItems.COFFEE_SEEDS.get());
            this.tag(Tags.Items.SEEDS).addTag(UselessTags.Items.SEEDS_USELESS_WHEAT).addTag(UselessTags.Items.SEEDS_COFFEEBEAN);
            this.tag(Tags.Items.BONES).add(UselessItems.USELESS_BONE.get());
            this.tag(Tags.Items.LEATHER).add(UselessItems.USELESS_LEATHER.get());
            this.tag(Tags.Items.FEATHERS).add(UselessItems.USELESS_FEATHER.get());
        }
    }

    public static class Fluids extends FluidTagsProvider {
        public Fluids(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            this.tag(UselessTags.Fluids.PAINT).add(UselessFluids.PAINT.get(), UselessFluids.FLOWING_PAINT.get());
        }
    }

    public static class Entities extends EntityTypeTagsProvider {
        public Entities(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            this.tag(EntityTypeTags.SKELETONS).add(UselessEntityTypes.USELESS_SKELETON.get());
        }
    }

    public static class Paintings extends PaintingVariantTagsProvider {
        public Paintings(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            this.tag(PaintingVariantTags.PLACEABLE).add(UselessPaintingVariants.LARGE_LOGO_RED.getKey(), UselessPaintingVariants.LARGE_LOGO_BLUE.getKey(),
                    UselessPaintingVariants.SMALL_LOGO_RED.getKey(), UselessPaintingVariants.SMALL_LOGO_BLUE.getKey());
        }
    }
}
