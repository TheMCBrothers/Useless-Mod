package tk.themcbros.uselessmod.world.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.storage.WorldSavedData;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.energy.EnergyCableNetwork;

public class CableNetworksSavedData extends WorldSavedData {

	private static final String DATA_NAME = UselessMod.MOD_ID + "_CableNetworks";

	public CableNetworksSavedData() {
		super(DATA_NAME);
	}
	
	public CableNetworksSavedData(String name) {
		super(name);
	}

	@Override
	public void read(CompoundNBT nbt) {
		ListNBT networkCompoundList = nbt.getList("Networks", 0);
		for(INBT networkINBT : networkCompoundList) {
			if(networkINBT instanceof CompoundNBT) {
				CompoundNBT networkCompound = (CompoundNBT) networkINBT;
				EnergyCableNetwork.addNetworkFromNBT(networkCompound);
			}
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		ListNBT networkList = new ListNBT();
		EnergyCableNetwork.NETWORK_LIST.forEach((key, network) -> {
			networkList.add(network.serializeNBT());
		});
		compound.put("Networks", networkList);
		return compound;
	}

}
