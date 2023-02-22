package net.themcbrothers.uselessmod.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.themcbrothers.lib.registration.object.ItemObject;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.world.item.PaintBrushItem;
import net.themcbrothers.uselessmod.world.item.UselessElytraItem;

import static net.themcbrothers.uselessmod.init.Registration.ITEMS;

public final class ModItems {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(UselessMod.TAB);

    // Metal
    public static final ItemObject<Item> RAW_USELESS = ITEMS.register("raw_useless", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_DUST = ITEMS.register("useless_dust", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_INGOT = ITEMS.register("useless_ingot", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_NUGGET = ITEMS.register("useless_nugget", GENERAL_PROPS);
    public static final ItemObject<Item> RAW_SUPER_USELESS = ITEMS.register("raw_super_useless", GENERAL_PROPS);
    public static final ItemObject<Item> SUPER_USELESS_DUST = ITEMS.register("super_useless_dust", GENERAL_PROPS);
    public static final ItemObject<Item> SUPER_USELESS_INGOT = ITEMS.register("super_useless_ingot", GENERAL_PROPS);
    public static final ItemObject<Item> SUPER_USELESS_NUGGET = ITEMS.register("super_useless_nugget", GENERAL_PROPS);

    // Weapons and Tools
    public static final ItemObject<Item> USELESS_SHEARS = ITEMS.register("useless_shears", () -> new ShearsItem(new Item.Properties().tab(UselessMod.TAB).durability(320)));
    public static final ItemObject<Item> USELESS_SWORD = ITEMS.register("useless_sword", () -> new SwordItem(ModTiers.USELESS, 3, -2.4F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_SHOVEL = ITEMS.register("useless_shovel", () -> new ShovelItem(ModTiers.USELESS, 1.5F, -3.0F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_PICKAXE = ITEMS.register("useless_pickaxe", () -> new PickaxeItem(ModTiers.USELESS, 1, -2.8F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_AXE = ITEMS.register("useless_axe", () -> new AxeItem(ModTiers.USELESS, 6.0F, -3.1F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_HOE = ITEMS.register("useless_hoe", () -> new HoeItem(ModTiers.USELESS, -2, -1.0F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_SWORD = ITEMS.register("super_useless_sword", () -> new SwordItem(ModTiers.SUPER_USELESS, 3, -2.4F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_SHOVEL = ITEMS.register("super_useless_shovel", () -> new ShovelItem(ModTiers.SUPER_USELESS, 1.5F, -3.0F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_PICKAXE = ITEMS.register("super_useless_pickaxe", () -> new PickaxeItem(ModTiers.SUPER_USELESS, 1, -2.8F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_AXE = ITEMS.register("super_useless_axe", () -> new AxeItem(ModTiers.SUPER_USELESS, 6.0F, -3.1F, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_HOE = ITEMS.register("super_useless_hoe", () -> new HoeItem(ModTiers.SUPER_USELESS, -2, -1.0F, new Item.Properties().tab(UselessMod.TAB)));

    // Armor
    public static final ItemObject<Item> USELESS_ELYTRA = ITEMS.register("useless_elytra", () -> new UselessElytraItem(new Item.Properties().tab(UselessMod.TAB).durability(500).rarity(Rarity.UNCOMMON)));
    public static final ItemObject<Item> USELESS_HELMET = ITEMS.register("useless_helmet", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.HEAD, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_CHESTPLATE = ITEMS.register("useless_chestplate", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.CHEST, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_LEGGINGS = ITEMS.register("useless_leggings", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.LEGS, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> USELESS_BOOTS = ITEMS.register("useless_boots", () -> new ArmorItem(ModArmorMaterials.USELESS, EquipmentSlot.FEET, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_ELYTRA = ITEMS.register("super_useless_elytra", () -> new UselessElytraItem(new Item.Properties().tab(UselessMod.TAB).durability(500).rarity(Rarity.UNCOMMON)));
    public static final ItemObject<Item> SUPER_USELESS_HELMET = ITEMS.register("super_useless_helmet", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, EquipmentSlot.HEAD, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_CHESTPLATE = ITEMS.register("super_useless_chestplate", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, EquipmentSlot.CHEST, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_LEGGINGS = ITEMS.register("super_useless_leggings", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, EquipmentSlot.LEGS, new Item.Properties().tab(UselessMod.TAB)));
    public static final ItemObject<Item> SUPER_USELESS_BOOTS = ITEMS.register("super_useless_boots", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, EquipmentSlot.FEET, new Item.Properties().tab(UselessMod.TAB)));

    public static final ItemObject<Item> USELESS_SKELETON_SKULL = ITEMS.register("useless_skeleton_skull", () -> new StandingAndWallBlockItem(ModBlocks.USELESS_SKELETON_SKULL.get(), ModBlocks.USELESS_SKELETON_WALL_SKULL.get(), new Item.Properties().tab(UselessMod.TAB).rarity(Rarity.UNCOMMON)));

    // Misc Items
    public static final ItemObject<Item> USELESS_WHEAT_SEEDS = ITEMS.register("useless_wheat_seeds", () -> new ItemNameBlockItem(ModBlocks.USELESS_WHEAT.get(), GENERAL_PROPS));
    public static final ItemObject<Item> USELESS_WHEAT = ITEMS.register("useless_wheat", GENERAL_PROPS);
    public static final ItemObject<Item> COFFEE_SEEDS = ITEMS.register("coffee_seeds", () -> new ItemNameBlockItem(ModBlocks.COFFEE_BEANS.get(), GENERAL_PROPS));
    public static final ItemObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_BONE = ITEMS.register("useless_bone", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_LEATHER = ITEMS.register("useless_leather", GENERAL_PROPS);
    public static final ItemObject<Item> USELESS_FEATHER = ITEMS.register("useless_feather", GENERAL_PROPS);

    // Color
    public static final ItemObject<Item> PAINT_BRUSH = ITEMS.register("paint_brush", () -> new PaintBrushItem(GENERAL_PROPS.durability(16)));
}
