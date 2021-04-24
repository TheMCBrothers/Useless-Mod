package themcbros.uselessmod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.mantle.registration.object.FluidObject;
import themcbros.uselessmod.UselessMod;
import themcbros.uselessmod.api.coffee.CoffeeType;
import themcbros.uselessmod.api.coffee.CoffeeTypeInit;
import themcbros.uselessmod.block.LampBlock;
import themcbros.uselessmod.color.ColorModule;
import themcbros.uselessmod.init.*;

import java.util.function.Supplier;

/**
 * @author TheMCBrothers
 */
public class UselessEnglishLanguageProvider extends LanguageProvider {
    public UselessEnglishLanguageProvider(DataGenerator gen) {
        super(gen, UselessMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // --- BLOCKS --- //

        // METAL
        this.addBlock(BlockInit.USELESS_BLOCK, "Block of Useless");
        this.addBlock(BlockInit.SUPER_USELESS_BLOCK, "Block of Super-Useless");
        this.addBlock(BlockInit.USELESS_ORE, "Useless Ore");
        this.addBlock(BlockInit.USELESS_ORE_NETHER, "Nether Useless Ore");
        this.addBlock(BlockInit.USELESS_ORE_END, "End Useless Ore");
        this.addBlock(BlockInit.SUPER_USELESS_ORE, "Super-Useless Ore");
        this.addBlock(BlockInit.SUPER_USELESS_ORE_NETHER, "Nether Super-Useless Ore");
        this.addBlock(BlockInit.SUPER_USELESS_ORE_END, "End Super-Useless Ore");
        // NATURAL
        this.addBlock(BlockInit.RED_ROSE, "Red Rose");
        this.addBlock(BlockInit.BLUE_ROSE, "Blue Rose");
        this.addBlock(BlockInit.USELESS_ROSE, "Useless Rose");
        this.addBlock(BlockInit.USELESS_SAPLING, "Useless Sapling");
        this.addBlock(BlockInit.USELESS_LEAVES, "Useless Leaves");
        this.addBlock(BlockInit.USELESS_LOG, "Useless Log");
        this.addBlock(BlockInit.USELESS_WOOD, "Useless Wood");
        this.addBlock(BlockInit.STRIPPED_USELESS_LOG, "Stripped Useless Log");
        this.addBlock(BlockInit.STRIPPED_USELESS_WOOD, "Stripped Useless Wood");
        this.addBlock(BlockInit.USELESS_PLANKS, "Useless Planks");
        this.addBlock(BlockInit.USELESS_STAIRS, "Useless Stairs");
        this.addBlock(BlockInit.USELESS_SLAB, "Useless Slab");
        this.addBlock(BlockInit.USELESS_FENCE, "Useless Fence");
        this.addBlock(BlockInit.USELESS_FENCE_GATE, "Useless Fence Gate");
        this.addBlock(BlockInit.WOODEN_USELESS_DOOR, "Wooden Useless Door");
        this.addBlock(BlockInit.WOODEN_USELESS_TRAPDOOR, "Wooden Useless Trapdoor");
        this.addBlock(BlockInit.USELESS_PRESSURE_PLATE, "Useless Pressure Plate");
        this.addBlock(BlockInit.USELESS_BUTTON, "Useless Button");
        // other
        this.addBlock(BlockInit.MACHINE_FRAME, "Machine Frame");
        this.addBlock(BlockInit.CREATIVE_ENERGY_CELL, "Creative Energy Cell");
        this.addBlock(BlockInit.USELESS_GENERATOR, "Useless Generator");
        this.addBlock(BlockInit.COFFEE_MACHINE, "Coffee Machine");
        this.addBlock(BlockInit.COFFEE_MACHINE_SUPPLIER, "Coffee Machine Supplier");
        this.addBlock(BlockInit.USELESS_WOOL, "Useless Wool");
        this.addBlock(BlockInit.USELESS_CARPET, "Useless Carpet");
        this.addBlock(BlockInit.USELESS_BED, "Useless Bed");
        // decorative
        this.addBlock(BlockInit.USELESS_STONE, "Useless Stone");
        this.addBlock(BlockInit.USELESS_COBBLESTONE, "Useless Cobblestone");
        this.addBlock(BlockInit.USELESS_STONE_STAIRS, "Useless Stone Stairs");
        this.addBlock(BlockInit.USELESS_COBBLESTONE_STAIRS, "Useless Cobblestone Stairs");
        this.addBlock(BlockInit.USELESS_STONE_SLAB, "Useless Stone Slab");
        this.addBlock(BlockInit.USELESS_COBBLESTONE_SLAB, "Useless Cobblestone Slab");
        this.addBlock(BlockInit.USELESS_COBBLESTONE_WALL, "Useless Cobblestone Wall");
        this.addBlock(BlockInit.USELESS_STONE_PRESSURE_PLATE, "Useless Stone Pressure Plate");
        this.addBlock(BlockInit.USELESS_STONE_BUTTON, "Useless Stone Button");
        // other misc
        this.addBlock(BlockInit.USELESS_RAIL, "Useless Rail");
        this.addBlock(BlockInit.USELESS_ACTIVATOR_RAIL, "Useless Activator Rail");
        this.addBlock(BlockInit.POWERED_USELESS_RAIL, "Powered Useless Rail");
        this.addBlock(BlockInit.USELESS_DETECTOR_RAIL, "Useless Detector Rail");
        this.addBlock(BlockInit.USELESS_CROSSOVER_RAIL, "Useless Crossover Rail");
        this.addBlock(BlockInit.WALL_CLOSET, "Wall Closet");
        this.addBlock(BlockInit.USELESS_CHEST, "Useless Chest");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.WHITE), "White Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.ORANGE), "Orange Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.MAGENTA), "Magenta Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.LIGHT_BLUE), "Light Blue Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.YELLOW), "Yellow Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.LIME), "Lime Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.PINK), "Pink Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.GRAY), "Gray Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.LIGHT_GRAY), "Light Gray Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.CYAN), "Cyan Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.PURPLE), "Purple Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.BLUE), "Blue Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.BROWN), "Brown Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.GREEN), "Green Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.RED), "Red Lamp");
        this.add(LampBlock.LAMP_MAP.get(DyeColor.BLACK), "Black Lamp");
        // Color Module
        this.addBlock(BlockInit.PAINT_BUCKET, "Paint Bucket");
        this.addBlock(BlockInit.CANVAS, "Canvas");

        // --- ITEMS --- //

        // BASIC ITEMS
        this.addItem(ItemInit.USELESS_ITEM, "Useless Item");
        this.add(ItemInit.USELESS_ITEM.get().getTranslationKey() + ".mode", "Mode");
        this.addItem(ItemInit.USELESS_DUST, "Useless Dust");
        this.addItem(ItemInit.SUPER_USELESS_DUST, "Super-Useless Dust");
        this.addItem(ItemInit.USELESS_INGOT, "Useless Ingot");
        this.addItem(ItemInit.SUPER_USELESS_INGOT, "Super-Useless Ingot");
        this.addItem(ItemInit.USELESS_NUGGET, "Useless Nugget");
        this.addItem(ItemInit.SUPER_USELESS_NUGGET, "Super-Useless Nugget");
        this.addItem(ItemInit.USELESS_PLATE, "Useless Plate");
        this.addItem(ItemInit.SUPER_USELESS_PLATE, "Super-Useless Plate");
        this.addItem(ItemInit.USELESS_GEAR, "Useless Gear");
        this.addItem(ItemInit.SUPER_USELESS_GEAR, "Super-Useless Gear");
        this.addItem(ItemInit.USELESS_ROD, "Useless Rod");
        this.addItem(ItemInit.SUPER_USELESS_ROD, "Super-Useless Rod");
        this.addItem(ItemInit.USELESS_HORSE_ARMOR, "Useless Horse Armor");
        this.addItem(ItemInit.SUPER_USELESS_HORSE_ARMOR, "Super-Useless Horse Armor");
        this.addItem(ItemInit.USELESS_SHIELD, "Useless Shield");
        this.addItem(ItemInit.SUPER_USELESS_SHIELD, "Super-Useless Shield");
        // TOOLS
        this.addItem(ItemInit.USELESS_WRENCH, "Useless Wrench");
        this.addItem(ItemInit.USELESS_SHEARS, "Useless Shears");
        this.addItem(ItemInit.USELESS_PAXEL, "Useless Paxel");
        this.addItem(ItemInit.SUPER_USELESS_PAXEL, "Super-Useless Paxel");
        this.addItem(ItemInit.USELESS_SWORD, "Useless Sword");
        this.addItem(ItemInit.USELESS_PICKAXE, "Useless Pickaxe");
        this.addItem(ItemInit.USELESS_AXE, "Useless Axe");
        this.addItem(ItemInit.USELESS_SHOVEL, "Useless Shovel");
        this.addItem(ItemInit.USELESS_HOE, "Useless Hoe");
        this.addItem(ItemInit.SUPER_USELESS_SWORD, "Super-Useless Sword");
        this.addItem(ItemInit.SUPER_USELESS_PICKAXE, "Super-Useless Pickaxe");
        this.addItem(ItemInit.SUPER_USELESS_AXE, "Super-Useless Axe");
        this.addItem(ItemInit.SUPER_USELESS_SHOVEL, "Super-Useless Shovel");
        this.addItem(ItemInit.SUPER_USELESS_HOE, "Super-Useless Hoe");
        // Armor
        this.addItem(ItemInit.USELESS_HELMET, "Useless Helmet");
        this.addItem(ItemInit.USELESS_CHESTPLATE, "Useless Chestplate");
        this.addItem(ItemInit.USELESS_LEGGINGS, "Useless Leggings");
        this.addItem(ItemInit.USELESS_BOOTS, "Useless Boots");
        this.addItem(ItemInit.SUPER_USELESS_HELMET, "Super-Useless Helmet");
        this.addItem(ItemInit.SUPER_USELESS_CHESTPLATE, "Super-Useless Chestplate");
        this.addItem(ItemInit.SUPER_USELESS_LEGGINGS, "Super-Useless Leggings");
        this.addItem(ItemInit.SUPER_USELESS_BOOTS, "Super-Useless Boots");
        this.addItem(ItemInit.USELESS_ELYTRA, "Useless Elytra");
        this.addItem(ItemInit.SUPER_USELESS_ELYTRA, "Super-Useless Elytra");
        // Misc
        this.addItem(ItemInit.USELESS_BOAT, "Useless Boat");
        this.addItem(ItemInit.SUPER_USELESS_BOAT, "Super-Useless Boat");
        this.addItem(ItemInit.USELESS_SHEEP_SPAWN_EGG, "Useless Sheep Spawn Egg");
        this.addItem(ItemInit.USELESS_PIG_SPAWN_EGG, "Useless Pig Spawn Egg");
        this.addItem(ItemInit.USELESS_CHICKEN_SPAWN_EGG, "Useless Chicken Spawn Egg");
        this.addItem(ItemInit.USELESS_COW_SPAWN_EGG, "Useless Cow Spawn Egg");
        this.addItem(ItemInit.USELESS_SKELETON_SPAWN_EGG, "Useless Skeleton Spawn Egg");
        this.addItem(ItemInit.USELESS_FLINT_AND_STEEL, "Useless Flint and Steel");
        this.addItem(ItemInit.CUP, "Cup");
        this.addItem(ItemInit.COFFEE_CUP, "Coffee in a Cup");
        this.addItem(ItemInit.USELESS_BUCKET, "Useless Bucket");
        this.add(ItemInit.USELESS_BUCKET.get().getTranslationKey() + ".filled", "Useless %s Bucket");
        // Crops
        this.addItem(ItemInit.USELESS_WHEAT_SEEDS, "Useless Wheat Seeds");
        this.addItem(ItemInit.USELESS_WHEAT, "Useless Wheat");
        this.addItem(ItemInit.COFFEE_SEEDS, "Coffee Seeds");
        this.addItem(ItemInit.COFFEE_BEANS, "Coffee Beans");
        this.addItem(ItemInit.ENDER_SEEDS, "Ender Seeds");
        // Special Block Items
        this.addItem(ItemInit.USELESS_SIGN, "Useless Sign");
        this.addItem(ItemInit.USELESS_SKELETON_SKULL, "Useless Skeleton Skull");
        // Mob Loot
        this.addItem(ItemInit.USELESS_BONE, "Useless Bone");
        this.addItem(ItemInit.USELESS_LEATHER, "Useless Leather");
        this.addItem(ItemInit.USELESS_FEATHER, "Useless Feather");
        this.addItem(ItemInit.USELESS_MUTTON, "Useless Mutton");
        this.addItem(ItemInit.COOKED_USELESS_MUTTON, "Cooked Useless Mutton");
        this.addItem(ItemInit.USELESS_PORKCHOP, "Useless Porkchop");
        this.addItem(ItemInit.COOKED_USELESS_PORKCHOP, "Cooked Useless Porkchop");
        this.addItem(ItemInit.USELESS_CHICKEN, "Useless Chicken");
        this.addItem(ItemInit.COOKED_USELESS_CHICKEN, "Cooked Useless Chicken");
        this.addItem(ItemInit.USELESS_BEEF, "Raw Useless Beef");
        this.addItem(ItemInit.COOKED_USELESS_BEEF, "Useless Steak");
        // Color Module
        this.addItem(ColorModule.PAINT_BRUSH, "Paint Brush");
        this.addItem(ColorModule.BUCKET_PAINT, "Bucket of Paint");
        // Immersive Compat
        this.add("item.uselessmod.wirecoil_uselessmod_useless_wire", "Useless Wire Coil");
        this.add("item.uselessmod.useless_wire", "Useless Wire");
        this.add("item.uselessmod.super_useless_wire", "Super-Useless Wire");

        // --- ENTITIES --- //

        this.addEntityType(EntityInit.USELESS_SHEEP, "Useless Sheep");
        this.addEntityType(EntityInit.USELESS_PIG, "Useless Pig");
        this.addEntityType(EntityInit.USELESS_CHICKEN, "Useless Chicken");
        this.addEntityType(EntityInit.USELESS_COW, "Useless Cow");
        this.addEntityType(EntityInit.USELESS_SKELETON, "Useless Skeleton");

        // --- FLUIDS --- //

        this.addFluid(ColorModule.PAINT, "Paint");
        this.addFluid(FluidInit.USELESS_GAS, "Useless Gas");
        this.addFluid(FluidInit.USELESS_WATER, "Useless Water");

        // --- BIOMES --- //

        this.addBiome(BiomeInit.USELESS_FOREST, "Useless Forest");

        // --- COFFEE TYPES --- //

        this.addCoffeeType(CoffeeTypeInit.BLACK, "Black Coffee");
        this.addCoffeeType(CoffeeTypeInit.MILK, "Milk Coffee");
        this.addCoffeeType(CoffeeTypeInit.SUGAR, "Sugar Coffee");
        this.addCoffeeType(CoffeeTypeInit.MILK_SUGAR, "Milk-Sugar Coffee");

        // --- CONTAINERS --- //

        this.add("container.uselessmod.coffee_machine", "Coffee Machine");
        this.add("container.uselessmod.coffee_machine.use_milk", "Use Milk");
        this.add("container.uselessmod.wall_closet", "Wall Closet");

        // --- ADVANCEMENTS --- //

        this.addAdvancement("root", "Useless Mod", "Not as useless as the name says!");
        this.addAdvancement("useless_ingot", "Acquire Upgraded Hardware", "Smelt a useless ingot");
        this.addAdvancement("useless_armor", "Suit up with Useless Material", "Protect yourself with a piece of useless armor");
        this.addAdvancement("super_useless_ingot", "Is it The Best?", "Smelt a super-useless ingot");
        this.addAdvancement("super_useless_armor", "Cover Me With The Best", "Super-Useless armor saves lives");
        this.addAdvancement("useless_biome", "Useless Forest", "Discover the useless forest biome");
        this.addAdvancement("useless_roses", "Rose Collection", "Collect all roses from the forest");

        // --- ITEM GROUPS --- //

        this.add("itemGroup.uselessmod", "Useless Mod");

        // --- MISC -- //

        this.add("stat.uselessmod.interact_with_coffee_machine", "Interactions with Coffee Machine");
        this.add("death.attack.ender_lily", "%1$s was poked to death by ender seeds");
        this.add("death.attack.ender_lily.player", "%1$s was poked to death by ender seeds whilst trying to escape %2$s");

        this.add("misc.uselessmod.energy", "%s FE");
        this.add("misc.uselessmod.energyWithMax", "%s / %s FE");
        this.add("misc.uselessmod.fluidWithMax", "%s / %s mB");
        this.add("misc.uselessmod.fluidWithMaxName", "%s: %s / %s mB");
        this.add("misc.uselessmod.fluidAmount", "%s mB");
        this.add("misc.uselessmod.empty", "Empty");
        this.add("misc.uselessmod.holdShift", "\u00A77Hold \u00A7e\u00A7oShift \u00A77for Details");

        // Silent Gear
        this.add("material.silentgear.useless", "Useless");
        this.add("material.silentgear.super_useless", "Super-Useless");

        // Mystical Agriculture Crops
        this.add("crop.uselessmod.useless", "Useless");
        this.add("crop.uselessmod.super_useless", "Super-Useless");
        this.add("crop.uselessmod.useless_sheep", "Useless Sheep");
        this.add("crop.uselessmod.useless_pig", "Useless Pig");
        this.add("crop.uselessmod.useless_chicken", "Useless Chicken");
        this.add("crop.uselessmod.useless_cow", "Useless Cow");
        this.add("crop.uselessmod.useless_skeleton", "Useless Skeleton");
    }

    private void addBiome(Supplier<Biome> key, String name) {
        String s = "biome." + String.valueOf(key.get().getRegistryName()).replace(':', '.');
        this.add(s, name);
    }

    private void addCoffeeType(Supplier<? extends CoffeeType> key, String name) {
        this.add(Util.makeTranslationKey("coffee", key.get().getRegistryName()), name);
    }

    private void addFluid(Supplier<? extends Fluid> key, String name) {
        this.add(key.get().getAttributes().getTranslationKey(), name);
    }

    private void addFluid(FluidObject<?> key, String name) {
        this.add(key.getStill().getAttributes().getTranslationKey(), name);
        this.add(key.asItem(), name + " Bucket");
    }

    private void addAdvancement(String name, String title, String description) {
        this.add("advancements." + UselessMod.MOD_ID + "." + name + ".title", title);
        this.add("advancements." + UselessMod.MOD_ID + "." + name + ".description", description);
    }

    @Override
    public String getName() {
        return "Useless Languages: en_us";
    }
}
