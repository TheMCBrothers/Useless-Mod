package net.themcbrothers.uselessmod.world.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.themcbrothers.uselessmod.config.ServerConfig;
import net.themcbrothers.uselessmod.core.UselessPlacementModifierTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ConfigFeaturePlacement extends PlacementFilter {
    public static final MapCodec<ConfigFeaturePlacement> CODEC = Type.CODEC.fieldOf("dim_type").xmap(ConfigFeaturePlacement::new, placement -> placement.type);

    private final Type type;

    public ConfigFeaturePlacement(Type type) {
        this.type = type;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        return this.type.enabledSupplier.get();
    }

    @Override
    public PlacementModifierType<?> type() {
        return UselessPlacementModifierTypes.CONFIG.get();
    }

    public enum Type implements StringRepresentable {
        OVERWORLD(ServerConfig.ORE_GEN_OVERWORLD),
        NETHER(ServerConfig.ORE_GEN_NETHER),
        END(ServerConfig.ORE_GEN_END);

        public static final StringRepresentable.EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);

        private final Supplier<Boolean> enabledSupplier;

        Type(Supplier<Boolean> enabledSupplier) {
            this.enabledSupplier = enabledSupplier;
        }

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
