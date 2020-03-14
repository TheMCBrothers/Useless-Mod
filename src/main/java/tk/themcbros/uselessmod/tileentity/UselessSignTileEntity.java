package tk.themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import tk.themcbros.uselessmod.lists.ModTileEntities;

public class UselessSignTileEntity extends SignTileEntity {

    @Override
    public TileEntityType<?> getType() {
        return ModTileEntities.USELESS_SIGN;
    }
}
