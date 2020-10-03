package themcbros.uselessmod.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.tileentity.UselessSignTileEntity;

public class UselessWallSignBlock extends WallSignBlock {

    public UselessWallSignBlock(Properties p_i225766_1_, WoodType p_i225766_2_) {
        super(p_i225766_1_, p_i225766_2_);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        return new UselessSignTileEntity();
    }
}
