package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.client.IItemRenderProperties;
import net.themcbrothers.uselessmod.client.renderer.UselessItemStackRendererProvider;

import java.util.function.Consumer;

public class UselessShieldItem extends ShieldItem {
    private final Tier tier;

    public UselessShieldItem(Tier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(UselessItemStackRendererProvider.shield());
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairIngredient().test(repair);
    }
}
