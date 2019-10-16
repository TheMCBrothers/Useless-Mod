package tk.themcbros.uselessmod.helper;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tk.themcbros.uselessmod.UselessMod;

public class TextUtils {

	private static final String ENERGY_FORMAT = "%,d";

	public static ITextComponent translate(String prefix, String suffix, Object... params) {
		String key = String.format("%s.%s.%s", prefix, UselessMod.MOD_ID, suffix);
		return new TranslationTextComponent(key, params);
	}

	public static ITextComponent energy(int amount) {
		String s1 = String.format(ENERGY_FORMAT, amount);
		return translate("misc", "energy", s1);
	}

	public static ITextComponent energyWithMax(int amount, int max) {
		String s1 = String.format(ENERGY_FORMAT, amount);
		String s2 = String.format(ENERGY_FORMAT, max);
		return translate("misc", "energyWithMax", s1, s2);
	}

	public static ITextComponent fluidWithMax(IFluidHandler tank) {
		FluidStack fluid = tank.getFluidInTank(0);
		ITextComponent fluidName = fluid.getDisplayName();
		String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
		String s2 = String.format(ENERGY_FORMAT, tank.getTankCapacity(0));
		return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
	}
	
	public static ITextComponent fluidWithMax(FluidStack fluid, int max) {
		ITextComponent fluidName = fluid.getDisplayName();
		String s1 = String.format(ENERGY_FORMAT, fluid.getAmount());
		String s2 = String.format(ENERGY_FORMAT, max);
		return translate("misc", fluid.getAmount() > 0 ? "fluidWithMaxName" : "empty", fluidName, s1, s2);
	}
	
	public static ITextComponent fluidWithMax(String fluid, int amount, int max) {
		String s1 = String.format(ENERGY_FORMAT, amount);
		String s2 = String.format(ENERGY_FORMAT, max);
		return translate("misc", amount > 0 ? "fluidWithMaxName" : "empty", fluid, s1, s2);
	}
	
	public static ITextComponent fluidWithMax(int amount, int max) {
		String s1 = String.format(ENERGY_FORMAT, amount);
		String s2 = String.format(ENERGY_FORMAT, max);
		return translate("misc", amount > 0 ? "fluidWithMax" : "empty", s1, s2);
	}

	public static ITextComponent fluidName(FluidStack stack) {
		if (stack.isEmpty()) return translate("misc", "empty");
		return stack.getDisplayName();
	}

	public static ITextComponent fluidName(Fluid fluid) {
		if (fluid == Fluids.EMPTY) return translate("misc", "empty");
		return fluid.getAttributes().getDisplayName(FluidStack.EMPTY);
	}

}
