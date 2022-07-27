package net.themcbrothers.uselessmod.setup;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.themcbrothers.uselessmod.init.ModBiomes;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.init.Registration;

public class CommonSetup {
    public CommonSetup() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.register(bus);
        bus.addListener(this::setup);
        bus.addListener(this::entityAttributes);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.RED_ROSE.getRegistryName(), ModBlocks.POTTED_RED_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLUE_ROSE.getRegistryName(), ModBlocks.POTTED_BLUE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.USELESS_ROSE.getRegistryName(), ModBlocks.POTTED_USELESS_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.USELESS_OAK_SAPLING.getRegistryName(), ModBlocks.POTTED_USELESS_OAK_SAPLING);

            ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES, ModBiomes.USELESS_FOREST.getId());
            BiomeDictionary.addTypes(key, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(key, 10));
        });
    }

    private void entityAttributes(final EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.USELESS_SHEEP.get(), Sheep.createAttributes().build());
        event.put(ModEntityTypes.USELESS_PIG.get(), Pig.createAttributes().build());
        event.put(ModEntityTypes.USELESS_CHICKEN.get(), Chicken.createAttributes().build());
        event.put(ModEntityTypes.USELESS_COW.get(), Cow.createAttributes().build());
        event.put(ModEntityTypes.USELESS_SKELETON.get(), AbstractSkeleton.createAttributes().build());
    }
}
