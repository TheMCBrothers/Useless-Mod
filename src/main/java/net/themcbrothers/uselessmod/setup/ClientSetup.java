package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.init.ModItems;
import net.themcbrothers.uselessmod.world.level.block.entity.CanvasBlockEntity;

public class ClientSetup extends CommonSetup {
    public ClientSetup() {
        super();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.addListener(this::blockColors);
        bus.addListener(this::itemColors);
        bus.addListener(this::entityRenders);
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
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_OAK_WOOD.getDoor(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_POWERED_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_DETECTOR_RAIL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.USELESS_ACTIVATOR_RAIL.get(), RenderType.cutout());
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
        }), ModBlocks.CANVAS.get());
    }

    private void entityRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }
}
