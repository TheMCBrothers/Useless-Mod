package net.themcbrothers.uselessmod.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.RawItem;
import slimeknights.mantle.registration.object.ItemObject;

import static net.themcbrothers.uselessmod.init.Registration.ITEMS;

public final class ModItems {
    static void register() {
    }

    public static final ItemObject<Item> RAW_USELESS = ITEMS.register("raw_useless", () -> new Item(new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_DUST = ITEMS.register("useless_dust", () -> new Item(new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_INGOT = ITEMS.register("useless_ingot", () -> new Item(new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_NUGGET = ITEMS.register("useless_nugget", () -> new Item(new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_SWORD = ITEMS.register("useless_sword", () -> new SwordItem(ModTiers.USELESS, 3, -2.4F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_SHOVEL = ITEMS.register("useless_shovel", () -> new ShovelItem(ModTiers.USELESS, 1.5F, -3.0F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_PICKAXE = ITEMS.register("useless_pickaxe", () -> new PickaxeItem(ModTiers.USELESS, 1, -2.8F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_AXE = ITEMS.register("useless_axe", () -> new AxeItem(ModTiers.USELESS, 6.0F, -3.1F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_HOE = ITEMS.register("useless_hoe", () -> new HoeItem(ModTiers.USELESS, -2, -1.0F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_HELMET = ITEMS.register("useless_helmet", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.HEAD, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_CHESTPLATE = ITEMS.register("useless_chestplate", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.CHEST, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_LEGGINGS = ITEMS.register("useless_leggings", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.LEGS, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_BOOTS = ITEMS.register("useless_boots", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.FEET, new Item.Properties().tab(UselessMod.TAB)));

    public static final ItemObject<Item> RAW_TEST = ITEMS.register("raw_test", () -> new RawItem(new Item.Properties().tab(UselessMod.TAB)));

    // todo useless bone, leather, etc
}