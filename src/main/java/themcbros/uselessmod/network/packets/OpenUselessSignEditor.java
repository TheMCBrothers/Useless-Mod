package themcbros.uselessmod.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import themcbros.uselessmod.client.screen.EditUselessSignScreen;

import java.util.function.Supplier;

public class OpenUselessSignEditor implements IMessage {
    private final BlockPos signPos;

    public OpenUselessSignEditor(BlockPos signPos) {
        this.signPos = signPos;
    }

    public OpenUselessSignEditor(PacketBuffer packetBuffer) {
        this.signPos = packetBuffer.readBlockPos();
    }

    @Override
    public void toBytes(PacketBuffer packetBuffer) {
        packetBuffer.writeBlockPos(this.signPos);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft client = Minecraft.getInstance();
            assert client.world != null;
            TileEntity tileentity = client.world.getTileEntity(this.signPos);
            if (!(tileentity instanceof SignTileEntity)) {
                tileentity = new SignTileEntity();
                tileentity.setWorldAndPos(client.world, this.signPos);
            }

            assert client.player != null;
            client.displayGuiScreen(new EditUselessSignScreen((SignTileEntity) tileentity));
        });
    }
}
