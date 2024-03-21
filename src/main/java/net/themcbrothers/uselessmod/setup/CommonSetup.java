package net.themcbrothers.uselessmod.setup;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.BiomeManager;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.themcbrothers.lib.util.Version;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.LampRegistry;
import net.themcbrothers.uselessmod.compat.VanillaCompatibility;
import net.themcbrothers.uselessmod.config.ClientConfig;
import net.themcbrothers.uselessmod.config.ServerConfig;
import net.themcbrothers.uselessmod.core.*;
import net.themcbrothers.uselessmod.network.UselessPacketHandler;
import net.themcbrothers.uselessmod.util.RecipeHelper;
import net.themcbrothers.uselessmod.util.WallClosetRecipeManager;
import net.themcbrothers.uselessmod.world.item.BucketWithPaintItem;
import net.themcbrothers.uselessmod.world.level.biome.UselessBiomes;
import org.jetbrains.annotations.Nullable;

public class CommonSetup {
    public CommonSetup(IEventBus bus, ModContainer modContainer) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);

        Registration.register(bus);
        bus.addListener(this::setup);
        bus.addListener(this::entityAttributes);
        bus.addListener(this::registerCapabilities);

        NeoForge.EVENT_BUS.register(new RecipeHelper());
        NeoForge.EVENT_BUS.register(new WallClosetRecipeManager());

        // Networking
        new UselessPacketHandler(bus, UselessMod.MOD_ID, new Version(modContainer));
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(UselessBlocks.RED_ROSE.getId(), UselessBlocks.POTTED_RED_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(UselessBlocks.BLUE_ROSE.getId(), UselessBlocks.POTTED_BLUE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(UselessBlocks.USELESS_ROSE.getId(), UselessBlocks.POTTED_USELESS_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(UselessBlocks.USELESS_OAK_SAPLING.getId(), UselessBlocks.POTTED_USELESS_OAK_SAPLING);
        });

        event.enqueueWork(() -> {
            LampRegistry.registerLampState(Blocks.REDSTONE_LAMP);
            LampRegistry.registerLampState(UselessBlocks.WHITE_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.ORANGE_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.MAGENTA_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.LIGHT_BLUE_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.YELLOW_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.LIME_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.PINK_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.GRAY_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.LIGHT_GRAY_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.CYAN_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.PURPLE_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.BLUE_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.BROWN_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.GREEN_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.RED_LAMP.get());
            LampRegistry.registerLampState(UselessBlocks.BLACK_LAMP.get());
            LampRegistry.registerLampState(Blocks.LANTERN, state -> state,
                    state -> UselessBlocks.LANTERN.get().defaultBlockState()
                            .setValue(BlockStateProperties.HANGING, state.getValue(BlockStateProperties.HANGING))
                            .setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
            LampRegistry.registerLampState(UselessBlocks.LANTERN.get(),
                    state -> Blocks.LANTERN.defaultBlockState()
                            .setValue(BlockStateProperties.HANGING, state.getValue(BlockStateProperties.HANGING))
                            .setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)), state -> state);
        });

        // Biome Manager
        event.enqueueWork(() -> {
            ResourceKey<Biome> key = UselessBiomes.USELESS_FOREST;
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(key, 10));
        });

        // make sure the stats appear in the menu
        event.enqueueWork(() -> Registration.CUSTOM_STATS.getEntries().stream().map(Holder::value).forEach(Stats.CUSTOM::get));

        // Vanilla Compatibility: Flammable, Strippable, Compostable, Cauldron
        event.enqueueWork(VanillaCompatibility::register);
    }

    private void entityAttributes(final EntityAttributeCreationEvent event) {
        event.put(UselessEntityTypes.USELESS_SHEEP.get(), Sheep.createAttributes().build());
        event.put(UselessEntityTypes.USELESS_PIG.get(), Pig.createAttributes().build());
        event.put(UselessEntityTypes.USELESS_CHICKEN.get(), Chicken.createAttributes().build());
        event.put(UselessEntityTypes.USELESS_COW.get(), Cow.createAttributes().build());
        event.put(UselessEntityTypes.USELESS_SKELETON.get(), AbstractSkeleton.createAttributes().build());
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event) {
        // Blocks
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UselessBlockEntityTypes.COFFEE_MACHINE.get(), SidedInvWrapper::new);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, UselessBlockEntityTypes.COFFEE_MACHINE.get(), (blockEntity, side) -> blockEntity.tankHandler);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, UselessBlockEntityTypes.COFFEE_MACHINE.get(), (blockEntity, side) -> blockEntity.energyStorage);

        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, UselessBlockEntityTypes.PAINT_BUCKET.get(), (blockEntity, side) -> blockEntity.stackHandler);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, UselessBlockEntityTypes.PAINT_BUCKET.get(), (blockEntity, side) -> side == null || side == Direction.UP ? blockEntity.colorTank : null);

        event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, pos, state, blockEntity, context) -> level.getCapability(Capabilities.ItemHandler.BLOCK, pos.above(), context), UselessBlocks.MACHINE_SUPPLIER.get());
        event.registerBlock(Capabilities.FluidHandler.BLOCK, (level, pos, state, blockEntity, context) -> level.getCapability(Capabilities.FluidHandler.BLOCK, pos.above(), context), UselessBlocks.MACHINE_SUPPLIER.get());
        event.registerBlock(Capabilities.EnergyStorage.BLOCK, (level, pos, state, blockEntity, context) -> level.getCapability(Capabilities.EnergyStorage.BLOCK, pos.above(), context), UselessBlocks.MACHINE_SUPPLIER.get());

        // Items
        event.registerItem(Capabilities.FluidHandler.ITEM, (container, context) -> new BucketWithPaintItem.PaintFluidBucketWrapper(container), UselessItems.BUCKET_PAINT);
    }

    public @Nullable Player getLocalPlayer() {
        return null;
    }
}
