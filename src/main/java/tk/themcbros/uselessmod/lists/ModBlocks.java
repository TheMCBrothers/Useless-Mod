package tk.themcbros.uselessmod.lists;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.blocks.CheeseMakerBlock;
import tk.themcbros.uselessmod.blocks.ClosetBlock;
import tk.themcbros.uselessmod.blocks.CoffeeMachineBlock;
import tk.themcbros.uselessmod.blocks.CoffeeSeedsBlock;
import tk.themcbros.uselessmod.blocks.CoffeeTableBlock;
import tk.themcbros.uselessmod.blocks.ColorableBlock;
import tk.themcbros.uselessmod.blocks.CompressorBlock;
import tk.themcbros.uselessmod.blocks.CreativePowerBlock;
import tk.themcbros.uselessmod.blocks.CrusherBlock;
import tk.themcbros.uselessmod.blocks.ElectricCrusherBlock;
import tk.themcbros.uselessmod.blocks.ElectricFurnaceBlock;
import tk.themcbros.uselessmod.blocks.EnergyCableBlock;
import tk.themcbros.uselessmod.blocks.GlowstoneGeneratorBlock;
import tk.themcbros.uselessmod.blocks.GreenstoneWireBlock;
import tk.themcbros.uselessmod.blocks.LampBlock;
import tk.themcbros.uselessmod.blocks.LightSwitchBlock;
import tk.themcbros.uselessmod.blocks.ModDoorBlock;
import tk.themcbros.uselessmod.blocks.ModPaneBlock;
import tk.themcbros.uselessmod.blocks.PaintBucketBlock;
import tk.themcbros.uselessmod.blocks.UnlitLanternBlock;
import tk.themcbros.uselessmod.blocks.UselessCropsBlock;
import tk.themcbros.uselessmod.blocks.UselessDoublePlantBlock;
import tk.themcbros.uselessmod.blocks.UselessFlowerBlock;
import tk.themcbros.uselessmod.blocks.UselessGrassBlock;
import tk.themcbros.uselessmod.blocks.UselessPressurePlateBlock;
import tk.themcbros.uselessmod.blocks.UselessSaplingBlock;
import tk.themcbros.uselessmod.blocks.UselessStairsBlock;
import tk.themcbros.uselessmod.blocks.UselessTallGrassBlock;
import tk.themcbros.uselessmod.blocks.UselessTrapdoorBlock;
import tk.themcbros.uselessmod.blocks.UselessWoodButtonBlock;
import tk.themcbros.uselessmod.world.feature.UselessTree;

@ObjectHolder(UselessMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Bus.MOD, modid = UselessMod.MOD_ID)
public class ModBlocks {
	
	private static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block USELESS_GRASS_BLOCK = register("useless_grass_block", new UselessGrassBlock(Block.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
	public static final Block USELESS_DIRT = register("useless_dirt", new Block(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
	public static final Block USELESS_GRASS = register("useless_grass", new UselessTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).doesNotBlockMovement()));
	public static final Block USELESS_FERN = register("useless_fern", new UselessTallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).doesNotBlockMovement()));
	public static final Block LARGE_USELESS_FERN = register("large_useless_fern", new UselessDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).doesNotBlockMovement()));
	public static final Block TALL_USELESS_GRASS = register("tall_useless_grass", new UselessDoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).sound(SoundType.PLANT).doesNotBlockMovement()));
	public static final Block POTTED_USELESS_FERN = register("potted_useless_fern", new FlowerPotBlock(USELESS_FERN, Block.Properties.create(Material.MISCELLANEOUS)));
	public static final Block USELESS_LOG = register("useless_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_USELESS_LOG = register("stripped_useless_log", new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
	public static final Block USELESS_WOOD = register("useless_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_USELESS_WOOD = register("stripped_useless_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
	public static final Block USELESS_PLANKS = register("useless_planks", new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block USELESS_SLAB = register("useless_slab", new SlabBlock(Block.Properties.from(USELESS_PLANKS)));
	public static final Block USELESS_STAIRS = register("useless_stairs", new UselessStairsBlock(USELESS_PLANKS.getDefaultState(), Block.Properties.from(USELESS_PLANKS)));
	public static final Block USELESS_SAPLING = register("useless_sapling", new UselessSaplingBlock(new UselessTree(), Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).tickRandomly().doesNotBlockMovement()));
	public static final Block POTTED_USELESS_SAPLING = register("potted_useless_sapling", new FlowerPotBlock(USELESS_SAPLING, Block.Properties.create(Material.MISCELLANEOUS)));
	public static final Block USELESS_LEAVES = register("useless_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT)));
	public static final Block USELESS_FENCE = register("useless_fence", new FenceBlock(Block.Properties.from(USELESS_PLANKS)));
	public static final Block USELESS_SIGN = register("useless_sign", new StandingSignBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
	public static final Block USELESS_WALL_SIGN = register("useless_wall_sign", new WallSignBlock(Block.Properties.from(USELESS_SIGN)));
	public static final Block USELESS_PRESSURE_PLATE = register("useless_pressure_plate", new UselessPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
	public static final Block USELESS_TRAPDOOR = register("useless_trapdoor", new UselessTrapdoorBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)));
	public static final Block USELESS_FENCE_GATE = register("useless_fence_gate", new FenceGateBlock(Block.Properties.from(USELESS_FENCE)));
	public static final Block USELESS_BUTTON = register("useless_button", new UselessWoodButtonBlock(Block.Properties.from(USELESS_PRESSURE_PLATE)));
	public static final Block WOODEN_USELESS_DOOR = register("wooden_useless_door", new ModDoorBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.0F)));
	
	public static final Block USELESS_BLOCK = register("useless_block", new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F, 6.0F)));
	public static final Block SUPER_USELESS_BLOCK = register("super_useless_block", new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F, 6.0F)));
	public static final Block USELESS_BARS = register("useless_bars", new ModPaneBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F, 6.0F)));
	public static final Block SUPER_USELESS_BARS = register("super_useless_bars", new ModPaneBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F, 6.0F)));
	public static final Block USELESS_DOOR = register("useless_door", new ModDoorBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F)));
	public static final Block SUPER_USELESS_DOOR = register("super_useless_door", new ModDoorBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F)));
	public static final Block USELESS_ORE = register("useless_ore", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block USELESS_ORE_NETHER = register("useless_ore_nether", new Block(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block USELESS_ORE_END = register("useless_ore_end", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block SUPER_USELESS_ORE = register("super_useless_ore", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block SUPER_USELESS_ORE_NETHER = register("super_useless_ore_nether", new Block(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block SUPER_USELESS_ORE_END = register("super_useless_ore_end", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F)));
	public static final Block CHEESE_MAKER = register("cheese_maker", new CheeseMakerBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.METAL)));
	public static final Block LAMP = register("lamp", new LampBlock(Block.Properties.create(Material.REDSTONE_LIGHT).sound(SoundType.GLASS).hardnessAndResistance(1.2F)));
	public static final Block RED_ROSE = register("red_rose", new UselessFlowerBlock(Effects.SPEED, 6, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)));
	public static final Block BLUE_ROSE = register("blue_rose", new UselessFlowerBlock(Effects.SPEED, 6, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)));
	public static final Block POTTED_RED_ROSE = register("potted_red_rose", new FlowerPotBlock(RED_ROSE, Block.Properties.create(Material.MISCELLANEOUS)));
	public static final Block POTTED_BLUE_ROSE = register("potted_blue_rose", new FlowerPotBlock(BLUE_ROSE, Block.Properties.create(Material.MISCELLANEOUS)));
	public static final Block USELESS_WHEAT = register("useless_wheat", new UselessCropsBlock(Block.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().doesNotBlockMovement(), false));
	public static final Block WILD_USELESS_WHEAT = register("wild_useless_wheat", new UselessCropsBlock(Block.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().doesNotBlockMovement(), true));
	public static final Block COFFEE_SEEDS = register("coffee_seeds", new CoffeeSeedsBlock(Block.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().doesNotBlockMovement(), false));
	public static final Block WILD_COFFEE_SEEDS = register("wild_coffee_seeds", new CoffeeSeedsBlock(Block.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().doesNotBlockMovement(), true));
	public static final Block CANVAS = register("canvas", new ColorableBlock(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(.3F)));
	public static final Block PAINT_BUCKET = register("paint_bucket", new PaintBucketBlock(Block.Properties.create(Material.WOOL).sound(SoundType.METAL).hardnessAndResistance(.25F)));
	
	public static final Block CRUSHER = register("crusher", new CrusherBlock(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).hardnessAndResistance(3.5F)));
	public static final Block ELECTRIC_CRUSHER = register("electric_crusher", new ElectricCrusherBlock(Block.Properties.from(CRUSHER)));
	public static final Block ELECTRIC_FURNACE = register("electric_furnace", new ElectricFurnaceBlock(Block.Properties.from(CRUSHER)));
	public static final Block COMPRESSOR = register("compressor", new CompressorBlock(Block.Properties.from(CRUSHER)));
	public static final Block GLOWSTONE_GENERATOR = register("glowstone_generator", new GlowstoneGeneratorBlock(Block.Properties.from(CRUSHER)));
	public static final Block CREATIVE_POWER_BLOCK = register("creative_power_block", new CreativePowerBlock(Block.Properties.from(CRUSHER)));
	public static final Block COFFEE_MACHINE = register("coffee_machine", new CoffeeMachineBlock(Block.Properties.from(CRUSHER)));
	public static final Block ENERGY_CABLE = register("energy_cable", new EnergyCableBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.125F)));
	public static final Block CLOSET = register("closet", new ClosetBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5F)));
	
	public static final Block GREENSTONE_WIRE = register("greenstone_wire", new GreenstoneWireBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()));
	
	public static final Block STRIPPED_OAK_COFFEE_TABLE = register("stripped_oak_coffee_table", new CoffeeTableBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD)));
	public static final Block LIGHT_SWITCH = register("light_switch", new LightSwitchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.2f)));
	public static final Block UNLIT_LANTERN = register("unlit_lantern", new UnlitLanternBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).lootFrom(Blocks.LANTERN)));
	
	@SubscribeEvent
	public static void blockRegister(final RegistryEvent.Register<Block> event) {
		BLOCKS.forEach(block -> {
			event.getRegistry().register(block);
		});
		UselessMod.LOGGER.debug("Registered useless blocks");
	}
	
	public static Block register(String name, Block block) {
		ResourceLocation location = new ResourceLocation(UselessMod.MOD_ID, name);
		return register(location, block);
	}
	
	public static Block register(ResourceLocation key, Block block) {
		block.setRegistryName(key);
		BLOCKS.add(block);
		return block;
	}
}
