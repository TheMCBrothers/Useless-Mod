package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;

import java.util.function.Consumer;

public class CoffeeMachineBlockItem extends BlockItem {
    public CoffeeMachineBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(UselessItemStackRendererProvider.blockEntity());
    }
}
