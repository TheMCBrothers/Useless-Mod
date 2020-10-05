package themcbros.uselessmod.init;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.registration.FluidDeferredRegister;
import themcbros.uselessmod.registration.FluidRegistryObject;

public class FluidInit {

    public static final FluidDeferredRegister REGISTER = new FluidDeferredRegister(UselessMod.MOD_ID);

    public static final FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> USELESS_GAS =
            REGISTER.register("useless_gas", FluidAttributes.builder(new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow")).density(0).temperature(1000).color(0xFF468b44).luminosity(10)
                    .translationKey("block.uselessmod.useless_gas").gaseous());
    public static final FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> USELESS_WATER =
            REGISTER.register("useless_water", FluidAttributes.builder(new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow")).color(0xFF468b44).translationKey("block.uselessmod.useless_water"));

}
