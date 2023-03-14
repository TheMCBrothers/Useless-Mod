package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.data.loot.UselessLootTableProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class UselessDataGen {
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Data
        generator.addProvider(event.includeServer(), new UselessRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new UselessLanguageProvider(generator));
        generator.addProvider(event.includeServer(), new UselessAdvancementProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessLootTableProvider(generator));
        final BlockTagsProvider blockTagsProvider = new UselessTagsProvider.Blocks(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Items(generator, blockTagsProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Entities(generator, existingFileHelper));

        // Resources
        generator.addProvider(event.includeClient(), new UselessBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(event.includeClient(), new UselessItemModelProvider(generator, existingFileHelper));
    }
}
