package themcbros.uselessmod.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.ItemInit;

import static themcbros.uselessmod.helpers.TagUtils.forgeItemTag;

/**
 * @author TheMCBrothers
 */
public class UselessItemTagsProvider extends ItemTagsProvider {

    public UselessItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        final ITag.INamedTag<Item> plates = forgeItemTag("plates");
        final ITag.INamedTag<Item> gears = forgeItemTag("gears");

        final ITag.INamedTag<Item> seeds = Tags.Items.SEEDS;
        final ITag.INamedTag<Item> seedsUselessWheat = forgeItemTag("seeds/useless_wheat");
        final ITag.INamedTag<Item> seedsCoffeebean = forgeItemTag("seeds/coffeebean");
        final ITag.INamedTag<Item> seedsEnder = forgeItemTag("seeds/ender");

        final ITag.INamedTag<Item> wiresUseless = forgeItemTag("wires/useless");
        final ITag.INamedTag<Item> wiresSuperUseless = forgeItemTag("wires/super_useless");

        this.getOrCreateBuilder(UselessTags.Items.DUSTS_USELESS).add(ItemInit.USELESS_DUST.get());
        this.getOrCreateBuilder(UselessTags.Items.DUSTS_SUPER_USELESS).add(ItemInit.SUPER_USELESS_DUST.get());
        this.getOrCreateBuilder(Tags.Items.DUSTS).addTag(UselessTags.Items.DUSTS_USELESS).addTag(UselessTags.Items.DUSTS_SUPER_USELESS);
        this.getOrCreateBuilder(UselessTags.Items.INGOTS_USELESS).add(ItemInit.USELESS_INGOT.get());
        this.getOrCreateBuilder(UselessTags.Items.INGOTS_SUPER_USELESS).add(ItemInit.SUPER_USELESS_INGOT.get());
        this.getOrCreateBuilder(Tags.Items.INGOTS).addTag(UselessTags.Items.INGOTS_USELESS).addTag(UselessTags.Items.INGOTS_SUPER_USELESS);
        this.getOrCreateBuilder(UselessTags.Items.NUGGETS_USELESS).add(ItemInit.USELESS_NUGGET.get());
        this.getOrCreateBuilder(UselessTags.Items.NUGGETS_SUPER_USELESS).add(ItemInit.SUPER_USELESS_NUGGET.get());
        this.getOrCreateBuilder(Tags.Items.NUGGETS).addTag(UselessTags.Items.NUGGETS_USELESS).addTag(UselessTags.Items.NUGGETS_SUPER_USELESS);
        this.getOrCreateBuilder(UselessTags.Items.PLATES_USELESS).add(ItemInit.USELESS_PLATE.get());
        this.getOrCreateBuilder(UselessTags.Items.PLATES_SUPER_USELESS).add(ItemInit.SUPER_USELESS_PLATE.get());
        this.getOrCreateBuilder(plates).addTag(UselessTags.Items.PLATES_USELESS).addTag(UselessTags.Items.PLATES_SUPER_USELESS);
        this.getOrCreateBuilder(UselessTags.Items.GEARS_USELESS).add(ItemInit.USELESS_GEAR.get());
        this.getOrCreateBuilder(UselessTags.Items.GEARS_SUPER_USELESS).add(ItemInit.SUPER_USELESS_GEAR.get());
        this.getOrCreateBuilder(gears).addTag(UselessTags.Items.GEARS_USELESS).addTag(UselessTags.Items.GEARS_SUPER_USELESS);
        this.getOrCreateBuilder(UselessTags.Items.RODS_USELESS).add(ItemInit.USELESS_ROD.get());
        this.getOrCreateBuilder(UselessTags.Items.RODS_SUPER_USELESS).add(ItemInit.SUPER_USELESS_ROD.get());
        this.getOrCreateBuilder(Tags.Items.RODS).addTag(UselessTags.Items.RODS_USELESS).addTag(UselessTags.Items.RODS_SUPER_USELESS);

        this.getOrCreateBuilder(seedsUselessWheat).add(ItemInit.USELESS_WHEAT_SEEDS.get());
        this.getOrCreateBuilder(seedsCoffeebean).add(ItemInit.COFFEE_SEEDS.get());
        this.getOrCreateBuilder(seedsEnder).add(ItemInit.ENDER_SEEDS.get());
        this.getOrCreateBuilder(seeds).addTag(seedsUselessWheat).addTag(seedsCoffeebean).addTag(seedsEnder);
        this.getOrCreateBuilder(UselessTags.Items.CROPS_USELESS_WHEAT).addItemEntry(ItemInit.USELESS_WHEAT.get());
        this.getOrCreateBuilder(UselessTags.Items.CROPS_COFFEEBEAN).addItemEntry(ItemInit.COFFEE_BEANS.get());
        this.getOrCreateBuilder(UselessTags.Items.CROPS_ENDER).addItemEntry(Items.ENDER_PEARL);
        this.getOrCreateBuilder(Tags.Items.CROPS)
                .addTag(UselessTags.Items.CROPS_USELESS_WHEAT)
                .addTag(UselessTags.Items.CROPS_COFFEEBEAN)
                .addTag(UselessTags.Items.CROPS_ENDER);

        this.getOrCreateBuilder(Tags.Items.BONES).addItemEntry(ItemInit.USELESS_BONE.get());
        this.getOrCreateBuilder(Tags.Items.FEATHERS).addItemEntry(ItemInit.USELESS_FEATHER.get());
        this.getOrCreateBuilder(Tags.Items.HEADS).addItemEntry(ItemInit.USELESS_SKELETON_SKULL.get());
        this.getOrCreateBuilder(Tags.Items.LEATHER).addItemEntry(ItemInit.USELESS_LEATHER.get());
        this.getOrCreateBuilder(Tags.Items.SHEARS).addItemEntry(ItemInit.USELESS_SHEARS.get());
        this.getOrCreateBuilder(UselessTags.Items.TOOLS_WRENCH).addItemEntry(ItemInit.USELESS_WRENCH.get());
        this.getOrCreateBuilder(UselessTags.Items.WRENCH).addItemEntry(ItemInit.USELESS_WRENCH.get());

        // Immersive Engineering
        this.getOrCreateBuilder(wiresUseless).addOptional(UselessMod.rl("useless_wire"));
        this.getOrCreateBuilder(wiresSuperUseless).addOptional(UselessMod.rl("super_useless_wire"));

        this.getOrCreateBuilder(ItemTags.STONE_TOOL_MATERIALS).addItemEntry(BlockInit.USELESS_COBBLESTONE.asItem());
        this.getOrCreateBuilder(ItemTags.STONE_CRAFTING_MATERIALS).addItemEntry(BlockInit.USELESS_COBBLESTONE.asItem());
        this.getOrCreateBuilder(ItemTags.BOATS).add(ItemInit.USELESS_OAK_BOAT.get(), ItemInit.SUPER_USELESS_BOAT.get());
        this.getOrCreateBuilder(ItemTags.SIGNS).add(ItemInit.USELESS_OAK_SIGN.get());
        this.getOrCreateBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .addTag(UselessTags.Items.INGOTS_USELESS)
                .addTag(UselessTags.Items.INGOTS_SUPER_USELESS);

        this.copy(Tags.Blocks.STONE, Tags.Items.STONE);
        this.copy(Tags.Blocks.COBBLESTONE, Tags.Items.COBBLESTONE);
        this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
        this.copy(UselessTags.Blocks.ORES_USELESS, UselessTags.Items.ORES_USELESS);
        this.copy(UselessTags.Blocks.ORES_SUPER_USELESS, UselessTags.Items.ORES_SUPER_USELESS);
        this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        this.copy(UselessTags.Blocks.STORAGE_BLOCKS_USELESS, UselessTags.Items.STORAGE_BLOCKS_USELESS);
        this.copy(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS, UselessTags.Items.STORAGE_BLOCKS_SUPER_USELESS);
        this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);

        this.copy(UselessTags.Blocks.USELESS_LOGS, UselessTags.Items.USELESS_LOGS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.WOOL, ItemTags.WOOL);
        this.copy(BlockTags.CARPETS, ItemTags.CARPETS);
        this.copy(BlockTags.BEDS, ItemTags.BEDS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        this.copy(BlockTags.RAILS, ItemTags.RAILS);
        this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);

        this.copy(UselessTags.Blocks.LAMPS, UselessTags.Items.LAMPS);

        this.getOrCreateBuilder(UselessTags.Items.MACHINE_FRAMES)
                .add(BlockInit.MACHINE_FRAME.asItem())
                .addOptional(new ResourceLocation("silents_mechanisms", "stone_machine_frame"))
                .addOptional(new ResourceLocation("silents_mechanisms", "alloy_machine_frame"))
                .addOptional(new ResourceLocation("mekanism", "steel_casing"))
                .addOptional(new ResourceLocation("industrialforegoing", "machine_frame_pity"))
                .addOptional(new ResourceLocation("industrialforegoing", "machine_frame_simple"))
                .addOptional(new ResourceLocation("industrialforegoing", "machine_frame_advanced"))
                .addOptional(new ResourceLocation("industrialforegoing", "machine_frame_supreme"))
                .addOptional(new ResourceLocation("thermal", "machine_frame"));
    }

    @Override
    public String getName() {
        return "Useless Item Tags";
    }
}
