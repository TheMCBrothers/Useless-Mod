package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModEntityTypes;

public class UselessChicken extends Chicken {
    public UselessChicken(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }

    @Override
    public UselessChicken getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return ModEntityTypes.USELESS_CHICKEN.get().create(level);
    }
}
