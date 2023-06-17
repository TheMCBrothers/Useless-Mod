package net.themcbrothers.uselessmod.data;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.data.loot.UselessLootTableProvider;
import net.themcbrothers.uselessmod.data.worldgen.biome.UselessBiomeData;
import net.themcbrothers.uselessmod.data.worldgen.biome.UselessBiomeModifiers;
import net.themcbrothers.uselessmod.world.worldgen.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class UselessDataGen {
    @SubscribeEvent
    public static void dataGen(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final PackOutput packOutput = generator.getPackOutput();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Data
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder()
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
                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, UselessBiomeModifiers::bootstrap);

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, registrySetBuilder, Set.of(UselessMod.MOD_ID)));
        generator.addProvider(event.includeServer(), new UselessRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new UselessLanguageProvider(packOutput));
        generator.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput, lookupProvider, existingFileHelper, List.of()));
        generator.addProvider(event.includeServer(), UselessLootTableProvider.create(packOutput));
        final BlockTagsProvider blockTagsProvider = new UselessTagsProvider.Blocks(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Items(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Fluids(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Entities(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new UselessTagsProvider.Paintings(packOutput, lookupProvider, existingFileHelper));

        // Resources
        generator.addProvider(event.includeClient(), new UselessSpriteSourceProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new UselessBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new UselessItemModelProvider(packOutput, existingFileHelper));

        // pack.mcmeta
        generator.addProvider(true, new PackMetadataGenerator(packOutput))
                .add(PackMetadataSection.TYPE,
                        new PackMetadataSection(Component.literal("Useless Mod resources"),
                                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                                Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion))));
    }
}
