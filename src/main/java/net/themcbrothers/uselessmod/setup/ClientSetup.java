package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.client.CoffeeTintSource;
import net.themcbrothers.uselessmod.client.PaintTintSource;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.client.renderer.blockentity.CoffeeMachineRenderer;
import net.themcbrothers.uselessmod.client.renderer.blockentity.PaintBucketRenderer;
import net.themcbrothers.uselessmod.client.renderer.blockentity.UselessBedRenderer;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.client.renderer.entity.layers.UselessWingsLayer;
import net.themcbrothers.uselessmod.config.ClientConfig;
import net.themcbrothers.uselessmod.core.*;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.WallClosetBlockEntity;

@Mod(value = UselessMod.MOD_ID, dist = Dist.CLIENT)
public class ClientSetup {
    public ClientSetup(IEventBus bus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);

        bus.addListener(this::clientSetup);
        bus.addListener(this::menuScreens);
        bus.addListener(this::extensions);
        bus.addListener(this::blockColors);
        bus.addListener(this::itemColors);
        bus.addListener(this::entityRegisterRenders);
        bus.addListener(this::entityAddLayers);
        bus.addListener(this::entityCreateSkullModels);
        bus.addListener(this::modelRegistry);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Block Entity Renderer
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(UselessBlockEntityTypes.BED.get(), UselessBedRenderer::new);
            BlockEntityRenderers.register(UselessBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);
            BlockEntityRenderers.register(UselessBlockEntityTypes.SIGN.get(), SignRenderer::new);
            BlockEntityRenderers.register(UselessBlockEntityTypes.HANGING_SIGN.get(), HangingSignRenderer::new);
            BlockEntityRenderers.register(UselessBlockEntityTypes.COFFEE_MACHINE.get(), CoffeeMachineRenderer::new);
            BlockEntityRenderers.register(UselessBlockEntityTypes.PAINT_BUCKET.get(), PaintBucketRenderer::new);
        });

        // Wood Type
        event.enqueueWork(() -> Sheets.addWoodType(UselessWoodTypes.USELESS_OAK));
    }

    private void menuScreens(final RegisterMenuScreensEvent event) {
        event.register(UselessMenuTypes.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
    }

    private void extensions(final RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final Identifier PAINT_STILL = UselessMod.id("block/paint_still"),
                    PAINT_FLOW = UselessMod.id("block/paint_flow");

            @Override
            public Identifier getStillTexture() {
                return PAINT_STILL;
            }

            @Override
            public Identifier getFlowingTexture() {
                return PAINT_FLOW;
            }

            @Override
            public int getTintColor(FluidStack stack) {
                Integer color = stack.get(UselessDataComponents.COLOR.get());
                return color != null ? ARGB.color(0xFF, color) : -1;
            }
        }, UselessFluidTypes.PAINT.get());
    }

    private void blockColors(final RegisterColorHandlersEvent.Block event) {
        final BlockColors colors = event.getBlockColors();

        event.register(((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity canvas) {
                return ARGB.color(0xFF, canvas.getColor());
            }
            return -1;
        }), UselessBlocks.PAINTED_WOOL.get());

        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof CupBlockEntity cup) {
                return cup.getCoffeeType().map(CoffeeType::getColor).map(color -> ARGB.color(0xFF, color)).orElse(-1);
            }
            return -1;
        }, UselessBlocks.CUP_COFFEE.get());

        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
                final BlockState mimic = blockEntity.getMimic();
                if (mimic != null) {
                    return colors.getColor(mimic, level, pos, tintIndex);
                }
            }
            return -1;
        }, UselessBlocks.MACHINE_SUPPLIER.get());

        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null &&
                    level.getBlockEntity(pos) instanceof WallClosetBlockEntity blockEntity) {
                return colors.getColor(blockEntity.getMaterial().defaultBlockState(), level, pos, tintIndex);
            }
            return -1;
        }, UselessBlocks.WALL_CLOSET.get());
    }

    private void itemColors(final RegisterColorHandlersEvent.ItemTintSources event) {
        event.register(UselessMod.id("paint"), PaintTintSource.MAP_CODEC);
        event.register(UselessMod.id("coffee"), CoffeeTintSource.MAP_CODEC);
    }

    private void entityRegisterRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(UselessEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }

    private void entityAddLayers(final EntityRenderersEvent.AddLayers event) {
        if (event.getRenderer(EntityType.PLAYER) instanceof AvatarRenderer<?> renderer) {
            renderer.addLayer(new UselessWingsLayer<>(renderer, event.getEntityModels(), event.getContext().getEquipmentRenderer()));
        }
    }

    private void entityCreateSkullModels(final EntityRenderersEvent.CreateSkullModels event) {
        event.registerSkullModel(UselessSkullBlock.Types.USELESS_SKELETON, ModelLayers.SKELETON_SKULL, UselessMod.id("textures/entity/skeleton/useless_skeleton.png"));
    }

    private void modelRegistry(final ModelEvent.RegisterLoaders event) {
        // TODO: custom loaders
//        event.register(UselessMod.id("machine_supplier"), MachineSupplierModel.Loader.INSTANCE);
//        event.register(UselessMod.id("wall_closet"), WallClosetModel.Loader.INSTANCE);
    }
}
