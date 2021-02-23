package themcbros.uselessmod.init;

import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import themcbros.uselessmod.UselessMod;

public class FluidInit {

    public static final FluidDeferredRegister REGISTER = new FluidDeferredRegister(UselessMod.MOD_ID);

    public static final FluidObject<ForgeFlowingFluid> USELESS_GAS = REGISTER.register("useless_gas",
            FluidAttributes.builder(new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow")).density(0).temperature(1000).color(0xFF468b44).luminosity(10)
                    .translationKey("block.uselessmod.useless_gas").gaseous(), Material.WATER, 1);
    public static final FluidObject<ForgeFlowingFluid> USELESS_WATER = REGISTER.register("useless_water",
            FluidAttributes.builder(new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow")).color(0xFF468b44)
                    .translationKey("block.uselessmod.useless_water"), Material.WATER, 1);

}
