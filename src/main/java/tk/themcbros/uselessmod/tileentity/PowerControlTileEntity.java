package tk.themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.blocks.PowerControlBlock;
import tk.themcbros.uselessmod.energy.CustomEnergyStorage;
import tk.themcbros.uselessmod.lists.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PowerControlTileEntity extends TileEntity implements ITickableTileEntity {

	private CustomEnergyStorage energyStorage;
	
	public PowerControlTileEntity() {
		super(ModTileEntities.POWER_CONTROL_BLOCK);
		this.energyStorage = new CustomEnergyStorage(0, 1000, 1000);
	}
	
	private LazyOptional<CustomEnergyStorage> energyHolder = LazyOptional.of(() -> { return this.energyStorage; });
	
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if(cap == CapabilityEnergy.ENERGY && side != null && this.canConnect(side))
			return this.energyHolder.cast();
		return LazyOptional.empty();
	}

	public boolean canConnect(@Nonnull Direction side) {
		return PowerControlBlock.getConnection(this.getBlockState(), side).canConnect();
	}

	@Override
	public void tick() {
	}

}
