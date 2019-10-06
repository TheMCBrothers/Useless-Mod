package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
public class ModPotions {
	
	private static final List<Potion> POTIONS = Lists.newArrayList();

	public static final Potion BLACK_COFFEE = register("black_coffee", new Potion(new EffectInstance(Effects.SPEED, 100, 2, false, true, false)));
	public static final Potion MILK_COFFEE = register("milk_coffee", new Potion(new EffectInstance(Effects.JUMP_BOOST, 100, 2, false, true, false)));
	public static final Potion SUGAR_COFFEE = register("sugar_coffee", new Potion(new EffectInstance(Effects.REGENERATION, 100, 2, false, true, false)));
	public static final Potion MILK_SUGAR_COFFEE = register("milk_sugar_coffee", new Potion(
			new EffectInstance(Effects.SPEED, 100, 2, false, true, false),
			new EffectInstance(Effects.JUMP_BOOST, 100, 2, false, true, false),
			new EffectInstance(Effects.REGENERATION, 100, 2, false, true, false)));
	
	private static <T extends Potion> T register(String regName, T potion) {
		potion.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, regName));
		POTIONS.add(potion);
		return potion;
	}
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onRegister(final RegistryEvent.Register<Potion> event) {
			POTIONS.forEach(potion -> event.getRegistry().register(potion));
			UselessMod.LOGGER.info("Registered Potions");
		}
	}
	
}
