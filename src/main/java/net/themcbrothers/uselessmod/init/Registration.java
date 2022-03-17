package net.themcbrothers.uselessmod.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.UselessMod;
import slimeknights.mantle.registration.deferred.*;

public final class Registration {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(UselessMod.MOD_ID);
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(UselessMod.MOD_ID);
    public static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final ContainerTypeDeferredRegister CONTAINERS = new ContainerTypeDeferredRegister(UselessMod.MOD_ID);
    public static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, UselessMod.MOD_ID);

    public static void register(IEventBus bus) {
        ModBlocks.register();
        ModItems.register();
        ModBlockEntityTypes.register();
        ModMenuTypes.register();
        ModEntityTypes.register();
        ModBiomes.register();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
        ENTITIES.register(bus);
        BIOMES.register(bus);
    }

    private Registration() {
    }
}
