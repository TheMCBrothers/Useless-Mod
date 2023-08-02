package net.themcbrothers.uselessmod.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;
import net.themcbrothers.uselessmod.UselessMod;

import java.util.function.Consumer;

public final class UselessFluidTypes {
    static void register() {
    }

    public static final RegistryObject<FluidType> PAINT = Registration.FLUID_TYPES.register("paint", () ->
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
                            return stack.hasTag() ? stack.getTag().getInt("Color") : 0xFFFFFFFF;
                        }
                    });
                }

                @Override
                public ItemStack getBucket(FluidStack stack) {
                    return new ItemStack(ModItems.BUCKET_PAINT, 1, stack.getTag());
                }
            });
}
