package net.themcbrothers.uselessmod.init;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.world.worldgen.UselessVegetationPlacements;

public final class ModBiomes {
    static void register() {
    }

    public static final RegistryObject<Biome> USELESS_FOREST = Registration.BIOMES.register("useless_forest", ModBiomes::createUselessBiome);

    private static Biome createUselessBiome() {
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder();
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
        BiomeDefaultFeatures.farmAnimals(mobSpawnSettings);
        BiomeDefaultFeatures.commonSpawns(mobSpawnSettings);
        return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST)
                .temperature(0.7F).downfall(0.8F).specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x468b44).waterFogColor(0x468b44).fogColor(12638463).skyColor(0x40B45F).grassColorOverride(0x40B45F)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
                .generationSettings(biomeGenerationSettings.build())
                .mobSpawnSettings(mobSpawnSettings.build()).build();
    }
}
