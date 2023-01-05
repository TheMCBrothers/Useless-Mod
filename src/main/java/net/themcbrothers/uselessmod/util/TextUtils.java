package net.themcbrothers.uselessmod.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.ModList;
import net.themcbrothers.uselessmod.UselessMod;
import org.apache.commons.lang3.StringUtils;

public class TextUtils {
    private static final String ENERGY_FORMAT = "%,d";

    public static MutableComponent translate(String prefix, String suffix, Object... params) {
        return new TranslatableComponent(String.format("%s.%s.%s", prefix, UselessMod.MOD_ID, suffix), params);
    }

    public static MutableComponent energy(long amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static MutableComponent energyWithMax(long amount, long max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static MutableComponent fluidWithMax(IFluidHandler tank) {
        FluidStack fluid = tank.getFluidInTank(0);
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tank.getTankCapacity(0));
        return translate("misc", "fluidWithMax", s1, s2);
    }

    public static MutableComponent fluidWithMax(FluidStack fluid, int max) {
        Component fluidName = fluid.getDisplayName();
        String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
    }

    public static MutableComponent fluidWithMax(String fluid, int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", amount > 0 ? "fluidWithMaxName" : "empty", fluid, s1, s2);
    }

    public static MutableComponent fluidWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", amount > 0 ? "fluidWithMax" : "empty", s1, s2);
    }

    public static MutableComponent fluidAmount(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "fluidAmount", s1);
    }

    public static MutableComponent fluidName(FluidStack stack) {
        if (stack.isEmpty()) return translate("misc", "empty");
        return stack.getDisplayName().copy();
    }

    public static MutableComponent fluidName(Fluid fluid) {
        if (fluid == Fluids.EMPTY) return translate("misc", "empty");
        return fluid.getAttributes().getDisplayName(FluidStack.EMPTY).copy();
    }

    public static MutableComponent getModName(ResourceLocation registryName) {
        return new TextComponent(getModNameForModId(registryName.getNamespace())).setStyle(Styles.MOD_NAME);
    }

    public static String getModNameForModId(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(modContainer -> modContainer.getModInfo().getDisplayName())
                .orElse(StringUtils.capitalize(modId));
    }
}
