package net.themcbrothers.uselessmod.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class UselessDataMapsProvider extends DataMapProvider {
    public UselessDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        //noinspection deprecation
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModBlocks.USELESS_OAK_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(ModItems.USELESS_WHEAT_SEEDS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(ModItems.COFFEE_SEEDS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(ModBlocks.RED_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(ModBlocks.BLUE_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(ModBlocks.USELESS_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(ModItems.USELESS_WHEAT.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(ModItems.COFFEE_BEANS.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
        ;
    }
}
