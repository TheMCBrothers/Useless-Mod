package themcbros.uselessmod.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import themcbros.uselessmod.UselessTags;
import themcbros.uselessmod.init.EntityInit;

public class UselessCowEntity extends CowEntity {

    private static final Ingredient BREED_ITEMS = Ingredient.fromTag(UselessTags.Items.CROPS_USELESS_WHEAT);

    public UselessCowEntity(EntityType<? extends CowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, BREED_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Override
    public void causeLightningStrike(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_) {
        super.causeLightningStrike(p_241841_1_, p_241841_2_);
        this.setGlowing(true);
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, AgeableEntity entity) {
        return EntityInit.USELESS_COW.get().create(this.world);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREED_ITEMS.test(stack);
    }
}
