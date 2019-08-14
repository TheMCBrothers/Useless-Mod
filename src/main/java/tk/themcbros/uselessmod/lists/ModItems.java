package tk.themcbros.uselessmod.lists;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.items.CoffeeCupItem;
import tk.themcbros.uselessmod.items.GrenadeItem;
import tk.themcbros.uselessmod.items.HammerItem;
import tk.themcbros.uselessmod.items.LampBlockItem;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.items.UselessItem;

@ObjectHolder(UselessMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class ModItems {
	
	private static final ItemGroup GROUP = ModItemGroups.USELESS_ITEM_GROUP;
	
	// Items
	public static Item USELESS_ITEM = null;
	public static Item USELESS_INGOT = null;
	public static Item SUPER_USELESS_INGOT = null;
	public static Item USELESS_NUGGET = null;
	public static Item SUPER_USELESS_NUGGET = null;
	public static Item USELESS_WHEAT_SEEDS = null;
	public static Item USELESS_WHEAT = null;
	public static Item COFFEE_SEEDS = null;
	public static Item COFFEE_BEANS = null;
	public static Item USELESS_SOUP = null;
	public static Item USELESS_BREAD = null;
	public static Item USELESS_FLOUR = null;
	public static Item SUGARED_MILK = null;
	public static Item CUP = null;
	public static Item COFFEE_CUP = null;
	public static Item USELESS_STICK = null;
	public static Item IRON_DUST = null;
	public static Item USELESS_DUST = null;
	public static Item SUPER_USELESS_DUST = null;
	public static Item COAL_DUST = null;
	public static Item GOLD_DUST = null;
	public static Item EMERALD_DUST = null;
	public static Item DIAMOND_DUST = null;
	public static Item IRON_PLATE = null;
	public static Item GOLD_PLATE = null;
	public static Item USELESS_PLATE = null;
	public static Item SUPER_USELESS_PLATE = null;
	public static Item MACHINEFRAME = null;

	public static Item USELESS_SWORD = null;
	public static Item USELESS_AXE = null;
	public static Item USELESS_PICKAXE = null;
	public static Item USELESS_SHOVEL = null;
	public static Item USELESS_HOE = null;
	public static Item SUPER_USELESS_SWORD = null;
	public static Item SUPER_USELESS_AXE = null;
	public static Item SUPER_USELESS_PICKAXE = null;
	public static Item SUPER_USELESS_SHOVEL = null;
	public static Item SUPER_USELESS_HOE = null;
	
	public static Item USELESS_HELMET = null;
	public static Item USELESS_CHESTPLATE = null;
	public static Item USELESS_LEGGINGS = null;
	public static Item USELESS_BOOTS = null;
	public static Item SUPER_USELESS_HELMET = null;
	public static Item SUPER_USELESS_CHESTPLATE = null;
	public static Item SUPER_USELESS_LEGGINS = null;
	public static Item SUPER_USELESS_BOOTS = null;

	public static Item HAMMER = null;
	public static Item PAINT_BRUSH = null;
	
	public static Item GREENSTONE = null;
	
	public static Item USELESS_ENTITY_SPAWN_EGG = null;
	public static Item GRENADE = null;

	// Block Items
	public static BlockItem USELESS_GRASS_BLOCK = null;
	public static BlockItem USELESS_DIRT = null;
	public static BlockItem USELESS_GRASS = null;
	public static BlockItem USELESS_FERN = null;
	public static BlockItem LARGE_USELESS_FERN = null;
	public static BlockItem TALL_USELESS_GRASS = null;
	public static BlockItem USELESS_LOG = null;
	public static BlockItem STRIPPED_USELESS_LOG = null;
	public static BlockItem USELESS_WOOD = null;
	public static BlockItem STRIPPED_USELESS_WOOD = null;
	public static BlockItem USELESS_PLANKS = null;
	public static BlockItem USELESS_SLAB = null;
	public static BlockItem USELESS_STAIRS = null;
	public static BlockItem USELESS_SAPLING = null;
	public static BlockItem USELESS_LEAVES = null;
	public static BlockItem USELESS_FENCE = null;
	public static BlockItem USELESS_SIGN = null;
	public static BlockItem USELESS_PRESSURE_PLATE = null;
	public static BlockItem USELESS_TRAPDOOR = null;
	public static BlockItem USELESS_FENCE_GATE = null;
	public static BlockItem USELESS_BUTTON = null;
	public static BlockItem WOODEN_USELESS_DOOR = null;
	
	public static BlockItem USELESS_BLOCK = null;
	public static BlockItem SUPER_USELESS_BLOCK = null;
	public static BlockItem USELESS_BARS = null;
	public static BlockItem SUPER_USELESS_BARS = null;
	public static BlockItem USELESS_DOOR = null;
	public static BlockItem SUPER_USELESS_DOOR = null;
	public static BlockItem USELESS_ORE = null;
	public static BlockItem USELESS_ORE_NETHER = null;
	public static BlockItem USELESS_ORE_END = null;
	public static BlockItem SUPER_USELESS_ORE = null;
	public static BlockItem SUPER_USELESS_ORE_NETHER = null;
	public static BlockItem SUPER_USELESS_ORE_END = null;
	public static BlockItem CHEESE_MAKER = null;
	public static BlockItem WHITE_LAMP = null;
	public static BlockItem ORANGE_LAMP = null;
	public static BlockItem MAGENTA_LAMP = null;
	public static BlockItem LIGHT_BLUE_LAMP = null;
	public static BlockItem YELLOW_LAMP = null;
	public static BlockItem LIME_LAMP = null;
	public static BlockItem PINK_LAMP = null;
	public static BlockItem GRAY_LAMP = null;
	public static BlockItem LIGHT_GRAY_LAMP = null;
	public static BlockItem CYAN_LAMP = null;
	public static BlockItem PURPLE_LAMP = null;
	public static BlockItem BLUE_LAMP = null;
	public static BlockItem BROWN_LAMP = null;
	public static BlockItem GREEN_LAMP = null;
	public static BlockItem RED_LAMP = null;
	public static BlockItem BLACK_LAMP = null;
	public static BlockItem RED_ROSE = null;
	public static BlockItem BLUE_ROSE = null;
	public static BlockItem CANVAS = null;
	public static BlockItem PAINT_BUCKET = null;
	
	public static BlockItem CRUSHER = null;
	public static BlockItem ELECTRIC_CRUSHER = null;
	public static BlockItem ELECTRIC_FURNACE = null;
	public static BlockItem COMPRESSOR = null;
	public static BlockItem GLOWSTONE_GENERATOR = null;
	public static BlockItem CREATIVE_POWER_BLOCK = null;
	public static BlockItem COFFEE_MACHINE = null;
	public static BlockItem BASIC_ENERGY_CABLE = null;
	public static BlockItem ADVANCED_ENERGY_CABLE = null;
	public static BlockItem CLOSET = null;
	
	public static BlockItem STRIPPED_OAK_COFFEE_TABLE = null;
	
	@SubscribeEvent
	public static void onRegister(final RegistryEvent.Register<Item> event) {
		// Block Items
		USELESS_GRASS_BLOCK = registerBlockItem("useless_grass_block", ModBlocks.USELESS_GRASS_BLOCK);
		USELESS_DIRT = registerBlockItem("useless_dirt", ModBlocks.USELESS_DIRT);
		USELESS_GRASS = registerBlockItem("useless_grass", new TallBlockItem(ModBlocks.USELESS_GRASS, new Item.Properties().group(GROUP)));
		USELESS_FERN = registerBlockItem("useless_fern", new TallBlockItem(ModBlocks.USELESS_FERN, new Item.Properties().group(GROUP)));
		LARGE_USELESS_FERN = registerBlockItem("large_useless_fern", new TallBlockItem(ModBlocks.LARGE_USELESS_FERN, new Item.Properties().group(GROUP)));
		TALL_USELESS_GRASS = registerBlockItem("tall_useless_grass", new TallBlockItem(ModBlocks.TALL_USELESS_GRASS, new Item.Properties().group(GROUP)));
		USELESS_LOG = registerBlockItem("useless_log", ModBlocks.USELESS_LOG);
		STRIPPED_USELESS_LOG = registerBlockItem("stripped_useless_log", ModBlocks.STRIPPED_USELESS_LOG);
		USELESS_WOOD = registerBlockItem("useless_wood", ModBlocks.USELESS_WOOD);
		STRIPPED_USELESS_WOOD = registerBlockItem("stripped_useless_wood", ModBlocks.STRIPPED_USELESS_WOOD);
		USELESS_PLANKS = registerBlockItem("useless_planks", ModBlocks.USELESS_PLANKS);
		USELESS_SLAB = registerBlockItem("useless_slab", ModBlocks.USELESS_SLAB);
		USELESS_STAIRS = registerBlockItem("useless_stairs", ModBlocks.USELESS_STAIRS);
		USELESS_SAPLING = registerBlockItem("useless_sapling", new TallBlockItem(ModBlocks.USELESS_SAPLING, new Item.Properties().group(GROUP)));
		USELESS_LEAVES = registerBlockItem("useless_leaves", ModBlocks.USELESS_LEAVES);
		USELESS_FENCE = registerBlockItem("useless_fence", ModBlocks.USELESS_FENCE);
		USELESS_SIGN = registerBlockItem("useless_sign", new SignItem(new Item.Properties().group(GROUP), ModBlocks.USELESS_SIGN, ModBlocks.USELESS_WALL_SIGN));
		USELESS_PRESSURE_PLATE = registerBlockItem("useless_pressure_plate", ModBlocks.USELESS_PRESSURE_PLATE);
		USELESS_TRAPDOOR = registerBlockItem("useless_trapdoor", ModBlocks.USELESS_TRAPDOOR);
		USELESS_FENCE_GATE = registerBlockItem("useless_fence_gate", ModBlocks.USELESS_FENCE_GATE);
		USELESS_BUTTON = registerBlockItem("useless_button", ModBlocks.USELESS_BUTTON);
		WOODEN_USELESS_DOOR = registerBlockItem("wooden_useless_door", ModBlocks.WOODEN_USELESS_DOOR);
		
		USELESS_BLOCK = registerBlockItem("useless_block", ModBlocks.USELESS_BLOCK);
		SUPER_USELESS_BLOCK = registerBlockItem("super_useless_block", ModBlocks.SUPER_USELESS_BLOCK);
		USELESS_BARS = registerBlockItem("useless_bars", ModBlocks.USELESS_BARS);
		SUPER_USELESS_BARS = registerBlockItem("super_useless_bars", ModBlocks.SUPER_USELESS_BARS);
		USELESS_DOOR = registerBlockItem("useless_door", ModBlocks.USELESS_DOOR);
		SUPER_USELESS_DOOR = registerBlockItem("super_useless_door", ModBlocks.SUPER_USELESS_DOOR);
		USELESS_ORE = registerBlockItem("useless_ore", ModBlocks.USELESS_ORE);
		USELESS_ORE_NETHER = registerBlockItem("useless_ore_nether", ModBlocks.USELESS_ORE_NETHER);
		USELESS_ORE_END = registerBlockItem("useless_ore_end", ModBlocks.USELESS_ORE_END);
		SUPER_USELESS_ORE = registerBlockItem("super_useless_ore", ModBlocks.SUPER_USELESS_ORE);
		SUPER_USELESS_ORE_NETHER = registerBlockItem("super_useless_ore_nether", ModBlocks.SUPER_USELESS_ORE_NETHER);
		SUPER_USELESS_ORE_END = registerBlockItem("super_useless_ore_end", ModBlocks.SUPER_USELESS_ORE_END);
		CHEESE_MAKER = registerBlockItem("cheese_maker", ModBlocks.CHEESE_MAKER);
		WHITE_LAMP = registerBlockItem("white_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.WHITE));
		ORANGE_LAMP = registerBlockItem("orange_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.ORANGE));
		MAGENTA_LAMP = registerBlockItem("magenta_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.MAGENTA));
		LIGHT_BLUE_LAMP = registerBlockItem("light_blue_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIGHT_BLUE));
		YELLOW_LAMP = registerBlockItem("yellow_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.YELLOW));
		LIME_LAMP = registerBlockItem("lime_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIME));
		PINK_LAMP = registerBlockItem("pink_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.PINK));
		GRAY_LAMP = registerBlockItem("gray_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.GRAY));
		LIGHT_GRAY_LAMP = registerBlockItem("light_gray_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIGHT_GRAY));
		CYAN_LAMP = registerBlockItem("cyan_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.CYAN));
		PURPLE_LAMP = registerBlockItem("purple_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.PURPLE));
		BLUE_LAMP = registerBlockItem("blue_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BLUE));
		BROWN_LAMP = registerBlockItem("brown_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BROWN));
		GREEN_LAMP = registerBlockItem("green_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.GREEN));
		RED_LAMP = registerBlockItem("red_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.RED));
		BLACK_LAMP = registerBlockItem("black_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BLACK));
		RED_ROSE = registerBlockItem("red_rose", ModBlocks.RED_ROSE);
		BLUE_ROSE = registerBlockItem("blue_rose", ModBlocks.BLUE_ROSE);
		CANVAS = registerBlockItem("canvas", ModBlocks.CANVAS);
		PAINT_BUCKET = registerBlockItem("paint_bucket", ModBlocks.PAINT_BUCKET);
		
		CRUSHER = registerBlockItem("crusher", ModBlocks.CRUSHER);
		ELECTRIC_CRUSHER = registerBlockItem("electric_crusher", ModBlocks.ELECTRIC_CRUSHER);
		ELECTRIC_FURNACE = registerBlockItem("electric_furnace", ModBlocks.ELECTRIC_FURNACE);
		COMPRESSOR = registerBlockItem("compressor", ModBlocks.COMPRESSOR);
		GLOWSTONE_GENERATOR = registerBlockItem("glowstone_generator", ModBlocks.GLOWSTONE_GENERATOR);
		CREATIVE_POWER_BLOCK = registerBlockItem("creative_power_block", ModBlocks.CREATIVE_POWER_BLOCK);
		COFFEE_MACHINE = registerBlockItem("coffee_machine", ModBlocks.COFFEE_MACHINE);
		BASIC_ENERGY_CABLE = registerBlockItem("basic_energy_cable", ModBlocks.BASIC_ENERGY_CABLE);
		ADVANCED_ENERGY_CABLE = registerBlockItem("advanced_energy_cable", ModBlocks.ADVANCED_ENERGY_CABLE);
		CLOSET = registerBlockItem("closet", ModBlocks.CLOSET, ModItemGroups.CLOSET_GROUP);
		
		STRIPPED_OAK_COFFEE_TABLE = registerBlockItem("stripped_oak_coffee_table", ModBlocks.STRIPPED_OAK_COFFEE_TABLE);
		
		// Items
		USELESS_ITEM = registerItem("useless_item", new UselessItem(new Item.Properties().group(GROUP)));
		USELESS_INGOT = registerItem("useless_ingot");
		SUPER_USELESS_INGOT = registerItem("super_useless_ingot");
		USELESS_NUGGET = registerItem("useless_nugget");
		SUPER_USELESS_NUGGET = registerItem("super_useless_nugget");
		USELESS_WHEAT_SEEDS = registerItem("useless_wheat_seeds", new BlockNamedItem(ModBlocks.USELESS_WHEAT, new Item.Properties().group(GROUP)));
		USELESS_WHEAT = registerItem("useless_wheat");
		COFFEE_SEEDS = registerItem("coffee_seeds", new BlockNamedItem(ModBlocks.COFFEE_SEEDS, new Item.Properties().group(GROUP)));
		COFFEE_BEANS = registerItem("coffee_beans");
		USELESS_SOUP = registerItem("useless_soup", new Item(new Item.Properties().food(FoodList.USELESS_SOUP).maxStackSize(1).group(GROUP)));
		USELESS_BREAD = registerItem("useless_bread", new Item(new Item.Properties().food(FoodList.USELESS_BREAD).group(GROUP)));
		USELESS_FLOUR = registerItem("useless_flour");
		SUGARED_MILK = registerItem("sugared_milk", new Item(new Item.Properties().maxStackSize(1)));
		CUP = registerItem("cup");
		COFFEE_CUP = registerItem("coffee_cup", new CoffeeCupItem(new Item.Properties().maxStackSize(1).group(GROUP)));
		USELESS_STICK = registerItem("useless_stick");
		IRON_DUST = registerItem("iron_dust");
		USELESS_DUST = registerItem("useless_dust");
		SUPER_USELESS_DUST = registerItem("super_useless_dust");
		COAL_DUST = registerItem("coal_dust");
		GOLD_DUST = registerItem("gold_dust");
		EMERALD_DUST = registerItem("emerald_dust");
		DIAMOND_DUST = registerItem("diamond_dust");
		IRON_PLATE = registerItem("iron_plate");
		GOLD_PLATE = registerItem("gold_plate");
		USELESS_PLATE = registerItem("useless_plate");
		SUPER_USELESS_PLATE = registerItem("super_useless_plate");
		MACHINEFRAME = registerItem("machineframe");

		USELESS_SWORD = registerItem("useless_sword", new SwordItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
		USELESS_AXE = registerItem("useless_axe", new AxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
		USELESS_PICKAXE = registerItem("useless_pickaxe", new PickaxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
		USELESS_SHOVEL = registerItem("useless_shovel", new ShovelItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
		USELESS_HOE = registerItem("useless_hoe", new HoeItem(ToolMaterialList.useless, 1f, new Item.Properties().group(GROUP)));
		SUPER_USELESS_SWORD = registerItem("super_useless_sword", new SwordItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
		SUPER_USELESS_AXE = registerItem("super_useless_axe", new AxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
		SUPER_USELESS_PICKAXE = registerItem("super_useless_pickaxe", new PickaxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
		SUPER_USELESS_SHOVEL = registerItem("super_useless_shovel", new ShovelItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
		SUPER_USELESS_HOE = registerItem("super_useless_hoe", new HoeItem(ToolMaterialList.super_useless, 1f, new Item.Properties().group(GROUP)));

		USELESS_HELMET = registerItem("useless_helmet", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(GROUP)));
		USELESS_CHESTPLATE = registerItem("useless_chestplate", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(GROUP)));
		USELESS_LEGGINGS = registerItem("useless_leggings", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(GROUP)));
		USELESS_BOOTS = registerItem("useless_boots", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.FEET, new Item.Properties().group(GROUP)));
		SUPER_USELESS_HELMET = registerItem("super_useless_helmet", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(GROUP)));
		SUPER_USELESS_CHESTPLATE = registerItem("super_useless_chestplate", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(GROUP)));
		SUPER_USELESS_LEGGINS = registerItem("super_useless_leggings", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(GROUP)));
		SUPER_USELESS_BOOTS = registerItem("super_useless_boots", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.FEET, new Item.Properties().group(GROUP)));

		HAMMER = registerItem("hammer", new HammerItem(new Item.Properties().group(GROUP)));
		PAINT_BRUSH = registerItem("paint_brush", new PaintBrushItem(new Item.Properties().maxStackSize(1).group(GROUP)));
		
		GREENSTONE = registerItem("greenstone", new BlockNamedItem(ModBlocks.GREENSTONE_WIRE, new Item.Properties().group(GROUP)));
		
		USELESS_ENTITY_SPAWN_EGG = registerItem("useless_entity_spawn_egg", new SpawnEggItem(ModEntityTypes.USELESS_ENTITY, 0x2b8a4a, 0x195c19, new Item.Properties().group(GROUP)));
		GRENADE = registerItem("grenade", new GrenadeItem(new Item.Properties().group(GROUP)));
	}
	
	public static Item registerItem(String name, Item item) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		item.setRegistryName(location);
		ForgeRegistries.ITEMS.register(item);
		UselessMod.LOGGER.debug("Registered item with registry name: " + location);
		return item;
	}
	
	public static Item registerItem(String name, ItemGroup group) {
		Item item = new Item(new Item.Properties().group(group));
		return registerItem(name, item);
	}
	
	public static Item registerItem(String name) {
		return registerItem(name, GROUP);
	}
	
	// Block Items
	
	public static BlockItem registerBlockItem(String name, BlockItem blockItem) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		blockItem.setRegistryName(location);
		ForgeRegistries.ITEMS.register(blockItem);
		UselessMod.LOGGER.debug("Registered item with registry name: " + location);
		return blockItem;
	}
	
	public static BlockItem registerBlockItem(String name, Block block, ItemGroup groupIn) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		BlockItem blockItem = new BlockItem(block, new Item.Properties().group(groupIn));
		blockItem.setRegistryName(location);
		ForgeRegistries.ITEMS.register(blockItem);
		UselessMod.LOGGER.debug("Registered item with registry name: " + location);
		return blockItem;
	}
	
	public static BlockItem registerBlockItem(String name, Block block) {
		return registerBlockItem(name, block, GROUP);
	}
	
}
