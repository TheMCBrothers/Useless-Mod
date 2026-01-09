package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessEntityTypes;
import org.jetbrains.annotations.Nullable;

public class UselessCow extends Cow {
    public UselessCow(EntityType<? extends UselessCow> type, Level level) {
        super(type, level);
    }

    @Override
    public @Nullable UselessCow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UselessEntityTypes.USELESS_COW.get().create(this.level(), EntitySpawnReason.BREEDING);
    }
}
