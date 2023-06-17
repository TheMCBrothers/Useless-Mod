package net.themcbrothers.uselessmod.data;

import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.Optional;

public class UselessSpriteSourceProvider extends SpriteSourceProvider {
    public UselessSpriteSourceProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, UselessMod.MOD_ID);
    }

    @Override
    protected void addSources() {
        this.atlas(BEDS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/bed/useless"), Optional.empty()));
        this.atlas(BLOCKS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/shield/useless"), Optional.empty()));
        this.atlas(BLOCKS_ATLAS).addSource(new SingleFile(UselessMod.rl("entity/shield/super_useless"), Optional.empty()));
    }
}
