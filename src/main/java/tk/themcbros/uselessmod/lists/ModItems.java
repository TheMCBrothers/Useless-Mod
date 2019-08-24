package tk.themcbros.uselessmod.lists;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.items.CoffeeCupItem;
import tk.themcbros.uselessmod.items.GrenadeItem;
import tk.themcbros.uselessmod.items.HammerItem;
import tk.themcbros.uselessmod.items.LampBlockItem;
import tk.themcbros.uselessmod.items.LightSwitchBlockItem;
import tk.themcbros.uselessmod.items.PaintBrushItem;
import tk.themcbros.uselessmod.items.UpgradeItem;
import tk.themcbros.uselessmod.items.UselessItem;
import tk.themcbros.uselessmod.machine.Upgrade;

@ObjectHolder(UselessMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = UselessMod.MOD_ID)
public class ModItems {
	
	private static final ItemGroup GROUP = ModItemGroups.USELESS_ITEM_GROUP;
	private static final List<Item> ITEMS = new ArrayList<Item>();
	
	// Block Items
	public static final BlockItem USELESS_GRASS_BLOCK = registerBlockItem("useless_grass_block", ModBlocks.USELESS_GRASS_BLOCK);
	public static final BlockItem USELESS_DIRT = registerBlockItem("useless_dirt", ModBlocks.USELESS_DIRT);
	public static final BlockItem USELESS_GRASS = registerBlockItem("useless_grass", new TallBlockItem(ModBlocks.USELESS_GRASS, new Item.Properties().group(GROUP)));
	public static final BlockItem USELESS_FERN = registerBlockItem("useless_fern", new TallBlockItem(ModBlocks.USELESS_FERN, new Item.Properties().group(GROUP)));
	public static final BlockItem LARGE_USELESS_FERN = registerBlockItem("large_useless_fern", new TallBlockItem(ModBlocks.LARGE_USELESS_FERN, new Item.Properties().group(GROUP)));
	public static final BlockItem TALL_USELESS_GRASS = registerBlockItem("tall_useless_grass", new TallBlockItem(ModBlocks.TALL_USELESS_GRASS, new Item.Properties().group(GROUP)));
	public static final BlockItem USELESS_LOG = registerBlockItem("useless_log", ModBlocks.USELESS_LOG);
	public static final BlockItem STRIPPED_USELESS_LOG = registerBlockItem("stripped_useless_log", ModBlocks.STRIPPED_USELESS_LOG);
	public static final BlockItem USELESS_WOOD = registerBlockItem("useless_wood", ModBlocks.USELESS_WOOD);
	public static final BlockItem STRIPPED_USELESS_WOOD = registerBlockItem("stripped_useless_wood", ModBlocks.STRIPPED_USELESS_WOOD);
	public static final BlockItem USELESS_PLANKS = registerBlockItem("useless_planks", ModBlocks.USELESS_PLANKS);
	public static final BlockItem USELESS_SLAB = registerBlockItem("useless_slab", ModBlocks.USELESS_SLAB);
	public static final BlockItem USELESS_STAIRS = registerBlockItem("useless_stairs", ModBlocks.USELESS_STAIRS);
	public static final BlockItem USELESS_SAPLING = registerBlockItem("useless_sapling", new TallBlockItem(ModBlocks.USELESS_SAPLING, new Item.Properties().group(GROUP)));
	public static final BlockItem USELESS_LEAVES = registerBlockItem("useless_leaves", ModBlocks.USELESS_LEAVES);
	public static final BlockItem USELESS_FENCE = registerBlockItem("useless_fence", ModBlocks.USELESS_FENCE);
	public static final BlockItem USELESS_SIGN = registerBlockItem("useless_sign", new SignItem(new Item.Properties().group(GROUP), ModBlocks.USELESS_SIGN, ModBlocks.USELESS_WALL_SIGN));
	public static final BlockItem USELESS_PRESSURE_PLATE = registerBlockItem("useless_pressure_plate", ModBlocks.USELESS_PRESSURE_PLATE);
	public static final BlockItem USELESS_TRAPDOOR = registerBlockItem("useless_trapdoor", ModBlocks.USELESS_TRAPDOOR);
	public static final BlockItem USELESS_FENCE_GATE = registerBlockItem("useless_fence_gate", ModBlocks.USELESS_FENCE_GATE);
	public static final BlockItem USELESS_BUTTON = registerBlockItem("useless_button", ModBlocks.USELESS_BUTTON);
	public static final BlockItem WOODEN_USELESS_DOOR = registerBlockItem("wooden_useless_door", ModBlocks.WOODEN_USELESS_DOOR);
	
	public static final BlockItem USELESS_BLOCK = registerBlockItem("useless_block", ModBlocks.USELESS_BLOCK);
	public static final BlockItem SUPER_USELESS_BLOCK = registerBlockItem("super_useless_block", ModBlocks.SUPER_USELESS_BLOCK);
	public static final BlockItem USELESS_BARS = registerBlockItem("useless_bars", ModBlocks.USELESS_BARS);
	public static final BlockItem SUPER_USELESS_BARS = registerBlockItem("super_useless_bars", ModBlocks.SUPER_USELESS_BARS);
	public static final BlockItem USELESS_DOOR = registerBlockItem("useless_door", ModBlocks.USELESS_DOOR);
	public static final BlockItem SUPER_USELESS_DOOR = registerBlockItem("super_useless_door", ModBlocks.SUPER_USELESS_DOOR);
	public static final BlockItem USELESS_ORE = registerBlockItem("useless_ore", ModBlocks.USELESS_ORE);
	public static final BlockItem USELESS_ORE_NETHER = registerBlockItem("useless_ore_nether", ModBlocks.USELESS_ORE_NETHER);
	public static final BlockItem USELESS_ORE_END = registerBlockItem("useless_ore_end", ModBlocks.USELESS_ORE_END);
	public static final BlockItem SUPER_USELESS_ORE = registerBlockItem("super_useless_ore", ModBlocks.SUPER_USELESS_ORE);
	public static final BlockItem SUPER_USELESS_ORE_NETHER = registerBlockItem("super_useless_ore_nether", ModBlocks.SUPER_USELESS_ORE_NETHER);
	public static final BlockItem SUPER_USELESS_ORE_END = registerBlockItem("super_useless_ore_end", ModBlocks.SUPER_USELESS_ORE_END);
	public static final BlockItem CHEESE_MAKER = registerBlockItem("cheese_maker", ModBlocks.CHEESE_MAKER);
	public static final BlockItem WHITE_LAMP = registerBlockItem("white_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.WHITE));
	public static final BlockItem ORANGE_LAMP = registerBlockItem("orange_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.ORANGE));
	public static final BlockItem MAGENTA_LAMP = registerBlockItem("magenta_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.MAGENTA));
	public static final BlockItem LIGHT_BLUE_LAMP = registerBlockItem("light_blue_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIGHT_BLUE));
	public static final BlockItem YELLOW_LAMP = registerBlockItem("yellow_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.YELLOW));
	public static final BlockItem LIME_LAMP = registerBlockItem("lime_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIME));
	public static final BlockItem PINK_LAMP = registerBlockItem("pink_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.PINK));
	public static final BlockItem GRAY_LAMP = registerBlockItem("gray_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.GRAY));
	public static final BlockItem LIGHT_GRAY_LAMP = registerBlockItem("light_gray_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.LIGHT_GRAY));
	public static final BlockItem CYAN_LAMP = registerBlockItem("cyan_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.CYAN));
	public static final BlockItem PURPLE_LAMP = registerBlockItem("purple_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.PURPLE));
	public static final BlockItem BLUE_LAMP = registerBlockItem("blue_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BLUE));
	public static final BlockItem BROWN_LAMP = registerBlockItem("brown_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BROWN));
	public static final BlockItem GREEN_LAMP = registerBlockItem("green_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.GREEN));
	public static final BlockItem RED_LAMP = registerBlockItem("red_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.RED));
	public static final BlockItem BLACK_LAMP = registerBlockItem("black_lamp", new LampBlockItem(new Item.Properties().group(GROUP), DyeColor.BLACK));
	public static final BlockItem RED_ROSE = registerBlockItem("red_rose", ModBlocks.RED_ROSE);
	public static final BlockItem BLUE_ROSE = registerBlockItem("blue_rose", ModBlocks.BLUE_ROSE);
	public static final BlockItem CANVAS = registerBlockItem("canvas", ModBlocks.CANVAS);
	public static final BlockItem PAINT_BUCKET = registerBlockItem("paint_bucket", ModBlocks.PAINT_BUCKET);
	
	public static final BlockItem CRUSHER = registerBlockItem("crusher", ModBlocks.CRUSHER);
	public static final BlockItem ELECTRIC_CRUSHER = registerBlockItem("electric_crusher", ModBlocks.ELECTRIC_CRUSHER);
	public static final BlockItem ELECTRIC_FURNACE = registerBlockItem("electric_furnace", ModBlocks.ELECTRIC_FURNACE);
	public static final BlockItem COMPRESSOR = registerBlockItem("compressor", ModBlocks.COMPRESSOR);
	public static final BlockItem GLOWSTONE_GENERATOR = registerBlockItem("glowstone_generator", ModBlocks.GLOWSTONE_GENERATOR);
	public static final BlockItem CREATIVE_POWER_BLOCK = registerBlockItem("creative_power_block", ModBlocks.CREATIVE_POWER_BLOCK);
	public static final BlockItem COFFEE_MACHINE = registerBlockItem("coffee_machine", ModBlocks.COFFEE_MACHINE);
	public static final BlockItem ENERGY_CABLE = registerBlockItem("energy_cable", ModBlocks.ENERGY_CABLE);
	public static final BlockItem CLOSET = registerBlockItem("closet", ModBlocks.CLOSET, ModItemGroups.CLOSET_GROUP);
	
	public static final BlockItem STRIPPED_OAK_COFFEE_TABLE = registerBlockItem("stripped_oak_coffee_table", ModBlocks.STRIPPED_OAK_COFFEE_TABLE);
	public static final BlockItem LIGHT_SWITCH = registerBlockItem("light_switch", new LightSwitchBlockItem(ModBlocks.LIGHT_SWITCH, new Item.Properties().group(GROUP)));
	public static final BlockItem LIGHT_SWITCH_BLOCK = registerBlockItem("light_switch_block", new LightSwitchBlockItem(ModBlocks.LIGHT_SWITCH_BLOCK, new Item.Properties().group(GROUP)));
	
	// Items
	public static final Item USELESS_ITEM = registerItem("useless_item", new UselessItem(new Item.Properties().group(GROUP)));
	public static final Item USELESS_INGOT = registerItem("useless_ingot");
	public static final Item SUPER_USELESS_INGOT = registerItem("super_useless_ingot");
	public static final Item USELESS_NUGGET = registerItem("useless_nugget");
	public static final Item SUPER_USELESS_NUGGET = registerItem("super_useless_nugget");
	public static final Item USELESS_WHEAT_SEEDS = registerItem("useless_wheat_seeds", new BlockNamedItem(ModBlocks.USELESS_WHEAT, new Item.Properties().group(GROUP)));
	public static final Item USELESS_WHEAT = registerItem("useless_wheat");
	public static final Item COFFEE_SEEDS = registerItem("coffee_seeds", new BlockNamedItem(ModBlocks.COFFEE_SEEDS, new Item.Properties().group(GROUP)));
	public static final Item COFFEE_BEANS = registerItem("coffee_beans");
	public static final Item USELESS_SOUP = registerItem("useless_soup", new Item(new Item.Properties().food(FoodList.USELESS_SOUP).maxStackSize(1).group(GROUP)));
	public static final Item USELESS_BREAD = registerItem("useless_bread", new Item(new Item.Properties().food(FoodList.USELESS_BREAD).group(GROUP)));
	public static final Item USELESS_FLOUR = registerItem("useless_flour");
	public static final Item SUGARED_MILK = registerItem("sugared_milk", new Item(new Item.Properties().maxStackSize(1).group(GROUP)));
	public static final Item CUP = registerItem("cup");
	public static final Item COFFEE_CUP = registerItem("coffee_cup", new CoffeeCupItem(new Item.Properties().maxStackSize(1).group(GROUP)));
	public static final Item USELESS_STICK = registerItem("useless_stick");
	public static final Item IRON_DUST = registerItem("iron_dust");
	public static final Item USELESS_DUST = registerItem("useless_dust");
	public static final Item SUPER_USELESS_DUST = registerItem("super_useless_dust");
	public static final Item COAL_DUST = registerItem("coal_dust");
	public static final Item GOLD_DUST = registerItem("gold_dust");
	public static final Item EMERALD_DUST = registerItem("emerald_dust");
	public static final Item DIAMOND_DUST = registerItem("diamond_dust");
	public static final Item IRON_PLATE = registerItem("iron_plate");
	public static final Item GOLD_PLATE = registerItem("gold_plate");
	public static final Item USELESS_PLATE = registerItem("useless_plate");
	public static final Item SUPER_USELESS_PLATE = registerItem("super_useless_plate");
	public static final Item MACHINEFRAME = registerItem("machineframe");
	public static final Item BLANK_UPGRADE = registerItem("blank_upgrade", new UpgradeItem(new Item.Properties().group(GROUP), Upgrade.NULL));
	public static final Item SPEED_UPGRADE = registerItem("speed_upgrade", new UpgradeItem(new Item.Properties().group(GROUP), Upgrade.SPEED));

	public static final Item USELESS_SWORD = registerItem("useless_sword", new SwordItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item USELESS_AXE = registerItem("useless_axe", new AxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item USELESS_PICKAXE = registerItem("useless_pickaxe", new PickaxeItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item USELESS_SHOVEL = registerItem("useless_shovel", new ShovelItem(ToolMaterialList.useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item USELESS_HOE = registerItem("useless_hoe", new HoeItem(ToolMaterialList.useless, 1f, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_SWORD = registerItem("super_useless_sword", new SwordItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_AXE = registerItem("super_useless_axe", new AxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_PICKAXE = registerItem("super_useless_pickaxe", new PickaxeItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_SHOVEL = registerItem("super_useless_shovel", new ShovelItem(ToolMaterialList.super_useless, 0, 1f, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_HOE = registerItem("super_useless_hoe", new HoeItem(ToolMaterialList.super_useless, 1f, new Item.Properties().group(GROUP)));

	public static final Item USELESS_HELMET = registerItem("useless_helmet", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(GROUP)));
	public static final Item USELESS_CHESTPLATE = registerItem("useless_chestplate", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(GROUP)));
	public static final Item USELESS_LEGGINGS = registerItem("useless_leggings", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(GROUP)));
	public static final Item USELESS_BOOTS = registerItem("useless_boots", new ArmorItem(ArmorMaterialList.USELESS, EquipmentSlotType.FEET, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_HELMET = registerItem("super_useless_helmet", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.HEAD, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_CHESTPLATE = registerItem("super_useless_chestplate", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.CHEST, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_LEGGINS = registerItem("super_useless_leggings", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.LEGS, new Item.Properties().group(GROUP)));
	public static final Item SUPER_USELESS_BOOTS = registerItem("super_useless_boots", new ArmorItem(ArmorMaterialList.SUPER_USELESS, EquipmentSlotType.FEET, new Item.Properties().group(GROUP)));

	public static final Item HAMMER = registerItem("hammer", new HammerItem(new Item.Properties().group(GROUP)));
	public static final Item PAINT_BRUSH = registerItem("paint_brush", new PaintBrushItem(new Item.Properties().maxStackSize(1).group(GROUP)));
	
	public static final Item GREENSTONE = registerItem("greenstone", new BlockNamedItem(ModBlocks.GREENSTONE_WIRE, new Item.Properties().group(GROUP)));
	
	public static final Item USELESS_ENTITY_SPAWN_EGG = registerItem("useless_cow_spawn_egg", new SpawnEggItem(ModEntityTypes.USELESS_COW, 0x2b8a4a, 0x195c19, new Item.Properties().group(GROUP)));
	public static final Item GRENADE = registerItem("grenade", new GrenadeItem(new Item.Properties().group(GROUP)));
	
	@SubscribeEvent
	public static void onRegister(final RegistryEvent.Register<Item> event) {
		ITEMS.forEach(item -> {
			event.getRegistry().register(item);
		});
		UselessMod.LOGGER.debug("Registered useless items");
	}
	
	public static Item registerItem(String name, Item item) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		item.setRegistryName(location);
		ITEMS.add(item);
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
		ITEMS.add(blockItem);
		return blockItem;
	}
	
	public static BlockItem registerBlockItem(String name, Block block, ItemGroup groupIn) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		BlockItem blockItem = new BlockItem(block, new Item.Properties().group(groupIn));
		blockItem.setRegistryName(location);
		ITEMS.add(blockItem);
		return blockItem;
	}
	
	public static BlockItem registerBlockItem(String name, Block block) {
		return registerBlockItem(name, block, GROUP);
	}
	
}
