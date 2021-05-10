package themcbros.uselessmod.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.init.BlockInit;

/**
 * @author TheMCBrothers
 */
public class UselessBlockTagsProvider extends BlockTagsProvider {

    public UselessBlockTagsProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        // Forge Tags
        this.getOrCreateBuilder(Tags.Blocks.CHESTS_WOODEN).add(BlockInit.USELESS_OAK_CHEST.get());
        this.getOrCreateBuilder(Tags.Blocks.COBBLESTONE).add(BlockInit.USELESS_COBBLESTONE.get());
        this.getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(BlockInit.USELESS_OAK_FENCE_GATE.get());
        this.getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).add(BlockInit.USELESS_OAK_FENCE.get());

        this.getOrCreateBuilder(UselessTags.Blocks.ORES_USELESS).add(BlockInit.USELESS_ORE.get(), BlockInit.USELESS_ORE_NETHER.get(), BlockInit.USELESS_ORE_END.get());
        this.getOrCreateBuilder(UselessTags.Blocks.ORES_SUPER_USELESS).add(BlockInit.SUPER_USELESS_ORE.get(), BlockInit.SUPER_USELESS_ORE_NETHER.get(), BlockInit.SUPER_USELESS_ORE_END.get());
        this.getOrCreateBuilder(Tags.Blocks.ORES)
                .addTag(UselessTags.Blocks.ORES_USELESS)
                .addTag(UselessTags.Blocks.ORES_SUPER_USELESS);

        this.getOrCreateBuilder(Tags.Blocks.STONE).add(BlockInit.USELESS_STONE.get());
        this.getOrCreateBuilder(UselessTags.Blocks.STORAGE_BLOCKS_USELESS).add(BlockInit.USELESS_BLOCK.get());
        this.getOrCreateBuilder(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS).add(BlockInit.SUPER_USELESS_BLOCK.get());
        this.getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(UselessTags.Blocks.STORAGE_BLOCKS_USELESS)
                .addTag(UselessTags.Blocks.STORAGE_BLOCKS_SUPER_USELESS);

        // Vanilla Tags
        this.getOrCreateBuilder(UselessTags.Blocks.USELESS_LOGS).add(
                BlockInit.USELESS_OAK_LOG.get(), BlockInit.USELESS_OAK_WOOD.get(),
                BlockInit.STRIPPED_USELESS_OAK_LOG.get(), BlockInit.STRIPPED_USELESS_OAK_WOOD.get()
        );
        this.getOrCreateBuilder(BlockTags.LOGS_THAT_BURN).addTag(UselessTags.Blocks.USELESS_LOGS);
        this.getOrCreateBuilder(BlockTags.PLANKS).add(BlockInit.USELESS_OAK_PLANKS.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(BlockInit.USELESS_OAK_BUTTON.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(BlockInit.USELESS_OAK_DOOR.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(BlockInit.USELESS_OAK_STAIRS.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(BlockInit.USELESS_OAK_SLAB.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(BlockInit.USELESS_OAK_FENCE.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_TRAPDOORS).add(BlockInit.USELESS_OAK_TRAPDOOR.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(BlockInit.USELESS_OAK_PRESSURE_PLATE.get());

        this.getOrCreateBuilder(BlockTags.STANDING_SIGNS).add(BlockInit.USELESS_OAK_SIGN.get());
        this.getOrCreateBuilder(BlockTags.WALL_SIGNS).add(BlockInit.USELESS_OAK_WALL_SIGN.get());
        this.getOrCreateBuilder(BlockTags.FENCE_GATES).add(BlockInit.USELESS_OAK_FENCE_GATE.get());

        this.getOrCreateBuilder(BlockTags.SAPLINGS).add(BlockInit.USELESS_OAK_SAPLING.get());
        this.getOrCreateBuilder(BlockTags.LEAVES).add(BlockInit.USELESS_OAK_LEAVES.get());
        this.getOrCreateBuilder(BlockTags.SMALL_FLOWERS).add(BlockInit.RED_ROSE.get(), BlockInit.BLUE_ROSE.get(), BlockInit.USELESS_ROSE.get());
        this.getOrCreateBuilder(BlockTags.CROPS).add(BlockInit.USELESS_WHEAT.get(), BlockInit.COFFEE_CROP.get());
        this.getOrCreateBuilder(BlockTags.FLOWER_POTS).add(
                BlockInit.POTTED_RED_ROSE.get(),
                BlockInit.POTTED_BLUE_ROSE.get(),
                BlockInit.POTTED_USELESS_ROSE.get(),
                BlockInit.POTTED_USELESS_OAK_SAPLING.get()
        );

        this.getOrCreateBuilder(BlockTags.WOOL).add(BlockInit.USELESS_WOOL.get());
        this.getOrCreateBuilder(BlockTags.CARPETS).add(BlockInit.USELESS_CARPET.get());
        this.getOrCreateBuilder(BlockTags.BEDS).add(BlockInit.USELESS_BED.get());
        this.getOrCreateBuilder(BlockTags.STAIRS).add(BlockInit.USELESS_STONE_STAIRS.get(), BlockInit.USELESS_COBBLESTONE_STAIRS.get());
        this.getOrCreateBuilder(BlockTags.SLABS).add(BlockInit.USELESS_STONE_SLAB.get(), BlockInit.USELESS_COBBLESTONE_SLAB.get());
        this.getOrCreateBuilder(BlockTags.WALLS).add(BlockInit.USELESS_COBBLESTONE_WALL.get());
        this.getOrCreateBuilder(BlockTags.BUTTONS).add(BlockInit.USELESS_STONE_BUTTON.get());

        this.getOrCreateBuilder(BlockTags.STONE_PRESSURE_PLATES).addItemEntry(BlockInit.USELESS_STONE_PRESSURE_PLATE.get());
        this.getOrCreateBuilder(BlockTags.BASE_STONE_OVERWORLD).addItemEntry(BlockInit.USELESS_STONE.get());

        this.getOrCreateBuilder(BlockTags.RAILS).add(
                BlockInit.USELESS_RAIL.get(),
                BlockInit.POWERED_USELESS_RAIL.get(),
                BlockInit.USELESS_ACTIVATOR_RAIL.get(),
                BlockInit.USELESS_DETECTOR_RAIL.get(),
                BlockInit.USELESS_CROSSOVER_RAIL.get()
        );
        this.getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(BlockInit.USELESS_BLOCK.get(), BlockInit.SUPER_USELESS_BLOCK.get());
        this.getOrCreateBuilder(BlockTags.IMPERMEABLE).addItemEntry(BlockInit.MACHINE_FRAME.get());
        this.getOrCreateBuilder(BlockTags.FIRE).addItemEntry(BlockInit.USELESS_FIRE.get());

        // Mod Tags
        this.getOrCreateBuilder(UselessTags.Blocks.LAMPS).add(
                BlockInit.WHITE_LAMP.get(),
                BlockInit.ORANGE_LAMP.get(),
                BlockInit.MAGENTA_LAMP.get(),
                BlockInit.LIGHT_BLUE_LAMP.get(),
                BlockInit.YELLOW_LAMP.get(),
                BlockInit.LIME_LAMP.get(),
                BlockInit.PINK_LAMP.get(),
                BlockInit.GRAY_LAMP.get(),
                BlockInit.LIGHT_GRAY_LAMP.get(),
                BlockInit.CYAN_LAMP.get(),
                BlockInit.PURPLE_LAMP.get(),
                BlockInit.BLUE_LAMP.get(),
                BlockInit.BROWN_LAMP.get(),
                BlockInit.GREEN_LAMP.get(),
                BlockInit.RED_LAMP.get(),
                BlockInit.BLACK_LAMP.get()
        );
    }

    @Override
    public String getName() {
        return "Useless Block Tags";
    }
}
