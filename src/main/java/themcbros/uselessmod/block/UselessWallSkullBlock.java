package themcbros.uselessmod.block;

import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.init.TileEntityInit;

public class UselessWallSkullBlock extends WallSkullBlock {
    public UselessWallSkullBlock(SkullBlock.ISkullType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return TileEntityInit.USELESS_SKULL.get().create();
    }
}
