package themcbros.uselessmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import themcbros.uselessmod.init.FluidInit;

/**
 * @author TheMCBrothers
 */
public class UselessFluidTagsProvider extends FluidTagsProvider {

    public UselessFluidTagsProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {

    }

    @Override
    public String getName() {
        return "Useless Fluid Tags";
    }

}
