package net.themcbrothers.uselessmod.util;

/**
 * Some useful methods for colors
 *
 * @author TheMCLoveMan
 */
public class ColorUtils {
    /**
     * Returns the given color with a full alpha channel
     *
     * @param color Color
     * @return Color with full alpha
     */
    public static int fullAlpha(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        int a = 0xFF;

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
