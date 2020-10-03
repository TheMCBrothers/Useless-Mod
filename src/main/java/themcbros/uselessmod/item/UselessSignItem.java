package themcbros.uselessmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import themcbros.uselessmod.network.Messages;
import themcbros.uselessmod.network.packets.OpenUselessSignEditor;

import javax.annotation.Nullable;

public class UselessSignItem extends WallOrFloorItem {

    public UselessSignItem(Properties properties, Block standingSign, Block wallSign) {
        super(standingSign, wallSign, properties);
    }

    protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity playerEntity, ItemStack stack, BlockState state) {
        boolean flag = super.onBlockPlaced(pos, worldIn, playerEntity, stack, state);
        if (!worldIn.isRemote && !flag && playerEntity != null) {
            SignTileEntity signTileEntity = (SignTileEntity) worldIn.getTileEntity(pos);
            if (signTileEntity == null) return false; // make sure not null
            signTileEntity.setPlayer(playerEntity);
            Messages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity),
                    new OpenUselessSignEditor(pos));
        }

        return flag;
    }

}
