package net.themcbrothers.uselessmod.datagen;

import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class UselessSpriteSourceProvider extends SpriteSourceProvider {
    public UselessSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper fileHelper) {
        super(output, lookupProvider, UselessMod.MOD_ID, fileHelper);
    }

    @Override
    protected void gather() {
        this.atlas(BEDS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/bed/useless"), Optional.empty()));
        this.atlas(BLOCKS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/shield/useless"), Optional.empty()));
        this.atlas(BLOCKS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/shield/super_useless"), Optional.empty()));
    }
}
