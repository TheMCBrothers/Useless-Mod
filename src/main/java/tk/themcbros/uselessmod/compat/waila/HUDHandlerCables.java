package tk.themcbros.uselessmod.compat.waila;

public class HUDHandlerCables /*implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerCables INSTANCE = new HUDHandlerCables();

	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if (!config.get(UselessPlugin.CONFIG_CABLES))
			return;

		if(accessor.getServerData().contains("Info", Constants.NBT.TAG_STRING)) {
			tooltip.add(new StringTextComponent(accessor.getServerData().getString("Info")));
		}
	}

	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity blockEntity) {
		if (blockEntity instanceof EnergyCableTileEntity)
			data.putString("Info", ((EnergyCableTileEntity) blockEntity).getNetworkInfos());
		if (blockEntity instanceof FluidPipeTileEntity)
			data.putString("Info", ((FluidPipeTileEntity) blockEntity).getNetworkInfos());
	}
}
*/{}