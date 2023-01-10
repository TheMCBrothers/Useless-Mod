package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.client.model.WallClosetModel;
import net.themcbrothers.uselessmod.client.renderer.blockentity.UselessBedRenderer;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.config.ClientConfig;
import net.themcbrothers.uselessmod.init.*;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CanvasBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;

public class ClientSetup extends CommonSetup {
    public ClientSetup() {
        super();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::blockColors);
        bus.addListener(this::itemColors);
        bus.addListener(this::entityRenders);
        bus.addListener(this::skullModels);
        bus.addListener(this::modelRegistry);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClientConfig.SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // client setup
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_LEAVES.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_BARS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SUPER_USELESS_BARS.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_RED_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_BLUE_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_USELESS_ROSE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_USELESS_OAK_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_WHEAT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_USELESS_WHEAT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.COFFEE_BEANS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_COFFEE_BEANS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_WOOD.getDoor(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_WOOD.getTrapdoor(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.COFFEE_MACHINE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_CLOSET.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_POWERED_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_DETECTOR_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_ACTIVATOR_RAIL.get(), RenderType.cutout());

            BlockEntityRenderers.register(ModBlockEntityTypes.BED.get(), UselessBedRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);

            MenuScreens.register(ModMenuTypes.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
        });
    }

    private void blockColors(final ColorHandlerEvent.Block event) {
        final BlockColors colors = event.getBlockColors();

        colors.register(((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof CanvasBlockEntity canvas) {
                return canvas.getColor();
            }
            return -1;
        }), ModBlocks.CANVAS.get());

        colors.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof CupBlockEntity cup
                    && cup.getCoffeeType() != null) {
                return cup.getCoffeeType().getColor();
            }
            return -1;
        }, ModBlocks.CUP_COFFEE.get());
    }

    private void itemColors(final ColorHandlerEvent.Item event) {
        final ItemColors colors = event.getItemColors();

        colors.register(((stack, layer) -> {
            final CompoundTag tag = stack.getTag();
            return layer == 1 && tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC) ? tag.getInt("Color") : -1;
        }), ModItems.PAINT_BRUSH);

        colors.register(((stack, layer) -> {
            final CompoundTag tag = BlockItem.getBlockEntityData(stack);
            return tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC) ? tag.getInt("Color") : -1;
        }), ModBlocks.CANVAS);

        colors.register((stack, layer) -> {
            final CoffeeType type = CoffeeUtils.getCoffeeType(stack);
            return type != null ? type.getColor() : -1;
        }, ModBlocks.CUP_COFFEE);
    }

    private void entityRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }

    private void skullModels(final EntityRenderersEvent.CreateSkullModels event) {
        SkullBlockRenderer.SKIN_BY_TYPE.put(UselessSkullBlock.Types.USELESS_SKELETON, UselessMod.rl("textures/entity/useless_skeleton.png"));
        event.registerSkullModel(UselessSkullBlock.Types.USELESS_SKELETON, new SkullModel(event.getEntityModelSet().bakeLayer(ModelLayers.SKELETON_SKULL)));
    }

    private void modelRegistry(final ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(UselessMod.rl("wall_closet"), WallClosetModel.Loader.INSTANCE);
    }
}
