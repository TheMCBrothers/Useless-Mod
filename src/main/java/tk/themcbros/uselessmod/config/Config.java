package tk.themcbros.uselessmod.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import tk.themcbros.uselessmod.UselessMod;

@Mod.EventBusSubscriber
public class Config {
	
	private static final ForgeConfigSpec.Builder server_builder = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec server_config;
	
	private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec client_config;
	
	static {
		
		OreGenConfig.init(server_builder, client_builder);
		
		server_config = server_builder.build();
		client_config = client_builder.build();
		
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		UselessMod.LOGGER.info("Loading config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		UselessMod.LOGGER.info("Built config: " + path);
		file.load();
		UselessMod.LOGGER.info("Loaded config: " + path);
		config.setConfig(file);
	}
	
}
