package net.themcbrothers.uselessmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.core.*;

import java.util.function.Supplier;

public class UselessLanguageProvider extends LanguageProvider {
    public UselessLanguageProvider(PackOutput packOutput) {
        super(packOutput, UselessMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addAdvancement("root", "Useless Mod", "Is this useless?");
        this.addAdvancement("mine_ore", "Useless Material", "Mine some useless ore");
        this.addAdvancement("collect_roses", "All the Roses", "Collect all useless rose types");
        this.addAdvancement("visit_useless_forest", "Another Forest", "Visit the useless forest");

        this.addBlock(UselessBlocks.USELESS_ORE, "Useless Ore");
        this.addBlock(UselessBlocks.SUPER_USELESS_ORE, "Super-Useless Ore");
        this.addBlock(UselessBlocks.DEEPSLATE_USELESS_ORE, "Deepslate Useless Ore");
        this.addBlock(UselessBlocks.DEEPSLATE_SUPER_USELESS_ORE, "Deepslate Super-Useless Ore");
        this.addBlock(UselessBlocks.NETHER_USELESS_ORE, "Nether Useless Ore");
        this.addBlock(UselessBlocks.NETHER_SUPER_USELESS_ORE, "Nether Super-Useless Ore");
        this.addBlock(UselessBlocks.END_USELESS_ORE, "End Useless Ore");
        this.addBlock(UselessBlocks.END_SUPER_USELESS_ORE, "End Super-Useless Ore");
        this.addBlock(UselessBlocks.USELESS_BLOCK, "Block of Useless");
        this.addBlock(UselessBlocks.SUPER_USELESS_BLOCK, "Super-Block of Useless");
        this.addBlock(UselessBlocks.RAW_USELESS_BLOCK, "Block of Raw Useless");
        this.addBlock(UselessBlocks.RAW_SUPER_USELESS_BLOCK, "Block of Raw Super-Useless");
        this.addBlock(UselessBlocks.USELESS_OAK_LOG, "Useless Oak Log");
        this.addBlock(UselessBlocks.USELESS_OAK_WOOD, "Useless Oak Wood");
        this.addBlock(UselessBlocks.STRIPPED_USELESS_OAK_LOG, "Stripped Useless Oak Log");
        this.addBlock(UselessBlocks.STRIPPED_USELESS_OAK_WOOD, "Stripped Useless Oak Wood");
        this.addBlock(UselessBlocks.USELESS_OAK_SAPLING, "Useless Oak Sapling");
        this.addBlock(UselessBlocks.RED_ROSE, "Red Rose");
        this.addBlock(UselessBlocks.BLUE_ROSE, "Blue Rose");
        this.addBlock(UselessBlocks.USELESS_ROSE, "Useless Rose");
        this.addBlock(UselessBlocks.USELESS_OAK_LEAVES, "Useless Oak Leaves");
        this.addBlock(UselessBlocks.USELESS_OAK_PLANKS, "Useless Oak Planks");
        this.addBlock(UselessBlocks.USELESS_OAK_SLAB, "Useless Oak Slab");
        this.addBlock(UselessBlocks.USELESS_OAK_STAIRS, "Useless Oak Stairs");
        this.addBlock(UselessBlocks.USELESS_OAK_FENCE, "Useless Oak Fence");
        this.addBlock(UselessBlocks.USELESS_OAK_FENCE_GATE, "Useless Oak Fence Gate");
        this.addBlock(UselessBlocks.USELESS_OAK_DOOR, "Useless Oak Door");
        this.addBlock(UselessBlocks.USELESS_OAK_TRAPDOOR, "Useless Oak Trapdoor");
        this.addBlock(UselessBlocks.USELESS_OAK_PRESSURE_PLATE, "Useless Oak Pressure Plate");
        this.addBlock(UselessBlocks.USELESS_OAK_BUTTON, "Useless Oak Button");
        this.addBlock(UselessBlocks.USELESS_OAK_SIGN, "Useless Oak Sign");
        this.addBlock(UselessBlocks.USELESS_OAK_HANGING_SIGN, "Useless Oak Hanging Sign");
        this.addBlock(UselessBlocks.USELESS_RAIL, "Useless Rail");
        this.addBlock(UselessBlocks.USELESS_POWERED_RAIL, "Useless Powered Rail");
        this.addBlock(UselessBlocks.USELESS_DETECTOR_RAIL, "Useless Detector Rail");
        this.addBlock(UselessBlocks.USELESS_ACTIVATOR_RAIL, "Useless Activator Rail");
        this.addBlock(UselessBlocks.USELESS_BARS, "Useless Bars");
        this.addBlock(UselessBlocks.SUPER_USELESS_BARS, "Super-Useless Bars");
        this.addBlock(UselessBlocks.USELESS_DOOR, "Useless Door");
        this.addBlock(UselessBlocks.SUPER_USELESS_DOOR, "Super-Useless Door");
        this.addBlock(UselessBlocks.USELESS_TRAPDOOR, "Useless Trapdoor");
        this.addBlock(UselessBlocks.SUPER_USELESS_TRAPDOOR, "Super-Useless Trapdoor");
        this.addBlock(UselessBlocks.MACHINE_SUPPLIER, "Machine Supplier");
        this.addBlock(UselessBlocks.COFFEE_MACHINE, "Coffee Machine");
        this.addBlock(UselessBlocks.CUP, "Cup");
        this.addBlock(UselessBlocks.CUP_COFFEE, "Cup with Coffee");
        this.addBlock(UselessBlocks.WALL_CLOSET, "Wall Closet");
        this.addBlock(UselessBlocks.PAINT_BUCKET, "Paint Bucket");
        this.addBlock(UselessBlocks.PAINTED_WOOL, "Painted Wool");
        this.addBlock(UselessBlocks.USELESS_WOOL, "Useless Wool");
        this.addBlock(UselessBlocks.USELESS_CARPET, "Useless Carpet");
        this.addBlock(UselessBlocks.USELESS_BED, "Useless Bed");
        this.addBlock(UselessBlocks.USELESS_SKELETON_SKULL, "Useless Skeleton Skull");
        this.addBlock(UselessBlocks.LIGHT_SWITCH, "Light Switch");
        this.addBlock(UselessBlocks.LIGHT_SWITCH_BLOCK, "Light Switch Block");
        this.addBlock(UselessBlocks.LANTERN, "Lantern");
        this.addBlock(UselessBlocks.WHITE_LAMP, "White Lamp");
        this.addBlock(UselessBlocks.ORANGE_LAMP, "Orange Lamp");
        this.addBlock(UselessBlocks.MAGENTA_LAMP, "Magenta Lamp");
        this.addBlock(UselessBlocks.LIGHT_BLUE_LAMP, "Light Blue Lamp");
        this.addBlock(UselessBlocks.YELLOW_LAMP, "Yellow Lamp");
        this.addBlock(UselessBlocks.LIME_LAMP, "Lime Lamp");
        this.addBlock(UselessBlocks.PINK_LAMP, "Pink Lamp");
        this.addBlock(UselessBlocks.GRAY_LAMP, "Gray Lamp");
        this.addBlock(UselessBlocks.LIGHT_GRAY_LAMP, "Light Gray Lamp");
        this.addBlock(UselessBlocks.CYAN_LAMP, "Cyan Lamp");
        this.addBlock(UselessBlocks.PURPLE_LAMP, "Purple Lamp");
        this.addBlock(UselessBlocks.BLUE_LAMP, "Blue Lamp");
        this.addBlock(UselessBlocks.BROWN_LAMP, "Brown Lamp");
        this.addBlock(UselessBlocks.GREEN_LAMP, "Green Lamp");
        this.addBlock(UselessBlocks.RED_LAMP, "Red Lamp");
        this.addBlock(UselessBlocks.BLACK_LAMP, "Black Lamp");

        this.addItem(UselessItems.RAW_USELESS, "Raw Useless");
        this.addItem(UselessItems.RAW_SUPER_USELESS, "Raw Super-Useless");
        this.addItem(UselessItems.USELESS_DUST, "Useless Dust");
        this.addItem(UselessItems.SUPER_USELESS_DUST, "Super-Useless Dust");
        this.addItem(UselessItems.USELESS_INGOT, "Useless Ingot");
        this.addItem(UselessItems.SUPER_USELESS_INGOT, "Super-Useless Ingot");
        this.addItem(UselessItems.USELESS_NUGGET, "Useless Nugget");
        this.addItem(UselessItems.SUPER_USELESS_NUGGET, "Super-Useless Nugget");
        this.addItem(UselessItems.USELESS_SHEARS, "Useless Shears");
        this.addItem(UselessItems.USELESS_SHIELD, "Useless Shield");
        this.addItem(UselessItems.SUPER_USELESS_SHIELD, "Super-Useless Shield");
        this.addItem(UselessItems.USELESS_SWORD, "Useless Sword");
        this.addItem(UselessItems.SUPER_USELESS_SWORD, "Super-Useless Sword");
        this.addItem(UselessItems.USELESS_SHOVEL, "Useless Shovel");
        this.addItem(UselessItems.SUPER_USELESS_SHOVEL, "Super-Useless Shovel");
        this.addItem(UselessItems.USELESS_PICKAXE, "Useless Pickaxe");
        this.addItem(UselessItems.SUPER_USELESS_PICKAXE, "Super-Useless Pickaxe");
        this.addItem(UselessItems.USELESS_AXE, "Useless Axe");
        this.addItem(UselessItems.SUPER_USELESS_AXE, "Super-Useless Axe");
        this.addItem(UselessItems.USELESS_HOE, "Useless Hoe");
        this.addItem(UselessItems.SUPER_USELESS_HOE, "Super-Useless Hoe");
        this.addItem(UselessItems.USELESS_HELMET, "Useless Helmet");
        this.addItem(UselessItems.SUPER_USELESS_HELMET, "Super-Useless Helmet");
        this.addItem(UselessItems.USELESS_CHESTPLATE, "Useless Chestplate");
        this.addItem(UselessItems.SUPER_USELESS_CHESTPLATE, "Super-Useless Chestplate");
        this.addItem(UselessItems.USELESS_LEGGINGS, "Useless Leggings");
        this.addItem(UselessItems.SUPER_USELESS_LEGGINGS, "Super-Useless Leggings");
        this.addItem(UselessItems.USELESS_BOOTS, "Useless Boots");
        this.addItem(UselessItems.SUPER_USELESS_BOOTS, "Super-Useless Boots");
        this.addItem(UselessItems.USELESS_ELYTRA, "Useless Elytra");
        this.addItem(UselessItems.SUPER_USELESS_ELYTRA, "Super-Useless Elytra");
        this.addItem(UselessItems.USELESS_WHEAT_SEEDS, "Useless Wheat Seeds");
        this.addItem(UselessItems.USELESS_WHEAT, "Useless Wheat");
        this.addItem(UselessItems.COFFEE_SEEDS, "Coffee Seeds");
        this.addItem(UselessItems.COFFEE_BEANS, "Coffee Beans");
        this.addItem(UselessItems.USELESS_BONE, "Useless Bone");
        this.addItem(UselessItems.USELESS_LEATHER, "Useless Leather");
        this.addItem(UselessItems.USELESS_FEATHER, "Useless Feather");
        this.addItem(UselessItems.PAINT_BRUSH, "Paint Brush");
        this.addItem(UselessItems.BUCKET_PAINT, "Bucket with Paint");
        this.add("item.uselessmod.useless_sheep_spawn_egg", "Useless Sheep Spawn Egg");
        this.add("item.uselessmod.useless_pig_spawn_egg", "Useless Pig Spawn Egg");
        this.add("item.uselessmod.useless_chicken_spawn_egg", "Useless Chicken Spawn Egg");
        this.add("item.uselessmod.useless_cow_spawn_egg", "Useless Cow Spawn Egg");
        this.add("item.uselessmod.useless_skeleton_spawn_egg", "Useless Skeleton Spawn Egg");

        this.add("fluid_type.uselessmod.paint", "Paint");

        this.addEntityType(UselessEntityTypes.USELESS_SHEEP, "Useless Sheep");
        this.addEntityType(UselessEntityTypes.USELESS_PIG, "Useless Pig");
        this.addEntityType(UselessEntityTypes.USELESS_CHICKEN, "Useless Chicken");
        this.addEntityType(UselessEntityTypes.USELESS_COW, "Useless Cow");
        this.addEntityType(UselessEntityTypes.USELESS_SKELETON, "Useless Skeleton");

        this.addCoffeeType(UselessCoffeeTypes.BLACK, "Black Coffee");
        this.addCoffeeType(UselessCoffeeTypes.MILK, "Milk Coffee");
        this.addCoffeeType(UselessCoffeeTypes.SUGAR, "Sugar Coffee");
        this.addCoffeeType(UselessCoffeeTypes.MILK_SUGAR, "Milk-Sugar Coffee");

        this.addStat(UselessStats.OPEN_WALL_CLOSET, "Wall Closets Opened");
        this.addStat(UselessStats.INTERACT_WITH_COFFEE_MACHINE, "Interactions with Coffee Machine");

        this.add("painting.uselessmod.small_logo_red.title", "Small 'Useless Mod' Logo (Red)");
        this.add("painting.uselessmod.small_logo_red.author", "TheMCLoveMan");
        this.add("painting.uselessmod.small_logo_blue.title", "Small 'Useless Mod' Logo (Blue)");
        this.add("painting.uselessmod.small_logo_blue.author", "TheMCLoveMan");
        this.add("painting.uselessmod.large_logo_red.title", "Large 'Useless Mod' Logo (Red)");
        this.add("painting.uselessmod.large_logo_red.author", "TheMCLoveMan");
        this.add("painting.uselessmod.large_logo_blue.title", "Large 'Useless Mod' Logo (Blue)");
        this.add("painting.uselessmod.large_logo_blue.author", "TheMCLoveMan");

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

        this.add("status.uselessmod.coming_soon", "This Feature is coming soon");
        this.add("status.uselessmod.light_switch.block_added", "Added Block at [%s] to Light Switch");
        this.add("status.uselessmod.light_switch.block_already_added", "This Block has already been added to this Light Switch");
        this.add("status.uselessmod.light_switch.cleared", "Light Switch Configuration cleared");
        this.add("tooltip.uselessmod.light_switch.clear", "Sneak and right click to clear Configuration");
        this.add("tooltip.uselessmod.hold_shift", "<Hold Shift>");

        this.add("itemGroup.uselessmod", "Useless Mod");
        this.add("itemGroup.uselessmod.paint", "Useless Mod: Paint");
        this.add("itemGroup.uselessmod.coffee", "Useless Mod: Coffee");
        this.add("itemGroup.uselessmod.wall_closet", "Useless Mod: Wall Closets");
    }

    private void addAdvancement(String id, String title, String description) {
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".title", title);
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".description", description);
    }

    private void addStat(Supplier<ResourceLocation> key, String name) {
        this.add(String.format("stat.%s.%s", key.get().getNamespace(), key.get().getPath()), name);
    }

    private void addCoffeeType(Supplier<CoffeeType> key, String name) {
        this.add(key.get().getDescriptionId(), name);
    }
}
