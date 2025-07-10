package net.themcbrothers.uselessmod.world.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.themcbrothers.uselessmod.core.UselessEntityTypes;
import net.themcbrothers.uselessmod.world.level.storage.loot.UselessLootTables;
import org.jetbrains.annotations.Nullable;

public class UselessSheep extends Sheep {
    public UselessSheep(EntityType<? extends UselessSheep> type, Level level) {
        super(type, level);
    }

    @Override
    public @Nullable UselessSheep getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return UselessEntityTypes.USELESS_SHEEP.get().create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    public void shear(ServerLevel level, SoundSource category, ItemStack stack) {
        level.playSound(null, this, SoundEvents.SHEEP_SHEAR, category, 1.0F, 1.0F);
        this.dropFromShearingLootTable(
                level,
                UselessLootTables.SHEEP_USELESS, // TODO: make useless sheep loot table
                stack,
                (serverLevel, itemStack) -> {
                    for (int i = 0; i < itemStack.getCount(); i++) {
                        ItemEntity itementity = this.spawnAtLocation(serverLevel, itemStack.copyWithCount(1), 1.0F);
                        if (itementity != null) {
                            itementity.setDeltaMovement(
                                    itementity.getDeltaMovement()
                                            .add(
                                                    (this.random.nextFloat() - this.random.nextFloat()) * 0.1F,
                                                    this.random.nextFloat() * 0.05F,
                                                    (this.random.nextFloat() - this.random.nextFloat()) * 0.1F
                                            )
                            );
                        }
                    }
                }
        );
        this.setSheared(true);
    }
}
