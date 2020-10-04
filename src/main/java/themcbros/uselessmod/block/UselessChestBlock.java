package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.client.renderer.tilentity.ISTERProvider;
import themcbros.uselessmod.init.TileEntityInit;

public class UselessChestBlock extends ChestBlock implements IBlockItemProvider {

    public UselessChestBlock(Properties properties) {
        super(properties, TileEntityInit.USELESS_CHEST::get);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return this.tileEntityType.get().create();
    }

    @Override
    public BlockItem provideBlockItem(Block block, Item.Properties properties) {
        properties.setISTER(ISTERProvider::useless);
        return new BlockItem(block, properties);
    }
}
