package tk.themcbros.uselessmod.closet;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public interface IClosetRegistry {

	/**
	 * Creates and registers an IBedMaterial instance
	 * 
	 * @param block           Used for the material id and crafting item
	 * @param textureLocation The resource location of the texture to be used
	 * @return The created IClosetMaterial instance
	 */
	public IClosetMaterial registerMaterial(@Nonnull Block block, ResourceLocation textureLocation);

	/**
	 * Registers an IBedMaterial instance
	 */
	public IClosetMaterial registerMaterial(@Nonnull IClosetMaterial material);

}
