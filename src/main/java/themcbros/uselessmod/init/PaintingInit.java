package themcbros.uselessmod.init;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;

public class PaintingInit {

    public static final DeferredRegister<PaintingType> REGISTER = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, UselessMod.MOD_ID);

    public static final RegistryObject<PaintingType> USELESS_TOOLS = REGISTER.register("useless_tools", () -> new PaintingType(64, 64));
    public static final RegistryObject<PaintingType> RED_USELESSMOD_LOGO = REGISTER.register("red_uselessmod_logo", () -> new PaintingType(192, 32));
    public static final RegistryObject<PaintingType> BLUE_USELESSMOD_LOGO = REGISTER.register("blue_uselessmod_logo", () -> new PaintingType(192, 32));

}
