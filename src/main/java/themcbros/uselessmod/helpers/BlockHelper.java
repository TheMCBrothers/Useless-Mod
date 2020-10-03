package themcbros.uselessmod.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockHelper {

    @Nonnull
    public static Block getSlab(Block block) {
        Block result;
        ResourceLocation blockReg = Objects.requireNonNull(block.getRegistryName());
        if (blockReg.getPath().endsWith("_planks")) {
            String newPath = blockReg.getPath().substring(0, blockReg.getPath().length() - "_planks".length()) + "_slab";
            ResourceLocation newReg = new ResourceLocation(blockReg.getNamespace(), newPath);
            result = ForgeRegistries.BLOCKS.getValue(newReg);
            if (result != null) return result;
        }
        String newPath = blockReg.getPath() + "_slab";
        ResourceLocation newReg = new ResourceLocation(blockReg.getNamespace(), newPath);
        result = ForgeRegistries.BLOCKS.getValue(newReg);
        return result != null ? result : Blocks.AIR;
    }
}
