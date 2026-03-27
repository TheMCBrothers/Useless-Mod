package net.themcbrothers.uselessmod.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;
import net.themcbrothers.uselessmod.world.level.block.entity.CoffeeMachineBlockEntity;

public class CoffeeMachineRenderState extends BlockEntityRenderState {
    public FluidStack waterTank = FluidStack.EMPTY;
    public FluidStack milkTank = FluidStack.EMPTY;
    public int waterCapacity = 0;
    public int milkCapacity = 0;

    /**
     * Extracts ONLY the specific properties for a coffee machine!
     *
     * @param blockEntity Coffee machine block entity
     * @param renderState Coffee machine render state
     */
    public static void extract(CoffeeMachineBlockEntity blockEntity, CoffeeMachineRenderState renderState) {
        renderState.waterTank = FluidUtil.getStack(blockEntity.tankHandler, 0);
        renderState.milkTank = FluidUtil.getStack(blockEntity.tankHandler, 1);
        renderState.waterCapacity = blockEntity.tankHandler.getCapacityAsInt(0, FluidResource.EMPTY);
        renderState.milkCapacity = blockEntity.tankHandler.getCapacityAsInt(1, FluidResource.EMPTY);
    }
}
