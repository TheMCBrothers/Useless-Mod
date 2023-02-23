package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.client.gui.screens.inventory.CoffeeMachineScreen;
import net.themcbrothers.uselessmod.client.model.MachineSupplierModel;
import net.themcbrothers.uselessmod.client.model.WallClosetModel;
import net.themcbrothers.uselessmod.client.renderer.blockentity.UselessBedRenderer;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.client.renderer.entity.layers.UselessElytraLayer;
import net.themcbrothers.uselessmod.config.ClientConfig;
import net.themcbrothers.uselessmod.init.*;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ClientSetup extends CommonSetup {
    public ClientSetup() {
        super();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::blockColors);
        bus.addListener(this::itemColors);
        bus.addListener(this::textureStitching);
        bus.addListener(this::entityRegisterRenders);
        bus.addListener(this::entityAddLayers);
        bus.addListener(this::entityCreateSkullModels);
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
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_TRAPDOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MACHINE_SUPPLIER.get(), renderType -> true);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.COFFEE_MACHINE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_CLOSET.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_POWERED_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_DETECTOR_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_ACTIVATOR_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LANTERN.get(), RenderType.cutout());

            BlockEntityRenderers.register(ModBlockEntityTypes.BED.get(), UselessBedRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.SIGN.get(), SignRenderer::new);

            MenuScreens.register(ModMenuTypes.COFFEE_MACHINE.get(), CoffeeMachineScreen::new);

            Sheets.addWoodType(UselessWoodTypes.USELESS_OAK);
        });
    }

    private void blockColors(final ColorHandlerEvent.Block event) {
        final BlockColors colors = event.getBlockColors();

        colors.register(((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity canvas) {
                return canvas.getColor();
            }
            return -1;
        }), ModBlocks.PAINTED_WOOL.get());

        colors.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof CupBlockEntity cup
                    && cup.getCoffeeType() != null) {
                return cup.getCoffeeType().getColor();
            }
            return -1;
        }, ModBlocks.CUP_COFFEE.get());

        colors.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
                final BlockState mimic = blockEntity.getMimic();
                if (mimic != null) {
                    return colors.getColor(mimic, level, pos, tintIndex);
                }
            }
            return -1;
        }, ModBlocks.MACHINE_SUPPLIER.get());
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
        }), ModBlocks.PAINTED_WOOL);

        colors.register((stack, layer) -> {
            final CoffeeType type = CoffeeUtils.getCoffeeType(stack);
            return type != null ? type.getColor() : -1;
        }, ModBlocks.CUP_COFFEE);

        colors.register((stack, layer) -> {
            final CompoundTag tag = BlockItem.getBlockEntityData(stack);
            if (tag != null && tag.contains("Mimic", Tag.TAG_COMPOUND)) {
                final BlockState mimic = NbtUtils.readBlockState(tag.getCompound("Mimic"));
                return colors.getColor(new ItemStack(mimic.getBlock().asItem()), layer);
            }
            return -1;
        }, ModBlocks.MACHINE_SUPPLIER);
    }

    private void textureStitching(final TextureStitchEvent.Pre event) {
        if (event.getAtlas().location() == Sheets.BED_SHEET) {
            event.addSprite(UselessMod.rl("entity/bed/useless"));
        }
    }

    private void entityRegisterRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }

    private void entityAddLayers(final EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            LivingEntityRenderer<Player, EntityModel<Player>> renderer = event.getSkin(skin);

            if (renderer != null) {
                renderer.addLayer(new UselessElytraLayer<>(renderer, event.getEntityModels()));
            }
        }
    }

    private void entityCreateSkullModels(final EntityRenderersEvent.CreateSkullModels event) {
        SkullBlockRenderer.SKIN_BY_TYPE.put(UselessSkullBlock.Types.USELESS_SKELETON, UselessMod.rl("textures/entity/useless_skeleton.png"));
        event.registerSkullModel(UselessSkullBlock.Types.USELESS_SKELETON, new SkullModel(event.getEntityModelSet().bakeLayer(ModelLayers.SKELETON_SKULL)));
    }

    private void modelRegistry(final ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(UselessMod.rl("machine_supplier"), MachineSupplierModel.Loader.INSTANCE);
        ModelLoaderRegistry.registerLoader(UselessMod.rl("wall_closet"), WallClosetModel.Loader.INSTANCE);
    }

    @Override
    public @Nullable Player getLocalPlayer() {
        return Minecraft.getInstance().player;
    }
}
