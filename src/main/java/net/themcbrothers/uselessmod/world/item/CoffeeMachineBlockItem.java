package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class CoffeeMachineBlockItem extends BlockItem {
    public CoffeeMachineBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(@NotNull Consumer<IItemRenderProperties> consumer) {
        consumer.accept(UselessItemStackRendererProvider.blockEntity());
    }
}
