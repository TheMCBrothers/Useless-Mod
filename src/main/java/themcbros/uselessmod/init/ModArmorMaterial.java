package themcbros.uselessmod.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.UselessTags;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    USELESS("useless", 36, new int[]{5, 8, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F,
            () -> Ingredient.fromTag(UselessTags.Items.INGOTS_USELESS)),
    SUPER_USELESS("super_useless", 64, new int[]{7, 10, 12, 7}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F,
            () -> Ingredient.fromTag(UselessTags.Items.INGOTS_SUPER_USELESS));

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyValue<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurability(EquipmentSlotType equipmentSlotType) {
        return MAX_DAMAGE_ARRAY[equipmentSlotType.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType equipmentSlotType) {
        return damageReductionAmountArray[equipmentSlotType.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @Override
    public String getName() {
        return UselessMod.rl(this.name).toString();
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this == SUPER_USELESS ? 1.0f : 0.0f;
    }
}
