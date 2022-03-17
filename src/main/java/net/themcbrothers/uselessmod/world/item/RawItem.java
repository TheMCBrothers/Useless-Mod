package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.client.IItemRenderProperties;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;

import java.util.function.Consumer;

public class RawItem extends Item {
    public RawItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(UselessItemStackRendererProvider.useless());
    }
}
