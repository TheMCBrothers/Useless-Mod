package themcbros.uselessmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.api.energy.IUselessEnergyStorage;
import themcbros.uselessmod.energy.DummyEnergyStorage;
import themcbros.uselessmod.energy.UselessEnergyStorage;
import themcbros.uselessmod.init.TileEntityInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author TheMCBrothers
 */
public class UselessGeneratorTileEntity extends TileEntity implements ITickableTileEntity {

    public UselessEnergyStorage energyStorage = new UselessEnergyStorage(50_000, 0, 1024);

    public UselessGeneratorTileEntity() {
        super(TileEntityInit.USELESS_GENERATOR.get());
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
        assert this.world != null;
        this.read(this.world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("EnergyStored", this.energyStorage.getEnergyStored());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);
        this.energyStorage.setEnergyStored(tag.getInt("EnergyStored"));
    }

    private final LazyOptional<IEnergyStorage> forgeEnergyHolder = LazyOptional.of(() -> this.energyStorage);
    private final LazyOptional<IUselessEnergyStorage> uselessEnergyHolder = LazyOptional.of(() -> this.energyStorage);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && (side == Direction.DOWN || side == null))
            return this.forgeEnergyHolder.cast();
        else if (cap == CapabilityUselessEnergy.USELESS_ENERGY && (side == Direction.DOWN || side == null))
            return this.uselessEnergyHolder.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        this.forgeEnergyHolder.invalidate();
        this.uselessEnergyHolder.invalidate();
    }

    public void generate(int energy) {
        this.energyStorage.growEnergy(energy);
    }

    @Override
    public void tick() {
        assert world != null;
        if (!world.isRemote) {
            for (Direction side : Direction.values()) {
                TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(side));
                if (tileEntity != null) {
                    IEnergyStorage energyStorage = tileEntity.getCapability(CapabilityEnergy.ENERGY).orElse(DummyEnergyStorage.INSTANCE);
                    if (energyStorage.canReceive()) {
                        int maxReceive = Math.min(this.energyStorage.getEnergyStored(), this.energyStorage.getMaxTransfer(true));
                        int received = energyStorage.receiveEnergy(maxReceive, false);
                        this.energyStorage.consumeEnergy(received);
                    }
                }
            }
        }
    }
}
