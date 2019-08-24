package tk.themcbros.uselessmod.energy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import tk.themcbros.uselessmod.UselessMod;
import tk.themcbros.uselessmod.config.MachineConfig;
import tk.themcbros.uselessmod.helper.StringHelper;
import tk.themcbros.uselessmod.tileentity.EnergyCableTileEntity;

public class EnergyCableNetwork implements INBTSerializable<CompoundNBT> {

	public static final Map<String, EnergyCableNetwork> NETWORK_LIST = Maps.newHashMap();
	public static final EnergyCableNetwork EMPTY = new EnergyCableNetwork("EMPTYCABLENETWORK");
	
	// Class start
	private static final int ENERGY_CABLE_CAPACITY = MachineConfig.energy_cable_capacity.get();
	
	public final List<TileEntity> CONSUMERS = new ArrayList<TileEntity>();
	public final List<EnergyCableTileEntity> CABLES = new ArrayList<EnergyCableTileEntity>();
	
	public CustomEnergyStorage energyStorage = new CustomEnergyStorage(4000, 250, 250, 2000);
	public String key = "null";
	
	public LazyOptional<CustomEnergyStorage> energyHolder = LazyOptional.of(() -> { return this.energyStorage; });
	
	private EnergyCableNetwork(String key) {
		this.key = key;
	}
	
	private EnergyCableNetwork() {}
	
	public static void addNetworkFromNBT(CompoundNBT compound) {
		EnergyCableNetwork network = new EnergyCableNetwork();
		network.deserializeNBT(compound);
		if(NETWORK_LIST.containsKey(network.key)) {
			NETWORK_LIST.put(network.key, network);
			UselessMod.LOGGER.debug("Added network to NETWORK_LIST from NBT || key : {}", network.key);
		}
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT compound = new CompoundNBT();
		compound.putString("NetworkKey", this.key);
		compound.put("Energy", this.energyStorage.serializeNBT());
		return compound;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		this.key = nbt.getString("NetworkKey");
		this.energyStorage.deserializeNBT(nbt.getCompound("Energy"));
		this.updateEnergyHolder();
	}
	
	public void updateEnergyHolder() {
		this.energyHolder = LazyOptional.of(() -> { return this.energyStorage; });
	}
	
	public void addCable(EnergyCableTileEntity energyCable) {
		if(!CABLES.contains(energyCable)) {
			CABLES.add(energyCable);
			this.updateNetwork();
			UselessMod.LOGGER.debug("Added EnergyCable to network : {}", this.key);
		}
	}
	
	public void removeCable(EnergyCableTileEntity energyCable) {
		if(CABLES.contains(energyCable)) {
			CABLES.remove(energyCable);
			
			Map<Direction, EnergyCableTileEntity> offsetCables = Maps.newHashMap();
			for(Direction direction : Direction.values()) {
				EnergyCableTileEntity offsetCable = energyCable.getOffsetCableTE(direction);
				if(offsetCable != null) {
					offsetCables.put(direction, offsetCable);
				}
			}
			if(offsetCables.size() <= 1) {
				UselessMod.LOGGER.debug("Remove EnergyCable from network : {}", this.key);
				this.updateNetwork();
				return;
			} else {
				//TODO FIX THIS ALL
				EnergyCableNetwork network = new EnergyCableNetwork();
				network.key = StringHelper.randomAlphaNumeric(20);
				NETWORK_LIST.put(network.key, network);
				offsetCables.forEach((direction, offsetCable) -> {
					NETWORK_LIST.remove(offsetCable.getNetwork().key, offsetCable.getNetwork());
					offsetCable.getNetwork().CABLES.forEach(cable -> {
						cable.setNetwork(network);
					});
				}); // BIS DA
				UselessMod.LOGGER.debug("Remove EnergyCable from network : {}", offsetCables.size(), this.key);
				UselessMod.LOGGER.debug("Split network {} into {} new networks", offsetCables.size(), this.key);
			}
		}
	}
	
	public void updateNetwork() {
		if (this.key == null || this.key == EMPTY.key) {
			NETWORK_LIST.remove(this.key, this);
			return;
		}
		if (this.energyStorage == null || this.energyHolder == null) {
			NETWORK_LIST.remove(this.key, this);
			return;
		}
		if (this.CABLES.size() == 0) {
			NETWORK_LIST.remove(this.key, this);
			return;
		}
		
		this.CABLES.forEach(cable -> {
			if(cable == null) this.CABLES.remove(cable);
		});
		
		int cap = this.CABLES.size() * ENERGY_CABLE_CAPACITY;
		if(this.energyStorage.getMaxEnergyStored() != cap)
			this.energyStorage.setCapacity(cap);
		
		this.updateEnergyHolder();
	}
	
	public static void updateNetworks() {
		NETWORK_LIST.forEach((networkKey, network) -> {
			network.updateNetwork();
		});
	}
	
	public static EnergyCableNetwork createWithCable(EnergyCableTileEntity energyCable, int maxTransfer) {
		EnergyCableNetwork network = new EnergyCableNetwork();
		network.CABLES.add(energyCable);
		network.key = StringHelper.randomAlphaNumeric(20);
		NETWORK_LIST.put(network.key, network);
		network.updateNetwork();
		UselessMod.LOGGER.debug("Created new EnergyCableNetwork with KEY : {}", network.key);
		return network;
	}
	
	public static EnergyCableNetwork combinedNetwork(EnergyCableNetwork... cableNetworks) {
		EnergyCableNetwork network = new EnergyCableNetwork();
		int energy = 0;
		int maxTransfer = 0;
		int capacity = 0;
		for(EnergyCableNetwork cableNetwork : cableNetworks) {
//			if(cableNetwork == EMPTY) return network; TODO
			network.CABLES.addAll(cableNetwork.CABLES);
			if(NETWORK_LIST.containsKey(cableNetwork.key)) NETWORK_LIST.remove(cableNetwork.key);
			CustomEnergyStorage cableEnergy = cableNetwork.energyStorage;
			if(cableEnergy != null) {
				energy += cableEnergy.getEnergyStored();
				if(cableEnergy.getMaxExtract() > maxTransfer) maxTransfer = cableEnergy.getMaxExtract();
			}
		}
		capacity = network.CABLES.size() * ENERGY_CABLE_CAPACITY;
		network.energyStorage = new CustomEnergyStorage(capacity, maxTransfer, maxTransfer, energy);
		network.key = StringHelper.randomAlphaNumeric(20);
		network.CABLES.forEach(cable -> {
			cable.setNetwork(network);
		});
		NETWORK_LIST.put(network.key, network);
		UselessMod.LOGGER.debug("Combined EnergyCableNetwork Array together -> Key: {} Size: {}", network.key, cableNetworks.length);
		return network;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
