package themcbros.uselessmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import themcbros.uselessmod.api.UselessAPI;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.proxy.ClientProxy;
import themcbros.uselessmod.proxy.CommonProxy;
import themcbros.uselessmod.proxy.ServerProxy;

/**
 * @author TheMCBrothers
 */
@Mod(UselessMod.MOD_ID)
public class UselessMod {

    public static final String MOD_ID = UselessAPI.MOD_ID;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static UselessMod instance;
    public final CommonProxy proxy;

    public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.USELESS_ORE.get());
        }
    };

    public UselessMod() {
        instance = this;

        // Enable Milk Fluid
        ForgeMod.enableMilkFluid();

        UselessAPI.init(new UselessModAPI());

        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    }

    public static UselessMod getInstance() {
        return instance;
    }

    public static ResourceLocation rl(String s) {
        return new ResourceLocation(MOD_ID, s);
    }

}
