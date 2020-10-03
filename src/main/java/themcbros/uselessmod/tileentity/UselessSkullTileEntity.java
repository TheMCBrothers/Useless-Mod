package themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.tileentity.TileEntityType;
import themcbros.uselessmod.init.TileEntityInit;

/**
 * @author TheMCBrothers
 */
public class UselessSkullTileEntity extends SkullTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return TileEntityInit.USELESS_SKULL.get();
    }
}
