package themcbros.uselessmod.events;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.client.models.block.WallClosetModel;
import themcbros.uselessmod.helpers.ClientUtils;
import themcbros.uselessmod.init.BlockInit;

import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onModelBakeEvent(final ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();

        try {

            ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(BlockInit.WALL_CLOSET.get());
            assert resourceLocation != null;
            ResourceLocation unbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(), "block/" + resourceLocation.getPath());
            ResourceLocation openUnbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(), "block/" + resourceLocation.getPath() + "_open");

            BlockModel model = (BlockModel) event.getModelLoader().getUnbakedModel(unbakedModelLoc);
            BlockModel openModel = (BlockModel) event.getModelLoader().getUnbakedModel(openUnbakedModelLoc);

            IBakedModel customModel = new WallClosetModel(event.getModelLoader(), model,
                    Objects.requireNonNull(model.bakeModel(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
                            ClientUtils.getRotation(Direction.NORTH), unbakedModelLoc)));
            IBakedModel customOpenModel = new WallClosetModel(event.getModelLoader(), openModel,
                    Objects.requireNonNull(openModel.bakeModel(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
                            ClientUtils.getRotation(Direction.NORTH), openUnbakedModelLoc)));

            // Replace all valid block states
            BlockInit.WALL_CLOSET.get().getStateContainer().getValidStates().forEach(state -> {
                if (state.hasProperty(BlockStateProperties.OPEN) && state.get(BlockStateProperties.OPEN)) {
                    modelRegistry.put(BlockModelShapes.getModelLocation(state), customOpenModel);
                } else {
                    modelRegistry.put(BlockModelShapes.getModelLocation(state), customModel);
                }
            });

            // Replace inventory model
            modelRegistry.put(new ModelResourceLocation(resourceLocation, "inventory"), customOpenModel);

        } catch (Exception ex) {
            UselessMod.LOGGER.warn("Could not get base Wall Closet model. Reverting to default textures...");
            ex.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void stitchTextures(final TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().getPath().contains("blocks")) {
            event.addSprite(UselessMod.rl("block/useless_generator"));
            event.addSprite(UselessMod.rl("entity/shield_useless"));
            event.addSprite(UselessMod.rl("entity/shield_super_useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("beds")) {
            event.addSprite(UselessMod.rl("entity/bed/useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("boats")) {
            event.addSprite(UselessMod.rl("entity/boat/useless"));
            event.addSprite(UselessMod.rl("entity/boat/super_useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("signs")) {
            event.addSprite(UselessMod.rl("entity/signs/useless"));
        }
        if (event.getMap().getTextureLocation().getPath().contains("chest")) {
            event.addSprite(UselessMod.rl("entity/chest/useless"));
            event.addSprite(UselessMod.rl("entity/chest/useless_left"));
            event.addSprite(UselessMod.rl("entity/chest/useless_right"));
        }
    }

}
