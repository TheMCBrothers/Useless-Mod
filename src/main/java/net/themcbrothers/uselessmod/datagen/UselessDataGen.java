package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessPaintingVariants;
import net.themcbrothers.uselessmod.datagen.loot.UselessLootTableProvider;
import net.themcbrothers.uselessmod.datagen.worldgen.biome.UselessBiomeData;
import net.themcbrothers.uselessmod.datagen.worldgen.biome.UselessBiomeModifiers;
import net.themcbrothers.uselessmod.world.worldgen.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class UselessDataGen {
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final PackOutput packOutput = generator.getPackOutput();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Data driven registries
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder()
                .add(Registries.PAINTING_VARIANT, UselessPaintingVariants::bootstrap)
                .add(Registries.CONFIGURED_FEATURE, context -> {
                    UselessTreeFeatures.bootstrap(context);
                    UselessVegetationFeatures.bootstrap(context);
                    UselessOreFeatures.bootstrap(context);
                })
                .add(Registries.PLACED_FEATURE, context -> {
                    UselessTreePlacements.bootstrap(context);
                    UselessVegetationPlacements.bootstrap(context);
                    UselessOrePlacements.bootstrap(context);
                })
                .add(Registries.BIOME, UselessBiomeData::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UselessBiomeModifiers::bootstrap);

        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(packOutput, event.getLookupProvider(), registrySetBuilder, Set.of(UselessMod.MOD_ID));
        generator.addProvider(event.includeServer(), datapackBuiltinEntriesProvider);

        // Now we can actually use this data
        final CompletableFuture<HolderLookup.Provider> lookupProvider = datapackBuiltinEntriesProvider.getRegistryProvider();

        generator.addProvider(event.includeServer(), new UselessRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new UselessLanguageProvider(packOutput));
        generator.addProvider(event.includeServer(), new AdvancementProvider(packOutput, lookupProvider, existingFileHelper, List.of(new UselessAdvancementProvider())));
        generator.addProvider(event.includeServer(), UselessLootTableProvider.create(packOutput, lookupProvider));
        final BlockTagsProvider blockTagsProvider = new UselessTagsProvider.Blocks(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Items(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Fluids(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Entities(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Paintings(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessDataMapsProvider(packOutput, lookupProvider));

        // Resources
        generator.addProvider(event.includeClient(), new UselessSpriteSourceProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new UselessBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new UselessItemModelProvider(packOutput, existingFileHelper));
    }
}
