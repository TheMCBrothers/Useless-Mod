package themcbros.uselessmod.api;

import net.minecraftforge.fml.ModList;

/**
 * @author TheMCBrothers
 */
public class UselessAPI {

    private static IUselessAPI instance;
    public static final String MOD_ID = "uselessmod";

    /**
     * Marker interface for Useless API
     */
    public interface IUselessAPI {
    }

    /**
     * @return API instance
     */
    public static IUselessAPI getInstance() {
        return instance;
    }

    /**
     * Internal use only. DO NOT CALL!
     * @param uselessAPI API instance
     */
    public static void init(IUselessAPI uselessAPI) {
        if (instance == null && ModList.get().isLoaded(MOD_ID)) {
            instance = uselessAPI;
        } else {
            throw new IllegalStateException("API already initialized!");
        }
    }

}
