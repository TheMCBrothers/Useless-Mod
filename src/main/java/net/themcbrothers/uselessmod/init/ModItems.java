package net.themcbrothers.uselessmod.init;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.themcbrothers.uselessmod.world.item.BucketWithPaintItem;
import net.themcbrothers.uselessmod.world.item.PaintBrushItem;
import net.themcbrothers.uselessmod.world.item.UselessElytraItem;
import net.themcbrothers.uselessmod.world.item.UselessShieldItem;

import static net.themcbrothers.uselessmod.init.Registration.ITEMS;

public final class ModItems {
    static void register() {
    }

    private static final Item.Properties GENERAL_PROPS = new Item.Properties();

    // Metal
    public static final DeferredItem<Item> RAW_USELESS = ITEMS.registerSimpleItem("raw_useless", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_DUST = ITEMS.registerSimpleItem("useless_dust", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_INGOT = ITEMS.registerSimpleItem("useless_ingot", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_NUGGET = ITEMS.registerSimpleItem("useless_nugget", GENERAL_PROPS);
    public static final DeferredItem<Item> RAW_SUPER_USELESS = ITEMS.registerSimpleItem("raw_super_useless", GENERAL_PROPS);
    public static final DeferredItem<Item> SUPER_USELESS_DUST = ITEMS.registerSimpleItem("super_useless_dust", GENERAL_PROPS);
    public static final DeferredItem<Item> SUPER_USELESS_INGOT = ITEMS.registerSimpleItem("super_useless_ingot", GENERAL_PROPS);
    public static final DeferredItem<Item> SUPER_USELESS_NUGGET = ITEMS.registerSimpleItem("super_useless_nugget", GENERAL_PROPS);

    // Weapons and Tools
    public static final DeferredItem<Item> USELESS_SHEARS = ITEMS.register("useless_shears", () -> new ShearsItem(new Item.Properties().durability(320)));
    public static final DeferredItem<Item> USELESS_SHIELD = ITEMS.register("useless_shield", () -> new UselessShieldItem(ModTiers.USELESS, new Item.Properties().durability(420)));
    public static final DeferredItem<Item> USELESS_SWORD = ITEMS.register("useless_sword", () -> new SwordItem(ModTiers.USELESS, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_SHOVEL = ITEMS.register("useless_shovel", () -> new ShovelItem(ModTiers.USELESS, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_PICKAXE = ITEMS.register("useless_pickaxe", () -> new PickaxeItem(ModTiers.USELESS, 1, -2.8F, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_AXE = ITEMS.register("useless_axe", () -> new AxeItem(ModTiers.USELESS, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_HOE = ITEMS.register("useless_hoe", () -> new HoeItem(ModTiers.USELESS, -2, -1.0F, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_SHIELD = ITEMS.register("super_useless_shield", () -> new UselessShieldItem(ModTiers.SUPER_USELESS, new Item.Properties().durability(640)));
    public static final DeferredItem<Item> SUPER_USELESS_SWORD = ITEMS.register("super_useless_sword", () -> new SwordItem(ModTiers.SUPER_USELESS, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_SHOVEL = ITEMS.register("super_useless_shovel", () -> new ShovelItem(ModTiers.SUPER_USELESS, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_PICKAXE = ITEMS.register("super_useless_pickaxe", () -> new PickaxeItem(ModTiers.SUPER_USELESS, 1, -2.8F, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_AXE = ITEMS.register("super_useless_axe", () -> new AxeItem(ModTiers.SUPER_USELESS, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_HOE = ITEMS.register("super_useless_hoe", () -> new HoeItem(ModTiers.SUPER_USELESS, -2, -1.0F, new Item.Properties()));

    // Armor
    public static final DeferredItem<Item> USELESS_ELYTRA = ITEMS.register("useless_elytra", () -> new UselessElytraItem(new Item.Properties().durability(540).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> USELESS_HELMET = ITEMS.register("useless_helmet", () -> new ArmorItem(ModArmorMaterials.USELESS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_CHESTPLATE = ITEMS.register("useless_chestplate", () -> new ArmorItem(ModArmorMaterials.USELESS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_LEGGINGS = ITEMS.register("useless_leggings", () -> new ArmorItem(ModArmorMaterials.USELESS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> USELESS_BOOTS = ITEMS.register("useless_boots", () -> new ArmorItem(ModArmorMaterials.USELESS, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_ELYTRA = ITEMS.register("super_useless_elytra", () -> new UselessElytraItem(new Item.Properties().durability(864).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> SUPER_USELESS_HELMET = ITEMS.register("super_useless_helmet", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_CHESTPLATE = ITEMS.register("super_useless_chestplate", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_LEGGINGS = ITEMS.register("super_useless_leggings", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> SUPER_USELESS_BOOTS = ITEMS.register("super_useless_boots", () -> new ArmorItem(ModArmorMaterials.SUPER_USELESS, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final DeferredItem<Item> USELESS_SKELETON_SKULL = ITEMS.register("useless_skeleton_skull", () -> new StandingAndWallBlockItem(ModBlocks.USELESS_SKELETON_SKULL.get(), ModBlocks.USELESS_SKELETON_WALL_SKULL.get(), new Item.Properties().rarity(Rarity.UNCOMMON), Direction.DOWN));

    // Misc Items
    public static final DeferredItem<Item> USELESS_WHEAT_SEEDS = ITEMS.register("useless_wheat_seeds", () -> new ItemNameBlockItem(ModBlocks.USELESS_WHEAT.get(), GENERAL_PROPS));
    public static final DeferredItem<Item> USELESS_WHEAT = ITEMS.registerSimpleItem("useless_wheat", GENERAL_PROPS);
    public static final DeferredItem<Item> COFFEE_SEEDS = ITEMS.register("coffee_seeds", () -> new ItemNameBlockItem(ModBlocks.COFFEE_BEANS.get(), GENERAL_PROPS));
    public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.registerSimpleItem("coffee_beans", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_BONE = ITEMS.registerSimpleItem("useless_bone", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_LEATHER = ITEMS.registerSimpleItem("useless_leather", GENERAL_PROPS);
    public static final DeferredItem<Item> USELESS_FEATHER = ITEMS.registerSimpleItem("useless_feather", GENERAL_PROPS);

    // Color
    public static final DeferredItem<Item> PAINT_BRUSH = ITEMS.register("paint_brush", () -> new PaintBrushItem(new Item.Properties().durability(16)));
    public static final DeferredItem<Item> BUCKET_PAINT = ITEMS.register("bucket_paint", () -> new BucketWithPaintItem(UselessFluids.PAINT, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Spawn Eggs (registered via Entity Type Deferred Register)
    public static final DeferredItem<Item> USELESS_SKELETON_SPAWN_EGG = DeferredItem.createItem(ModEntityTypes.USELESS_SKELETON.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_CHICKEN_SPAWN_EGG = DeferredItem.createItem(ModEntityTypes.USELESS_CHICKEN.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_COW_SPAWN_EGG = DeferredItem.createItem(ModEntityTypes.USELESS_COW.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_PIG_SPAWN_EGG = DeferredItem.createItem(ModEntityTypes.USELESS_PIG.getId().withSuffix("_spawn_egg"));
    public static final DeferredItem<Item> USELESS_SHEEP_SPAWN_EGG = DeferredItem.createItem(ModEntityTypes.USELESS_SHEEP.getId().withSuffix("_spawn_egg"));
}
