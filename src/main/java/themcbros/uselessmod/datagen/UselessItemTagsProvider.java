package themcbros.uselessmod.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.init.ItemInit;

import static themcbros.uselessmod.helpers.TagUtils.*;

/**
 * @author TheMCBrothers
 */
public class UselessItemTagsProvider extends ItemTagsProvider {

    public UselessItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        final ITag.INamedTag<Item> nuggets = Tags.Items.NUGGETS;
        final ITag.INamedTag<Item> nuggetsUseless = forgeItemTag("nuggets/useless");
        final ITag.INamedTag<Item> nuggetsSuperUseless = forgeItemTag("nuggets/super_useless");
        final ITag.INamedTag<Item> dusts = Tags.Items.DUSTS;
        final ITag.INamedTag<Item> dustsUseless = forgeItemTag("dusts/useless");
        final ITag.INamedTag<Item> dustsSuperUseless = forgeItemTag("dusts/super_useless");
        final ITag.INamedTag<Item> ingots = Tags.Items.INGOTS;
        final ITag.INamedTag<Item> ingotsUseless = forgeItemTag("ingots/useless");
        final ITag.INamedTag<Item> ingotsSuperUseless = forgeItemTag("ingots/super_useless");
        final ITag.INamedTag<Item> plates = forgeItemTag("plates");
        final ITag.INamedTag<Item> platesUseless = forgeItemTag("plates/useless");
        final ITag.INamedTag<Item> platesSuperUseless = forgeItemTag("plates/super_useless");
        final ITag.INamedTag<Item> gears = forgeItemTag("gears");
        final ITag.INamedTag<Item> gearsUseless = forgeItemTag("gears/useless");
        final ITag.INamedTag<Item> gearsSuperUseless = forgeItemTag("gears/super_useless");
        final ITag.INamedTag<Item> rods = forgeItemTag("rods");
        final ITag.INamedTag<Item> rodsUseless = forgeItemTag("rods/useless");
        final ITag.INamedTag<Item> rodsSuperUseless = forgeItemTag("rods/super_useless");
        final ITag.INamedTag<Item> ores = Tags.Items.ORES;
        final ITag.INamedTag<Item> oresUseless = forgeItemTag("ores/useless");
        final ITag.INamedTag<Item> oresSuperUseless = forgeItemTag("ores/super_useless");
        final ITag.INamedTag<Item> storageBlocks = Tags.Items.STORAGE_BLOCKS;
        final ITag.INamedTag<Item> storageBlocksUseless = forgeItemTag("storage_blocks/useless");
        final ITag.INamedTag<Item> storageBlocksSuperUseless = forgeItemTag("storage_blocks/super_useless");
        final ITag.INamedTag<Item> seeds = Tags.Items.SEEDS;
        final ITag.INamedTag<Item> seedsUselessWheat = forgeItemTag("seeds/useless_wheat");
        final ITag.INamedTag<Item> seedsCoffeebean = forgeItemTag("seeds/coffeebean");
        final ITag.INamedTag<Item> seedsEnder = forgeItemTag("seeds/ender");
        final ITag.INamedTag<Item> crops = Tags.Items.CROPS;
        final ITag.INamedTag<Item> cropsUselessWheat = forgeItemTag("crops/useless_wheat");
        final ITag.INamedTag<Item> cropsCoffeebean = forgeItemTag("crops/coffeebean");
        final ITag.INamedTag<Item> cropsEnder = forgeItemTag("crops/ender");

        this.copy(Tags.Blocks.STONE, Tags.Items.STONE);
        this.copy(Tags.Blocks.COBBLESTONE, Tags.Items.COBBLESTONE);
        this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
        this.copy(forgeBlockTag("ores/useless"), oresUseless);
        this.copy(forgeBlockTag("ores/super_useless"), oresSuperUseless);
        this.copy(forgeBlockTag("ores"), ores);
        this.copy(forgeBlockTag("storage_blocks/useless"), storageBlocksUseless);
        this.copy(forgeBlockTag("storage_blocks/super_useless"), storageBlocksSuperUseless);
        this.copy(forgeBlockTag("storage_blocks"), storageBlocks);
        this.getOrCreateBuilder(dustsUseless).add(ItemInit.USELESS_DUST.get());
        this.getOrCreateBuilder(dustsSuperUseless).add(ItemInit.SUPER_USELESS_DUST.get());
        this.getOrCreateBuilder(dusts).addTag(dustsUseless).addTag(dustsSuperUseless);
        this.getOrCreateBuilder(ingotsUseless).add(ItemInit.USELESS_INGOT.get());
        this.getOrCreateBuilder(ingotsSuperUseless).add(ItemInit.SUPER_USELESS_INGOT.get());
        this.getOrCreateBuilder(ingots).addTag(ingotsUseless).addTag(ingotsSuperUseless);
        this.getOrCreateBuilder(nuggetsUseless).add(ItemInit.USELESS_NUGGET.get());
        this.getOrCreateBuilder(nuggetsSuperUseless).add(ItemInit.SUPER_USELESS_NUGGET.get());
        this.getOrCreateBuilder(nuggets).addTag(nuggetsUseless).addTag(nuggetsSuperUseless);
        this.getOrCreateBuilder(platesUseless).add(ItemInit.USELESS_PLATE.get());
        this.getOrCreateBuilder(platesSuperUseless).add(ItemInit.SUPER_USELESS_PLATE.get());
        this.getOrCreateBuilder(plates).addTag(platesUseless).addTag(platesSuperUseless);
        this.getOrCreateBuilder(gearsUseless).add(ItemInit.USELESS_GEAR.get());
        this.getOrCreateBuilder(gearsSuperUseless).add(ItemInit.SUPER_USELESS_GEAR.get());
        this.getOrCreateBuilder(gears).addTag(gearsUseless).addTag(gearsSuperUseless);
        this.getOrCreateBuilder(rodsUseless).add(ItemInit.USELESS_ROD.get());
        this.getOrCreateBuilder(rodsSuperUseless).add(ItemInit.SUPER_USELESS_ROD.get());
        this.getOrCreateBuilder(rods).addTag(rodsUseless).addTag(rodsSuperUseless);
        this.getOrCreateBuilder(ItemTags.BOATS).add(ItemInit.USELESS_BOAT.get(), ItemInit.SUPER_USELESS_BOAT.get());
        this.getOrCreateBuilder(ItemTags.SIGNS).add(ItemInit.USELESS_SIGN.get());
        this.getOrCreateBuilder(Tags.Items.LEATHER).add(ItemInit.USELESS_LEATHER.get());
        this.getOrCreateBuilder(Tags.Items.FEATHERS).add(ItemInit.USELESS_FEATHER.get());
        this.getOrCreateBuilder(Tags.Items.BONES).add(ItemInit.USELESS_BONE.get());
        this.getOrCreateBuilder(Tags.Items.HEADS).add(ItemInit.USELESS_SKELETON_SKULL.get());
        this.getOrCreateBuilder(seedsUselessWheat).add(ItemInit.USELESS_WHEAT_SEEDS.get());
        this.getOrCreateBuilder(seedsCoffeebean).add(ItemInit.COFFEE_SEEDS.get());
        this.getOrCreateBuilder(seedsEnder).add(ItemInit.ENDER_SEEDS.get());
        this.getOrCreateBuilder(seeds).addTag(seedsUselessWheat).addTag(seedsCoffeebean).addTag(seedsEnder);
        this.getOrCreateBuilder(cropsUselessWheat).add(ItemInit.USELESS_WHEAT.get());
        this.getOrCreateBuilder(cropsCoffeebean).add(ItemInit.COFFEE_BEANS.get());
        this.getOrCreateBuilder(cropsEnder).add(Items.ENDER_PEARL);
        this.getOrCreateBuilder(crops).addTag(cropsUselessWheat).addTag(cropsCoffeebean).addTag(cropsEnder);

        this.getOrCreateBuilder(Tags.Items.SHEARS).add(ItemInit.USELESS_SHEARS.get());
        this.getOrCreateBuilder(UselessTags.Items.TOOLS_WRENCH).add(ItemInit.USELESS_WRENCH.get());
        this.getOrCreateBuilder(UselessTags.Items.WRENCHES).add(ItemInit.USELESS_WRENCH.get());
        this.getOrCreateBuilder(UselessTags.Items.WRENCH).add(ItemInit.USELESS_WRENCH.get());

        this.getOrCreateBuilder(ItemTags.BEACON_PAYMENT_ITEMS).addTag(ingotsUseless).addTag(ingotsSuperUseless);

        this.copy(uselessBlockTag("useless_logs"), uselessItemTag("useless_logs"));
        this.copy(uselessBlockTag("lamps"), uselessItemTag("lamps"));
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOOL, ItemTags.WOOL);
        this.copy(BlockTags.CARPETS, ItemTags.CARPETS);
        this.copy(BlockTags.RAILS, ItemTags.RAILS);
        this.copy(BlockTags.BEDS, ItemTags.BEDS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
    }

    @Override
    public String getName() {
        return "Useless Item Tags";
    }
}
