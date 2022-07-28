package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.themcbrothers.uselessmod.client.renderer.entity.*;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModEntityTypes;

public class ClientSetup extends CommonSetup {
    public ClientSetup() {
        super();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
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

    private void entityRenders(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.USELESS_SHEEP.get(), UselessSheepRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_PIG.get(), UselessPigRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_CHICKEN.get(), UselessChickenRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_COW.get(), UselessCowRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.USELESS_SKELETON.get(), UselessSkeletonRenderer::new);
    }
}
