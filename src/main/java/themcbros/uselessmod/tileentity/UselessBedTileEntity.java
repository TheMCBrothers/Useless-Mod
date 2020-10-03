package themcbros.uselessmod.tileentity;

import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntityType;
import themcbros.uselessmod.init.TileEntityInit;

/**
 * @author TheMCBrothers
 */
public class UselessBedTileEntity extends BedTileEntity {

    public UselessBedTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntityInit.USELESS_BED.get();
    }

    public UselessBedTileEntity(DyeColor color) {
        super(color);
    }

}
