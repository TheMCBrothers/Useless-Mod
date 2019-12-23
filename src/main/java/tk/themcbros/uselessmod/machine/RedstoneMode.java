package tk.themcbros.uselessmod.machine;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public enum RedstoneMode implements IStringSerializable {

    IGNORED(new ResourceLocation("textures/item/gunpowder")),
    ON(new ResourceLocation("textures/item/redstone_torch")),
    OFF(new ResourceLocation("textures/item/redstone_torch_off"));

    private static final RedstoneMode[] VALUES = values();
    private static final Map<String, RedstoneMode> NAME_LOOKUP = Arrays.stream(VALUES)
            .collect(Collectors.toMap(RedstoneMode::getName, (tier) -> tier));

    private final ResourceLocation icon;

    RedstoneMode(ResourceLocation icon) {
        this.icon = icon;
    }

    /**
     * Get the mode specified by the given name
     */
    @Nullable
    public static RedstoneMode byName(@Nullable String name) {
        return name == null ? IGNORED : NAME_LOOKUP.get(name.toLowerCase(Locale.ROOT));
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public ResourceLocation getIcon() {
        return icon;
    }
}
