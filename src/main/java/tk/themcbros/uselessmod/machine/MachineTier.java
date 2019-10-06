package tk.themcbros.uselessmod.machine;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.util.IStringSerializable;

public enum MachineTier implements IStringSerializable {

	COFFEE(8_000, 100, 1.0f),
	STANDARD(16_000, 250, 1.0f),
	USELESS(24_000, 750, 2.0f),
	SUPER_USELESS(32_000, 2000, 4.0f);

	private static final MachineTier[] VALUES = values();
	private static final Map<String, MachineTier> NAME_LOOKUP = Arrays.stream(VALUES)
			.collect(Collectors.toMap(MachineTier::getName, (tier) -> {
				return tier;
			}));

	private final int machineCapacity, maxTransfer;
	private final float machineSpeed;

	private MachineTier(int machineCapacity, int maxTransfer, float machineSpeed) {
		this.machineCapacity = machineCapacity;
		this.maxTransfer = maxTransfer;
		this.machineSpeed = machineSpeed;
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

	public int getMachineCapacity() {
		return machineCapacity;
	}

	public float getMachineSpeed() {
		return machineSpeed;
	}

	public int getMaxEnergyTransfer() {
		return maxTransfer;
	}

	/**
	 * Get the facing specified by the given name
	 */
	@Nullable
	public static MachineTier byName(@Nullable String name) {
		return name == null ? null : NAME_LOOKUP.get(name.toLowerCase(Locale.ROOT));
	}

}
