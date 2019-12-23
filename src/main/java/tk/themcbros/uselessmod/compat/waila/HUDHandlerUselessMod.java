package tk.themcbros.uselessmod.compat.waila;

public class HUDHandlerUselessMod /*implements IComponentProvider {
	
	static final HUDHandlerUselessMod INSTANCE = new HUDHandlerUselessMod();

	@Override
	public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
		if(accessor.getBlock() == ModBlocks.USELESS_WHEAT)
			return new ItemStack(ModItems.USELESS_WHEAT);
		if(accessor.getBlock() == ModBlocks.COFFEE_SEEDS)
			return new ItemStack(ModItems.COFFEE_BEANS);
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
		if (config.get(UselessPlugin.CONFIG_LAMP) && accessor.getBlock() instanceof LampBlock) {
            boolean lit = accessor.getBlockState().get(BlockStateProperties.LIT);
            tooltip.add(new TranslationTextComponent("tooltip.waila.state", new TranslationTextComponent("tooltip.waila.state_" + (lit ? "on" : "off"))));
            return;
        }
		if(config.get(UselessPlugin.CONFIG_LANTERN) && accessor.getBlock() instanceof LanternBlock) {
			boolean lit = accessor.getBlock() == Blocks.LANTERN;
            tooltip.add(new TranslationTextComponent("tooltip.waila.state", new TranslationTextComponent("tooltip.waila.state_" + (lit ? "on" : "off"))));
            return;
		}
		if(config.get(UselessPlugin.CONFIG_LIGHT_SWITCH) && (accessor.getBlock() instanceof LightSwitchBlock || accessor.getBlock() instanceof LightSwitchBlockBlock)) {
			boolean lit = accessor.getBlockState().get(BlockStateProperties.POWERED);
            tooltip.add(new TranslationTextComponent("tooltip.waila.state", new TranslationTextComponent("tooltip.waila.state_" + (lit ? "on" : "off"))));
            return;
		}
	}
	
}*/{}
