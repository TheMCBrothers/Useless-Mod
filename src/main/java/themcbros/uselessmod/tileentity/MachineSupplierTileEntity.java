package themcbros.uselessmod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import themcbros.uselessmod.helpers.WrenchUtils;
import themcbros.uselessmod.init.TileEntityInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author TheMCBrothers
 */
public class MachineSupplierTileEntity extends TileEntity implements IWrenchableTileEntity {

    public static final ModelProperty<BlockState> MIMIC = new ModelProperty<>();

    @Nullable
    private BlockState mimic = null;

    public MachineSupplierTileEntity() {
        super(TileEntityInit.MACHINE_SUPPLIER.get());
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder().withInitial(MIMIC, this.mimic).build();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (this.world != null) {
            BlockState state = this.world.getBlockState(this.pos.up());
            Block block = state.getBlock();

            if (block.hasTileEntity(state)) {
                TileEntity tileEntity = this.world.getTileEntity(this.pos.up());
                if (tileEntity != null) {
                    return tileEntity.getCapability(cap, side);
                }
            }
        }
        return super.getCapability(cap, side);
    }

    @Nullable
    public BlockState getMimic() {
        return mimic;
    }

    public void setMimic(@Nullable BlockState mimic) {
        this.mimic = mimic;
        this.markDirty();
        if (this.world != null) {
            this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.DEFAULT);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (this.mimic != null)
            compound.put("Mimic", NBTUtil.writeBlockState(this.mimic));
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        if (compound.contains("Mimic"))
            this.mimic = NBTUtil.readBlockState(compound.getCompound("Mimic"));
        super.read(state, compound);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        BlockState oldMimic = this.mimic;
        assert this.world != null;
        this.handleUpdateTag(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
        if (!Objects.equals(oldMimic, this.mimic)) {
            this.requestModelDataUpdate();
            if (this.world != null)
                this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.DEFAULT);
        }
    }

    @Override
    public boolean tryWrench(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        assert world != null;
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty()) {
            if (WrenchUtils.isWrench(stack)) {
                if (player.isSneaking()) {
                    WrenchUtils.dismantleBlock(state, world, pos, this, null, null);
                    return true;
                } else if (this.mimic != null) {
                    Block.spawnAsEntity(this.world, this.pos, new ItemStack(this.mimic.getBlock()));
                    this.setMimic(null);
                    this.markDirty();
                    return true;
                }
            }
        }
        return false;
    }
}
