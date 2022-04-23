package net.themcbrothers.uselessmod.world.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class UselessElytraItem extends ElytraItem {
    public UselessElytraItem(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }
}
