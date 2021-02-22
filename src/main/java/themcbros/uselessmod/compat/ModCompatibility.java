package themcbros.uselessmod.compat;

import net.minecraftforge.fml.ModList;

public class ModCompatibility {

    public static boolean isImmersiveLoaded;

    public static void init() {
        isImmersiveLoaded = ModList.get().isLoaded("immersiveengineering");
    }

}
