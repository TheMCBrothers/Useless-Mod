package tk.themcbros.uselessmod.closet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.lists.ModBlocks;

public class ClosetRegistry implements IClosetRegistry {

	public static final ClosetRegistry CASINGS = new ClosetRegistry("casing");
	public static final ClosetRegistry BEDDINGS = new ClosetRegistry("bedding");
	
	private final List<IClosetMaterial> REGISTRY = new ArrayList<IClosetMaterial>();
	private final String key;
	
	public ClosetRegistry(String key) {
		this.key = key;
	}
	
	@Override
    public IClosetMaterial registerMaterial(@Nonnull Block block, ResourceLocation textureLocation) {
        return this.registerMaterial(new ClosetMaterial(block, textureLocation, Ingredient.fromItems(block)));
    }
	
	public IClosetMaterial registerMaterial(ResourceLocation key, ResourceLocation textureLocation) {
		return this.registerMaterial(new ClosetMaterial(key, textureLocation, Ingredient.fromItems(ForgeRegistries.BLOCKS.getValue(key))));
	}
    
    @Override
    public IClosetMaterial registerMaterial(IClosetMaterial material) {
        if(this.REGISTRY.contains(material)) {
            UselessMod.LOGGER.warn("Tried to register a closet material with the id {} more that once", material); 
            return null;
        }
        else {
            this.REGISTRY.add(material.setRegName(this.key));
            UselessMod.LOGGER.debug("Register closet {} under the key {}", this.key, material);
            return material;
        }
    }
    
    public List<IClosetMaterial> getKeys() {
        return this.REGISTRY;
    }
    
    public IClosetMaterial get(String saveId) {
        if(saveId.equals("missing"))
            return IClosetMaterial.NULL;
        
        // Try find a registered material
        for(IClosetMaterial thing : this.REGISTRY) {
            if(thing.getSaveId().equals(saveId)) {
                return thing;
            }
        }
        
        // Gets a holders so saveId is preserved
        return IClosetMaterial.getHolder(saveId);
    }
    
    public IClosetMaterial getFromStack(ItemStack stack) {
        for(IClosetMaterial m : this.REGISTRY) {
            if(m.getIngredient().test(stack))
                return m;
        }
        return IClosetMaterial.NULL;
    }
    
    public static ItemStack createItemStack(IClosetMaterial casingId, IClosetMaterial beddingId) {
        ItemStack stack = new ItemStack(ModBlocks.CLOSET, 1);
        
        CompoundNBT tag = stack.getOrCreateChildTag("uselessmod");
        tag.putString("casingId", casingId.getSaveId());
        tag.putString("beddingId", beddingId.getSaveId());
        return stack;
    }

}
