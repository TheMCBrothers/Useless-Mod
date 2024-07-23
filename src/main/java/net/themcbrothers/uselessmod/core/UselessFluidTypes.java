package net.themcbrothers.uselessmod.core;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Supplier;

public final class UselessFluidTypes {
    static void register() {
    }

    public static final Supplier<FluidType> PAINT = Registration.FLUID_TYPES.register("paint", () ->
            new FluidType(FluidType.Properties.create()
                    .density(2048)
                    .viscosity(2048)) {
                @Override
                public ItemStack getBucket(FluidStack stack) {
                    return new ItemStack(UselessItems.BUCKET_PAINT, 1, stack.getComponentsPatch());
                }
            });
}
