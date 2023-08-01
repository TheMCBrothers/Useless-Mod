package net.themcbrothers.uselessmod.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
import net.themcbrothers.uselessmod.init.*;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import net.themcbrothers.uselessmod.world.level.block.UselessSkullBlock;
import net.themcbrothers.uselessmod.world.level.block.entity.CupBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.MachineSupplierBlockEntity;
import net.themcbrothers.uselessmod.world.level.block.entity.PaintedWoolBlockEntity;
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
        bus.addListener(UselessItemStackRendererProvider::initialize);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Block Entity Renderer
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(ModBlockEntityTypes.BED.get(), UselessBedRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.SKULL.get(), SkullBlockRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.SIGN.get(), SignRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.COFFEE_MACHINE.get(), CoffeeMachineRenderer::new);
            BlockEntityRenderers.register(ModBlockEntityTypes.PAINT_BUCKET.get(), PaintBucketRenderer::new);
        });

        // Screen
        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.COFFEE_MACHINE.get(), CoffeeMachineScreen::new));

        // Wood Type
        event.enqueueWork(() -> Sheets.addWoodType(UselessWoodTypes.USELESS_OAK));

        // Item Properties
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.USELESS_ELYTRA.get(), new ResourceLocation("broken"),
                    (stack, level, entity, seed) -> ElytraItem.isFlyEnabled(stack) ? 0.0F : 1.0F);
            ItemProperties.register(ModItems.SUPER_USELESS_ELYTRA.get(), new ResourceLocation("broken"),
                    (stack, level, entity, seed) -> ElytraItem.isFlyEnabled(stack) ? 0.0F : 1.0F);
            ItemProperties.register(ModItems.USELESS_SHIELD.get(), new ResourceLocation("blocking"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
            ItemProperties.register(ModItems.SUPER_USELESS_SHIELD.get(), new ResourceLocation("blocking"),
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
        });
    }

    private void blockColors(final RegisterColorHandlersEvent.Block event) {
        final BlockColors colors = event.getBlockColors();

        event.register(((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof PaintedWoolBlockEntity canvas) {
                return canvas.getColor();
            }
            return -1;
        }), ModBlocks.PAINTED_WOOL.get());

        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof CupBlockEntity cup) {
                return cup.getCoffeeType().map(CoffeeType::getColor).orElse(-1);
            }
            return -1;
        }, ModBlocks.CUP_COFFEE.get());

        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof MachineSupplierBlockEntity blockEntity) {
                final BlockState mimic = blockEntity.getMimic();
                if (mimic != null) {
                    return colors.getColor(mimic, level, pos, tintIndex);
                }
            }
            return -1;
        }, ModBlocks.MACHINE_SUPPLIER.get());
    }

    private void itemColors(final RegisterColorHandlersEvent.Item event) {
        final ItemColors colors = event.getItemColors();

        event.register(((stack, layer) -> {
            final CompoundTag tag = stack.getTag();
            return layer == 1 && tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC) ? tag.getInt("Color") : -1;
        }), ModItems.PAINT_BRUSH);

        event.register(((stack, layer) -> {
            final CompoundTag tag = BlockItem.getBlockEntityData(stack);
            return tag != null && tag.contains("Color", Tag.TAG_ANY_NUMERIC) ? tag.getInt("Color") : -1;
        }), ModBlocks.PAINTED_WOOL);

        event.register((stack, layer) ->
                CoffeeUtils.getCoffeeType(stack).map(CoffeeType::getColor).orElse(-1), ModBlocks.CUP_COFFEE);

        event.register((stack, layer) -> {
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

        if (event.getAtlas().location() == InventoryMenu.BLOCK_ATLAS) {
            event.addSprite(UselessMod.rl("entity/shield/useless"));
            event.addSprite(UselessMod.rl("entity/shield/super_useless"));
            event.addSprite(UselessMod.rl("item/empty_coffee_machine_slot_beans"));
            event.addSprite(UselessMod.rl("item/empty_coffee_machine_slot_bucket"));
            event.addSprite(UselessMod.rl("item/empty_coffee_machine_slot_cup"));
            event.addSprite(UselessMod.rl("item/empty_coffee_machine_slot_extra"));
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

    private void modelRegistry(final ModelEvent.RegisterGeometryLoaders event) {
        event.register("machine_supplier", MachineSupplierModel.Loader.INSTANCE);
        event.register("wall_closet", WallClosetModel.Loader.INSTANCE);
    }

    @Override
    public @Nullable Player getLocalPlayer() {
        return Minecraft.getInstance().player;
    }
}
