package tk.themcbros.uselessmod.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tk.themcbros.uselessmod.UselessMod;

public class ServerProxy extends CommonProxy {

	public ServerProxy() {
		super();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
	}
	
	private void serverSetup(FMLDedicatedServerSetupEvent event) {
		UselessMod.LOGGER.debug("ServerProxy serverSetup method");
	}
	
}
