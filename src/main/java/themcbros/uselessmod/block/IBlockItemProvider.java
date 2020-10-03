package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public interface IBlockItemProvider {

    BlockItem provideBlockItem(Block block, Item.Properties properties);

}
