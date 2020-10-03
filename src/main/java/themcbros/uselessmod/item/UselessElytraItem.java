package themcbros.uselessmod.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UselessElytraItem extends ElytraItem {

    public UselessElytraItem(Properties builder) {
        super(builder);
        ItemModelsProperties.registerProperty(this, new ResourceLocation("broken"),
                (itemStack, clientWorld, livingEntity) -> ElytraItem.isUsable(itemStack) ? 0.0F : 1.0F);
    }

    @Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
        return EquipmentSlotType.CHEST;
    }
}
