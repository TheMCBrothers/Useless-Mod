package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.*;

@ObjectHolder(UselessMod.MOD_ID)
public class ModContainerTypes {
	
	private static final List<ContainerType<?>> CONTAINER_TYPES = Lists.newArrayList();

	// Machines
	public static final ContainerType<CrusherContainer> CRUSHER = register("crusher_gui", new ContainerType<>(CrusherContainer::new));
	public static final ContainerType<ElectricCrusherContainer> ELECTRIC_CRUSHER = register("electric_crusher_gui", new ContainerType<>(ElectricCrusherContainer::new));
	public static final ContainerType<ElectricFurnaceContainer> ELECTRIC_FURNACE = register("electric_furnace_gui", new ContainerType<>(ElectricFurnaceContainer::new));
	public static final ContainerType<CompressorContainer> COMPRESSOR = register("compressor_gui", new ContainerType<>(CompressorContainer::new));
	public static final ContainerType<MagmaCrucibleContainer> MAGMA_CRUCIBLE = register("magma_crucible", new ContainerType<>(MagmaCrucibleContainer::new));
	public static final ContainerType<ChargerContainer> CHARGER = register("charger", new ContainerType<>(ChargerContainer::new));
	public static final ContainerType<GlowstoneGeneratorContainer> GLOWSTONE_GENRATOR = register("glowstone_generator_gui", new ContainerType<>(GlowstoneGeneratorContainer::new));
	public static final ContainerType<LavaGeneratorContainer> LAVA_GENERATOR = register("lava_generator", new ContainerType<>(LavaGeneratorContainer::new));
	public static final ContainerType<CoffeeMachineContainer> COFFEE_MACHINE = register("coffee_machine", new ContainerType<>(CoffeeMachineContainer::new));

	// Other
	public static final ContainerType<ClosetContainer> CLOSET = register("closet", new ContainerType<>(ClosetContainer::new));
	
	private static <T extends ContainerType<? extends Container>> T register(String registryName, T type) {
		type.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, registryName));
		CONTAINER_TYPES.add(type);
		return type;
	}
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onContainerTypeRegister(final RegistryEvent.Register<ContainerType<?>> event) {
			CONTAINER_TYPES.forEach(type -> event.getRegistry().register(type));
			UselessMod.LOGGER.info("Registered container types");
		}
	}
	
}
