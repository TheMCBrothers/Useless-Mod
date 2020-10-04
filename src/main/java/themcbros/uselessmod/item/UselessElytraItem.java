package themcbros.uselessmod.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;

public class UselessElytraItem extends ElytraItem {

    public UselessElytraItem(Properties builder) {
        super(builder);
    }

    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.CHEST;
    }
}
