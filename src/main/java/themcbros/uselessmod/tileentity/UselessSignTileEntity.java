package themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import themcbros.uselessmod.init.TileEntityInit;

/**
 * @author TheMCBrothers
 */
public class UselessSignTileEntity extends SignTileEntity {

    public UselessSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntityInit.USELESS_SIGN.get();
    }
}
