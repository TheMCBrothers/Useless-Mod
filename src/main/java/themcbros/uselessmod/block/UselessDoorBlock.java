package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;

public class UselessDoorBlock extends DoorBlock implements IBlockItemProvider {
    public UselessDoorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockItem provideBlockItem(Block block, Item.Properties properties) {
        return new TallBlockItem(block, properties);
    }
}
