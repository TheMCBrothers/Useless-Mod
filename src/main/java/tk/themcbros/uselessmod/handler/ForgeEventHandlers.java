package tk.themcbros.uselessmod.handler;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import tk.themcbros.uselessmod.commands.ModCommands;

public class ForgeEventHandlers {

    @SubscribeEvent
    public void serverLoad(final FMLServerStartingEvent event) {
        ModCommands.register(event.getCommandDispatcher());
    }

}
