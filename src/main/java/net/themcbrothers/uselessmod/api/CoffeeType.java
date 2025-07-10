package net.themcbrothers.uselessmod.api;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.Consumable;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public class CoffeeType {
    public static final Codec<CoffeeType> CODEC = UselessRegistries.COFFEE_REGISTRY.byNameCodec();

    private final Consumable consumable;
    private final int color;
    private final boolean foil;
    @Nullable
    private String descriptionId;

    public CoffeeType(Properties properties) {
        this.color = properties.color;
        this.consumable = properties.consumable;
        this.foil = properties.foil;
    }

    public boolean isFoil() {
        return this.foil;
    }

    public int getColor() {
        return this.color;
    }

    public Consumable getConsumable() {
        return this.consumable;
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
        return color == that.color && foil == that.foil && Objects.equals(consumable, that.consumable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumable, color, foil);
    }

    public static class Properties {
        private final Consumable consumable;
        private final int color;
        private boolean foil;

        public Properties(int color, Consumable consumable) {
            this.color = color;
            this.consumable = consumable;
        }

        public Properties foil() {
            this.foil = true;
            return this;
        }
    }
}
