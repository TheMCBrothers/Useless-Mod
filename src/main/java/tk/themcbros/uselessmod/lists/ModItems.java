package tk.themcbros.uselessmod.lists;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.items.CoffeeCupItem;
import tk.themcbros.uselessmod.items.GrenadeItem;
import tk.themcbros.uselessmod.items.HammerItem;
import tk.themcbros.uselessmod.items.ItemUselessSoup;
import tk.themcbros.uselessmod.items.ModAxeItem;
import tk.themcbros.uselessmod.items.ModPickaxeItem;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.items.UselessItem;

@ObjectHolder(UselessMod.MOD_ID)
public class ModItems {

	@ObjectHolder("useless_item")
	public static final Item USELESS_ITEM = null;
	@ObjectHolder("useless_ingot")
	public static final Item USELESS_INGOT = null;
	@ObjectHolder("super_useless_ingot")
	public static final Item SUPER_USELESS_INGOT = null;
	@ObjectHolder("useless_nugget")
	public static final Item USELESS_NUGGET = null;
	@ObjectHolder("super_useless_nugget")
	public static final Item SUPER_USELESS_NUGGET = null;
	@ObjectHolder("useless_wheat_seeds")
	public static final Item USELESS_WHEAT_SEEDS = null;
	@ObjectHolder("useless_wheat")
	public static final Item USELESS_WHEAT = null;
	@ObjectHolder("useless_soup")
	public static final Item USELESS_SOUP = null;
	@ObjectHolder("useless_bread")
	public static final Item USELESS_BREAD = null;
	@ObjectHolder("useless_flour")
	public static final Item USELESS_FLOUR = null;
	@ObjectHolder("coffee_seeds")
	public static final Item COFFEE_SEEDS = null;
	@ObjectHolder("coffee_beans")
	public static final Item COFFEE_BEANS = null;
	@ObjectHolder("cup")
	public static final Item CUP = null;
	@ObjectHolder("coffee_cup")
	public static final Item COFFEE_CUP = null;
	@ObjectHolder("iron_dust")
	public static final Item IRON__DUST = null;
	@ObjectHolder("useless_dust")
	public static final Item USELESS_DUST = null;
	@ObjectHolder("super_useless_dust")
	public static final Item SUPER_USELESS_DUST = null;
	@ObjectHolder("coal_dust")
	public static final Item COAL_DUST = null;
	@ObjectHolder("gold_dust")
	public static final Item GOLD_DUST = null;
	@ObjectHolder("emerald_dust")
	public static final Item EMERALD_DUST = null;
	@ObjectHolder("diamond_dust")
	public static final Item DIAMOND_DUST = null;
	@ObjectHolder("iron_plate")
	public static final Item IRON_PLATE = null;
	@ObjectHolder("gold_plate")
	public static final Item GOLD_PLATE = null;
	@ObjectHolder("useless_plate")
	public static final Item USELESS_PLATE = null;
	@ObjectHolder("super_useless_plate")
	public static final Item SUPER_USELESS_PLATE = null;
	@ObjectHolder("machineframe")
	public static final Item MACHINEFRAME = null;

	@ObjectHolder("useless_sword")
	public static final Item USELESS_SWORD = null;
	@ObjectHolder("useless_axe")
	public static final Item USELESS_AXE = null;
	@ObjectHolder("useless_pickaxe")
	public static final Item USELESS_PICKAXE = null;
	@ObjectHolder("useless_shovel")
	public static final Item USELESS_SHOVEL = null;
	@ObjectHolder("useless_hoe")
	public static final Item USELESS_HOE = null;
	@ObjectHolder("super_useless_sword")
	public static final Item SUPER_USELESS_SWORD = null;
	@ObjectHolder("super_useless_axe")
	public static final Item SUPER_USELESS_AXE = null;
	@ObjectHolder("super_useless_pickaxe")
	public static final Item SUPER_USELESS_PICKAXE = null;
	@ObjectHolder("super_useless_shovel")
	public static final Item SUPER_USELESS_SHOVEL = null;
	@ObjectHolder("super_useless_hoe")
	public static final Item SUPER_USELESS_HOE = null;

	@ObjectHolder("useless_helmet")
	public static final Item USELESS_HELMET = null;
	@ObjectHolder("useless_chestplate")
	public static final Item USELESS_CHESTPLATE = null;
	@ObjectHolder("useless_leggings")
	public static final Item USELESS_LEGGINGS = null;
	@ObjectHolder("useless_boots")
	public static final Item USELESS_BOOTS = null;
	@ObjectHolder("super_useless_helmet")
	public static final Item SUPER_USELESS_HELMET = null;
	@ObjectHolder("super_useless_chestplate")
	public static final Item SUPER_USELESS_CHESTPLATE = null;
	@ObjectHolder("super_useless_leggings")
	public static final Item SUPER_USELESS_LEGGINS = null;
	@ObjectHolder("super_useless_boots")
	public static final Item SUPER_USELESS_BOOTS = null;

	@ObjectHolder("hammer")
	public static final Item HAMMER = null;
	@ObjectHolder("paint_brush")
	public static final Item PAINT_BRUSH = null;
	
	@ObjectHolder("greenstone")
	public static final Item GREENSTONE = null;
	
	@ObjectHolder("useless_entity_spawn_egg")
	public static final Item USELESS_ENTITY_SPAWN_EGG = null;
	@ObjectHolder("grenade")
	public static final Item GRENADE = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
			IForgeRegistry<Item> registry = event.getRegistry();
			
			registry.register(new UselessItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_ITEM));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_INGOT));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_INGOT));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_NUGGET));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_NUGGET));
			registry.register(new BlockNamedItem(ModBlocks.USELESS_CROPS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_WHEAT_SEEDS));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_WHEAT));
			registry.register(new ItemUselessSoup(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP).food(FoodList.USELESS_SOUP)).setRegistryName(ItemNames.USELESS_SOUP));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP).food(FoodList.USELESS_BREAD)).setRegistryName(ItemNames.USELESS_BREAD));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_FLOUR));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.COFFEE_SEEDS));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.COFFEE_BEANS));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.CUP));
			registry.register(new CoffeeCupItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP).maxStackSize(1)).setRegistryName(ItemNames.COFFEE_CUP));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.IRON_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.COAL_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.GOLD_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.EMERALD_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.DIAMOND_DUST));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.IRON_PLATE));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.GOLD_PLATE));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_PLATE));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_PLATE));
			registry.register(new Item(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.MACHINEFRAME));

			registry.register(new SwordItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_SWORD));
			registry.register(new ModAxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_AXE));
			registry.register(new ModPickaxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_PICKAXE));
			registry.register(new ShovelItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_SHOVEL));
			registry.register(new HoeItem(ToolMaterialList.useless, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_HOE));
			registry.register(new SwordItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_SWORD));
			registry.register(new ModAxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_AXE));
			registry.register(new ModPickaxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_PICKAXE));
			registry.register(new ShovelItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_SHOVEL));
			registry.register(new HoeItem(ToolMaterialList.super_useless, 1f, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_HOE));
			
			registry.register(new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_HELMET));
			registry.register(new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_CHESTPLATE));
			registry.register(new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_LEGGINGS));
			registry.register(new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.USELESS_BOOTS));
			registry.register(new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_HELMET));
			registry.register(new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_CHESTPLATE));
			registry.register(new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_LEGGINGS));
			registry.register(new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.SUPER_USELESS_BOOTS));
			
			registry.register(new HammerItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)));
			registry.register(new PaintBrushItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP).maxStackSize(1)).setRegistryName(ItemNames.PAINT_BRUSH));
			
			registry.register(new BlockNamedItem(ModBlocks.GREENSTONE_WIRE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.GREENSTONE));
			
			registry.register(ModEntityTypes.registerSpawnEgg("useless_entity_spawn_egg", ModEntityTypes.USELESS_ENTITY, 0x2b8a4a, 0x195c19));
			registry.register(new GrenadeItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(ItemNames.GRENADE));
		
			UselessMod.LOGGER.info("Registred Items");
		}
	}
	
}
