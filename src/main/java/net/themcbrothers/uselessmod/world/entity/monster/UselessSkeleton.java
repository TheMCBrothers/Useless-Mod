package net.themcbrothers.uselessmod.world.entity.monster;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.init.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class UselessSkeleton extends AbstractSkeleton {
    public UselessSkeleton(EntityType<? extends AbstractSkeleton> type, Level level) {
        super(type, level);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int p_33575_, boolean p_33576_) {
        super.dropCustomDeathLoot(damageSource, p_33575_, p_33576_);
        Entity entity = damageSource.getEntity();
        if (entity instanceof Creeper creeper) {
            if (creeper.canDropMobsSkull()) {
                creeper.increaseDroppedSkulls();
                // TODO: useless skeleton skull
                this.spawnAtLocation(ModBlocks.USELESS_BLOCK.get());
            }
        }
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }
}
