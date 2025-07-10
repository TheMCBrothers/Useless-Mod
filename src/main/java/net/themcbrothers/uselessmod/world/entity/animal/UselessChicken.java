package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessEntityTypes;
import org.jetbrains.annotations.Nullable;

public class UselessChicken extends Chicken {
    public UselessChicken(EntityType<? extends UselessChicken> type, Level level) {
        super(type, level);
    }

    @Override
    public @Nullable UselessChicken getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UselessEntityTypes.USELESS_CHICKEN.get().create(this.level(), EntitySpawnReason.BREEDING);
    }
}
