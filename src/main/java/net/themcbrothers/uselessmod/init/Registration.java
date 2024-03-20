package net.themcbrothers.uselessmod.init;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.themcbrothers.lib.registries.*;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.api.UselessRegistries;

public final class Registration {
    public static final ItemDeferredRegister ITEMS = ItemDeferredRegister.create(UselessMod.MOD_ID);
    public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UselessMod.MOD_ID, ITEMS);
    public static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = BlockEntityTypeDeferredRegister.create(UselessMod.MOD_ID);
    public static final MenuTypeDeferredRegister MENU_TYPES = MenuTypeDeferredRegister.create(UselessMod.MOD_ID);
    public static final EntityTypeDeferredRegister ENTITY_TYPES = EntityTypeDeferredRegister.create(UselessMod.MOD_ID, ITEMS);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, UselessMod.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, UselessMod.MOD_ID);
    public static final DeferredRegister<CoffeeType> COFFEE_TYPES = DeferredRegister.create(UselessRegistries.COFFEE_KEY, UselessMod.MOD_ID);
    public static final DeferredRegister<ResourceLocation> CUSTOM_STATS = DeferredRegister.create(Registries.CUSTOM_STAT, UselessMod.MOD_ID);
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(Registries.PAINTING_VARIANT, UselessMod.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, UselessMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, UselessMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UselessMod.MOD_ID);
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, UselessMod.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, UselessMod.MOD_ID);

    public static void register(IEventBus bus) {
        bus.addListener(NewRegistryEvent.class, event -> event.register(UselessRegistries.COFFEE_REGISTRY));

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
        UselessDataComponents.register();
        UselessArmorMaterials.register();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        MENU_TYPES.register(bus);
        ENTITY_TYPES.register(bus);
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        COFFEE_TYPES.register(bus);
        CUSTOM_STATS.register(bus);
        PAINTING_VARIANTS.register(bus);
        FLUID_TYPES.register(bus);
        FLUIDS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
        DATA_COMPONENT_TYPES.register(bus);
        ARMOR_MATERIALS.register(bus);
    }

    private Registration() {
    }
}
