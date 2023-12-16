package net.themcbrothers.uselessmod.init;

import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class UselessFluids {
    static void register() {
    }

    private static final BaseFlowingFluid.Properties PAINT_PROPERTIES = new BaseFlowingFluid.Properties(UselessFluidTypes.PAINT, () -> UselessFluids.PAINT.get(), () -> UselessFluids.FLOWING_PAINT.get());

    public static final DeferredHolder<Fluid, Fluid> PAINT = Registration.FLUIDS.register("paint", () -> new BaseFlowingFluid.Source(PAINT_PROPERTIES));
    public static final DeferredHolder<Fluid, Fluid> FLOWING_PAINT = Registration.FLUIDS.register("flowing_paint", () -> new BaseFlowingFluid.Flowing(PAINT_PROPERTIES));
}
