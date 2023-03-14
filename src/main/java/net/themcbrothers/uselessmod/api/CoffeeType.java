package net.themcbrothers.uselessmod.api;

import net.minecraft.Util;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class CoffeeType {
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
            this.descriptionId = Util.makeDescriptionId("coffee", UselessRegistries.coffeeRegistry.get().getKey(this));
        }

        return this.descriptionId;
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
