package themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

/**
 * @author TheMCBrothers
 */
public interface ISyncableTileEntity {

    default TileEntity getSyncTile() {
        return (TileEntity) this;
    }

    default void sendSyncPacket(int type) {}
    default void receiveMessageFromServer(CompoundNBT nbt) {}
    default void receiveMessageFromClient(CompoundNBT nbt) {}

}
