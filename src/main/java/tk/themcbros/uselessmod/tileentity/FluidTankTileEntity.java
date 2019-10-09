package tk.themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import tk.themcbros.uselessmod.lists.ModTileEntities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTankTileEntity extends TileEntity {

	private FluidTank tank = new FluidTank(FluidAttributes.BUCKET_VOLUME * 10); // todo make config entry

	public FluidTankTileEntity() {
		super(ModTileEntities.FLUID_TANK);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		this.tank.writeToNBT(compound);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		this.tank.readFromNBT(compound);
		super.read(compound);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return this.tank.writeToNBT(super.getUpdateTag());
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, @Nonnull SUpdateTileEntityPacket pkt) {
		this.tank.readFromNBT(pkt.getNbtCompound());
	}

	private LazyOptional<FluidTank> holder = LazyOptional.of(() -> this.tank);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return holder.cast();
		}
		return LazyOptional.empty();
	}

	public FluidTank getTank() {
		return this.tank;
	}
}
