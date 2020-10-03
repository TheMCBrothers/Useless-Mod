package themcbros.uselessmod.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.init.UselessFeatures;

/**
 * @author TheMCBrothers
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = UselessMod.MOD_ID)
public class UselessWorldGen {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder settings = event.getGeneration();

        if (Config.SERVER_CONFIG.oreGenOverworld.get()) {
            // Overworld Ores
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.USELESS_ORE);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.SUPER_USELESS_ORE);
        }
        if (Config.SERVER_CONFIG.oreGenOverworld.get()) {
            // Nether Ores
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.USELESS_ORE_NETHER);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.SUPER_USELESS_ORE_NETHER);
        }
        if (Config.SERVER_CONFIG.oreGenOverworld.get()) {
            // End Ores
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.USELESS_ORE_END);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, UselessFeatures.SUPER_USELESS_ORE_END);
        }

        // Flowers
        if (event.getCategory() == Biome.Category.PLAINS) {
            settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UselessFeatures.USELESS_ROSES);
        }
    }

}
