package tk.themcbros.uselessmod.closet;

import java.util.*;

import net.minecraft.util.IStringSerializable;

public enum WoodType implements IStringSerializable {
    OAK("oak"),
    DARK_OAK("dark_oak"),
    SPRUCE("spruce"),
    BIRCH("birch"),
    ACACIA("acacia"),
    JUNGLE("jungle"),
    CHERRY("cherry"),
    DEAD("dead"),
    ETHEREAL("ethereal"),
    FIR("fir"),
    HELLBARK("hellbark"),
    JACARANDA("jacaranda"),
    MAGIC("magic"),
    MAHOGANY("mahogany"),
    PALM("palm"),
    REDWOOD("redwood"),
    UMBRAN("umbran"),
    WILLOW("willow");

    private static final Map<SubType, Set<WoodType>> BY_SUBTYPE = new HashMap<>();
    private final String name;

    WoodType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Set<WoodType> values(SubType subType) {
        return BY_SUBTYPE.get(subType);
    }
    
    static {
        for(SubType subType : SubType.values()) {
            BY_SUBTYPE.put(subType, new TreeSet<>());
        }
        BY_SUBTYPE.get(SubType.VANILLA).add(OAK);
        BY_SUBTYPE.get(SubType.VANILLA).add(DARK_OAK);
        BY_SUBTYPE.get(SubType.VANILLA).add(SPRUCE);
        BY_SUBTYPE.get(SubType.VANILLA).add(BIRCH);
        BY_SUBTYPE.get(SubType.VANILLA).add(JUNGLE);
        BY_SUBTYPE.get(SubType.VANILLA).add(ACACIA);

        BY_SUBTYPE.get(SubType.BOP).add(CHERRY);
        BY_SUBTYPE.get(SubType.BOP).add(DEAD);
        BY_SUBTYPE.get(SubType.BOP).add(ETHEREAL);
        BY_SUBTYPE.get(SubType.BOP).add(FIR);
        BY_SUBTYPE.get(SubType.BOP).add(HELLBARK);
        BY_SUBTYPE.get(SubType.BOP).add(JACARANDA);
        BY_SUBTYPE.get(SubType.BOP).add(MAGIC);
        BY_SUBTYPE.get(SubType.BOP).add(MAHOGANY);
        BY_SUBTYPE.get(SubType.BOP).add(PALM);
        BY_SUBTYPE.get(SubType.BOP).add(REDWOOD);
        BY_SUBTYPE.get(SubType.BOP).add(UMBRAN);
        BY_SUBTYPE.get(SubType.BOP).add(WILLOW);

        for(SubType subType : BY_SUBTYPE.keySet()) {
            BY_SUBTYPE.put(subType, Collections.unmodifiableSet(BY_SUBTYPE.get(subType)));
        }
    }

    public enum SubType implements IStringSerializable {
        VANILLA("vanilla"),
        BOP("bop");
        private final String name;

        SubType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}