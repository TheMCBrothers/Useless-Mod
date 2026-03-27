package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.AtlasIds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.SpriteSourceProvider;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UselessSpriteSourceProvider extends SpriteSourceProvider {
    public UselessSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, UselessMod.MOD_ID);
    }

    @Override
    protected void gather() {
        this.atlas(AtlasIds.BEDS).addSource(new SingleFile(UselessMod.id("entity/bed/useless"), Optional.empty()));
        this.atlas(AtlasIds.BLOCKS).addSource(new SingleFile(UselessMod.id("entity/shield/useless"), Optional.empty()));
        this.atlas(AtlasIds.BLOCKS).addSource(new SingleFile(UselessMod.id("entity/shield/super_useless"), Optional.empty()));
    }
}
