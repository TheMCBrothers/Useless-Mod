package tk.themcbros.uselessmod.proxy;

import net.minecraft.block.ShearableDoublePlantBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.blocks.LampBlock;
import tk.themcbros.uselessmod.client.gui.*;
import tk.themcbros.uselessmod.client.renders.entity.UselessRenderRegistry;
import tk.themcbros.uselessmod.config.Config;
import tk.themcbros.uselessmod.lists.*;
import tk.themcbros.uselessmod.tileentity.ColorableTileEntity;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

	public ClientProxy() {
		super();
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerBlockColours);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerItemColors);

		Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("uselessmod-client.toml").toString());
	}
	
	private void clientSetup(FMLClientSetupEvent event) {
		UselessMod.LOGGER.debug("ClientProxy clientSetup method");
		
		ScreenManager.registerFactory(ModContainerTypes.CRUSHER, CrusherScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_CRUSHER, ElectricCrusherScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.ELECTRIC_FURNACE, ElectricFurnaceScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.COMPRESSOR, CompressorScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.MAGMA_CRUCIBLE, MagmaCrucibleScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.CHARGER, ChargerScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.GLOWSTONE_GENRATOR, GlowstoneGeneratorScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.LAVA_GENERATOR, LavaGeneratorScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.COFFEE_MACHINE, CoffeeMachineScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.CLOSET, ClosetScreen::new);
		
		UselessRenderRegistry.registerEntityRenders();
		
//		if (ModList.get().isLoaded("filters")) {
//			com.mrcrayfish.filters.Filters filters = com.mrcrayfish.filters.Filters.get();
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/natural"), new ItemStack(ModBlocks.USELESS_FERN));
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/wood"), new ItemStack(ModBlocks.USELESS_LOG));
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/colored"), new ItemStack(ModBlocks.PAINT_BUCKET));
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/materials"), new ItemStack(ModItems.SUPER_USELESS_INGOT));
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/energy"), new ItemStack(ModBlocks.ENERGY_CABLE));
//			filters.register(ModItemGroups.USELESS_ITEM_GROUP, UselessMod.getId("filters/tools_armor"), new ItemStack(ModItems.USELESS_PICKAXE));
//
//			filters.register(ModItemGroups.CLOSET_GROUP, UselessMod.getId("filters/closets"), new ItemStack(ModBlocks.CLOSET));
//		}

	}
	
	private void registerBlockColours(@Nonnull ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();
        
        blockColors.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.func_228363_c_(world, pos) : -1, ModBlocks.CHEESE_MAKER);
        
        blockColors.register((state, world, pos, tintIndex) -> {
        	if(world != null && pos != null) {
	        	TileEntity tileEntity = world.getTileEntity(pos);
	        	if(tileEntity instanceof ColorableTileEntity) {
	        		return ((ColorableTileEntity) tileEntity).getColor();
	        	}
	        }
            return 4159204;
         }, ModBlocks.CANVAS, ModBlocks.PAINT_BUCKET);
        
        blockColors.register((state, world, pos, tintIndex) -> {
        	DyeColor color = state.get(LampBlock.COLOR);
        	float[] colourComponents = color.getColorComponentValues();
            int colour = (int) (colourComponents[0] * 255F);
            colour = (int) ((colour << 8) + colourComponents[1] * 255F);
            colour = (int) ((colour << 8) + colourComponents[2] * 255F);
            return colour;
        }, ModBlocks.LAMP);
        
		blockColors.register((state, world, pos, tint_index) -> world != null && pos != null ? BiomeColors.func_228358_a_(world, pos) : GrassColors.get(0.5D, 1.0D), ModBlocks.USELESS_GRASS_BLOCK, ModBlocks.USELESS_FERN, ModBlocks.USELESS_GRASS,
				ModBlocks.POTTED_USELESS_FERN);

		blockColors.register((p_210234_0_, p_210234_1_, p_210234_2_, p_210234_3_) -> p_210234_1_ != null && p_210234_2_ != null ? BiomeColors.func_228358_a_(p_210234_1_,
				p_210234_0_.get(ShearableDoublePlantBlock.HALF) == DoubleBlockHalf.UPPER
						? p_210234_2_.down()
						: p_210234_2_)
				: -1, ModBlocks.LARGE_USELESS_FERN, ModBlocks.TALL_USELESS_GRASS);
        
    }

	private void registerItemColors(@Nonnull ColorHandlerEvent.Item event) {
		ItemColors itemColors = event.getItemColors();

		// Colored
		itemColors.register((stack, tintIndex) -> stack.hasTag() && stack.getTag().contains("color", Constants.NBT.TAG_INT) && tintIndex == 1 ? stack.getTag().getInt("color") : -1, ModItems.PAINT_BRUSH);
		itemColors.register((stack, tintIndex) -> stack.hasTag() && stack.getTag().contains("color", Constants.NBT.TAG_INT) ? stack.getTag().getInt("color") : -1, ModBlocks.CANVAS, ModBlocks.PAINT_BUCKET);

		// Coffee
		itemColors.register((stack, tintIndex) -> {
			if(stack.hasTag() && stack.getTag().contains("CoffeeType", Constants.NBT.TAG_STRING)) {
				for(CoffeeType type : CoffeeType.values()) {
					if(stack.getTag().getString("CoffeeType").equals(type.getName())) {
						return type.getColor();
					}
				}
			}
			return -1;
		}, ModItems.COFFEE_CUP);

		// Useless Plants
		itemColors.register((stack, tintIndex) -> ModBiomes.USELESS_BIOME.func_225529_c_(), ModBlocks.USELESS_GRASS_BLOCK, ModBlocks.USELESS_GRASS, ModBlocks.USELESS_FERN, ModBlocks.TALL_USELESS_GRASS, ModBlocks.LARGE_USELESS_FERN);

		// Fluid Canister
		itemColors.register((stack, layer) -> layer == 1 ? ModItems.CANISTER.getFluid(stack).getFluid().getAttributes().getColor() : -1, ModItems.CANISTER);
	}
	
}
