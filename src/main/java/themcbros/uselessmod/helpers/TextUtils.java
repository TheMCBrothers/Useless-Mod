package themcbros.uselessmod.helpers;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.ForgeI18n;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.StringUtils;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.util.Styles;

public class TextUtils {

    private static final String ENERGY_FORMAT = "%,d";

    public static IFormattableTextComponent translate(String prefix, String suffix, Object... params) {
        return new TranslationTextComponent(String.format("%s.%s.%s", prefix, UselessMod.MOD_ID, suffix), params);
    }

    public static IFormattableTextComponent energy(long amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static IFormattableTextComponent energyWithMax(long amount, long max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static IFormattableTextComponent fluidWithMax(IFluidHandler tank) {
        FluidStack fluid = tank.getFluidInTank(0);
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tank.getTankCapacity(0));
        return translate("misc", "fluidWithMax", s1, s2);
    }

    public static IFormattableTextComponent fluidWithMax(FluidStack fluid, int max) {
        ITextComponent fluidName = fluid.getDisplayName();
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
    }

    public static IFormattableTextComponent fluidWithMax(String fluid, int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", amount > 0 ? "fluidWithMaxName" : "empty", fluid, s1, s2);
    }

    public static IFormattableTextComponent fluidWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", amount > 0 ? "fluidWithMax" : "empty", s1, s2);
    }

    public static IFormattableTextComponent fluidAmount(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "fluidAmount", s1);
    }

    public static ITextComponent fluidName(FluidStack stack) {
        if (stack.isEmpty()) return translate("misc", "empty");
        return stack.getDisplayName();
    }

    public static ITextComponent fluidName(Fluid fluid) {
        if (fluid == Fluids.EMPTY) return translate("misc", "empty");
        return fluid.getAttributes().getDisplayName(FluidStack.EMPTY);
    }

    public static ITextComponent getModName(ResourceLocation registryName) {
        return new StringTextComponent(getModNameForModId(registryName.getNamespace())).setStyle(Styles.MOD_NAME);
    }

    public static String getModNameForModId(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(modContainer -> modContainer.getModInfo().getDisplayName())
                .orElse(StringUtils.capitalize(modId));
    }

    /**
     * Checks if the given key can be translated
     * @param key   Key to check
     * @return      True if it can be translated
     */
    public static boolean canTranslate(String key) {
        return !ForgeI18n.getPattern(key).equals(key);
    }

    /**
     * @return Message for holding shift
     */
    public static ITextComponent holdShiftMessage() {
        return translate("misc", "holdShift");
    }
}
