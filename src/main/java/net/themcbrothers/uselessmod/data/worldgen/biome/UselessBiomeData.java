package net.themcbrothers.uselessmod.data.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.world.level.biome.UselessBiomes;
import net.themcbrothers.uselessmod.world.worldgen.UselessVegetationPlacements;

public final class UselessBiomeData {
    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(UselessBiomes.USELESS_FOREST, createUselessBiome(placedFeatures, configuredCarvers));
    }

    private static Biome createUselessBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers) {
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSprings(biomeGenerationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);
        BiomeDefaultFeatures.addForestFlowers(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);
        BiomeDefaultFeatures.addForestGrass(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeGenerationSettings);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UselessVegetationPlacements.FLOWER_USELESS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UselessVegetationPlacements.TREES_USELESS_OAK);

        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.USELESS_SHEEP.get(), 12, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.USELESS_PIG.get(), 10, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.USELESS_CHICKEN.get(), 10, 4, 4));
        mobSpawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.USELESS_COW.get(), 8, 4, 4));
        BiomeDefaultFeatures.commonSpawns(mobSpawnSettings);
        return new Biome.BiomeBuilder()
                .temperature(0.7F).downfall(0.8F).specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x468b44).waterFogColor(0x468b44).fogColor(12638463).skyColor(0x40B45F).grassColorOverride(0x40B45F)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
                .generationSettings(biomeGenerationSettings.build())
                .mobSpawnSettings(mobSpawnSettings.build()).build();
    }
}
