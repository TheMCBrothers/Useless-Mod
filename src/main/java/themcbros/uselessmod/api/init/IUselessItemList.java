package themcbros.uselessmod.api.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import themcbros.uselessmod.item.CoffeeCupItem;

/**
 * List of all items that are accessible is the API
 *
 * @author TheMCBrothers
 */
public interface IUselessItemList {

    RegistryObject<Item> sugaredMilk();
    RegistryObject<Item> cup();
    RegistryObject<CoffeeCupItem> coffeeCup();

}
