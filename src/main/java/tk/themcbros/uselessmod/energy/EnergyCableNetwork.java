package tk.themcbros.uselessmod.energy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.LazyOptional;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

public class EnergyCableNetwork {

	public static final List<EnergyCableNetwork> NETWORK_LIST = new ArrayList<EnergyCableNetwork>();
	
	// Class start
	public final List<TileEntity> CONSUMERS = new ArrayList<TileEntity>();
	public final List<EnergyCableTileEntity> CABLES = new ArrayList<EnergyCableTileEntity>();
	
	public CustomEnergyStorage energyStorage;
	
	public LazyOptional<CustomEnergyStorage> energyHolder = LazyOptional.of(() -> { return this.energyStorage; });
	
	public EnergyCableNetwork(int maxTransfer) {
		this.energyStorage = new CustomEnergyStorage(4000, maxTransfer);
	}
	
}
