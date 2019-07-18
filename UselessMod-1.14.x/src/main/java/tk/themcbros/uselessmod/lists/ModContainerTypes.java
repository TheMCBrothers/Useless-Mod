package tk.themcbros.uselessmod.lists;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.container.ClosetContainer;
import tk.themcbros.uselessmod.container.CompressorContainer;
import tk.themcbros.uselessmod.container.CrusherContainer;
import tk.themcbros.uselessmod.container.ElectricCrusherContainer;
import tk.themcbros.uselessmod.container.ElectricFurnaceContainer;
import tk.themcbros.uselessmod.container.GlowstoneGeneratorContainer;

@ObjectHolder(UselessMod.MOD_ID)
public class ModContainerTypes {
	
	@ObjectHolder("crusher_gui")
	public static final ContainerType<CrusherContainer> CRUSHER = null;
	@ObjectHolder("electric_crusher_gui")
	public static final ContainerType<ElectricCrusherContainer> ELECTRIC_CRUSHER = null;
	@ObjectHolder("electric_furnace_gui")
	public static final ContainerType<ElectricFurnaceContainer> ELECTRIC_FURNACE = null;
	@ObjectHolder("compressor_gui")
	public static final ContainerType<CompressorContainer> COMPRESSOR = null;
	@ObjectHolder("glowstone_generator_gui")
	public static final ContainerType<GlowstoneGeneratorContainer> GLOWSTONE_GENRATOR = null;
	@ObjectHolder("closet")
	public static final ContainerType<ClosetContainer> CLOSET = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onContainerTypeRegister(final RegistryEvent.Register<ContainerType<?>> event) {
			IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
			
			registry.register(new ContainerType<CrusherContainer>(CrusherContainer::new).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "crusher_gui")));
			registry.register(new ContainerType<ElectricCrusherContainer>(ElectricCrusherContainer::new).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "electric_crusher_gui")));
			registry.register(new ContainerType<ElectricFurnaceContainer>(ElectricFurnaceContainer::new).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "electric_furnace_gui")));
			registry.register(new ContainerType<CompressorContainer>(CompressorContainer::new).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "compressor_gui")));
			registry.register(new ContainerType<GlowstoneGeneratorContainer>(GlowstoneGeneratorContainer::new).setRegistryName(new ResourceLocation(UselessMod.MOD_ID, "glowstone_generator_gui")));
			registry.register(new ContainerType<ClosetContainer>(ClosetContainer::new).setRegistryName(BlockNames.CLOSET));
			
			UselessMod.LOGGER.info("Registred container types");
		}
	}
	
}
