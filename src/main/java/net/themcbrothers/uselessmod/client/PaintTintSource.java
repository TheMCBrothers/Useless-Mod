package net.themcbrothers.uselessmod.client;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.themcbrothers.uselessmod.core.UselessDataComponents;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record PaintTintSource() implements ItemTintSource {
    public static final PaintTintSource INSTANCE = new PaintTintSource();
    public static final MapCodec<PaintTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

    @Override
    public int calculate(@NonNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        return ARGB.color(0xFF, stack.getOrDefault(UselessDataComponents.COLOR.get(), 0xFFFFFFFF));
    }

    @Override
    public @NonNull MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
