package tk.themcbros.uselessmod.compat.waila;

public class HUDHandlerCloset /*implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerCloset INSTANCE = new HUDHandlerCloset();
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if(config.get(UselessPlugin.CONFIG_DISPLAY_CLOSET_MATERIAL) && accessor.getBlock() == ModBlocks.CLOSET) {
			CompoundNBT tag = accessor.getServerData();
			IClosetMaterial casingId = ClosetRegistry.CASINGS.get(tag.getString("casingId"));
			IClosetMaterial beddingId = ClosetRegistry.BEDDINGS.get(tag.getString("beddingId"));
			tooltip.add(casingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
			tooltip.add(beddingId.getTooltip().applyTextStyle(TextFormatting.GRAY));
		}
	}
	
	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
		if(tileEntity instanceof ClosetTileEntity) {
			ClosetTileEntity closet = (ClosetTileEntity) tileEntity;
			CompoundNBT closetTags = closet.write(new CompoundNBT());
			data.putString("casingId", closetTags.getString("casingId"));
			data.putString("beddingId", closetTags.getString("beddingId"));
		}
	}

}
*/{}