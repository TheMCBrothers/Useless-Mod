package net.themcbrothers.uselessmod.core;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.UselessTags;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class UselessArmorMaterials {
    static void register() {
    }

    public static final Holder<ArmorMaterial> USELESS = register("useless",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            11,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.25F, 0.01F,
            () -> Ingredient.of(UselessTags.Items.INGOTS_USELESS)
    );

    public static final Holder<ArmorMaterial> SUPER_USELESS = register("super_useless",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            11,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.25F, 0.025F,
            () -> Ingredient.of(UselessTags.Items.INGOTS_SUPER_USELESS)
    );

    @SuppressWarnings("SameParameterValue")
    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(UselessMod.rl(name)));
        return Registration.ARMOR_MATERIALS.register(name, () ->
                new ArmorMaterial(defense, enchantmentValue, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}
