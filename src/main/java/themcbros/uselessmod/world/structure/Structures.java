package themcbros.uselessmod.world.structure;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static themcbros.uselessmod.UselessMod.rl;

public class Structures {
    public static IStructurePieceType uselessHousePiece;

    @SubscribeEvent
    public void onFeaturesRegistry(final RegistryEvent.Register<Feature<?>> event) {
        uselessHousePiece = Registry.register(Registry.STRUCTURE_PIECE, rl("useless_house_piece"), UselessHousePieces.Piece::new);
    }

    @SubscribeEvent
    public void onStructuresRegistry(final RegistryEvent.Register<Structure<?>> event) {
        event.getRegistry().register(
                UselessHouseStructure.INSTANCE.setRegistryName(UselessHouseStructure.ID)
        );
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, rl("useless_house"), UselessHouseStructure.CONFIGURED_INSTANCE);
        Structure.field_236365_a_.put("uselessmod:useless_house", UselessHouseStructure.INSTANCE);
//        DimensionSettings.func_242746_i().getStructures().func_236195_a_().put(UselessHouseStructure.INSTANCE, new StructureSeparationSettings(20, 11, 14357800));
    }

}
