package tk.themcbros.uselessmod.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBlockBase extends BlockItem {

	public ItemBlockBase(Block block, ItemGroup group) {
		super(block, new Item.Properties().group(group));
		this.setRegistryName(block.getRegistryName());
	}

}
