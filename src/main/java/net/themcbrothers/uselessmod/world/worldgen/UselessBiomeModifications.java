package net.themcbrothers.uselessmod.world.worldgen;

import net.minecraftforge.fml.common.Mod;
import net.themcbrothers.uselessmod.UselessMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = UselessMod.MOD_ID)
public final class UselessBiomeModifications {
//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public static void setupBiomeModifications(final BiomeLoadingEvent event) {
//        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
//            event.getSpawns().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntityTypes.USELESS_SKELETON.get(), 50, 2, 2));
//        }
//        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UselessOrePlacements.ORE_USELESS_NETHER);
//        }
//        if (event.getCategory() == Biome.BiomeCategory.THEEND) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UselessOrePlacements.ORE_USELESS_END);
//        }
//        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UselessOrePlacements.ORE_USELESS_MIDDLE);
//        }
//    }
}
