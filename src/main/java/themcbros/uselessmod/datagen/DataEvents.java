package themcbros.uselessmod.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * @author TheMCBrothers
 */
public class DataEvents {
    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeClient()) {
            generator.addProvider(new UselessEnglishLanguageProvider(generator));
        }
        if (event.includeServer()) {
            generator.addProvider(new UselessRecipeProvider(generator));
            generator.addProvider(new UselessAdvancementProvider(generator));
            BlockTagsProvider blockTagsProvider = new UselessBlockTagsProvider(generator);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new UselessItemTagsProvider(generator, blockTagsProvider));
            generator.addProvider(new UselessFluidTagsProvider(generator, event.getExistingFileHelper()));
            generator.addProvider(new UselessEntityTypeTagsProvider(generator));
//            generator.addProvider(new UselessLootTableProvider(generator));
        }
    }
}