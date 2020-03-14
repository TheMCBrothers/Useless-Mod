package tk.themcbros.uselessmod.blocks;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import tk.themcbros.uselessmod.tileentity.UselessSignTileEntity;

public class UselessWallSignBlock extends WallSignBlock {

    public UselessWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new UselessSignTileEntity();
    }
}
