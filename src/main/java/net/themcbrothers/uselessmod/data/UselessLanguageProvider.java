package net.themcbrothers.uselessmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.LanguageProvider;
import net.themcbrothers.uselessmod.UselessMod;
import net.themcbrothers.uselessmod.init.ModBlocks;
import net.themcbrothers.uselessmod.init.ModEntityTypes;
import net.themcbrothers.uselessmod.init.ModItems;

public class UselessLanguageProvider extends LanguageProvider {
    public UselessLanguageProvider(DataGenerator gen) {
        super(gen, UselessMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addAdvancement("root", "Useless Mod", "Is this useless?");
        this.addAdvancement("mine", " Mod", "what Is this?");

        this.addBlock(ModBlocks.USELESS_ORE, "Useless Ore");
        this.addBlock(ModBlocks.DEEPSLATE_USELESS_ORE, "Deepslate Useless Ore");
        this.addBlock(ModBlocks.NETHER_USELESS_ORE, "Nether Useless Ore");
        this.addBlock(ModBlocks.END_USELESS_ORE, "End Useless Ore");
        this.addBlock(ModBlocks.USELESS_BLOCK, "Block of Useless");
        this.addBlock(ModBlocks.RAW_USELESS_BLOCK, "Block of Raw Useless");
        this.add(ModBlocks.USELESS_OAK_WOOD.getLog(), "Useless Oak Log");
        this.add(ModBlocks.USELESS_OAK_WOOD.getWood(), "Useless Oak Wood");
        this.add(ModBlocks.USELESS_OAK_WOOD.getStrippedLog(), "Stripped Useless Oak Log");
        this.add(ModBlocks.USELESS_OAK_WOOD.getStrippedWood(), "Stripped Useless Oak Wood");
        this.addBlock(ModBlocks.USELESS_OAK_SAPLING, "Useless Oak Sapling");
        this.addBlock(ModBlocks.USELESS_OAK_LEAVES, "Useless Oak Leaves");
        this.add(ModBlocks.USELESS_OAK_WOOD.get(), "Useless Oak Planks");
        this.add(ModBlocks.USELESS_OAK_WOOD.getSlab(), "Useless Oak Slab");
        this.add(ModBlocks.USELESS_OAK_WOOD.getStairs(), "Useless Oak Stairs");
        this.add(ModBlocks.USELESS_OAK_WOOD.getFence(), "Useless Oak Fence");
        this.add(ModBlocks.USELESS_OAK_WOOD.getFenceGate(), "Useless Oak Fence Gate");
        this.add(ModBlocks.USELESS_OAK_WOOD.getDoor(), "Useless Oak Door");
        this.add(ModBlocks.USELESS_OAK_WOOD.getTrapdoor(), "Useless Oak Trapdoor");
        this.add(ModBlocks.USELESS_OAK_WOOD.getPressurePlate(), "Useless Oak Pressure Plate");
        this.add(ModBlocks.USELESS_OAK_WOOD.getButton(), "Useless Oak Button");
        this.add(ModBlocks.USELESS_OAK_WOOD.getSign(), "Useless Oak Sign");
        this.addBlock(ModBlocks.PAINT_BUCKET, "Paint Bucket");
        this.addBlock(ModBlocks.USELESS_RAIL, "Useless Rail");
        this.addBlock(ModBlocks.USELESS_POWERED_RAIL, "Useless Powered Rail");
        this.addBlock(ModBlocks.USELESS_DETECTOR_RAIL, "Useless Detector Rail");
        this.addBlock(ModBlocks.USELESS_ACTIVATOR_RAIL, "Useless Activator Rail");
        this.addBlock(ModBlocks.USELESS_BARS, "Useless Bars");

        this.addItem(ModItems.RAW_USELESS, "Raw Useless");
        this.addItem(ModItems.USELESS_DUST, "Useless Dust");
        this.addItem(ModItems.USELESS_INGOT, "Useless Ingot");
        this.addItem(ModItems.USELESS_NUGGET, "Useless Nugget");
        this.addItem(ModItems.USELESS_SWORD, "Useless Sword");
        this.addItem(ModItems.USELESS_SHOVEL, "Useless Shovel");
        this.addItem(ModItems.USELESS_PICKAXE, "Useless Pickaxe");
        this.addItem(ModItems.USELESS_AXE, "Useless Axe");
        this.addItem(ModItems.USELESS_HOE, "Useless Hoe");
        this.addItem(ModItems.USELESS_HELMET, "Useless Helmet");
        this.addItem(ModItems.USELESS_CHESTPLATE, "Useless Chestplate");
        this.addItem(ModItems.USELESS_LEGGINGS, "Useless Leggings");
        this.addItem(ModItems.USELESS_BOOTS, "Useless Boots");
        this.add("item.uselessmod.useless_skeleton_spawn_egg", "Useless Skeleton Spawn Egg");

        this.addEntityType(ModEntityTypes.USELESS_SKELETON, "Useless Skeleton");

        this.add("itemGroup.uselessmod", "Useless Mod");
    }

    private void addAdvancement(String id, String title, String description) {
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".title", title);
        this.add("advancement." + UselessMod.MOD_ID + "." + id + ".description", description);
    }
}
