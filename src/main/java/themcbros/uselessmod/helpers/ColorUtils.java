package themcbros.uselessmod.helpers;

import net.minecraft.util.text.*;

public class ColorUtils {

    public static float[] getRGBA(int color) {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        float alpha = ((color >> 24) & 0xFF) / 255F;

        return new float[] {red, green, blue, alpha};
    }

    public static int fullAlpha(int color) {
        return (color & 0x00ffffff) | (255 << 24);
    }

    public static String getHex(int color) {
        return String.format("#%06X", color);
    }

    public static IFormattableTextComponent getHexAsText(int color) {
        IFormattableTextComponent text = new StringTextComponent(getHex(color));
        text.setStyle(Style.EMPTY.setColor(Color.fromInt(color)));
        return text;
    }

}
