package tk.themcbros.uselessmod.lists;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;

@ObjectHolder(UselessMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class ModFluids {

	private static final List<Fluid> FLUIDS = Lists.newArrayList();
	
	private static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(() -> ModFluids.USELESS_WATER, () -> ModFluids.FLOWING_USELESS_WATER, 
			FluidAttributes.builder( new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).overlay(new ResourceLocation("block/water_overlay"))
                    .translationKey("block.uselessmod.useless_water")
                    .color(0xFF468b44).density(1000))
			.block(() -> ModBlocks.USELESS_WATER).bucket(() -> ModItems.USELESS_WATER_BUCKET).canMultiply().renderLayer(BlockRenderLayer.TRANSLUCENT);
	public static final FlowingFluid USELESS_WATER = registerFluid("useless_water", new ForgeFlowingFluid.Source(PROPERTIES));
	public static final FlowingFluid FLOWING_USELESS_WATER = registerFluid("flowing_useless_water", new ForgeFlowingFluid.Flowing(PROPERTIES));
	
	private static <T extends Fluid> T registerFluid(String registryName, T fluid) {
		fluid.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, registryName));
		FLUIDS.add(fluid);
		return fluid;
	}

	@SubscribeEvent
	public static void onFluidRegister(final RegistryEvent.Register<Fluid> event) {
		FLUIDS.forEach(fluid -> event.getRegistry().register(fluid));
		UselessMod.LOGGER.debug("Registered fluids");
	}
	
}
