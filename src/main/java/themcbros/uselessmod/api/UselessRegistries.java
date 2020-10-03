package themcbros.uselessmod.api;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.api.wall_closet.ClosetMaterial;

/**
 * @author TheMCBrothers
 */
public class UselessRegistries {

    public static final IForgeRegistry<ClosetMaterial> CLOSET_MATERIALS = new RegistryBuilder<ClosetMaterial>()
            .setType(ClosetMaterial.class)
            .setName(UselessMod.rl("closet_material"))
            .create();
    public static final IForgeRegistry<CoffeeType> COFFEE_TYPES = new RegistryBuilder<CoffeeType>()
            .setType(CoffeeType.class)
            .setName(UselessMod.rl("coffee_type"))
            .create();

    public static void init() {}

}
