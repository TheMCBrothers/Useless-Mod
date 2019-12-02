package tk.themcbros.uselessmod.lists;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;

public class ModTags {

    public static class Fluids {

        public static final Tag<Fluid> USELESS_WATER = fluidTag("useless_water");

    }

    private static Tag<Fluid> fluidTag(String path) {
        return new FluidTags.Wrapper(new ResourceLocation(UselessMod.MOD_ID, path));
    }

}
