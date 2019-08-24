package tk.themcbros.uselessmod.closet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.minecraft.block.Blocks;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum WoodType implements IStringSerializable {
    OAK("oak", Blocks.OAK_PLANKS.getRegistryName()),
    DARK_OAK("dark_oak", Blocks.DARK_OAK_PLANKS.getRegistryName()),
    SPRUCE("spruce", Blocks.SPRUCE_PLANKS.getRegistryName()),
    BIRCH("birch", Blocks.BIRCH_PLANKS.getRegistryName()),
    ACACIA("acacia", Blocks.ACACIA_PLANKS.getRegistryName()),
    JUNGLE("jungle", Blocks.JUNGLE_PLANKS.getRegistryName()),
    CHERRY("cherry", new ResourceLocation("biomesoplenty", "cherry_planks"), new ResourceLocation("biomesoplenty", "block/cherry_planks")),
    DEAD("dead", new ResourceLocation("biomesoplenty", "dead_planks"), new ResourceLocation("biomesoplenty", "block/dead_planks")),
    ETHEREAL("ethereal", new ResourceLocation("biomesoplenty", "ethereal_planks"), new ResourceLocation("biomesoplenty", "block/ethereal_planks")),
    FIR("fir", new ResourceLocation("biomesoplenty", "fir_planks"), new ResourceLocation("biomesoplenty", "block/fir_planks")),
    HELLBARK("hellbark", new ResourceLocation("biomesoplenty", "hellbark_planks"), new ResourceLocation("biomesoplenty", "block/hellbark_planks")),
    JACARANDA("jacaranda", new ResourceLocation("biomesoplenty", "jacaranda_planks"), new ResourceLocation("biomesoplenty", "block/jacaranda_planks")),
    MAGIC("magic", new ResourceLocation("biomesoplenty", "magic_planks"), new ResourceLocation("biomesoplenty", "block/magic_planks")),
    MAHOGANY("mahogany", new ResourceLocation("biomesoplenty", "mahogany_planks"), new ResourceLocation("biomesoplenty", "block/mahogany_planks")),
    PALM("palm", new ResourceLocation("biomesoplenty", "palm_planks"), new ResourceLocation("biomesoplenty", "block/palm_planks")),
    REDWOOD("redwood", new ResourceLocation("biomesoplenty", "redwood_planks"), new ResourceLocation("biomesoplenty", "block/redwood_planks")),
    UMBRAN("umbran", new ResourceLocation("biomesoplenty", "umbran_planks"), new ResourceLocation("biomesoplenty", "block/umbran_planks")),
    WILLOW("willow", new ResourceLocation("biomesoplenty", "willow_planks"), new ResourceLocation("biomesoplenty", "block/willow_planks"));

    private static final Map<SubType, Set<WoodType>> BY_SUBTYPE = new HashMap<>();
    private final String name;
    private final ResourceLocation registryName, textureLocation;

    WoodType(String name, ResourceLocation registryName, ResourceLocation textureLocation) {
        this.name = name;
        this.registryName = registryName;
        this.textureLocation = textureLocation;
    }
    
    WoodType(String name, ResourceLocation registryName) {
        this.name = name;
        this.registryName = registryName;
        this.textureLocation = new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath());
    }

    @Override
    public String getName() {
        return name;
    }
    
    public ResourceLocation getRegistryName() {
		return registryName;
	}
    
    public ResourceLocation getTextureLocation() {
		return textureLocation;
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