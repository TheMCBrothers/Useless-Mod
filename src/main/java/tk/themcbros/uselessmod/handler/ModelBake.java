package tk.themcbros.uselessmod.handler;

//@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelBake {

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