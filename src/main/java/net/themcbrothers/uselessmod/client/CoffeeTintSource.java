package net.themcbrothers.uselessmod.client;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.api.CoffeeType;
import net.themcbrothers.uselessmod.util.CoffeeUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record CoffeeTintSource() implements ItemTintSource {
    public static final CoffeeTintSource INSTANCE = new CoffeeTintSource();
    public static final MapCodec<CoffeeTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

    @Override
    public int calculate(@NonNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        return CoffeeUtils.getCoffeeType(stack)
                .map(CoffeeType::getColor)
                .map(color -> ARGB.color(0xFF, color))
                .orElse(-1);
    }

    @Override
    public @NonNull MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
