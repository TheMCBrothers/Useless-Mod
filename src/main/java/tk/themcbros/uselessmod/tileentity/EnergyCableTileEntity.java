package tk.themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.energy.EnergyCableNetwork;
import tk.themcbros.uselessmod.energy.EnergyCableNetworkManager;
import tk.themcbros.uselessmod.lists.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {

	public int energyStored;

	public EnergyCableTileEntity() {
		super(ModTileEntities.ENERGY_CABLE);
	}

	@Override
	public void tick() {
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		nbt.putInt("EnergyStored", energyStored);
		return super.write(nbt);
	}

	@Override
	public void read(CompoundNBT nbt) {
		this.energyStored = nbt.getInt("EnergyStored");
		super.read(nbt);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (world != null && !removed && cap == CapabilityEnergy.ENERGY && side != null) {
			LazyOptional<EnergyCableNetwork> networkOptional = EnergyCableNetworkManager.getLazy(world, pos);
			if (networkOptional.isPresent()) {
				return networkOptional.orElseThrow(IllegalStateException::new).getConnection(pos, side).getLazyOptional().cast();
			}
		}
		return LazyOptional.empty();
	}

	@Override
	public void remove() {
		if (this.world != null) {
			EnergyCableNetworkManager.invalidateNetwork(this.world, this.pos);
		}
		super.remove();
	}

	public String getNetworkInfos() {
		if (world == null) return "world is null";

		EnergyCableNetwork net = EnergyCableNetworkManager.get(world, pos);
		return net != null ? net.toString() : "null";
	}
}
