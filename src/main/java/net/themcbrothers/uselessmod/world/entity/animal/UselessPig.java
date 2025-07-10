package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessEntityTypes;
import org.jetbrains.annotations.Nullable;

public class UselessPig extends Pig {
    public UselessPig(EntityType<? extends UselessPig> type, Level level) {
        super(type, level);
    }

    @Override
    public @Nullable UselessPig getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UselessEntityTypes.USELESS_PIG.get().create(level, EntitySpawnReason.BREEDING);
    }
}
