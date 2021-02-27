package themcbros.uselessmod.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import slimeknights.mantle.client.model.fluid.FluidCuboid;
import slimeknights.mantle.client.render.FluidRenderer;

public class RenderUtils {

    /**
     * A METHOD FROM TINKERS CONSTRUCT
     * Add textured quads for a fluid tank
     * @param matrices      Matrix stack instance
     * @param buffer        Render type buffer instance
     * @param tank          Fluid tank animated to render=
     * @param light         Quad lighting
     * @param cube          Fluid cuboid instance
     */
    public static void renderFluidTank(MatrixStack matrices, IRenderTypeBuffer buffer, FluidCuboid cube, FluidTank tank, int light) {
        // render liquid if present
        FluidStack liquid = tank.getFluid();
        int capacity = tank.getCapacity();
        if (!liquid.isEmpty() && capacity > 0) {
            // fetch fluid information from the model
            FluidRenderer.renderScaledCuboid(matrices, buffer, cube, liquid, 0F, capacity, light, liquid.getFluid().getAttributes().isGaseous(liquid));
        }
    }

}
