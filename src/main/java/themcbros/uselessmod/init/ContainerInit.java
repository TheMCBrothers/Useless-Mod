package themcbros.uselessmod.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.registration.deferred.ContainerTypeDeferredRegister;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.container.CoffeeMachineContainer;

public class ContainerInit {

    public static final ContainerTypeDeferredRegister REGISTER = new ContainerTypeDeferredRegister(UselessMod.MOD_ID);

    public static final RegistryObject<ContainerType<CoffeeMachineContainer>> COFFEE_MACHINE =
            REGISTER.register("coffee_machine", CoffeeMachineContainer::new);

}
