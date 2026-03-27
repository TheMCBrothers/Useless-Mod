package net.themcbrothers.uselessmod.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.themcbrothers.lib.crafting.FluidRecipeWrapper;

public class CoffeeRecipeInput extends FluidRecipeWrapper {
    private final boolean useMilk;

    public CoffeeRecipeInput(ResourceHandler<ItemResource> items, ResourceHandler<FluidResource> fluids, boolean useMilk) {
        super(items, fluids);
        this.useMilk = useMilk;
    }

    public boolean useMilk() {
        return this.useMilk;
    }

    public ItemStack getCup() {
        return this.getItem(0);
    }

    public ItemStack getBean() {
        return this.getItem(1);
    }

    public ItemStack getExtra() {
        return this.getItem(2);
    }

    public FluidStack getWater() {
        return this.getFluid(0);
    }

    public FluidStack getMilk() {
        return this.getFluid(1);
    }
}
