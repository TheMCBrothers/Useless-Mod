package themcbros.uselessmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.init.EntityInit;

/**
 * @author TheMCBrothers
 */
public class UselessEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public UselessEntityTypeTagsProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, UselessMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(EntityTypeTags.SKELETONS).add(EntityInit.USELESS_SKELETON.get());
    }

    @Override
    public String getName() {
        return "Useless Entity Type Tags";
    }
}
