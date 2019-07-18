package tk.themcbros.uselessmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import tk.themcbros.uselessmod.proxy.ClientProxy;
import tk.themcbros.uselessmod.proxy.CommonProxy;
import tk.themcbros.uselessmod.proxy.ServerProxy;

@Mod(UselessMod.MOD_ID)
public class UselessMod {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "uselessmod";

	public static UselessMod instance;
	public static CommonProxy proxy;

	public UselessMod() {

		instance = this;
		proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	}
	
	
}
