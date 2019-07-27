package tk.themcbros.uselessmod.lists;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.closet.BeddingRegistryEvent;
import tk.themcbros.uselessmod.closet.IClosetMaterial;
import tk.themcbros.uselessmod.closet.IClosetRegistry;

public class ModBeddings {

	public static IClosetMaterial OAK;
    public static IClosetMaterial WHITE;
    
    @Mod.EventBusSubscriber(modid = UselessMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {
        
        @SubscribeEvent
        public static void registerBeddingMaterial(BeddingRegistryEvent event) {
            IClosetRegistry beddingReg = event.getBeddingRegistry();
            IClosetRegistry casingReg = event.getCasingRegistry();
            
            OAK = casingReg.registerMaterial(Blocks.OAK_PLANKS, new ResourceLocation("block/oak_planks"));
            casingReg.registerMaterial(Blocks.SPRUCE_PLANKS, new ResourceLocation("block/spruce_planks"));
            casingReg.registerMaterial(Blocks.BIRCH_PLANKS, new ResourceLocation("block/birch_planks"));
            casingReg.registerMaterial(Blocks.JUNGLE_PLANKS, new ResourceLocation("block/jungle_planks"));
            casingReg.registerMaterial(Blocks.ACACIA_PLANKS, new ResourceLocation("block/acacia_planks"));
            casingReg.registerMaterial(Blocks.DARK_OAK_PLANKS, new ResourceLocation("block/dark_oak_planks"));
            
            WHITE = beddingReg.registerMaterial(Blocks.WHITE_WOOL, new ResourceLocation("block/white_wool"));
            beddingReg.registerMaterial(Blocks.ORANGE_WOOL, new ResourceLocation("block/orange_wool"));
            beddingReg.registerMaterial(Blocks.MAGENTA_WOOL, new ResourceLocation("block/magenta_wool"));
            beddingReg.registerMaterial(Blocks.LIGHT_BLUE_WOOL, new ResourceLocation("block/light_blue_wool"));
            beddingReg.registerMaterial(Blocks.YELLOW_WOOL, new ResourceLocation("block/yellow_wool"));
            beddingReg.registerMaterial(Blocks.LIME_WOOL, new ResourceLocation("block/lime_wool"));
            beddingReg.registerMaterial(Blocks.PINK_WOOL, new ResourceLocation("block/pink_wool"));
            beddingReg.registerMaterial(Blocks.GRAY_WOOL, new ResourceLocation("block/gray_wool"));
            beddingReg.registerMaterial(Blocks.LIGHT_GRAY_WOOL, new ResourceLocation("block/light_gray_wool"));
            beddingReg.registerMaterial(Blocks.CYAN_WOOL, new ResourceLocation("block/cyan_wool"));
            beddingReg.registerMaterial(Blocks.PURPLE_WOOL, new ResourceLocation("block/purple_wool"));
            beddingReg.registerMaterial(Blocks.BLUE_WOOL, new ResourceLocation("block/blue_wool"));
            beddingReg.registerMaterial(Blocks.BROWN_WOOL, new ResourceLocation("block/brown_wool"));
            beddingReg.registerMaterial(Blocks.GREEN_WOOL, new ResourceLocation("block/green_wool"));
            beddingReg.registerMaterial(Blocks.RED_WOOL, new ResourceLocation("block/red_wool"));
            beddingReg.registerMaterial(Blocks.BLACK_WOOL, new ResourceLocation("block/black_wool"));
        }
    }
	
}
