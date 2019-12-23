package tk.themcbros.uselessmod.compat.waila;

public class HUDHandlerModFluids /*implements IComponentProvider {

	static final HUDHandlerModFluids INSTANCE = new HUDHandlerModFluids();

	@Override
	public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
		if (accessor.getBlock() instanceof FlowingFluidBlock) {
			FlowingFluidBlock block = (FlowingFluidBlock) accessor.getBlock();
			return new ItemStack(block.getFluid().getFilledBucket());
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public void appendHead(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		tooltip.add(new StringTextComponent(Waila.CONFIG.get().getFormatting().getFluidName() + I18n.format(accessor.getBlock().getTranslationKey())));
	}

}
*/{}