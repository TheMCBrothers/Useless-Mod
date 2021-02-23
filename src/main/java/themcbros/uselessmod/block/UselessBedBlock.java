package themcbros.uselessmod.block;

import net.minecraft.block.BedBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import themcbros.uselessmod.tileentity.UselessBedTileEntity;

public class UselessBedBlock extends BedBlock {

    private final DyeColor color;

    public UselessBedBlock(DyeColor color, Properties properties) {
        super(color, properties);
        this.color = color;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new UselessBedTileEntity(this.color);
    }

}
