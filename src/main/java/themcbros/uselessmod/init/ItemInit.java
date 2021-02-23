package themcbros.uselessmod.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.deferred.ItemDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.client.renderer.tilentity.ISTERProvider;
import themcbros.uselessmod.entity.UselessBoatEntity;
import themcbros.uselessmod.item.*;

@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

    public static final ItemDeferredRegister REGISTER = new ItemDeferredRegister(UselessMod.MOD_ID);

    public static final ItemObject<UselessItemItem> USELESS_ITEM = REGISTER.register("useless_item", UselessItemItem::new);
    public static final ItemObject<Item> USELESS_DUST = REGISTER.register("useless_dust", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_DUST = REGISTER.register("super_useless_dust", SimpleItem::new);
//    public static final ItemObject<Item> USELESS_GOLD_DUST = REGISTER.register("useless_gold_dust", SimpleItem::new);
    public static final ItemObject<Item> USELESS_INGOT = REGISTER.register("useless_ingot", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_INGOT = REGISTER.register("super_useless_ingot", SimpleItem::new);
//    public static final ItemObject<Item> USELESS_GOLD_INGOT = REGISTER.register("useless_gold_ingot", SimpleItem::new);
    public static final ItemObject<Item> USELESS_NUGGET = REGISTER.register("useless_nugget", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_NUGGET = REGISTER.register("super_useless_nugget", SimpleItem::new);
    public static final ItemObject<Item> USELESS_PLATE = REGISTER.register("useless_plate", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_PLATE = REGISTER.register("super_useless_plate", SimpleItem::new);
    public static final ItemObject<Item> USELESS_GEAR = REGISTER.register("useless_gear", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_GEAR = REGISTER.register("super_useless_gear", SimpleItem::new);
    public static final ItemObject<Item> USELESS_ROD = REGISTER.register("useless_rod", SimpleItem::new);
    public static final ItemObject<Item> SUPER_USELESS_ROD = REGISTER.register("super_useless_rod", SimpleItem::new);
    public static final ItemObject<Item> USELESS_HORSE_ARMOR = REGISTER.register("useless_horse_armor",
            () -> new HorseArmorItem(13, UselessMod.rl("textures/entity/horse/armor/horse_armor_useless.png"),
                    new Item.Properties().group(UselessMod.GROUP).maxStackSize(1)));
    public static final ItemObject<Item> SUPER_USELESS_HORSE_ARMOR = REGISTER.register("super_useless_horse_armor",
            () -> new HorseArmorItem(16, UselessMod.rl("textures/entity/horse/armor/horse_armor_super_useless.png"),
                    new Item.Properties().group(UselessMod.GROUP).maxStackSize(1)));
    public static final ItemObject<Item> USELESS_SHIELD = REGISTER.register("useless_shield",
            () -> new UselessShieldItem(new Item.Properties().maxDamage(1000).group(UselessMod.GROUP)));
    public static final ItemObject<Item> SUPER_USELESS_SHIELD = REGISTER.register("super_useless_shield",
            () -> new UselessShieldItem(new Item.Properties().maxDamage(2000).group(UselessMod.GROUP)));

    // TOOLS
    public static final ItemObject<Item> USELESS_WRENCH = REGISTER.register("useless_wrench", WrenchItem::new);
    public static final ItemObject<Item> USELESS_SHEARS = REGISTER.register("useless_shears",
            () -> new ShearsItem(new Item.Properties().group(UselessMod.GROUP).maxDamage(500)));
    public static final ItemObject<Item> USELESS_PAXEL = REGISTER.register("useless_paxel",
            () -> new UselessPaxelItem(ModItemTier.USELESS,7.5F, -1.5F, new Item.Properties().maxDamage(1000).group(UselessMod.GROUP)));
    public static final ItemObject<Item> SUPER_USELESS_PAXEL = REGISTER.register("super_useless_paxel",
            () -> new UselessPaxelItem(ModItemTier.SUPER_USELESS,10F, -1F, new Item.Properties().maxDamage(2000).group(UselessMod.GROUP)));
    public static final ItemObject<SwordItem> USELESS_SWORD = REGISTER.register("useless_sword",
            () -> new SwordItem(ModItemTier.USELESS, 3, -2.4F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<PickaxeItem> USELESS_PICKAXE = REGISTER.register("useless_pickaxe",
            () -> new PickaxeItem(ModItemTier.USELESS, 1, -2.8F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<AxeItem> USELESS_AXE = REGISTER.register("useless_axe",
            () -> new AxeItem(ModItemTier.USELESS, 5.0F, -3.0F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ShovelItem> USELESS_SHOVEL = REGISTER.register("useless_shovel",
            () -> new ShovelItem(ModItemTier.USELESS, 1.5F, -3.0F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<HoeItem> USELESS_HOE = REGISTER.register("useless_hoe",
            () -> new HoeItem(ModItemTier.USELESS, -3, 0.0F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<SwordItem> SUPER_USELESS_SWORD = REGISTER.register("super_useless_sword",
            () -> new SwordItem(ModItemTier.SUPER_USELESS, 3, -2.4F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<PickaxeItem> SUPER_USELESS_PICKAXE = REGISTER.register("super_useless_pickaxe",
            () -> new PickaxeItem(ModItemTier.SUPER_USELESS, 1, -2.8F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<AxeItem> SUPER_USELESS_AXE = REGISTER.register("super_useless_axe",
            () -> new AxeItem(ModItemTier.SUPER_USELESS, 5.0F, -3.0F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ShovelItem> SUPER_USELESS_SHOVEL = REGISTER.register("super_useless_shovel",
            () -> new ShovelItem(ModItemTier.SUPER_USELESS, 1.5F, -3.0F, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<HoeItem> SUPER_USELESS_HOE = REGISTER.register("super_useless_hoe",
            () -> new HoeItem(ModItemTier.SUPER_USELESS, -3, 0.0F, new Item.Properties().group(UselessMod.GROUP)));

    // Armor
    public static final ItemObject<ArmorItem> USELESS_HELMET = REGISTER.register("useless_helmet",
            () -> new ArmorItem(ModArmorMaterial.USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> USELESS_CHESTPLATE = REGISTER.register("useless_chestplate",
            () -> new ArmorItem(ModArmorMaterial.USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> USELESS_LEGGINGS = REGISTER.register("useless_leggings",
            () -> new ArmorItem(ModArmorMaterial.USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> USELESS_BOOTS = REGISTER.register("useless_boots",
            () -> new ArmorItem(ModArmorMaterial.USELESS, EquipmentSlotType.FEET, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> SUPER_USELESS_HELMET = REGISTER.register("super_useless_helmet",
            () -> new ArmorItem(ModArmorMaterial.SUPER_USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> SUPER_USELESS_CHESTPLATE = REGISTER.register("super_useless_chestplate",
            () -> new ArmorItem(ModArmorMaterial.SUPER_USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> SUPER_USELESS_LEGGINGS = REGISTER.register("super_useless_leggings",
            () -> new ArmorItem(ModArmorMaterial.SUPER_USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ArmorItem> SUPER_USELESS_BOOTS = REGISTER.register("super_useless_boots",
            () -> new ArmorItem(ModArmorMaterial.SUPER_USELESS, EquipmentSlotType.FEET, new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<ElytraItem> USELESS_ELYTRA = REGISTER.register("useless_elytra",
            () -> new UselessElytraItem(new Item.Properties().group(UselessMod.GROUP).maxDamage(750).rarity(Rarity.UNCOMMON)));
    public static final ItemObject<ElytraItem> SUPER_USELESS_ELYTRA = REGISTER.register("super_useless_elytra",
            () -> new UselessElytraItem(new Item.Properties().group(UselessMod.GROUP).maxDamage(1000).rarity(Rarity.UNCOMMON)));

    // Misc
    public static final ItemObject<Item> USELESS_BOAT = REGISTER.register("useless_boat",
            () -> new UselessBoatItem(new Item.Properties().maxStackSize(1).group(UselessMod.GROUP), UselessBoatEntity.Type.USELESS));
    public static final ItemObject<Item> SUPER_USELESS_BOAT = REGISTER.register("super_useless_boat",
            () -> new UselessBoatItem(new Item.Properties().maxStackSize(1).group(UselessMod.GROUP), UselessBoatEntity.Type.SUPER_USELESS));
    public static final ItemObject<Item> USELESS_FLINT_AND_STEEL = REGISTER.register("useless_flint_and_steel",
            () -> new UselessFlintAndSteelItem(new Item.Properties().maxDamage(64).group(UselessMod.GROUP)));
    public static final ItemObject<Item> SUGARED_MILK = REGISTER.register("sugared_milk",
            () -> new Item(new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(UselessMod.GROUP)));
    public static final ItemObject<Item> CUP = REGISTER.register("cup", CupBlockItem::new);
    public static final ItemObject<CoffeeCupItem> COFFEE_CUP = REGISTER.register("coffee_cup", CoffeeCupItem::new);
    public static final ItemObject<UselessBucketItem> USELESS_BUCKET = REGISTER.register("useless_bucket",
            () -> new UselessBucketItem(FluidAttributes.BUCKET_VOLUME * 8, new Item.Properties().group(UselessMod.GROUP).maxStackSize(1)));

    // Crops
    public static final ItemObject<Item> USELESS_WHEAT_SEEDS = REGISTER.register("useless_wheat_seeds",
            () -> new BlockNamedItem(BlockInit.USELESS_WHEAT.get(), new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<Item> USELESS_WHEAT = REGISTER.register("useless_wheat", SimpleItem::new);
    public static final ItemObject<Item> COFFEE_SEEDS = REGISTER.register("coffee_seeds",
            () -> new BlockNamedItem(BlockInit.COFFEE_CROP.get(), new Item.Properties().group(UselessMod.GROUP)));
    public static final ItemObject<Item> COFFEE_BEANS = REGISTER.register("coffee_beans", SimpleItem::new);
    public static final ItemObject<Item> ENDER_SEEDS = REGISTER.register("ender_seeds",
            () -> new BlockNamedItem(BlockInit.ENDER_SEEDS.get(), new Item.Properties().group(UselessMod.GROUP)));

    // Special Block Items
    public static final ItemObject<UselessSignItem> USELESS_SIGN = REGISTER.register("useless_sign",
            () -> new UselessSignItem(new Item.Properties().maxStackSize(16).group(UselessMod.GROUP), BlockInit.USELESS_SIGN.get(), BlockInit.USELESS_WALL_SIGN.get()));
    public static final ItemObject<WallOrFloorItem> USELESS_SKELETON_SKULL = REGISTER.register("useless_skeleton_skull",
            () -> new WallOrFloorItem(BlockInit.USELESS_SKELETON_SKULL.get(), BlockInit.USELESS_SKELETON_WALL_SKULL.get(),
                    new Item.Properties().group(UselessMod.GROUP).setISTER(ISTERProvider::useless).rarity(Rarity.UNCOMMON)));

    // Mob loot
    public static final ItemObject<Item> USELESS_BONE = REGISTER.register("useless_bone", SimpleItem::new);
    public static final ItemObject<Item> USELESS_LEATHER = REGISTER.register("useless_leather", SimpleItem::new);
    public static final ItemObject<Item> USELESS_FEATHER = REGISTER.register("useless_feather", SimpleItem::new);
    public static final ItemObject<Item> USELESS_MUTTON = REGISTER.register("useless_mutton",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.MUTTON)));
    public static final ItemObject<Item> COOKED_USELESS_MUTTON = REGISTER.register("cooked_useless_mutton",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.COOKED_MUTTON)));
    public static final ItemObject<Item> USELESS_PORKCHOP = REGISTER.register("useless_porkchop",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.PORKCHOP)));
    public static final ItemObject<Item> COOKED_USELESS_PORKCHOP = REGISTER.register("cooked_useless_porkchop",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.COOKED_PORKCHOP)));
    public static final ItemObject<Item> USELESS_CHICKEN = REGISTER.register("useless_chicken",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.CHICKEN)));
    public static final ItemObject<Item> COOKED_USELESS_CHICKEN = REGISTER.register("cooked_useless_chicken",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.COOKED_CHICKEN)));
    public static final ItemObject<Item> USELESS_BEEF = REGISTER.register("useless_beef",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.BEEF)));
    public static final ItemObject<Item> COOKED_USELESS_BEEF = REGISTER.register("cooked_useless_beef",
            () -> new Item(new Item.Properties().group(UselessMod.GROUP).food(Foods.COOKED_BEEF)));

    // Mob Spawn Eggs
    public static final RegistryObject<SpawnEggItem> USELESS_SHEEP_SPAWN_EGG =
            RegistryObject.of(UselessMod.rl("useless_sheep_spawn_egg"), ForgeRegistries.ITEMS);
    public static final RegistryObject<SpawnEggItem> USELESS_PIG_SPAWN_EGG =
            RegistryObject.of(UselessMod.rl("useless_pig_spawn_egg"), ForgeRegistries.ITEMS);
    public static final RegistryObject<SpawnEggItem> USELESS_CHICKEN_SPAWN_EGG =
            RegistryObject.of(UselessMod.rl("useless_chicken_spawn_egg"), ForgeRegistries.ITEMS);
    public static final RegistryObject<SpawnEggItem> USELESS_COW_SPAWN_EGG =
            RegistryObject.of(UselessMod.rl("useless_cow_spawn_egg"), ForgeRegistries.ITEMS);
    public static final RegistryObject<SpawnEggItem> USELESS_SKELETON_SPAWN_EGG =
            RegistryObject.of(UselessMod.rl("useless_skeleton_spawn_egg"), ForgeRegistries.ITEMS);
}
