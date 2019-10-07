package tk.themcbros.uselessmod.helper;

import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

/**
 * @author TheMCLoveMan
 */
public interface IHammer {

    /**
     * @param context The context given from {@code HammerItem}
     * @return {@code ActionResultType} success, pass or fail
     */
    ActionResultType onHammer(ItemUseContext context);

}
