package tk.themcbros.uselessmod.lists;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
public class ModPotions {

	public static final Potion BLACK_COFFEE = null;
	public static final Potion MILK_COFFEE = null;
	public static final Potion SUGAR_COFFEE = null;
	public static final Potion MILK_SUGAR_COFFEE = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<Potion> event) {
			IForgeRegistry<Potion> registry = event.getRegistry();
			
			registry.register(new Potion(new EffectInstance(Effects.SPEED, 100, 2, false, true, false)).setRegistryName(PotionNames.BLACK_COFFEE));
			registry.register(new Potion(new EffectInstance(Effects.JUMP_BOOST, 100, 2, false, true, false)).setRegistryName(PotionNames.MILK_COFFEE));
			registry.register(new Potion(new EffectInstance(Effects.REGENERATION, 100, 2, false, true, false)).setRegistryName(PotionNames.SUGAR_COFFEE));
			registry.register(new Potion(
					new EffectInstance(Effects.SPEED, 100, 2, false, true, false),
					new EffectInstance(Effects.JUMP_BOOST, 100, 2, false, true, false),
					new EffectInstance(Effects.REGENERATION, 100, 2, false, true, false)).setRegistryName(PotionNames.MILK_SUGAR_COFFEE));
			
			UselessMod.LOGGER.debug("Registered Potions");
		}
	}
	
}
