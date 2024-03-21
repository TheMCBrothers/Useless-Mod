package net.themcbrothers.uselessmod.api;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public class CoffeeType {
    public static final Codec<CoffeeType> CODEC = UselessRegistries.COFFEE_REGISTRY.byNameCodec();

    private final Set<MobEffectInstance> effects;
    private final int color;
    private final boolean foil;
    @Nullable
    private String descriptionId;

    public CoffeeType(Properties properties) {
        this.color = properties.color;
        this.effects = properties.effects;
        this.foil = properties.foil;
    }

    public boolean isFoil() {
        return this.foil;
    }

    public int getColor() {
        return this.color;
    }

    public Set<MobEffectInstance> getEffects() {
        return this.effects;
    }

    public String getDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("coffee", UselessRegistries.COFFEE_REGISTRY.getKey(this));
        }

        return this.descriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeType that = (CoffeeType) o;
        return color == that.color && foil == that.foil && Objects.equals(effects, that.effects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effects, color, foil);
    }

    public static class Properties {
        private final Set<MobEffectInstance> effects;
        private final int color;
        private boolean foil;

        public Properties(int color, Set<MobEffectInstance> effects) {
            this.color = color;
            this.effects = effects;
        }

        public Properties foil() {
            this.foil = true;
            return this;
        }
    }
}
