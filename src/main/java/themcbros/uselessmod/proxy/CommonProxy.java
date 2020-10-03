package themcbros.uselessmod.proxy;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.UselessRegistries;
import themcbros.uselessmod.api.coffee.CoffeeTypeInit;
import themcbros.uselessmod.api.color.CapabilityColor;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.api.wall_closet.ClosetMaterialInit;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.compat.ModCompatibility;
import themcbros.uselessmod.compat.VanillaCompat;
import themcbros.uselessmod.config.Config;
import themcbros.uselessmod.datagen.DataEvents;
import themcbros.uselessmod.helpers.RecipeHelper;
import themcbros.uselessmod.init.*;
import themcbros.uselessmod.network.Messages;
import themcbros.uselessmod.recipe.CoffeeRecipeManager;
import themcbros.uselessmod.recipe.WallClosetRecipeManager;
import themcbros.uselessmod.useless_mana.player.PlayerMana;
import themcbros.uselessmod.world.structure.Structures;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class CommonProxy {

    CommonProxy() {

        UselessRegistries.init();
        UselessInitialization.init();
        ModCompatibility.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ParticleInit.REGISTER.register(bus);
        PaintingInit.REGISTER.register(bus);
        EnchantmentInit.REGISTER.register(bus);
//        FluidInit.REGISTER.register(bus);
        BlockInit.REGISTER.register(bus);
        ItemInit.REGISTER.register(bus);
        ContainerInit.REGISTER.register(bus);
        TileEntityInit.REGISTER.register(bus);
        EntityInit.REGISTER.register(bus);
        BiomeInit.REGISTER.register(bus);
        ClosetMaterialInit.REGISTER.register(bus);
        CoffeeTypeInit.REGISTER.register(bus);
        RecipeSerializerInit.REGISTER.register(bus);
        bus.register(new Structures());

        ColorModule.init();
        ColorModule.register(bus);

//        if (ModCompatibility.isMekanismLoaded) {
//            try {
//                Class.forName("themcbros.uselessmod.compat.mekanism.MekanismCompat")
//                        .getDeclaredMethod("register", IEventBus.class).invoke(null, bus);
//            } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException exception) {
//                exception.printStackTrace();
//            }
//        }

        if (ModCompatibility.isImmersiveLoaded) {
            try {
                Class.forName("themcbros.uselessmod.compat.immersiveengineering.ImmersiveCompat")
                        .getDeclaredMethod("register", IEventBus.class).invoke(null, bus);
            } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }

        bus.register(new DataEvents());

        Messages.init();

        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);

        Config.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
    }

    protected void setup(final FMLCommonSetupEvent event) {
        // Common Setup

        MinecraftForge.EVENT_BUS.register(new RecipeHelper());
        MinecraftForge.EVENT_BUS.register(new WallClosetRecipeManager());
        MinecraftForge.EVENT_BUS.register(new CoffeeRecipeManager());

        // Biomes
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, BiomeInit.USELESS_FOREST.getId());
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(key, 10));

        CapabilityUselessEnergy.register();
        CapabilityColor.register();
        CapabilityManager.INSTANCE.register(PlayerMana.class, new Capability.IStorage<PlayerMana>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<PlayerMana> capability, PlayerMana instance, Direction side) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void readNBT(Capability<PlayerMana> capability, PlayerMana instance, Direction side, INBT nbt) {
                throw new UnsupportedOperationException();
            }
        }, () -> null);

        // Set Entity Attributes
        GlobalEntityTypeAttributes.put(EntityInit.USELESS_SHEEP.get(), SheepEntity.func_234225_eI_().create());
        GlobalEntityTypeAttributes.put(EntityInit.USELESS_PIG.get(), PigEntity.func_234215_eI_().create());
        GlobalEntityTypeAttributes.put(EntityInit.USELESS_CHICKEN.get(), ChickenEntity.func_234187_eI_().create());
        GlobalEntityTypeAttributes.put(EntityInit.USELESS_COW.get(), CowEntity.func_234188_eI_().create());
        GlobalEntityTypeAttributes.put(EntityInit.USELESS_SKELETON.get(), SkeletonEntity.registerAttributes().create());

        // Vanilla Compatibility
        VanillaCompat.register();

        // World Generation
        UselessFeatures.init();
    }

    protected void enqueueIMC(final InterModEnqueueEvent event) {
        // Enqueue IMC
    }

    protected void processIMC(final InterModProcessEvent event) {
        // Process IMC
    }

}
