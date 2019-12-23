package tk.themcbros.uselessmod.client.models.block;

public class ClosetItemOverride /*extends ItemOverrideList {


	@Override
	public IBakedModel getModelWithOverrides(IBakedModel modelOriginal, ItemStack stack, World worldIn, LivingEntity entityIn) {
		if(modelOriginal instanceof ClosetModel) {
			CompoundNBT tag = stack.getChildTag("uselessmod");
			if(tag != null) {
				String casingId = ClosetRegistry.CASINGS.get(tag.getString("casingId")).getTexture();
				String beddingId = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId")).getTexture();
				return ((ClosetModel) modelOriginal).getCustomModel(casingId, beddingId, Direction.NORTH);
			}
		}
		
		return modelOriginal;
	}

}
*/{}