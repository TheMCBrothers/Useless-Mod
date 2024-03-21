package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.themcbrothers.uselessmod.core.UselessBlocks;
import net.themcbrothers.uselessmod.core.UselessItems;

import java.util.concurrent.CompletableFuture;

public class UselessDataMapsProvider extends DataMapProvider {
    public UselessDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        //noinspection deprecation
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(UselessBlocks.USELESS_OAK_SAPLING.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(UselessItems.USELESS_WHEAT_SEEDS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(UselessItems.COFFEE_SEEDS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false)
                .add(UselessBlocks.RED_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(UselessBlocks.BLUE_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(UselessBlocks.USELESS_ROSE.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(UselessItems.USELESS_WHEAT.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
                .add(UselessItems.COFFEE_BEANS.asItem().builtInRegistryHolder(), new Compostable(0.65F), false)
        ;
    }
}
