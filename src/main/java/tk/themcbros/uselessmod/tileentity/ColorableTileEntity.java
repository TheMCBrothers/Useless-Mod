package tk.themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.client.model.ModelDataManager;

public abstract  class ColorableTileEntity extends TileEntity {

	private int color;

	public ColorableTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
		this.color = 0xFFFFFF;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.color = compound.getInt("color");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("color", this.color);
		return super.write(compound);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
	}

	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}

	public void setColor(int color) {
		this.color = color;
		this.markDirty();
		if(this.world.isRemote) {
			ModelDataManager.requestModelDataRefresh(this);
//			TODO this.world.markForRerender(this.getPos());
			this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}

	public int getColor() {
		return color;
	}

}
