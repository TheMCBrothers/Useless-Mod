package tk.themcbros.uselessmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import tk.themcbros.uselessmod.UselessMod;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> uselessCmd = dispatcher.register(
                Commands.literal(UselessMod.MOD_ID)
                .then(CommandTest.register(dispatcher))
        );

        dispatcher.register(Commands.literal("usmo").redirect(uselessCmd));
    }

}
