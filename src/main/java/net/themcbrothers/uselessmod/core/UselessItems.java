package net.themcbrothers.uselessmod.core;

import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.themcbrothers.uselessmod.world.item.BucketWithPaintItem;
import net.themcbrothers.uselessmod.world.item.PaintBrushItem;

import static net.themcbrothers.uselessmod.core.Registration.ITEMS;

// TODO: add new content
public final class UselessItems {
    static void register() {
    }

    // Metal
    public static final DeferredItem<Item> RAW_USELESS = ITEMS.registerSimpleItem("raw_useless");
    public static final DeferredItem<Item> USELESS_DUST = ITEMS.registerSimpleItem("useless_dust");
    public static final DeferredItem<Item> USELESS_INGOT = ITEMS.registerSimpleItem("useless_ingot");
    public static final DeferredItem<Item> USELESS_NUGGET = ITEMS.registerSimpleItem("useless_nugget");
    public static final DeferredItem<Item> RAW_SUPER_USELESS = ITEMS.registerSimpleItem("raw_super_useless");
    public static final DeferredItem<Item> SUPER_USELESS_DUST = ITEMS.registerSimpleItem("super_useless_dust");
    public static final DeferredItem<Item> SUPER_USELESS_INGOT = ITEMS.registerSimpleItem("super_useless_ingot");
    public static final DeferredItem<Item> SUPER_USELESS_NUGGET = ITEMS.registerSimpleItem("super_useless_nugget");

    // Weapons and Tools
    public static final DeferredItem<Item> USELESS_SHEARS = ITEMS.registerItem("useless_shears", ShearsItem::new, props -> props.durability(320));
    public static final DeferredItem<Item> USELESS_SHIELD = ITEMS.registerItem("useless_shield", ShieldItem::new, props -> props.durability(420).repairable(ItemTags.REPAIRS_IRON_ARMOR).equippableUnswappable(EquipmentSlot.OFFHAND)); // TODO: repairable
    public static final DeferredItem<Item> USELESS_SWORD = ITEMS.registerSimpleItem("useless_sword", props -> props.sword(UselessToolMaterials.USELESS, 3, -2.4F));
    public static final DeferredItem<Item> USELESS_SHOVEL = ITEMS.registerItem("useless_shovel", props -> new ShovelItem(UselessToolMaterials.USELESS, 1.5F, -3.0F, props));
    public static final DeferredItem<Item> USELESS_PICKAXE = ITEMS.registerSimpleItem("useless_pickaxe", props -> props.pickaxe(UselessToolMaterials.USELESS, 1, -2.8F));
    public static final DeferredItem<Item> USELESS_AXE = ITEMS.registerItem("useless_axe", props -> new AxeItem(UselessToolMaterials.USELESS, 6.0F, -3.1F, props));
    public static final DeferredItem<Item> USELESS_HOE = ITEMS.registerItem("useless_hoe", props -> new HoeItem(UselessToolMaterials.USELESS, -2, -1.0F, props));
    public static final DeferredItem<Item> SUPER_USELESS_SHIELD = ITEMS.registerItem("super_useless_shield", props -> new ShieldItem(props.durability(640).repairable(ItemTags.REPAIRS_IRON_ARMOR).equippableUnswappable(EquipmentSlot.OFFHAND))); // TODO: repairable
    public static final DeferredItem<Item> SUPER_USELESS_SWORD = ITEMS.registerSimpleItem("super_useless_sword", props -> props.sword(UselessToolMaterials.SUPER_USELESS, 3, -2.4F));
    public static final DeferredItem<Item> SUPER_USELESS_SHOVEL = ITEMS.registerItem("super_useless_shovel", props -> new ShovelItem(UselessToolMaterials.SUPER_USELESS, 1.5F, -3.0F, props));
    public static final DeferredItem<Item> SUPER_USELESS_PICKAXE = ITEMS.registerSimpleItem("super_useless_pickaxe", props -> props.pickaxe(UselessToolMaterials.SUPER_USELESS, 1, -2.8F));
    public static final DeferredItem<Item> SUPER_USELESS_AXE = ITEMS.registerItem("super_useless_axe", props -> new AxeItem(UselessToolMaterials.SUPER_USELESS, 6.0F, -3.1F, props));
    public static final DeferredItem<Item> SUPER_USELESS_HOE = ITEMS.registerItem("super_useless_hoe", props -> new HoeItem(UselessToolMaterials.SUPER_USELESS, -2, -1.0F, props));

    // Armor
    public static final DeferredItem<Item> USELESS_ELYTRA = ITEMS.registerSimpleItem("useless_elytra", props -> props.durability(540).rarity(Rarity.EPIC).component(DataComponents.GLIDER, Unit.INSTANCE).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA).setAsset(EquipmentAssets.ELYTRA).setDamageOnHurt(false).build()).repairable(Items.PHANTOM_MEMBRANE));
    public static final DeferredItem<Item> USELESS_HELMET = ITEMS.registerSimpleItem("useless_helmet", props -> props.humanoidArmor(UselessArmorMaterials.USELESS, ArmorType.HELMET));
    public static final DeferredItem<Item> USELESS_CHESTPLATE = ITEMS.registerSimpleItem("useless_chestplate", props -> props.humanoidArmor(UselessArmorMaterials.USELESS, ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> USELESS_LEGGINGS = ITEMS.registerSimpleItem("useless_leggings", props -> props.humanoidArmor(UselessArmorMaterials.USELESS, ArmorType.LEGGINGS));
    public static final DeferredItem<Item> USELESS_BOOTS = ITEMS.registerSimpleItem("useless_boots", props -> props.humanoidArmor(UselessArmorMaterials.USELESS, ArmorType.BOOTS));
    public static final DeferredItem<Item> SUPER_USELESS_ELYTRA = ITEMS.registerSimpleItem("super_useless_elytra", props -> props.durability(864).rarity(Rarity.EPIC).component(DataComponents.GLIDER, Unit.INSTANCE).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA).setAsset(EquipmentAssets.ELYTRA).setDamageOnHurt(false).build()).repairable(Items.PHANTOM_MEMBRANE));
    public static final DeferredItem<Item> SUPER_USELESS_HELMET = ITEMS.registerSimpleItem("super_useless_helmet", props -> props.humanoidArmor(UselessArmorMaterials.SUPER_USELESS, ArmorType.HELMET));
    public static final DeferredItem<Item> SUPER_USELESS_CHESTPLATE = ITEMS.registerSimpleItem("super_useless_chestplate", props -> props.humanoidArmor(UselessArmorMaterials.SUPER_USELESS, ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> SUPER_USELESS_LEGGINGS = ITEMS.registerSimpleItem("super_useless_leggings", props -> props.humanoidArmor(UselessArmorMaterials.SUPER_USELESS, ArmorType.LEGGINGS));
    public static final DeferredItem<Item> SUPER_USELESS_BOOTS = ITEMS.registerSimpleItem("super_useless_boots", props -> props.humanoidArmor(UselessArmorMaterials.SUPER_USELESS, ArmorType.BOOTS));

    public static final DeferredItem<Item> USELESS_SKELETON_SKULL = ITEMS.registerItem("useless_skeleton_skull", props -> new StandingAndWallBlockItem(UselessBlocks.USELESS_SKELETON_SKULL.get(), UselessBlocks.USELESS_SKELETON_WALL_SKULL.get(), Direction.DOWN, props.rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix()));

    // Misc Items
    public static final DeferredItem<BlockItem> USELESS_WHEAT_SEEDS = ITEMS.registerItem("useless_wheat_seeds", props -> new BlockItem(UselessBlocks.USELESS_WHEAT.get(), props.useItemDescriptionPrefix()));
    public static final DeferredItem<Item> USELESS_WHEAT = ITEMS.registerSimpleItem("useless_wheat");
    public static final DeferredItem<BlockItem> COFFEE_SEEDS = ITEMS.registerItem("coffee_seeds", props -> new BlockItem(UselessBlocks.COFFEE_BEANS.get(), props.useItemDescriptionPrefix()));
    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.registerSimpleItem("coffee_beans");
    public static final DeferredItem<Item> USELESS_BONE = ITEMS.registerSimpleItem("useless_bone");
    public static final DeferredItem<Item> USELESS_LEATHER = ITEMS.registerSimpleItem("useless_leather");
    public static final DeferredItem<Item> USELESS_FEATHER = ITEMS.registerSimpleItem("useless_feather");

    // Color
    public static final DeferredItem<Item> PAINT_BRUSH = ITEMS.registerItem("paint_brush", PaintBrushItem::new, props -> props.durability(16));
    public static final DeferredItem<Item> BUCKET_PAINT = ITEMS.registerItem("bucket_paint", props -> new BucketWithPaintItem(UselessFluids.PAINT.get(), props), props -> props.craftRemainder(Items.BUCKET).stacksTo(1));

    // Spawn Eggs (registered via Entity Type Deferred Register)
    public static final DeferredItem<Item> USELESS_SKELETON_SPAWN_EGG = DeferredItem.createItem(UselessEntityTypes.USELESS_SKELETON.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_CHICKEN_SPAWN_EGG = DeferredItem.createItem(UselessEntityTypes.USELESS_CHICKEN.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_COW_SPAWN_EGG = DeferredItem.createItem(UselessEntityTypes.USELESS_COW.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_PIG_SPAWN_EGG = DeferredItem.createItem(UselessEntityTypes.USELESS_PIG.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_SHEEP_SPAWN_EGG = DeferredItem.createItem(UselessEntityTypes.USELESS_SHEEP.getId().withSuffix("_spawn_egg"));
}
