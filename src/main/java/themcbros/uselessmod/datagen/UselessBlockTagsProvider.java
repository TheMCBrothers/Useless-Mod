package themcbros.uselessmod.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import themcbros.uselessmod.init.BlockInit;

import static themcbros.uselessmod.helpers.TagUtils.forgeBlockTag;
import static themcbros.uselessmod.helpers.TagUtils.uselessBlockTag;

/**
 * @author TheMCBrothers
 */
public class UselessBlockTagsProvider extends BlockTagsProvider {

    public UselessBlockTagsProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {
        // Forge Tags
        this.getOrCreateBuilder(Tags.Blocks.STONE).add(BlockInit.USELESS_STONE.get());
        this.getOrCreateBuilder(Tags.Blocks.COBBLESTONE).add(BlockInit.USELESS_COBBLESTONE.get());
        this.getOrCreateBuilder(forgeBlockTag("ores/useless")).add(BlockInit.USELESS_ORE.get(), BlockInit.USELESS_ORE_NETHER.get(), BlockInit.USELESS_ORE_END.get());
        this.getOrCreateBuilder(forgeBlockTag("ores/super_useless")).add(BlockInit.SUPER_USELESS_ORE.get(), BlockInit.SUPER_USELESS_ORE_NETHER.get(), BlockInit.SUPER_USELESS_ORE_END.get());
        this.getOrCreateBuilder(forgeBlockTag("ores"))
                .addTag(forgeBlockTag("ores/useless"))
                .addTag(forgeBlockTag("ores/super_useless"));
        this.getOrCreateBuilder(forgeBlockTag("storage_blocks/useless")).add(BlockInit.USELESS_BLOCK.get());
        this.getOrCreateBuilder(forgeBlockTag("storage_blocks/super_useless")).add(BlockInit.SUPER_USELESS_BLOCK.get());
        this.getOrCreateBuilder(forgeBlockTag("storage_blocks"))
                .addTag(forgeBlockTag("storage_blocks/useless"))
                .addTag(forgeBlockTag("storage_blocks/super_useless"));

        this.getOrCreateBuilder(Tags.Blocks.CHESTS_WOODEN).add(BlockInit.USELESS_CHEST.get());
        this.getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(BlockInit.USELESS_FENCE_GATE.get());
        this.getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).add(BlockInit.USELESS_FENCE.get());

        // Vanilla Tags
        this.getOrCreateBuilder(BlockTags.WALLS).add(BlockInit.USELESS_COBBLESTONE_WALL.get());
        this.getOrCreateBuilder(BlockTags.SAPLINGS).add(BlockInit.USELESS_SAPLING.get());
        this.getOrCreateBuilder(BlockTags.LEAVES).add(BlockInit.USELESS_LEAVES.get());
        this.getOrCreateBuilder(uselessBlockTag("useless_logs")).add(BlockInit.USELESS_LOG.get(), BlockInit.USELESS_WOOD.get(), BlockInit.STRIPPED_USELESS_LOG.get(), BlockInit.STRIPPED_USELESS_WOOD.get());
        this.getOrCreateBuilder(BlockTags.LOGS).addTag(uselessBlockTag("useless_logs"));
        this.getOrCreateBuilder(BlockTags.PLANKS).add(BlockInit.USELESS_PLANKS.get());
        this.getOrCreateBuilder(BlockTags.SLABS).add(BlockInit.USELESS_STONE_SLAB.get(), BlockInit.USELESS_COBBLESTONE_SLAB.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(BlockInit.USELESS_SLAB.get());
        this.getOrCreateBuilder(BlockTags.STAIRS).add(BlockInit.USELESS_STONE_STAIRS.get(), BlockInit.USELESS_COBBLESTONE_STAIRS.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(BlockInit.USELESS_STAIRS.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(BlockInit.WOODEN_USELESS_DOOR.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_TRAPDOORS).add(BlockInit.WOODEN_USELESS_TRAPDOOR.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(BlockInit.USELESS_FENCE.get());
        this.getOrCreateBuilder(BlockTags.FENCE_GATES).add(BlockInit.USELESS_FENCE_GATE.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(BlockInit.USELESS_BUTTON.get());
        this.getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(BlockInit.USELESS_PRESSURE_PLATE.get());
        this.getOrCreateBuilder(BlockTags.STONE_PRESSURE_PLATES).add(BlockInit.USELESS_STONE_PRESSURE_PLATE.get());
        this.getOrCreateBuilder(BlockTags.STANDING_SIGNS).add(BlockInit.USELESS_SIGN.get());
        this.getOrCreateBuilder(BlockTags.WALL_SIGNS).add(BlockInit.USELESS_WALL_SIGN.get());

        this.getOrCreateBuilder(BlockTags.CROPS).add(BlockInit.USELESS_WHEAT.get(), BlockInit.COFFEE_CROP.get());
        this.getOrCreateBuilder(BlockTags.SMALL_FLOWERS).add(BlockInit.RED_ROSE.get(), BlockInit.BLUE_ROSE.get(), BlockInit.USELESS_ROSE.get());
        this.getOrCreateBuilder(BlockTags.FLOWER_POTS).add(
                BlockInit.POTTED_RED_ROSE.get(),
                BlockInit.POTTED_BLUE_ROSE.get(),
                BlockInit.POTTED_USELESS_ROSE.get(),
                BlockInit.POTTED_USELESS_SAPLING.get()
        );
        this.getOrCreateBuilder(BlockTags.WOOL).add(BlockInit.USELESS_WOOL.get());
        this.getOrCreateBuilder(BlockTags.CARPETS).add(BlockInit.USELESS_CARPET.get());
        this.getOrCreateBuilder(BlockTags.BEDS).add(BlockInit.USELESS_BED.get());
        this.getOrCreateBuilder(BlockTags.RAILS).add(
                BlockInit.USELESS_RAIL.get(),
                BlockInit.POWERED_USELESS_RAIL.get(),
                BlockInit.USELESS_ACTIVATOR_RAIL.get(),
                BlockInit.USELESS_DETECTOR_RAIL.get(),
                BlockInit.USELESS_CROSSOVER_RAIL.get()
        );
        this.getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(BlockInit.USELESS_BLOCK.get(), BlockInit.SUPER_USELESS_BLOCK.get());
        this.getOrCreateBuilder(BlockTags.FIRE).add(BlockInit.USELESS_FIRE.get());

        // Mod Tags
        this.getOrCreateBuilder(uselessBlockTag("lamps")).add(
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
