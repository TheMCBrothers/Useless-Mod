package tk.themcbros.uselessmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import tk.themcbros.uselessmod.UselessMod;

public class BasicItem extends Item {

	public BasicItem(String registryName, ItemGroup group) {
		super(new Item.Properties().group(group));
		this.setRegistryName(new ResourceLocation(UselessMod.MOD_ID, registryName));
	}

}
