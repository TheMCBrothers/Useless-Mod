package net.themcbrothers.uselessmod.init;

import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.themcbrothers.uselessmod.world.inventory.CoffeeMachineMenu;

import static net.themcbrothers.uselessmod.init.Registration.MENU_TYPES;

public final class ModMenuTypes {
    static void register() {
    }

    public static final DeferredHolder<MenuType<?>, MenuType<CoffeeMachineMenu>> COFFEE_MACHINE = MENU_TYPES.register("coffee_machine", CoffeeMachineMenu::new);
}
