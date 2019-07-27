package tk.themcbros.uselessmod.lists;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
public class ModSounds {
	
	public static final SoundEvent MUSIC_DISC_SHOULD_I_STAY_OR_SHOULD_I_GO = null;
	public static final SoundEvent MOSKAU = null;

	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<SoundEvent> event) {
			IForgeRegistry<SoundEvent> registry = event.getRegistry();
			
			registry.register(new SoundEvent(SoundNames.MUSIC_DISC_SHOULD_I_STAY_OR_SHOULD_I_GO).setRegistryName(SoundNames.MUSIC_DISC_SHOULD_I_STAY_OR_SHOULD_I_GO));
			registry.register(new SoundEvent(SoundNames.MOSKAU).setRegistryName(SoundNames.MOSKAU));
			
			UselessMod.LOGGER.debug("Registered Sounds");
		}
	}
	
}
