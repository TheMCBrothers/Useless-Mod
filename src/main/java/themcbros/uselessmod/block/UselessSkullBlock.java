package themcbros.uselessmod.block;

import net.minecraft.block.SkullBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.init.TileEntityInit;

public class UselessSkullBlock extends SkullBlock {
    public UselessSkullBlock(ISkullType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return TileEntityInit.USELESS_SKULL.get().create();
    }

    public enum Types implements ISkullType {
        USELESS_SKELETON
    }

}
