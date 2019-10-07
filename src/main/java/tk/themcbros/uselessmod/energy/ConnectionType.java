package tk.themcbros.uselessmod.energy;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ConnectionType implements IStringSerializable {
    NONE(false, false),
    INPUT(true, false),
    OUTPUT(false, true),
    BOTH(true, true),
    BLOCKED(false, false);

    private final boolean canExtract, canReceive;

    ConnectionType(boolean canExtract, boolean canReceive) {
        this.canExtract = canExtract;
        this.canReceive = canReceive;
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
}
