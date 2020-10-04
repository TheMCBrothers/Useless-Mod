package themcbros.uselessmod.network.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import themcbros.uselessmod.network.MessageProxy;

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
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MessageProxy.openSignGui(this.signPos));
        });
    }
}
