package themcbros.uselessmod.init;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import themcbros.uselessmod.UselessMod;

public class StatsInit {

    public static final ResourceLocation INTERACT_WITH_COFFEE_MACHINE = registerCustom("interact_with_coffee_machine", IStatFormatter.DEFAULT);

    private static ResourceLocation registerCustom(String name, IStatFormatter statFormatter) {
        ResourceLocation registryName = UselessMod.rl(name);
        Registry.register(Registry.CUSTOM_STAT, name, registryName);
        Stats.CUSTOM.get(registryName, statFormatter);
        return registryName;
    }

    static void init() {}

}
