package themcbros.uselessmod.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.datagen.UselessLootTables;
import themcbros.uselessmod.init.BlockInit;
import themcbros.uselessmod.init.EntityInit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class UselessSheepEntity extends SheepEntity {

    private static final Ingredient BREEDING_ITEMS = Ingredient.fromTag(UselessTags.Items.CROPS_USELESS_WHEAT);

    public UselessSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, BREEDING_ITEMS, false));
    }

    @Override
    public ActionResultType getEntityInteractionResult(PlayerEntity playerEntity, Hand hand) {
        if (DyeColor.getColor(playerEntity.getHeldItem(hand)) != null) return ActionResultType.FAIL;
        return super.getEntityInteractionResult(playerEntity, hand);
    }

    @Override
    public DyeColor getFleeceColor() {
        return DyeColor.WHITE;
}

    @Override
    public ResourceLocation getLootTable() {
        return this.getSheared() ? this.getType().getLootTable() : UselessLootTables.ENTITIES_USELESS_SHEEP_USELESS;
    }

    @Override
    public SheepEntity createChild(ServerWorld serverWorld, AgeableEntity p_90011_1_) {
        return EntityInit.USELESS_SHEEP.get().create(this.world);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_ITEMS.test(stack);
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!world.isRemote) {
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);

            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for (int j = 0; j < i; ++j) {
                items.add(new ItemStack(BlockInit.USELESS_WOOL.get()));
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }
}
