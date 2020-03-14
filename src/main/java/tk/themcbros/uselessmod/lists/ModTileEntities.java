package tk.themcbros.uselessmod.lists;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.tileentity.*;

import java.util.List;
import java.util.function.Supplier;

@ObjectHolder(UselessMod.MOD_ID)
public class ModTileEntities {
	
	private static final List<TileEntityType<?>> TYPES = Lists.newArrayList();

	// Machines
	public static final TileEntityType<CrusherTileEntity> CRUSHER = register("crusher", CrusherTileEntity::new, ModBlocks.CRUSHER);
	public static final TileEntityType<ElectricCrusherTileEntity> ELECTRIC_CRUSHER = register("electric_crusher", ElectricCrusherTileEntity::new, ModBlocks.ELECTRIC_CRUSHER);
	public static final TileEntityType<ElectricFurnaceTileEntity> ELECTRIC_FURNACE = register("electric_furnace", ElectricFurnaceTileEntity::new, ModBlocks.ELECTRIC_FURNACE);
	public static final TileEntityType<CompressorTileEntity> COMPRESSOR = register("compressor", CompressorTileEntity::new, ModBlocks.COMPRESSOR);
	public static final TileEntityType<MagmaCrucibleTileEntity> MAGMA_CRUCIBLE = register("magma_crucible", MagmaCrucibleTileEntity::new, ModBlocks.MAGMA_CRUCIBLE);
	public static final TileEntityType<ChargerTileEntity> CHARGER = register("charger", ChargerTileEntity::new, ModBlocks.CHARGER);
	public static final TileEntityType<GlowstoneGeneratorTileEntity> GLOWSTONE_GENERATOR = register("glowstone_generator", GlowstoneGeneratorTileEntity::new, ModBlocks.GLOWSTONE_GENERATOR);
	public static final TileEntityType<LavaGeneratorTileEntity> LAVA_GENERATOR = register("lava_generator", LavaGeneratorTileEntity::new, ModBlocks.LAVA_GENERATOR);
	public static final TileEntityType<CoffeeMachineTileEntity> COFFEE_MACHINE = register("coffee_machine", CoffeeMachineTileEntity::new, ModBlocks.COFFEE_MACHINE);

	// Other
	public static final TileEntityType<CreativePowerBlockTileEntity> CREATIVE_POWER_BLOCK = register("creative_power_block", CreativePowerBlockTileEntity::new, ModBlocks.CREATIVE_POWER_BLOCK);
	public static final TileEntityType<PowerControlTileEntity> POWER_CONTROL_BLOCK = register("power_control_block", PowerControlTileEntity::new, ModBlocks.POWER_CONTROL_BLOCK);
	public static final TileEntityType<EnergyCableTileEntity> ENERGY_CABLE = register("energy_cable", EnergyCableTileEntity::new, ModBlocks.ENERGY_CABLE);
	public static final TileEntityType<FluidPipeTileEntity> FLUID_PIPE = register("fluid_pipe", FluidPipeTileEntity::new, ModBlocks.FLUID_PIPE);
	public static final TileEntityType<FluidTankTileEntity> FLUID_TANK = register("fluid_tank", FluidTankTileEntity::new, ModBlocks.FLUID_TANK);
	public static final TileEntityType<ClosetTileEntity> CLOSET = register("closet", ClosetTileEntity::new, ModBlocks.CLOSET);
	public static final TileEntityType<ColorableTileEntity> COLORABLE = register("colorable", ColorableTileEntity::new, ModBlocks.CANVAS, ModBlocks.PAINT_BUCKET);
	public static final TileEntityType<LightSwitchTileEntity> LIGHT_SWITCH = register("light_switch", LightSwitchTileEntity::new, ModBlocks.LIGHT_SWITCH, ModBlocks.LIGHT_SWITCH_BLOCK);
	public static final TileEntityType<UselessSignTileEntity> USELESS_SIGN = register("useless_sign", UselessSignTileEntity::new, ModBlocks.USELESS_WALL_SIGN, ModBlocks.USELESS_SIGN);

	private static <T extends TileEntity> TileEntityType<T> register(String regName, Supplier<T> supplier, Block... blocks) {
		TileEntityType<T> type = TileEntityType.Builder.create(supplier, blocks).build(null);
		type.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, regName));
		TYPES.add(type);
		return type;
	}
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onTileEntityRegister(final RegistryEvent.Register<TileEntityType<?>> event) {
			TYPES.forEach(type -> event.getRegistry().register(type));
			UselessMod.LOGGER.info("Registered TileEntitiyTypes");
		}
	}
	
}
