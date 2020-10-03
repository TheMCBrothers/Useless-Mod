package themcbros.uselessmod.block;

import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.tileentity.UselessSignTileEntity;

public class UselessStandingSignBlock extends StandingSignBlock {
    public UselessStandingSignBlock(Properties p_i225764_1_, WoodType p_i225764_2_) {
        super(p_i225764_1_, p_i225764_2_);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        return new UselessSignTileEntity();
    }
}
