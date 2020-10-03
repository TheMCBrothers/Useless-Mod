package themcbros.uselessmod.api.color;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;

/**
 * @author TheMCBrothers
 */
public interface IColorHandler {

    /**
     * @return Color
     */
    int getColor();

    /**
     * Method to set color
     */
    void setColor(int color);

    /**
     * @return TRUE if it contains color
     */
    default boolean hasColor() {
        return getColor() != -1;
    }

    /**
     * @param stack Clicked stack
     * @return Result
     */
    default ActionResultType onClick(ItemStack stack) {
        return ActionResultType.PASS;
    }

}
