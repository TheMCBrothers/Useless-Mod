package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.core.UselessPaintingVariants;
import net.themcbrothers.uselessmod.datagen.loot.UselessLootTableProvider;
import net.themcbrothers.uselessmod.datagen.models.BlockModelProvider;
import net.themcbrothers.uselessmod.datagen.models.ItemModelProvider;
import net.themcbrothers.uselessmod.datagen.models.UselessModelProvider;
import net.themcbrothers.uselessmod.datagen.worldgen.biome.UselessBiomeData;
import net.themcbrothers.uselessmod.datagen.worldgen.biome.UselessBiomeModifiers;
import net.themcbrothers.uselessmod.world.worldgen.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = UselessMod.MOD_ID)
public class UselessDataGen {
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent.Client event) {
        final DataGenerator generator = event.getGenerator();
        final PackOutput packOutput = generator.getPackOutput();

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
        generator.addProvider(true, datapackBuiltinEntriesProvider);

        // Now we can actually use this data
        final CompletableFuture<HolderLookup.Provider> lookupProvider = datapackBuiltinEntriesProvider.getRegistryProvider();

        generator.addProvider(true, new UselessRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new UselessLanguageProvider(packOutput));
        generator.addProvider(true, new AdvancementProvider(packOutput, lookupProvider, List.of(new UselessAdvancementProvider())));
        generator.addProvider(true, UselessLootTableProvider.create(packOutput, lookupProvider));
        final BlockTagsProvider blockTagsProvider = new UselessTagsProvider.Blocks(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new UselessTagsProvider.Items(packOutput, lookupProvider, blockTagsProvider.contentsGetter()));
        generator.addProvider(true, new UselessTagsProvider.Fluids(packOutput, lookupProvider));
        generator.addProvider(true, new UselessTagsProvider.Entities(packOutput, lookupProvider));
        generator.addProvider(true, new UselessTagsProvider.Paintings(packOutput, lookupProvider));
        generator.addProvider(true, new UselessDataMapsProvider(packOutput, lookupProvider));

        // Resources
        generator.addProvider(true, new UselessSpriteSourceProvider(packOutput, lookupProvider));
        generator.addProvider(true, UselessModelProvider.create(
                BlockModelProvider::new,
                ItemModelProvider::new
        ));
    }
}
