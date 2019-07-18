package tk.themcbros.uselessmod.lists;

import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.blocks.*;
import tk.themcbros.uselessmod.items.LampBlockItem;

@ObjectHolder(UselessMod.MOD_ID)
public class ModBlocks {

//	public static final Block USELESS_GRASS_BLOCK = null;
//	public static final Block USELESS_GRASS = null;
//	public static final Block USELESS_DIRT = null;
	
	@ObjectHolder("useless_block")
	public static final Block USELESS_BLOCK = null;
	@ObjectHolder("super_useless_block")
	public static final Block SUPER_USELESS_BLOCK = null;
	@ObjectHolder("useless_bars")
	public static final Block USELESS_BARS = null;
	@ObjectHolder("super_useless_bars")
	public static final Block SUPER_USELESS_BARS = null;
	@ObjectHolder("useless_door")
	public static final Block USELESS_DOOR = null;
	@ObjectHolder("super_useless_door")
	public static final Block SUPER_USELESS_DOOR = null;
	@ObjectHolder("useless_ore")
	public static final Block USELESS_ORE = null;
	@ObjectHolder("useless_ore_nether")
	public static final Block USELESS_ORE_NETHER = null;
	@ObjectHolder("useless_ore_end")
	public static final Block USELESS_ORE_END = null;
	@ObjectHolder("super_useless_ore")
	public static final Block SUPER_USELESS_ORE = null;
	@ObjectHolder("super_useless_ore_nether")
	public static final Block SUPER_USELESS_ORE_NETHER = null;
	@ObjectHolder("super_useless_ore_end")
	public static final Block SUPER_USELESS_ORE_END = null;
	@ObjectHolder("cheese_maker")
	public static final Block CHEESE_MAKER = null;
	@ObjectHolder("lamp")
	public static final Block LAMP = null;
	@ObjectHolder("red_rose")
	public static final Block RED_ROSE = null;
	@ObjectHolder("potted_red_rose")
	public static final Block POTTED_RED_ROSE = null;
	@ObjectHolder("blue_rose")
	public static final Block BLUE_ROSE = null;
	@ObjectHolder("potted_blue_rose")
	public static final Block POTTED_BLUE_ROSE = null;
	@ObjectHolder("useless_wheat")
	public static final Block USELESS_CROPS = null;
	@ObjectHolder("canvas")
	public static final Block CANVAS = null;
	@ObjectHolder("paint_bucket")
	public static final Block PAINT_BUCKET = null;
	
	@ObjectHolder("crusher")
	public static final Block CRUSHER = null;
	@ObjectHolder("electric_crusher")
	public static final Block ELECTRIC_CRUSHER = null;
	@ObjectHolder("electric_furnace")
	public static final Block ELECTRIC_FURNACE = null;
	@ObjectHolder("compressor")
	public static final Block COMPRESSOR = null;
	@ObjectHolder("glowstone_generator")
	public static final Block GLOWSTONE_GENERATOR = null;
	@ObjectHolder("creative_power_block")
	public static final Block CREATIVE_POWER_BLOCK = null;
	@ObjectHolder("closet")
	public static final Block CLOSET = null;
	
	@ObjectHolder("greenstone_wire")
	public static final Block GREENSTONE_WIRE = null;
	
	@ObjectHolder("stripped_oak_coffee_table")
	public static final Block STRIPPED_OAK_COFFEE_TABLE = null;
	
	@ObjectHolder("fload")
	public static final Block FLOAD = null;
	
	// Item Blocks
	
	@ObjectHolder("white_lamp")
	public static final BlockItem WHITE_LAMP = null;
	@ObjectHolder("orange_lamp")
	public static final BlockItem ORANGE_LAMP = null;
	@ObjectHolder("magenta_lamp")
	public static final BlockItem MAGENTA_LAMP = null;
	@ObjectHolder("light_blue_lamp")
	public static final BlockItem LIGHT_BLUE_LAMP = null;
	@ObjectHolder("yellow_lamp")
	public static final BlockItem YELLOW_LAMP = null;
	@ObjectHolder("lime_lamp")
	public static final BlockItem LIME_LAMP = null;
	@ObjectHolder("pink_lamp")
	public static final BlockItem PINK_LAMP = null;
	@ObjectHolder("gray_lamp")
	public static final BlockItem GRAY_LAMP = null;
	@ObjectHolder("light_gray_lamp")
	public static final BlockItem LIGHT_GRAY_LAMP = null;
	@ObjectHolder("cyan_lamp")
	public static final BlockItem CYAN_LAMP = null;
	@ObjectHolder("purple_lamp")
	public static final BlockItem PURPLE_LAMP = null;
	@ObjectHolder("blue_lamp")
	public static final BlockItem BLUE_LAMP = null;
	@ObjectHolder("brown_lamp")
	public static final BlockItem BROWN_LAMP = null;
	@ObjectHolder("green_lamp")
	public static final BlockItem GREEN_LAMP = null;
	@ObjectHolder("red_lamp")
	public static final BlockItem RED_LAMP = null;
	@ObjectHolder("black_lamp")
	public static final BlockItem BLACK_LAMP = null;
	
	@Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registration {
		@SubscribeEvent
		public static void onBlockRegistry(final RegistryEvent.Register<Block> event) {
			IForgeRegistry<Block> registry = event.getRegistry();
			
			registry.register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.USELESS_BLOCK));
			registry.register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f)).setRegistryName(BlockNames.SUPER_USELESS_BLOCK));
			registry.register(new ModPaneBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3f)).setRegistryName(BlockNames.USELESS_BARS));
			registry.register(new ModPaneBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.2f)).setRegistryName(BlockNames.SUPER_USELESS_BARS));
			registry.register(new ModDoorBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(2f)).setRegistryName(BlockNames.USELESS_DOOR));
			registry.register(new ModDoorBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(2.5f)).setRegistryName(BlockNames.SUPER_USELESS_DOOR));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75f)).setRegistryName(BlockNames.USELESS_ORE));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75f)).setRegistryName(BlockNames.USELESS_ORE_NETHER));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75f)).setRegistryName(BlockNames.USELESS_ORE_END));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f)).setRegistryName(BlockNames.SUPER_USELESS_ORE));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f)).setRegistryName(BlockNames.SUPER_USELESS_ORE_NETHER));
			registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f)).setRegistryName(BlockNames.SUPER_USELESS_ORE_END));
			registry.register(new CheeseMakerBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1f)).setRegistryName(BlockNames.CHEESE_MAKER));
			registry.register(new LampBlock(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(1f)).setRegistryName(BlockNames.LAMP));
			registry.register(new FlowerBlock(Effects.SPEED, 6, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)).setRegistryName(BlockNames.RED_ROSE));
			registry.register(new FlowerPotBlock(RED_ROSE, Block.Properties.create(Material.MISCELLANEOUS)).setRegistryName(BlockNames.POTTED_RED_ROSE));
			registry.register(new FlowerBlock(Effects.SPEED, 6, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)).setRegistryName(BlockNames.BLUE_ROSE));
			registry.register(new FlowerPotBlock(BLUE_ROSE, Block.Properties.create(Material.MISCELLANEOUS)).setRegistryName(BlockNames.POTTED_BLUE_ROSE));
			registry.register(new UselessCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)).setRegistryName(BlockNames.USELESS_CROPS));
			registry.register(new CanvasBlock(Block.Properties.create(Material.WOOL).hardnessAndResistance(.5f)).setRegistryName(BlockNames.CANVAS));
			registry.register(new PaintBucketBlock(Block.Properties.create(Material.WOOL).hardnessAndResistance(.4f)).setRegistryName(BlockNames.PAINT_BUCKET));
			registry.register(new CrusherBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.CRUSHER));
			registry.register(new ElectricCrusherBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.ELECTRIC_CRUSHER));
			registry.register(new ElectricFurnaceBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.ELECTRIC_FURNACE));
			registry.register(new CompressorBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.COMPRESSOR));
			registry.register(new GlowstoneGeneratorBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.GLOWSTONE_GENERATOR));
			registry.register(new CreativePowerBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(1.5f)).setRegistryName(BlockNames.CREATIVE_POWER_BLOCK));
			registry.register(new ClosetBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5f)).setRegistryName(BlockNames.CLOSET));
			registry.register(new GreenstoneWireBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()).setRegistryName(BlockNames.GREENSTONE_WIRE));
			registry.register(new CoffeeTableBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5f)).setRegistryName(BlockNames.STRIPPED_OAK_COFFEE_TABLE));
//			registry.register(new ModFlowingFluidBlock(ModFluids.FLOAD, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F)).setRegistryName(FluidNames.FLOAD));
			
			UselessMod.LOGGER.info("Registred blocks");
		}
		@SubscribeEvent
		public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
			IForgeRegistry<Item> registry = event.getRegistry();

			registry.register(new BlockItem(USELESS_BLOCK, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_BLOCK));
			registry.register(new BlockItem(SUPER_USELESS_BLOCK, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_BLOCK));
			registry.register(new BlockItem(USELESS_BARS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_BARS));
			registry.register(new BlockItem(SUPER_USELESS_BARS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_BARS));
			registry.register(new BlockItem(USELESS_DOOR, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_DOOR));
			registry.register(new BlockItem(SUPER_USELESS_DOOR, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_DOOR));
			registry.register(new BlockItem(USELESS_ORE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_ORE));
			registry.register(new BlockItem(USELESS_ORE_NETHER, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_ORE_NETHER));
			registry.register(new BlockItem(USELESS_ORE_END, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.USELESS_ORE_END));
			registry.register(new BlockItem(SUPER_USELESS_ORE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_ORE));
			registry.register(new BlockItem(SUPER_USELESS_ORE_NETHER, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_ORE_NETHER));
			registry.register(new BlockItem(SUPER_USELESS_ORE_END, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.SUPER_USELESS_ORE_END));
			registry.register(new BlockItem(CHEESE_MAKER, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.CHEESE_MAKER));
			registry.register(new BlockItem(RED_ROSE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.RED_ROSE));
			registry.register(new BlockItem(BLUE_ROSE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.BLUE_ROSE));
			registry.register(new BlockItem(CANVAS, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.CANVAS));
			registry.register(new BlockItem(PAINT_BUCKET, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.PAINT_BUCKET));
			
			registry.register(new BlockItem(CRUSHER, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.CRUSHER));
			registry.register(new BlockItem(ELECTRIC_CRUSHER, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.ELECTRIC_CRUSHER));
			registry.register(new BlockItem(ELECTRIC_FURNACE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.ELECTRIC_FURNACE));
			registry.register(new BlockItem(COMPRESSOR, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.COMPRESSOR));
			registry.register(new BlockItem(GLOWSTONE_GENERATOR, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.GLOWSTONE_GENERATOR));	
			registry.register(new BlockItem(CREATIVE_POWER_BLOCK, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.CREATIVE_POWER_BLOCK));
			registry.register(new BlockItem(CLOSET, new Item.Properties().group(ModItemGroups.CLOSET_GROUP)).setRegistryName(BlockNames.CLOSET));
			
			registry.register(new BlockItem(STRIPPED_OAK_COFFEE_TABLE, new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP)).setRegistryName(BlockNames.STRIPPED_OAK_COFFEE_TABLE));

			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.WHITE));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.ORANGE));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.MAGENTA));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.LIGHT_BLUE));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.YELLOW));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.LIME));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.PINK));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.GRAY));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.LIGHT_GRAY));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.CYAN));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.PURPLE));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.BLUE));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.BROWN));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.GREEN));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.RED));
			registry.register(new LampBlockItem(new Item.Properties().group(ModItemGroups.USELESS_ITEM_GROUP), DyeColor.BLACK));
			
			UselessMod.LOGGER.info("Registered item blocks");
		}
	}
	
}
