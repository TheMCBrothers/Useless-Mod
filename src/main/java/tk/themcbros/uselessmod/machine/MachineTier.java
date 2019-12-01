package tk.themcbros.uselessmod.machine;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

public enum MachineTier implements IStringSerializable {

	COFFEE(0, 8_000, 100, 1.0f),
	STANDARD(1, 16_000, 250, 1.0f),
	USELESS(2, 24_000, 750, 2.0f),
	SUPER_USELESS(3, 32_000, 2000, 4.0f);

	private static final MachineTier[] VALUES = values();
	private static final Map<String, MachineTier> NAME_LOOKUP = Arrays.stream(VALUES)
			.collect(Collectors.toMap(MachineTier::getName, (tier) -> tier));
	private static final MachineTier[] BY_INDEX = Arrays.stream(VALUES).sorted(Comparator.comparingInt((tier) -> tier.index)).toArray(MachineTier[]::new);

	private final int index;
	private final int machineCapacity, maxTransfer;
	private final float machineSpeed;

	MachineTier(int index, int machineCapacity, int maxTransfer, float machineSpeed) {
		this.index = index;
		this.machineCapacity = machineCapacity;
		this.maxTransfer = maxTransfer;
		this.machineSpeed = machineSpeed;
	}

	@Override
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

	public int getIndex() {
		return index;
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
		return name == null ? STANDARD : NAME_LOOKUP.get(name.toLowerCase(Locale.ROOT));
	}

	/**
	 * Gets the EnumFacing corresponding to the given index (0-3). Out of bounds values are wrapped around. The order is
	 * COFFEE-STANDARD-USELESS-SUPER_USELESS
	 */
	public static MachineTier byIndex(int index) {
		return BY_INDEX[MathHelper.abs(index % BY_INDEX.length)];
	}

}
