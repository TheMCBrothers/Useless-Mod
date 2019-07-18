package tk.themcbros.uselessmod.lists;

import net.minecraft.entity.item.PaintingType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
public class ModPaintings {

	public static final PaintingType USELESS_PAINTING = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		
		@SubscribeEvent
		public static void onPaintingRegistry(final RegistryEvent.Register<PaintingType> event) {
			IForgeRegistry<PaintingType> registry = event.getRegistry();
			
			registry.register(new PaintingType(64, 64).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "useless_painting")));
			
			UselessMod.LOGGER.info("Paintings registred");
		}
		
	}
	
}
