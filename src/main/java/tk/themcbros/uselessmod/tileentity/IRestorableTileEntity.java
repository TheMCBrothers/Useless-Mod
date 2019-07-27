package tk.themcbros.uselessmod.tileentity;

import net.minecraft.nbt.CompoundNBT;

public interface IRestorableTileEntity {

	public CompoundNBT writeRestorableToNBT(CompoundNBT compound);
	
	public void readRestorableFromNBT(CompoundNBT compound);
	
}
