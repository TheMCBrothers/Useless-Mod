package tk.themcbros.uselessmod.lists;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.tileentity.*;

@ObjectHolder(UselessMod.MOD_ID)
public class ModTileEntities {

	@ObjectHolder("crusher")
	public static final TileEntityType<CrusherTileEntity> CRUSHER = null;
	@ObjectHolder("electric_crusher")
	public static final TileEntityType<CrusherTileEntity> ELECTRIC_CRUSHER = null;
	@ObjectHolder("electric_furnace")
	public static final TileEntityType<CrusherTileEntity> ELECTRIC_FURNACE = null;
	@ObjectHolder("compressor")
	public static final TileEntityType<CompressorTileEntity> COMPRESSOR = null;
	@ObjectHolder("glowstone_generator")
	public static final TileEntityType<GlowstoneGeneratorTileEntity> GLOWSTONE_GENERATOR = null;
	@ObjectHolder("creative_power_block")
	public static final TileEntityType<CreativePowerBlockTileEntity> CREATIVE_POWER_BLOCK = null;
	@ObjectHolder("closet")
	public static final TileEntityType<ClosetTileEntity> CLOSET = null;
	@ObjectHolder("canvas")
	public static final TileEntityType<ClosetTileEntity> CANVAS = null;
	@ObjectHolder("paint_bucket")
	public static final TileEntityType<PaintBucketTileEntity> PAINT_BUCKET = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onTileEntityRegister(final RegistryEvent.Register<TileEntityType<?>> event) {
			IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
			
			registry.register(TileEntityType.Builder.create(CrusherTileEntity::new, ModBlocks.CRUSHER).build(null).setRegistryName(BlockNames.CRUSHER));
			registry.register(TileEntityType.Builder.create(ElectricCrusherTileEntity::new, ModBlocks.ELECTRIC_CRUSHER).build(null).setRegistryName(BlockNames.ELECTRIC_CRUSHER));
			registry.register(TileEntityType.Builder.create(ElectricFurnaceTileEntity::new, ModBlocks.ELECTRIC_FURNACE).build(null).setRegistryName(BlockNames.ELECTRIC_FURNACE));
			registry.register(TileEntityType.Builder.create(CompressorTileEntity::new, ModBlocks.COMPRESSOR).build(null).setRegistryName(BlockNames.COMPRESSOR));
			registry.register(TileEntityType.Builder.create(GlowstoneGeneratorTileEntity::new, ModBlocks.GLOWSTONE_GENERATOR).build(null).setRegistryName(BlockNames.GLOWSTONE_GENERATOR));
			registry.register(TileEntityType.Builder.create(CreativePowerBlockTileEntity::new, ModBlocks.CREATIVE_POWER_BLOCK).build(null).setRegistryName(BlockNames.CREATIVE_POWER_BLOCK));
			registry.register(TileEntityType.Builder.create(ClosetTileEntity::new, ModBlocks.CLOSET).build(null).setRegistryName(BlockNames.CLOSET));
			registry.register(TileEntityType.Builder.create(CanvasTileEntity::new, ModBlocks.CANVAS).build(null).setRegistryName(BlockNames.CANVAS));
			registry.register(TileEntityType.Builder.create(PaintBucketTileEntity::new, ModBlocks.PAINT_BUCKET).build(null).setRegistryName(BlockNames.PAINT_BUCKET));
			
			UselessMod.LOGGER.info("Registered TileEntities");
		}
	}
	
}
