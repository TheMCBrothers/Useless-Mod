package themcbros.uselessmod.network;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import themcbros.uselessmod.tileentity.ISyncableTileEntity;

public class MessageProxy {
    public static Runnable receiveServerUpdates(BlockPos pos, CompoundNBT nbt) {
        return () -> {
            net.minecraft.client.world.ClientWorld world = net.minecraft.client.Minecraft.getInstance().world;
            if (world != null) {
                TileEntity tile = world.getTileEntity(pos);
                if (tile instanceof ISyncableTileEntity) {
                    ((ISyncableTileEntity) tile).receiveMessageFromServer(nbt);
                }
            }
        };
    }

}
