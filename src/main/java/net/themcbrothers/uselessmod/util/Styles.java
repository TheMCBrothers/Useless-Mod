package net.themcbrothers.uselessmod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class Styles {
    public static final Style TOOLTIP = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.GRAY));
    public static final Style FORGE_ENERGY = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.RED));
    public static final Style MODE_MANA = Style.EMPTY.withColor(TextColor.fromRgb(0x62B15F));
    public static final Style MODE_FLUID = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.BLUE));
    public static final Style MODE_ENTITY = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW));
    public static final Style USELESS_ENERGY = Style.EMPTY.withColor(TextColor.fromRgb(0x62B15F));
    public static final Style FLUID_MILK = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.WHITE));
    public static final Style MOD_NAME = Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.BLUE)).withItalic(true);
}
