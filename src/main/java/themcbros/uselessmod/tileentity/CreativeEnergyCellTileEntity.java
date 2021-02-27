package themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import themcbros.uselessmod.api.energy.CapabilityUselessEnergy;
import themcbros.uselessmod.api.energy.IUselessEnergyStorage;
import themcbros.uselessmod.energy.UselessEnergyStorage;
import themcbros.uselessmod.init.TileEntityInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CreativeEnergyCellTileEntity extends TileEntity implements ITickableTileEntity {

    public CreativeEnergyCellTileEntity() {
        super(TileEntityInit.CREATIVE_ENERGY_CELL.get());
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote) {
            for (Direction direction : Direction.values()) {
                TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(direction));
                if (tileEntity != null) {
                    tileEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
                            .ifPresent(energyHandler -> energyHandler.receiveEnergy(Integer.MAX_VALUE, false));
                }
            }
        }
    }

    private IUselessEnergyStorage creativeCell() {
        return new UselessEnergyStorage(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private final LazyOptional<IUselessEnergyStorage> energyHandler = LazyOptional.of(this::creativeCell);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityUselessEnergy.USELESS_ENERGY || cap == CapabilityEnergy.ENERGY)
            return this.energyHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        this.energyHandler.invalidate();
        super.invalidateCaps();
    }
}
