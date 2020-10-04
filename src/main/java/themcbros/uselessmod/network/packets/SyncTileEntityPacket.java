package themcbros.uselessmod.network.packets;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import themcbros.uselessmod.network.MessageProxy;
import themcbros.uselessmod.tileentity.ISyncableTileEntity;

import java.util.Objects;
import java.util.function.Supplier;

public class SyncTileEntityPacket implements IMessage {

    private final BlockPos pos;
    private final CompoundNBT nbt;

    public SyncTileEntityPacket(ISyncableTileEntity tileEntity, CompoundNBT nbt) {
        this.pos = tileEntity.getSyncTile().getPos();
        this.nbt = nbt;
    }

    public SyncTileEntityPacket(PacketBuffer packetBuffer) {
        this.pos = packetBuffer.readBlockPos();
        this.nbt = packetBuffer.readCompoundTag();
    }

    @Override
    public void toBytes(PacketBuffer packetBuffer) {
        packetBuffer.writeBlockPos(this.pos);
        packetBuffer.writeCompoundTag(this.nbt);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        if (ctx.getDirection().getReceptionSide() == LogicalSide.SERVER) {
            ctx.enqueueWork(() -> {
                ServerWorld world = Objects.requireNonNull(ctx.getSender()).getServerWorld();
                if (world.isAreaLoaded(this.pos, 1)) {
                    TileEntity tile = world.getTileEntity(this.pos);
                    if (tile instanceof ISyncableTileEntity) {
                        ((ISyncableTileEntity) tile).receiveMessageFromClient(this.nbt);
                    }
                }

            });
        } else {
            ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MessageProxy.receiveServerUpdates(this.pos, this.nbt)));
        }
    }
}
