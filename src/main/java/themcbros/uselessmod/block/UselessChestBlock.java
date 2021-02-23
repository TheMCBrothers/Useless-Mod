package themcbros.uselessmod.block;

import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.init.TileEntityInit;

public class UselessChestBlock extends ChestBlock {

    public UselessChestBlock(Properties properties) {
        super(properties, TileEntityInit.USELESS_CHEST::get);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return this.tileEntityType.get().create();
    }
}
