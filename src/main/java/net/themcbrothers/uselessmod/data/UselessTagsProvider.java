package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.init.ModItems;
import org.jetbrains.annotations.Nullable;

public class UselessTagsProvider {
    public static class Blocks extends BlockTagsProvider {
        public Blocks(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            this.tag(UselessTags.Blocks.ORES_USELESS).add(ModBlocks.USELESS_ORE.get(), ModBlocks.DEEPSLATE_USELESS_ORE.get(), ModBlocks.NETHER_USELESS_ORE.get(), ModBlocks.END_USELESS_ORE.get());
            this.tag(UselessTags.Blocks.ORES_SUPER_USELESS).add(ModBlocks.SUPER_USELESS_ORE.get(), ModBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), ModBlocks.NETHER_SUPER_USELESS_ORE.get(), ModBlocks.END_SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORES).addTag(UselessTags.Blocks.ORES_USELESS).addTag(UselessTags.Blocks.ORES_SUPER_USELESS);
            this.tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ModBlocks.USELESS_ORE.get(), ModBlocks.SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(ModBlocks.DEEPSLATE_USELESS_ORE.get(), ModBlocks.DEEPSLATE_SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK).add(ModBlocks.NETHER_USELESS_ORE.get(), ModBlocks.NETHER_SUPER_USELESS_ORE.get());
            this.tag(Tags.Blocks.ORE_RATES_SINGULAR).add(ModBlocks.USELESS_ORE.get(), ModBlocks.DEEPSLATE_USELESS_ORE.get(), ModBlocks.NETHER_USELESS_ORE.get(), ModBlocks.END_USELESS_ORE.get(), ModBlocks.SUPER_USELESS_ORE.get(), ModBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), ModBlocks.NETHER_SUPER_USELESS_ORE.get(), ModBlocks.END_SUPER_USELESS_ORE.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_USELESS).add(ModBlocks.USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS).add(ModBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_USELESS).add(ModBlocks.RAW_USELESS_BLOCK.get());
            this.tag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_SUPER_USELESS).add(ModBlocks.RAW_SUPER_USELESS_BLOCK.get());
            this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS).addTag(UselessTags.Blocks.STORAGE_BLOCKS_RAW_SUPER_USELESS);

            this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.USELESS_WHEAT.get(), ModBlocks.WILD_USELESS_WHEAT.get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.USELESS_ORE.get(), ModBlocks.DEEPSLATE_USELESS_ORE.get(), ModBlocks.NETHER_USELESS_ORE.get(), ModBlocks.END_USELESS_ORE.get(), ModBlocks.SUPER_USELESS_ORE.get(), ModBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), ModBlocks.NETHER_SUPER_USELESS_ORE.get(), ModBlocks.END_SUPER_USELESS_ORE.get(), ModBlocks.USELESS_BLOCK.get(), ModBlocks.SUPER_USELESS_BLOCK.get(), ModBlocks.RAW_USELESS_BLOCK.get(), ModBlocks.RAW_SUPER_USELESS_BLOCK.get(), ModBlocks.USELESS_BARS.get(), ModBlocks.SUPER_USELESS_BARS.get(), ModBlocks.PAINT_BUCKET.get(), ModBlocks.USELESS_RAIL.get(), ModBlocks.USELESS_POWERED_RAIL.get(), ModBlocks.USELESS_DETECTOR_RAIL.get(), ModBlocks.USELESS_ACTIVATOR_RAIL.get());
            this.tag(BlockTags.MINEABLE_WITH_HOE).add(ModBlocks.USELESS_OAK_LEAVES.get());
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.USELESS_ORE.get(), ModBlocks.DEEPSLATE_USELESS_ORE.get(), ModBlocks.NETHER_USELESS_ORE.get(), ModBlocks.END_USELESS_ORE.get(), ModBlocks.USELESS_BLOCK.get());
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SUPER_USELESS_ORE.get(), ModBlocks.DEEPSLATE_SUPER_USELESS_ORE.get(), ModBlocks.NETHER_SUPER_USELESS_ORE.get(), ModBlocks.END_SUPER_USELESS_ORE.get(), ModBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.USELESS_BLOCK.get(), ModBlocks.SUPER_USELESS_BLOCK.get());
            this.tag(BlockTags.RAILS).add(ModBlocks.USELESS_RAIL.get(), ModBlocks.USELESS_POWERED_RAIL.get(), ModBlocks.USELESS_DETECTOR_RAIL.get(), ModBlocks.USELESS_ACTIVATOR_RAIL.get());
            this.tag(UselessTags.Blocks.USELESS_OAK_LOGS).add(ModBlocks.USELESS_OAK_WOOD.getLog(), ModBlocks.USELESS_OAK_WOOD.getWood(), ModBlocks.USELESS_OAK_WOOD.getStrippedLog(), ModBlocks.USELESS_OAK_WOOD.getStrippedWood());
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(UselessTags.Blocks.USELESS_OAK_LOGS);
            this.tag(BlockTags.SMALL_FLOWERS).add(ModBlocks.RED_ROSE.get(), ModBlocks.BLUE_ROSE.get(), ModBlocks.USELESS_ROSE.get());
            this.tag(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_RED_ROSE.get(), ModBlocks.POTTED_BLUE_ROSE.get(), ModBlocks.POTTED_USELESS_ROSE.get(), ModBlocks.POTTED_USELESS_OAK_SAPLING.get());
            this.tag(BlockTags.SAPLINGS).add(ModBlocks.USELESS_OAK_SAPLING.get());
            this.tag(BlockTags.CROPS).add(ModBlocks.USELESS_WHEAT.get(), ModBlocks.WILD_USELESS_WHEAT.get(), ModBlocks.COFFEE_BEANS.get(), ModBlocks.WILD_COFFEE_BEANS.get());
            this.tag(BlockTags.LEAVES).add(ModBlocks.USELESS_OAK_LEAVES.get());
            this.tag(BlockTags.PLANKS).add(ModBlocks.USELESS_OAK_WOOD.get());
            this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.USELESS_OAK_WOOD.getStairs());
            this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.USELESS_OAK_WOOD.getSlab());
            this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.USELESS_OAK_WOOD.getFence());
            this.tag(BlockTags.FENCE_GATES).add(ModBlocks.USELESS_OAK_WOOD.getFenceGate());
            this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.USELESS_OAK_WOOD.getDoor());
            this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.USELESS_OAK_WOOD.getTrapdoor());
            this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.USELESS_OAK_WOOD.getPressurePlate());
            this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.USELESS_OAK_WOOD.getButton());
            this.tag(BlockTags.WOOL).add(ModBlocks.USELESS_WOOL.get(), ModBlocks.CANVAS.get());
            this.tag(BlockTags.CARPETS).add(ModBlocks.USELESS_CARPET.get());
            this.tag(BlockTags.BEDS).add(ModBlocks.USELESS_BED.get());
        }
    }

    public static class Items extends ItemTagsProvider {
        public Items(DataGenerator generator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, blockTagsProvider, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            // ores
            this.copy(UselessTags.Blocks.ORES_USELESS, UselessTags.Items.ORES_USELESS);
            this.copy(UselessTags.Blocks.ORES_SUPER_USELESS, UselessTags.Items.ORES_SUPER_USELESS);
            this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
            this.copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
            this.copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);
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
            this.copy(BlockTags.WOOL, ItemTags.WOOL);
            this.copy(BlockTags.CARPETS, ItemTags.CARPETS);
            this.copy(BlockTags.BEDS, ItemTags.BEDS);

            // materials
            this.tag(UselessTags.Items.INGOTS_USELESS).add(ModItems.USELESS_INGOT.get());
            this.tag(UselessTags.Items.INGOTS_SUPER_USELESS).add(ModItems.SUPER_USELESS_INGOT.get());
            this.tag(Tags.Items.INGOTS).addTag(UselessTags.Items.INGOTS_USELESS).addTag(UselessTags.Items.INGOTS_SUPER_USELESS);
            this.tag(UselessTags.Items.DUSTS_USELESS).add(ModItems.USELESS_DUST.get());
            this.tag(UselessTags.Items.DUSTS_SUPER_USELESS).add(ModItems.SUPER_USELESS_DUST.get());
            this.tag(Tags.Items.DUSTS).addTag(UselessTags.Items.DUSTS_USELESS).addTag(UselessTags.Items.DUSTS_SUPER_USELESS);
            this.tag(UselessTags.Items.RAW_MATERIALS_USELESS).add(ModItems.RAW_USELESS.get());
            this.tag(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS).add(ModItems.RAW_SUPER_USELESS.get());
            this.tag(Tags.Items.RAW_MATERIALS).addTag(UselessTags.Items.RAW_MATERIALS_USELESS).addTag(UselessTags.Items.RAW_MATERIALS_SUPER_USELESS);

            // other forge tags
            this.tag(UselessTags.Items.CROPS_USELESS_WHEAT).add(ModItems.USELESS_WHEAT.get());
            this.tag(UselessTags.Items.CROPS_COFFEEBEAN).add(ModItems.COFFEE_BEANS.get());
            this.tag(Tags.Items.CROPS).addTag(UselessTags.Items.CROPS_USELESS_WHEAT).addTag(UselessTags.Items.CROPS_COFFEEBEAN);
            this.tag(UselessTags.Items.SEEDS_USELESS_WHEAT).add(ModItems.USELESS_WHEAT_SEEDS.get());
            this.tag(UselessTags.Items.SEEDS_COFFEEBEAN).add(ModItems.COFFEE_SEEDS.get());
            this.tag(Tags.Items.SEEDS).addTag(UselessTags.Items.SEEDS_USELESS_WHEAT).addTag(UselessTags.Items.SEEDS_COFFEEBEAN);
            this.tag(Tags.Items.BONES).add(ModItems.USELESS_BONE.get());
            this.tag(Tags.Items.LEATHER).add(ModItems.USELESS_LEATHER.get());
            this.tag(Tags.Items.FEATHERS).add(ModItems.USELESS_FEATHER.get());
        }
    }

    public static class Entities extends EntityTypeTagsProvider {
        public Entities(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, UselessMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            this.tag(EntityTypeTags.SKELETONS).add(ModEntityTypes.USELESS_SKELETON.get());
        }
    }
}
