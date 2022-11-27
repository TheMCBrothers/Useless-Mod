package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.BedItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;

import java.util.function.Consumer;

public class UselessBedItem extends BedItem {
    public UselessBedItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(UselessItemStackRendererProvider.useless());
    }
}
