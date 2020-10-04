package themcbros.uselessmod.init;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.container.CoffeeMachineContainer;

public class ContainerInit {

    public static final DeferredRegister<ContainerType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, UselessMod.MOD_ID);

    public static final RegistryObject<ContainerType<CoffeeMachineContainer>> COFFEE_MACHINE =
            register("coffee_machine", CoffeeMachineContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String registryName, IContainerFactory<T> containerFactory) {
        return REGISTER.register(registryName, () -> new ContainerType<>(containerFactory));
    }

}
