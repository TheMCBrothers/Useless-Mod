package tk.themcbros.uselessmod.machine;

import net.minecraft.util.IStringSerializable;

public enum Upgrade implements IStringSerializable {

	NULL("null", 0),
	SPEED("speed", 10);
	
	private final String name;
	private final int additionalEnergyUsage;
	
	private Upgrade(String name, int additionalEnergyUsage) {
		this.name = name;
		this.additionalEnergyUsage = additionalEnergyUsage;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public int getAdditionalEnergyUsage() {
		return additionalEnergyUsage;
	}
	
}
