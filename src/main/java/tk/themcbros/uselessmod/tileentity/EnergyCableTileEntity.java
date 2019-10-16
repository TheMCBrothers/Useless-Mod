package tk.themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import tk.themcbros.uselessmod.energy.EnergyCableNetworkManager;
import tk.themcbros.uselessmod.lists.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyCableTileEntity extends TileEntity implements ITickableTileEntity {

	public EnergyCableTileEntity() {
		super(ModTileEntities.ENERGY_CABLE);
	}

	@Override
	public void tick() {
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return EnergyCableNetworkManager.getLazy(this.world, this.pos).cast();
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
		if (world == null) return "error";
		return EnergyCableNetworkManager.get(this.world, this.pos).toString();
	}
}
