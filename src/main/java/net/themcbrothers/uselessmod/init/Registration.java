package net.themcbrothers.uselessmod.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.themcbrothers.lib.registration.deferred.BlockEntityTypeDeferredRegister;
import net.themcbrothers.lib.registration.deferred.EntityTypeDeferredRegister;
import net.themcbrothers.lib.registration.deferred.MenuTypeDeferredRegister;
import net.themcbrothers.lib.registries.BlockDeferredRegister;
import net.themcbrothers.lib.registries.ItemDeferredRegister;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;

public final class Registration {
    public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UselessMod.MOD_ID);
    public static final ItemDeferredRegister ITEMS = ItemDeferredRegister.create(UselessMod.MOD_ID);
    public static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final MenuTypeDeferredRegister MENU_TYPES = new MenuTypeDeferredRegister(UselessMod.MOD_ID);
    public static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, UselessMod.MOD_ID);
    public static final DeferredRegister<CoffeeType> COFFEE_TYPES = DeferredRegister.create(UselessRegistries.COFFEE_KEY, UselessMod.MOD_ID);
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, UselessMod.MOD_ID);
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(Registries.PAINTING_VARIANT, UselessMod.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, UselessMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, UselessMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UselessMod.MOD_ID);

    public static void register(IEventBus bus) {
        UselessRegistries.coffeeRegistry = COFFEE_TYPES.makeRegistry(RegistryBuilder::create);

        ModBlocks.register();
        ModItems.register();
        ModBlockEntityTypes.register();
        ModMenuTypes.register();
        ModEntityTypes.register();
        ModRecipeTypes.register();
        ModRecipeSerializers.register();
        ModCoffeeTypes.register();
        ModStats.register();
        UselessPaintingVariants.register();
        UselessFluidTypes.register();
        UselessFluids.register();
        UselessCreativeModeTabs.register();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU_TYPES.register(bus);
        ENTITIES.register(bus);
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        COFFEE_TYPES.register(bus);
        CUSTOM_STATS.register(bus);
        PAINTING_VARIANTS.register(bus);
        FLUID_TYPES.register(bus);
        FLUIDS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }

    private Registration() {
    }
}
