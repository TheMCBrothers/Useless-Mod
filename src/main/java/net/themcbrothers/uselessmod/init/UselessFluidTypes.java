package net.themcbrothers.uselessmod.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class UselessFluidTypes {
    static void register() {
    }

    public static final Supplier<FluidType> PAINT = Registration.FLUID_TYPES.register("paint", () ->
            new FluidType(FluidType.Properties.create()
                    .density(2048)
                    .viscosity(2048)) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation PAINT_STILL = UselessMod.rl("block/paint_still"),
                                PAINT_FLOW = UselessMod.rl("block/paint_flow");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return PAINT_STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return PAINT_FLOW;
                        }

                        @Override
                        public int getTintColor(FluidStack stack) {
                            return stack.getOrDefault(UselessDataComponents.COLOR.get(), 0xFFFFFFFF);
                        }
                    });
                }

                @Override
                public ItemStack getBucket(FluidStack stack) {
                    return new ItemStack(ModItems.BUCKET_PAINT, 1, stack.getComponentsPatch());
                }
            });
}
