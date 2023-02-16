package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModCoffeeTypes;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.init.ModItems;

import java.util.function.Supplier;

public class UselessLanguageProvider extends LanguageProvider {
    public UselessLanguageProvider(DataGenerator gen) {
        super(gen, UselessMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addAdvancement("root", "Useless Mod", "Is this useless?");
        this.addAdvancement("mine_ore", "Useless Material", "Mine some useless ore");
        this.addAdvancement("collect_roses", "All the Roses", "Collect all useless rose types");
        this.addAdvancement("visit_useless_forest", "Another Forest", "Visit the useless forest");

        this.addBlock(ModBlocks.USELESS_ORE, "Useless Ore");
        this.addBlock(ModBlocks.SUPER_USELESS_ORE, "Super-Useless Ore");
        this.addBlock(ModBlocks.DEEPSLATE_USELESS_ORE, "Deepslate Useless Ore");
        this.addBlock(ModBlocks.DEEPSLATE_SUPER_USELESS_ORE, "Deepslate Super-Useless Ore");
        this.addBlock(ModBlocks.NETHER_USELESS_ORE, "Nether Useless Ore");
        this.addBlock(ModBlocks.NETHER_SUPER_USELESS_ORE, "Nether Super-Useless Ore");
        this.addBlock(ModBlocks.END_USELESS_ORE, "End Useless Ore");
        this.addBlock(ModBlocks.END_SUPER_USELESS_ORE, "End Super-Useless Ore");
        this.addBlock(ModBlocks.USELESS_BLOCK, "Block of Useless");
        this.addBlock(ModBlocks.SUPER_USELESS_BLOCK, "Super-Block of Useless");
        this.addBlock(ModBlocks.RAW_USELESS_BLOCK, "Block of Raw Useless");
        this.addBlock(ModBlocks.RAW_SUPER_USELESS_BLOCK, "Block of Raw Super-Useless");
        this.addBlock(ModBlocks.USELESS_OAK_LOG, "Useless Oak Log");
        this.addBlock(ModBlocks.USELESS_OAK_WOOD, "Useless Oak Wood");
        this.addBlock(ModBlocks.STRIPPED_USELESS_OAK_LOG, "Stripped Useless Oak Log");
        this.addBlock(ModBlocks.STRIPPED_USELESS_OAK_WOOD, "Stripped Useless Oak Wood");
        this.addBlock(ModBlocks.USELESS_OAK_SAPLING, "Useless Oak Sapling");
        this.addBlock(ModBlocks.RED_ROSE, "Red Rose");
        this.addBlock(ModBlocks.BLUE_ROSE, "Blue Rose");
        this.addBlock(ModBlocks.USELESS_ROSE, "Useless Rose");
        this.addBlock(ModBlocks.USELESS_OAK_LEAVES, "Useless Oak Leaves");
        this.addBlock(ModBlocks.USELESS_OAK_PLANKS, "Useless Oak Planks");
        this.addBlock(ModBlocks.USELESS_OAK_SLAB, "Useless Oak Slab");
        this.addBlock(ModBlocks.USELESS_OAK_STAIRS, "Useless Oak Stairs");
        this.addBlock(ModBlocks.USELESS_OAK_FENCE, "Useless Oak Fence");
        this.addBlock(ModBlocks.USELESS_OAK_FENCE_GATE, "Useless Oak Fence Gate");
        this.addBlock(ModBlocks.USELESS_OAK_DOOR, "Useless Oak Door");
        this.addBlock(ModBlocks.USELESS_OAK_TRAPDOOR, "Useless Oak Trapdoor");
        this.addBlock(ModBlocks.USELESS_OAK_PRESSURE_PLATE, "Useless Oak Pressure Plate");
        this.addBlock(ModBlocks.USELESS_OAK_BUTTON, "Useless Oak Button");
        this.addBlock(ModBlocks.USELESS_OAK_SIGN, "Useless Oak Sign");
        this.addBlock(ModBlocks.USELESS_RAIL, "Useless Rail");
        this.addBlock(ModBlocks.USELESS_POWERED_RAIL, "Useless Powered Rail");
        this.addBlock(ModBlocks.USELESS_DETECTOR_RAIL, "Useless Detector Rail");
        this.addBlock(ModBlocks.USELESS_ACTIVATOR_RAIL, "Useless Activator Rail");
        this.addBlock(ModBlocks.USELESS_BARS, "Useless Bars");
        this.addBlock(ModBlocks.SUPER_USELESS_BARS, "Super-Useless Bars");
        this.addBlock(ModBlocks.MACHINE_SUPPLIER, "Machine Supplier");
        this.addBlock(ModBlocks.COFFEE_MACHINE, "Coffee Machine");
        this.addBlock(ModBlocks.CUP, "Cup");
        this.addBlock(ModBlocks.CUP_COFFEE, "Cup with Coffee");
        this.addBlock(ModBlocks.WALL_CLOSET, "Wall Closet");
        this.addBlock(ModBlocks.PAINT_BUCKET, "Paint Bucket");
        this.addBlock(ModBlocks.CANVAS, "Canvas");
        this.addBlock(ModBlocks.USELESS_WOOL, "Useless Wool");
        this.addBlock(ModBlocks.USELESS_CARPET, "Useless Carpet");
        this.addBlock(ModBlocks.USELESS_BED, "Useless Bed");
        this.addBlock(ModBlocks.USELESS_SKELETON_SKULL, "Useless Skeleton Skull");
        this.addBlock(ModBlocks.LIGHT_SWITCH, "Light Switch");
        this.addBlock(ModBlocks.LANTERN, "Lantern");
        this.addBlock(ModBlocks.WHITE_LAMP, "White Lamp");
        this.addBlock(ModBlocks.ORANGE_LAMP, "Orange Lamp");
        this.addBlock(ModBlocks.MAGENTA_LAMP, "Magenta Lamp");
        this.addBlock(ModBlocks.LIGHT_BLUE_LAMP, "Light Blue Lamp");
        this.addBlock(ModBlocks.YELLOW_LAMP, "Yellow Lamp");
        this.addBlock(ModBlocks.LIME_LAMP, "Lime Lamp");
        this.addBlock(ModBlocks.PINK_LAMP, "Pink Lamp");
        this.addBlock(ModBlocks.GRAY_LAMP, "Gray Lamp");
        this.addBlock(ModBlocks.LIGHT_GRAY_LAMP, "Light Gray Lamp");
        this.addBlock(ModBlocks.CYAN_LAMP, "Cyan Lamp");
        this.addBlock(ModBlocks.PURPLE_LAMP, "Purple Lamp");
        this.addBlock(ModBlocks.BLUE_LAMP, "Blue Lamp");
        this.addBlock(ModBlocks.BROWN_LAMP, "Brown Lamp");
        this.addBlock(ModBlocks.GREEN_LAMP, "Green Lamp");
        this.addBlock(ModBlocks.RED_LAMP, "Red Lamp");
        this.addBlock(ModBlocks.BLACK_LAMP, "Black Lamp");

        this.addItem(ModItems.RAW_USELESS, "Raw Useless");
        this.addItem(ModItems.RAW_SUPER_USELESS, "Raw Super-Useless");
        this.addItem(ModItems.USELESS_DUST, "Useless Dust");
        this.addItem(ModItems.SUPER_USELESS_DUST, "Super-Useless Dust");
        this.addItem(ModItems.USELESS_INGOT, "Useless Ingot");
        this.addItem(ModItems.SUPER_USELESS_INGOT, "Super-Useless Ingot");
        this.addItem(ModItems.USELESS_NUGGET, "Useless Nugget");
        this.addItem(ModItems.SUPER_USELESS_NUGGET, "Super-Useless Nugget");
        this.addItem(ModItems.USELESS_WRENCH, "Useless Wrench");
        this.addItem(ModItems.USELESS_SHEARS, "Useless Shears");
        this.addItem(ModItems.USELESS_SWORD, "Useless Sword");
        this.addItem(ModItems.SUPER_USELESS_SWORD, "Super-Useless Sword");
        this.addItem(ModItems.USELESS_SHOVEL, "Useless Shovel");
        this.addItem(ModItems.SUPER_USELESS_SHOVEL, "Super-Useless Shovel");
        this.addItem(ModItems.USELESS_PICKAXE, "Useless Pickaxe");
        this.addItem(ModItems.SUPER_USELESS_PICKAXE, "Super-Useless Pickaxe");
        this.addItem(ModItems.USELESS_AXE, "Useless Axe");
        this.addItem(ModItems.SUPER_USELESS_AXE, "Super-Useless Axe");
        this.addItem(ModItems.USELESS_HOE, "Useless Hoe");
        this.addItem(ModItems.SUPER_USELESS_HOE, "Super-Useless Hoe");
        this.addItem(ModItems.USELESS_HELMET, "Useless Helmet");
        this.addItem(ModItems.SUPER_USELESS_HELMET, "Super-Useless Helmet");
        this.addItem(ModItems.USELESS_CHESTPLATE, "Useless Chestplate");
        this.addItem(ModItems.SUPER_USELESS_CHESTPLATE, "Super-Useless Chestplate");
        this.addItem(ModItems.USELESS_LEGGINGS, "Useless Leggings");
        this.addItem(ModItems.SUPER_USELESS_LEGGINGS, "Super-Useless Leggings");
        this.addItem(ModItems.USELESS_BOOTS, "Useless Boots");
        this.addItem(ModItems.SUPER_USELESS_BOOTS, "Super-Useless Boots");
        this.addItem(ModItems.USELESS_ELYTRA, "Useless Elytra");
        this.addItem(ModItems.SUPER_USELESS_ELYTRA, "Super-Useless Elytra");
        this.addItem(ModItems.USELESS_WHEAT_SEEDS, "Useless Wheat Seeds");
        this.addItem(ModItems.USELESS_WHEAT, "Useless Wheat");
        this.addItem(ModItems.COFFEE_SEEDS, "Coffee Seeds");
        this.addItem(ModItems.COFFEE_BEANS, "Coffee Beans");
        this.addItem(ModItems.USELESS_BONE, "Useless Bone");
        this.addItem(ModItems.USELESS_LEATHER, "Useless Leather");
        this.addItem(ModItems.USELESS_FEATHER, "Useless Feather");
        this.addItem(ModItems.PAINT_BRUSH, "Paint Brush");
        this.add("item.uselessmod.useless_sheep_spawn_egg", "Useless Sheep Spawn Egg");
        this.add("item.uselessmod.useless_pig_spawn_egg", "Useless Pig Spawn Egg");
        this.add("item.uselessmod.useless_chicken_spawn_egg", "Useless Chicken Spawn Egg");
        this.add("item.uselessmod.useless_cow_spawn_egg", "Useless Cow Spawn Egg");
        this.add("item.uselessmod.useless_skeleton_spawn_egg", "Useless Skeleton Spawn Egg");

        this.addEntityType(ModEntityTypes.USELESS_SHEEP, "Useless Sheep");
        this.addEntityType(ModEntityTypes.USELESS_PIG, "Useless Pig");
        this.addEntityType(ModEntityTypes.USELESS_CHICKEN, "Useless Chicken");
        this.addEntityType(ModEntityTypes.USELESS_COW, "Useless Cow");
        this.addEntityType(ModEntityTypes.USELESS_SKELETON, "Useless Skeleton");

        this.addCoffeeType(ModCoffeeTypes.BLACK, "Black Coffee");
        this.addCoffeeType(ModCoffeeTypes.MILK, "Milk Coffee");
        this.addCoffeeType(ModCoffeeTypes.SUGAR, "Sugar Coffee");
        this.addCoffeeType(ModCoffeeTypes.MILK_SUGAR, "Milk-Sugar Coffee");

        this.add("container.uselessmod.wall_closet", "Wall Closet");
        this.add("container.uselessmod.coffee_machine", "Coffee Machine");
        this.add("gui.uselessmod.use_milk", "Use Milk");
        this.add("gui.uselessmod.start", "Start");
        this.add("gui.uselessmod.stop", "Stop");
        this.add("misc.uselessmod.color", "Color: %s");
        this.add("misc.uselessmod.energy", "%s FE");
        this.add("misc.uselessmod.energyWithMax", "%s / %s FE");
        this.add("misc.uselessmod.fluidWithMax", "%s / %s mB");
        this.add("misc.uselessmod.fluidWithMaxName", "%s: %s / %s mB");
        this.add("misc.uselessmod.fluidAmount", "%s mB");
        this.add("misc.uselessmod.empty", "Empty");

        this.add("status.uselessmod.light_switch.block_added", "Added Block at [%s] to Light Switch");
        this.add("status.uselessmod.light_switch.block_already_added", "This Block has already been added to this Light Switch");
        this.add("status.uselessmod.light_switch.cleared", "Light Switch Configuration cleared");
        this.add("tooltip.uselessmod.light_switch.clear", "Sneak and right click to clear Configuration");
        this.add("tooltip.uselessmod.hold_shift", "<Hold Shift>");

        this.add("itemGroup.uselessmod", "Useless Mod");
    }

    private void addAdvancement(String id, String title, String description) {
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".title", title);
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".description", description);
    }

    private void addCoffeeType(Supplier<CoffeeType> key, String name) {
        this.add(key.get().getDescriptionId(), name);
    }
}
