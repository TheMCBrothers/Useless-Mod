package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public final class UselessFluids {
    static void register() {
    }

    private static final ForgeFlowingFluid.Properties PAINT_PROPERTIES = new ForgeFlowingFluid.Properties(UselessFluidTypes.PAINT, () -> UselessFluids.PAINT.get(), () -> UselessFluids.FLOWING_PAINT.get());

    public static final RegistryObject<Fluid> PAINT = Registration.FLUIDS.register("paint", () -> new ForgeFlowingFluid.Source(PAINT_PROPERTIES));
    public static final RegistryObject<Fluid> FLOWING_PAINT = Registration.FLUIDS.register("flowing_paint", () -> new ForgeFlowingFluid.Flowing(PAINT_PROPERTIES));
}
