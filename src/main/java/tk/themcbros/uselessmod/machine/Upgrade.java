package tk.themcbros.uselessmod.machine;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public enum Upgrade implements IStringSerializable {

	NULL(0, "tooltip.uselessmod.upgrade.null"),
	SPEED(10, "tooltip.uselessmod.upgrade.speed"),
	TIER_USELESS(20, null),
	TIER_SUPER_USELESS(30, null);

	private final int additionalEnergyUsage;
	private final String translationKey;
	
	Upgrade(int additionalEnergyUsage, @Nullable String translationKey) {
		this.additionalEnergyUsage = additionalEnergyUsage;
		this.translationKey = translationKey;
	}
	
	@Override
	@Nonnull
	public String getName() {
		return name().toLowerCase(Locale.ROOT);
	}

	public int getAdditionalEnergyUsage() {
		return additionalEnergyUsage;
	}

	@Nullable
    public String getTranslationKey() {
		return this.translationKey;
    }

	public boolean hasTranslationKey() {
		return this.translationKey != null && !this.translationKey.isEmpty();
	}
}
