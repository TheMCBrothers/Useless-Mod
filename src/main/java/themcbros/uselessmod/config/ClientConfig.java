package themcbros.uselessmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public final ForgeConfigSpec.BooleanValue renderManaBar;

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Welcome to the client config file!");

        builder.push("gui");
        this.renderManaBar = builder.comment("Render Mana bar if mana is in it").define("renderManaBar", true);
        builder.pop();
    }

}
