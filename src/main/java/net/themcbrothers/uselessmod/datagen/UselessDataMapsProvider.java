package net.themcbrothers.uselessmod.datagen;

import net.minecraft.core.Holder;
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
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(Holder.direct(UselessBlocks.USELESS_OAK_SAPLING.asItem()), new Compostable(0.3F), false)
                .add(Holder.direct(UselessItems.USELESS_WHEAT_SEEDS.asItem()), new Compostable(0.3F), false)
                .add(Holder.direct(UselessItems.COFFEE_SEEDS.asItem()), new Compostable(0.3F), false)
                .add(Holder.direct(UselessBlocks.RED_ROSE.asItem()), new Compostable(0.65F), false)
                .add(Holder.direct(UselessBlocks.BLUE_ROSE.asItem()), new Compostable(0.65F), false)
                .add(Holder.direct(UselessBlocks.USELESS_ROSE.asItem()), new Compostable(0.65F), false)
                .add(Holder.direct(UselessItems.USELESS_WHEAT.asItem()), new Compostable(0.65F), false)
                .add(Holder.direct(UselessItems.COFFEE_BEANS.asItem()), new Compostable(0.65F), false)
        ;
    }
}
