package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.item.PaintingType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
public class ModPaintings {

	private static final List<PaintingType> TYPES = Lists.newArrayList();
	
	public static final PaintingType USELESS_PAINTING = register("useless_painting", new PaintingType(64, 64));
	
	private static <T extends PaintingType> T register(String regName, T type) {
		type.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, regName));
		TYPES.add(type);
		return type;
	}
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onPaintingRegistry(final RegistryEvent.Register<PaintingType> event) {
			TYPES.forEach(type -> event.getRegistry().register(type));
			UselessMod.LOGGER.info("Registered paintings");
		}
	}
	
}
