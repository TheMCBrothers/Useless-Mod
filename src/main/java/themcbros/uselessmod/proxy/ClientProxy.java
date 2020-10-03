package themcbros.uselessmod.proxy;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.color.IColorHandler;
import themcbros.uselessmod.block.LampBlock;
import themcbros.uselessmod.client.models.block.supplier.MachineSupplierModelLoader;
import themcbros.uselessmod.client.renderer.entity.*;
import themcbros.uselessmod.client.renderer.entity.layer.UselessElytraLayer;
import themcbros.uselessmod.client.renderer.tilentity.*;
import themcbros.uselessmod.client.screen.CoffeeMachineScreen;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.init.*;
import themcbros.uselessmod.item.CoffeeCupItem;
import themcbros.uselessmod.tileentity.CoffeeCupTileEntity;
import themcbros.uselessmod.tileentity.MachineSupplierTileEntity;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modelRegister);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::itemColors);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::blockColors);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Client Setup

        // Elytra Layer
        Minecraft.getInstance().getRenderManager().getSkinMap().values()
                .forEach(player -> player.addLayer(new UselessElytraLayer<>(player)));

        // Render Types
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_ORE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_ORE_NETHER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_ORE_END.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SUPER_USELESS_ORE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SUPER_USELESS_ORE_NETHER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SUPER_USELESS_ORE_END.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_USELESS_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.WOODEN_USELESS_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.WOODEN_USELESS_TRAPDOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_FIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_WHEAT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.COFFEE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.WILD_COFFEE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ENDER_SEEDS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.RED_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.BLUE_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_RED_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_BLUE_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_USELESS_ROSE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.WALL_CLOSET.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.COFFEE_MACHINE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_DETECTOR_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_ACTIVATOR_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POWERED_USELESS_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.USELESS_CROSSOVER_RAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.COFFEE_MACHINE_SUPPLIER.get(), renderType -> true);

        // Screens
        ScreenManager.registerFactory(ContainerInit.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);

        // Tile Entity Renderer
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.USELESS_BED.get(), UselessBedTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.USELESS_SIGN.get(), UselessSignTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.WALL_CLOSET.get(), WallClosetTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.USELESS_CHEST.get(), UselessChestTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.COFFEE_MACHINE.get(), CoffeeMachineTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.PAINT_BUCKET.get(), PaintBucketTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityInit.USELESS_SKULL.get(), UselessSkullTileEntityRenderer::new);

        // Entity Renderer
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_PIG.get(), UselessPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_COW.get(), UselessCowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.USELESS_BOAT.get(), UselessBoatRenderer::new);

        // Color Module Setup
        ColorModule.clientSetup();
    }

    private void modelRegister(final ModelRegistryEvent event) {
        // Model Loaders
        ModelLoaderRegistry.registerLoader(UselessMod.rl("machine_supplier"), new MachineSupplierModelLoader());
    }

    private void itemColors(final ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        // Coffee Cup
        itemColors.register((itemStack, tintIndex) -> {
            if (itemStack.getItem() instanceof CoffeeCupItem) {
                CoffeeType type = ((CoffeeCupItem) itemStack.getItem()).getCoffeeType(itemStack);
                return type != null ? type.getColor() : -1;
            }
            return -1;
        }, ItemInit.COFFEE_CUP.get());

        ColorModule.itemColors(event);

        // Canvas
        itemColors.register((itemStack, tintIndex) -> {
            CompoundNBT tag = itemStack.getChildTag("BlockEntityTag");
            if (tag != null && tag.contains("Color", Constants.NBT.TAG_ANY_NUMERIC))
                return tag.getInt("Color");
            return -1;
        }, BlockInit.CANVAS.get());

        // Lamp
        for (DyeColor color : DyeColor.values()) {
            itemColors.register((itemStack, tintIndex) -> color.getColorValue(), LampBlock.LAMP_MAP.get(color));
        }
    }

    private void blockColors(final ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        // Coffee Cup
        blockColors.register((blockState, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof CoffeeCupTileEntity) {
                    CoffeeType type = ((CoffeeCupTileEntity) tileEntity).getCoffeeType();
                    return type != null ? type.getColor() : -1;
                }
            }
            return -1;
        }, BlockInit.COFFEE_CUP.get());

        // Canvas
        blockColors.register((blockState, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity te = world.getTileEntity(pos);
                if (te != null)
                    return te.getCapability(CapabilityColor.COLOR).map(IColorHandler::getColor).orElse(-1);
            }
            return -1;
        }, BlockInit.CANVAS.get());

        // Lamp
        for (DyeColor color : DyeColor.values()) {
            blockColors.register((blockState, world, pos, tintIndex) -> color.getColorValue(), LampBlock.LAMP_MAP.get(color));
        }

        // Machine Supplier
        blockColors.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                TileEntity tileEntity = world.getTileEntity(pos);
                if (tileEntity instanceof MachineSupplierTileEntity) {
                    BlockState mimic = ((MachineSupplierTileEntity) tileEntity).getMimic();
                    if (mimic != null) {
                        return blockColors.getColor(mimic, world, pos, tintIndex);
                    }
                }
            }
            return -1;
        }, BlockInit.COFFEE_MACHINE_SUPPLIER.get());
    }

}
