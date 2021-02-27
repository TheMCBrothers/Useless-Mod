package themcbros.uselessmod.util;

import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

/**
 * @author TheMCBrothers
 */
public class Styles {
    public static final Style TOOLTIP = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY));
    public static final Style FORGE_ENERGY = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.RED));
    public static final Style MODE_MANA = Style.EMPTY.setColor(Color.fromInt(0x62B15F));
    public static final Style MODE_FLUID = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.BLUE));
    public static final Style MODE_ENTITY = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.YELLOW));
    public static final Style USELESS_ENERGY = Style.EMPTY.setColor(Color.fromInt(0x62B15F));
    public static final Style FLUID_MILK = Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.WHITE));
}
