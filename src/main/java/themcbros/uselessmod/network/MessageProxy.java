package themcbros.uselessmod.network;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import themcbros.uselessmod.client.screen.EditUselessSignScreen;
import themcbros.uselessmod.tileentity.ISyncableTileEntity;

public class MessageProxy {

    public static Runnable openSignGui(BlockPos signPos) {
        return () -> {
            net.minecraft.client.Minecraft client = net.minecraft.client.Minecraft.getInstance();
            assert client.world != null;
            TileEntity tileentity = client.world.getTileEntity(signPos);
            if (!(tileentity instanceof SignTileEntity)) {
                tileentity = new SignTileEntity();
                tileentity.setWorldAndPos(client.world, signPos);
            }

            assert client.player != null;
            client.displayGuiScreen(new EditUselessSignScreen((SignTileEntity) tileentity));
        };
    }

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
