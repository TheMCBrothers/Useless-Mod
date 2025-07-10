package net.themcbrothers.uselessmod.core;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentModels;

import java.util.EnumMap;

public interface UselessArmorMaterials {
    static void register() {
    }

    ArmorMaterial USELESS = new ArmorMaterial(20, Util.make(new EnumMap<>(ArmorType.class), defense -> {
        defense.put(ArmorType.BOOTS, 3);
        defense.put(ArmorType.LEGGINGS, 6);
        defense.put(ArmorType.CHESTPLATE, 7);
        defense.put(ArmorType.HELMET, 3);
        defense.put(ArmorType.BODY, 7);
    }), 11, SoundEvents.ARMOR_EQUIP_IRON, 0.25F, 0.025F, ItemTags.REPAIRS_IRON_ARMOR, EquipmentModels.IRON); // TODO: useless model

    ArmorMaterial SUPER_USELESS = new ArmorMaterial(22, Util.make(new EnumMap<>(ArmorType.class), defense -> {
        defense.put(ArmorType.BOOTS, 3);
        defense.put(ArmorType.LEGGINGS, 6);
        defense.put(ArmorType.CHESTPLATE, 7);
        defense.put(ArmorType.HELMET, 3);
        defense.put(ArmorType.BODY, 8);
    }), 11, SoundEvents.ARMOR_EQUIP_IRON, 0.25F, 0.025F, ItemTags.REPAIRS_IRON_ARMOR, EquipmentModels.IRON); // TODO: super-useless model
}
