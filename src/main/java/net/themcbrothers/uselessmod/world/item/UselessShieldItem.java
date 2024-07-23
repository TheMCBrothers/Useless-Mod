package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;

public class UselessShieldItem extends ShieldItem {
    private final Tier tier;

    public UselessShieldItem(Tier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairIngredient().test(repair);
    }
}
