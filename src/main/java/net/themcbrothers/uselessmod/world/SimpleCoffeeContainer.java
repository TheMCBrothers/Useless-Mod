package net.themcbrothers.uselessmod.world;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class SimpleCoffeeContainer extends SimpleContainer implements CoffeeContainer {
    public SimpleCoffeeContainer(int slotCount) {
        super(slotCount);
    }

    public SimpleCoffeeContainer(ItemStack... stacks) {
        super(stacks);
    }
}
