package tk.themcbros.uselessmod.handler;

import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.client.models.block.NewClosetModel;
import tk.themcbros.uselessmod.helper.ClientUtils;
import tk.themcbros.uselessmod.lists.ModBlocks;

import java.util.Map;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelBake {

    @SubscribeEvent
    public static void onModelBakeEvent(final ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();

        try {

            ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(ModBlocks.CLOSET);
            assert resourceLocation != null;
            ResourceLocation unbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(), "block/" + resourceLocation.getPath());
            ResourceLocation openUnbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(), "block/" + resourceLocation.getPath() + "_open");

            BlockModel model = (BlockModel) event.getModelLoader().getUnbakedModel(unbakedModelLoc);
            BlockModel openModel = (BlockModel) event.getModelLoader().getUnbakedModel(openUnbakedModelLoc);

            IBakedModel customModel = new NewClosetModel(event.getModelLoader(), model,
                    Objects.requireNonNull(model.func_225613_a_(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
                            ClientUtils.getRotation(Direction.NORTH), unbakedModelLoc)));
            IBakedModel customOpenModel = new NewClosetModel(event.getModelLoader(), openModel,
                    Objects.requireNonNull(openModel.func_225613_a_(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
                            ClientUtils.getRotation(Direction.NORTH), openUnbakedModelLoc)));

            // Replace all valid block states
            ModBlocks.CLOSET.getStateContainer().getValidStates().forEach(state -> {
                if (state.has(BlockStateProperties.OPEN) && state.get(BlockStateProperties.OPEN)) {
                    modelRegistry.put(BlockModelShapes.getModelLocation(state), customOpenModel);
                } else {
                    modelRegistry.put(BlockModelShapes.getModelLocation(state), customModel);
                }
            });

            // Replace inventory model
            modelRegistry.put(new ModelResourceLocation(resourceLocation, "inventory"), customOpenModel);

        } catch (Exception ex) {
            UselessMod.LOGGER.warn("Could not get base Closet model. Reverting to default textures...");
			ex.printStackTrace();
        }
    }

//  ---------------------- Original 1.14.4 code --------------------------
//	@SubscribeEvent
//	public static void onModelBakeEvent(final ModelBakeEvent event) {
//		Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();
//
//		try {
//			ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(ModBlocks.CLOSET);
//			ResourceLocation unbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(),
//					"block/" + resourceLocation.getPath());
//			ResourceLocation openUnbakedModelLoc = new ResourceLocation(resourceLocation.getNamespace(),
//					"block/" + resourceLocation.getPath() + "_open");
//
//			BlockModel model = (BlockModel) event.getModelLoader().getUnbakedModel(unbakedModelLoc);
//			BlockModel openModel = (BlockModel) event.getModelLoader().getUnbakedModel(openUnbakedModelLoc);
//			IBakedModel customModel = new ClosetModel(event.getModelLoader(), model,
//					model.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
//							TRSRTransformation.getRotation(Direction.NORTH), DefaultVertexFormats.BLOCK),
//					DefaultVertexFormats.BLOCK);
//			IBakedModel customOpenModel = new ClosetModel(event.getModelLoader(), openModel,
//					openModel.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(),
//							TRSRTransformation.getRotation(Direction.NORTH), DefaultVertexFormats.BLOCK),
//					DefaultVertexFormats.BLOCK);
//
//			// Replace all valid block states
//			ModBlocks.CLOSET.getStateContainer().getValidStates().forEach(state -> {
//				if(state.has(BlockStateProperties.OPEN) && state.get(BlockStateProperties.OPEN))
//					modelRegistry.put(BlockModelShapes.getModelLocation(state), customOpenModel);
//				else
//					modelRegistry.put(BlockModelShapes.getModelLocation(state), customModel);
//			});
//
//			// Replace inventory model
//			modelRegistry.put(new ModelResourceLocation(resourceLocation, "inventory"), customOpenModel);
//
//		} catch (Exception e) {
//			UselessMod.LOGGER.warn("Could not get base Closet model. Reverting to default textures...");
//			e.printStackTrace();
//		}
//	}
}