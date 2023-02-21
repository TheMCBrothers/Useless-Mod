package net.themcbrothers.uselessmod.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryBuilder;
import net.themcbrothers.lib.registration.deferred.*;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;

public final class Registration {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(UselessMod.MOD_ID);
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(UselessMod.MOD_ID);
    public static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final MenuTypeDeferredRegister MENU_TYPES = new MenuTypeDeferredRegister(UselessMod.MOD_ID);
    public static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, UselessMod.MOD_ID);
    public static final DeferredRegister<CoffeeType> COFFEE_TYPES = DeferredRegister.create(UselessRegistries.COFFEE_KEY, UselessMod.MOD_ID);
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(Registry.CUSTOM_STAT_REGISTRY, UselessMod.MOD_ID);

    public static void register(IEventBus bus) {
        UselessRegistries.coffeeRegistry = COFFEE_TYPES.makeRegistry(CoffeeType.class, RegistryBuilder::new);

        ModBlocks.register();
        ModItems.register();
        ModBlockEntityTypes.register();
        ModMenuTypes.register();
        ModEntityTypes.register();
        ModBiomes.register();
        ModRecipeTypes.register();
        ModRecipeSerializers.register();
        ModCoffeeTypes.register();
        ModStats.register();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU_TYPES.register(bus);
        ENTITIES.register(bus);
        BIOMES.register(bus);
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        COFFEE_TYPES.register(bus);
        CUSTOM_STATS.register(bus);
    }

    private Registration() {
    }
}
