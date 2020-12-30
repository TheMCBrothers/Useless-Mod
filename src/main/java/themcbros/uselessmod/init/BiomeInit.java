package themcbros.uselessmod.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.world.structure.UselessHouseStructure;

public class BiomeInit {

    public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, UselessMod.MOD_ID);

    public static final RegistryObject<Biome> USELESS_FOREST = REGISTER.register("useless_forest",
            BiomeInit::createUselessForest);

    private static Biome createUselessForest() {
        MobSpawnInfo.Builder mobSpawnInfo = uselessForestSpawnList()
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 5, 4, 4))
                .isValidSpawnBiomeForPlayer();

        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
                .withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j);

        // Structures
        DefaultBiomeFeatures.withStrongholdAndMineshaft(biomeGenerationSettings);
        biomeGenerationSettings.withStructure(StructureFeatures.RUINED_PORTAL);
        biomeGenerationSettings.withStructure(UselessHouseStructure.CONFIGURED_INSTANCE);

        DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(biomeGenerationSettings);
        DefaultBiomeFeatures.withMonsterRoom(biomeGenerationSettings);

        // Underground Ores / Disks
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomeGenerationSettings);
        DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettings);
        DefaultBiomeFeatures.withDisks(biomeGenerationSettings);

        // Vegetal Decoration
        biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UselessFeatures.USELESS_ROSES);
        biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UselessFeatures.USELESS_TREES);
        DefaultBiomeFeatures.withDefaultFlowers(biomeGenerationSettings);
        DefaultBiomeFeatures.withForestGrass(biomeGenerationSettings);
        DefaultBiomeFeatures.withLightBambooVegetation(biomeGenerationSettings);

        DefaultBiomeFeatures.withNormalMushroomGeneration(biomeGenerationSettings);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(biomeGenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomeGenerationSettings);
        DefaultBiomeFeatures.withFrozenTopLayer(biomeGenerationSettings);
        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F)
                .scale(0.2F).temperature(0.7F).downfall(0.8F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x468b44)
                        .setWaterFogColor(0x468b44).setFogColor(12638463).withSkyColor(0x40B45F)
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).withGrassColor(0x40B45F).build())
                .withMobSpawnSettings(mobSpawnInfo.copy())
                .withGenerationSettings(biomeGenerationSettings.build()).build();
    }

    private static MobSpawnInfo.Builder uselessForestSpawnList() {
        MobSpawnInfo.Builder builder = new MobSpawnInfo.Builder();
        builder.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityInit.USELESS_SHEEP.get(), 12, 4, 4));
        builder.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityInit.USELESS_PIG.get(), 10, 4, 4));
        builder.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityInit.USELESS_CHICKEN.get(), 10, 4, 4));
        builder.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityInit.USELESS_COW.get(), 8, 4, 4));
        builder.withSpawner(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityInit.USELESS_SKELETON.get(), 100, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
        return builder;
    }

}
