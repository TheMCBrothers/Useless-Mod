package tk.themcbros.uselessmod.compat.waila;

public class HUDHandlerCrusher /*implements IComponentProvider, IServerDataProvider<TileEntity> {

	static final HUDHandlerCrusher INSTANCE = new HUDHandlerCrusher();

	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if (!config.get(UselessPlugin.CONFIG_DISPLAY_CRUSHER))
			return;

		if (!accessor.getBlockState().get(CrusherBlock.ACTIVE))
			return;

		ListNBT furnaceItems = accessor.getServerData().getList("crusher", Constants.NBT.TAG_COMPOUND);
		NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
		for (int i = 0; i < furnaceItems.size(); i++)
			inventory.set(i, ItemStack.read(furnaceItems.getCompound(i)));

		CompoundNBT progress = new CompoundNBT();
		progress.putInt("progress", accessor.getServerData().getInt("progress"));
		progress.putInt("total", accessor.getServerData().getInt("total"));

		RenderableTextComponent renderables = new RenderableTextComponent(getRenderable(inventory.get(0)),
				getRenderable(inventory.get(1)),
				new RenderableTextComponent(UselessPlugin.RENDER_CRUSHER_PROGRESS, progress),
				getRenderable(inventory.get(2)));

		tooltip.add(renderables);
	}

	@Override
	public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity blockEntity) {
		CrusherTileEntity crusher = (CrusherTileEntity) blockEntity;
		ListNBT items = new ListNBT();
		items.add(crusher.getStackInSlot(0).write(new CompoundNBT()));
		items.add(crusher.getStackInSlot(1).write(new CompoundNBT()));
		items.add(crusher.getStackInSlot(2).write(new CompoundNBT()));
		data.put("crusher", items);
		CompoundNBT furnaceTag = crusher.write(new CompoundNBT());
		data.putInt("progress", furnaceTag.getInt("CrushTime")); // smh
		data.putInt("total", furnaceTag.getInt("CrushTimeTotal")); // smh
	}

	private static RenderableTextComponent getRenderable(ItemStack stack) {
		if (!stack.isEmpty()) {
			CompoundNBT tag = new CompoundNBT();
			tag.putString("id", stack.getItem().getRegistryName().toString());
			tag.putInt("count", stack.getCount());
			if (stack.hasTag())
				tag.putString("nbt", stack.getTag().toString());
			return new RenderableTextComponent(UselessPlugin.RENDER_ITEM, tag);
		} else {
			CompoundNBT spacerTag = new CompoundNBT();
			spacerTag.putInt("width", 18);
			return new RenderableTextComponent(UselessPlugin.RENDER_SPACER, spacerTag);
		}
	}
}
*/{}