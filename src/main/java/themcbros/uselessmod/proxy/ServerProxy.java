package themcbros.uselessmod.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ServerProxy extends CommonProxy {

    public ServerProxy() {
        super();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
        // Server Setup
    }

}
