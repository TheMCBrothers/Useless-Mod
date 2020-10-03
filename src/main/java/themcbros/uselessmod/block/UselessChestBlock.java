package themcbros.uselessmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.client.renderer.tilentity.ItemStackTileEntityRenderer;
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
        properties.setISTER(() -> () -> new ItemStackTileEntityRenderer(() -> TileEntityInit.USELESS_CHEST.get().create()));
        return new BlockItem(block, properties);
    }
}
