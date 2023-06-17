package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModEntityTypes;

public class UselessCow extends Cow {
    public UselessCow(EntityType<? extends Cow> type, Level level) {
        super(type, level);
    }

    @Override
    public UselessCow getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return ModEntityTypes.USELESS_COW.get().create(this.level());
    }
}
