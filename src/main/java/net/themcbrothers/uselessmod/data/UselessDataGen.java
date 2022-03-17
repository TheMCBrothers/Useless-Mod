package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.data.loot.UselessLootTableProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class UselessDataGen {
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeServer()) {
            generator.addProvider(new UselessRecipeProvider(generator));
            generator.addProvider(new UselessLanguageProvider(generator));
            generator.addProvider(new UselessAdvancementProvider(generator, existingFileHelper));
            generator.addProvider(new UselessLootTableProvider(generator));
            final BlockTagsProvider blockTagsProvider = new UselessTagsProvider.Blocks(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new UselessTagsProvider.Items(generator, blockTagsProvider, existingFileHelper));
            generator.addProvider(new UselessTagsProvider.Entities(generator, existingFileHelper));
        }
        if (event.includeClient()) {
            generator.addProvider(new UselessBlockStateProvider(generator, existingFileHelper));
            generator.addProvider(new UselessItemModelProvider(generator, existingFileHelper));
        }
    }
}
