package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
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
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import net.neoforged.neoforge.fluids.FluidStack;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.client.model.MachineSupplierModel;
import net.themcbrothers.uselessmod.client.model.WallClosetModel;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;
import net.themcbrothers.uselessmod.client.renderer.blockentity.CoffeeMachineRenderer;
import net.themcbrothers.uselessmod.client.renderer.blockentity.PaintBucketRenderer;
import net.themcbrothers.uselessmod.client.renderer.blockentity.UselessBedRenderer;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.client.renderer.entity.layers.UselessElytraLayer;
import net.themcbrothers.uselessmod.config.ClientConfig;
import net.themcbrothers.uselessmod.core.*;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
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
        bus.addListener(UselessItemStackRendererProvider::initialize);
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

        // Item Properties
        event.enqueueWork(() -> {
            ItemProperties.register(UselessItems.USELESS_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
            ItemProperties.register(UselessItems.SUPER_USELESS_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
        });
    }

    private void menuScreens(final RegisterMenuScreensEvent event) {
        event.register(UselessMenuTypes.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);
    }

    private void extensions(final RegisterClientExtensionsEvent event) {
        event.registerItem(UselessItemStackRendererProvider.blockEntity(), UselessBlocks.COFFEE_MACHINE.asItem(), UselessBlocks.USELESS_BED.asItem());
        event.registerItem(UselessItemStackRendererProvider.shield(), UselessItems.USELESS_SHIELD.asItem(), UselessItems.SUPER_USELESS_SHIELD.asItem());

        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation PAINT_STILL = UselessMod.rl("block/paint_still"),
                    PAINT_FLOW = UselessMod.rl("block/paint_flow");

            @Override
            public ResourceLocation getStillTexture() {
                return PAINT_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
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

    private void itemColors(final RegisterColorHandlersEvent.Item event) {
        final ItemColors colors = event.getItemColors();

        event.register(((stack, layer) -> {
            Integer color = stack.get(UselessDataComponents.COLOR.get());
            return layer == 1 && color != null ? ARGB.color(0xFF, color) : -1;
        }), UselessItems.PAINT_BRUSH);

        event.register(((stack, layer) -> {
            Integer color = stack.get(UselessDataComponents.COLOR.get());
            return color != null ? ARGB.color(0xFF, color) : -1;
        }), UselessBlocks.PAINTED_WOOL);

        event.register((stack, layer) -> CoffeeUtils.getCoffeeType(stack)
                        .map(CoffeeType::getColor)
                        .map(color -> ARGB.color(0xFF, color))
                        .orElse(-1),
                UselessBlocks.CUP_COFFEE);

        event.register((stack, layer) -> {
            BlockState mimic = stack.get(UselessDataComponents.MIMIC.get());
            return mimic != null ? colors.getColor(new ItemStack(mimic.getBlock()), layer) : -1;
        }, UselessBlocks.MACHINE_SUPPLIER);

        event.register((stack, layer) -> {
            Holder<Block> block = stack.get(UselessDataComponents.WALL_CLOSET_MATERIAL.get());
            return block != null ? colors.getColor(new ItemStack(block.value()), layer) : -1;
        });

        event.register(new DynamicFluidContainerModel.Colors(), UselessItems.BUCKET_PAINT);
    }

    private void entityRegisterRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(UselessEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(UselessEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }

    private void entityAddLayers(final EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            if (event.getSkin(skin) instanceof PlayerRenderer renderer) {
                renderer.addLayer(new UselessElytraLayer<>(renderer, event.getEntityModels(), event.getContext().getEquipmentRenderer()));
            }
        }
    }

    private void entityCreateSkullModels(final EntityRenderersEvent.CreateSkullModels event) {
        SkullBlockRenderer.SKIN_BY_TYPE.put(UselessSkullBlock.Types.USELESS_SKELETON, UselessMod.rl("textures/entity/useless_skeleton.png"));
        event.registerSkullModel(UselessSkullBlock.Types.USELESS_SKELETON, new SkullModel(event.getEntityModelSet().bakeLayer(ModelLayers.SKELETON_SKULL)));
    }

    private void modelRegistry(final ModelEvent.RegisterGeometryLoaders event) {
        event.register(UselessMod.rl("machine_supplier"), MachineSupplierModel.Loader.INSTANCE);
        event.register(UselessMod.rl("wall_closet"), WallClosetModel.Loader.INSTANCE);
    }
}
