package themcbros.uselessmod.compat;

import net.minecraftforge.fml.ModList;

public class ModCompatibility {

    public static boolean isMekanismLoaded;
    public static boolean isImmersiveLoaded;

    public static void init() {
        isMekanismLoaded = ModList.get().isLoaded("mekanism");
        isImmersiveLoaded = ModList.get().isLoaded("immersiveengineering");
    }

}
