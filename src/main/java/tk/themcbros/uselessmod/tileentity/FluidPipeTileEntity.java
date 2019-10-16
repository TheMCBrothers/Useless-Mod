package tk.themcbros.uselessmod.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import tk.themcbros.uselessmod.fluids.FluidPipeNetworkManager;
import tk.themcbros.uselessmod.lists.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidPipeTileEntity extends TileEntity implements ITickableTileEntity {

	public FluidPipeTileEntity() {
		super(ModTileEntities.FLUID_PIPE);
	}

	@Override
	public void tick() {
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (!this.removed && cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return FluidPipeNetworkManager.getLazy(this.world, this.pos).cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public void remove() {
		if (this.world != null) {
			FluidPipeNetworkManager.invalidateNetwork(this.world, this.pos);
		}
		super.remove();
	}

	public String getNetworkInfos() {
		if (this.world == null) return "error";
		return FluidPipeNetworkManager.get(this.world, this.pos).toString();
	}
}
