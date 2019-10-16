package tk.themcbros.uselessmod.energy;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public enum ConnectionType implements IStringSerializable {
    NONE(false, false),
    INPUT(true, false),
    OUTPUT(false, true),
    BOTH(true, true),
    BLOCKED(false, false);

    private static final ConnectionType[] VALUES = values();
    private static final Map<String, ConnectionType> NAME_LOOKUP = Arrays.stream(VALUES).collect(Collectors.toMap(ConnectionType::getName, name -> name));

    private final boolean canExtract, canReceive;

    ConnectionType(boolean canExtract, boolean canReceive) {
        this.canExtract = canExtract;
        this.canReceive = canReceive;
    }

    /**
     * Get the type specified by the given name
     */
    @Nullable
    public static ConnectionType byName(@Nullable String name) {
        return name == null ? null : NAME_LOOKUP.get(name.toLowerCase(Locale.ROOT));
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public boolean canExtract() {
        return canExtract;
    }

    public boolean canReceive() {
        return canReceive;
    }

    public boolean canConnect() {
        return canExtract || canReceive;
    }

	public ConnectionType getOpposite() {
        if (this == INPUT) return OUTPUT;
        else if (this == OUTPUT) return INPUT;
        return NONE;
	}
}
